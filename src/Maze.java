import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Maze implements TextMaze {
	
	private char[][] maze;
	private int width, height;

	// Default constructor that instiates a maze and sets each space to the EMPTY character
	public Maze(int width, int height) {
		maze = new char[width][height];
		for (int x = 0; x < maze.length; x++) {
			for (int y = 0; y < maze[x].length; y++) {
				maze[x][y] = EMPTY;
			}
		}
		this.width = width; 
		this.height = height;
	}

	// Loads a maze and will return whether a maze was found or not
	public static Maze loadMaze(String fileName) {
		try {
			File namesFile = new File(fileName);
			Scanner fileInput = new Scanner(namesFile);
			int width = fileInput.nextInt();
			int height = fileInput.nextInt();		
			Maze maze = new Maze(width, height);
			fileInput.nextLine();
			while (fileInput.hasNextLine()) {
				height -= 1;
				String line = fileInput.nextLine();
				for (int x = 0; x < width; x++) {
					char c = line.charAt(x);
					Point point = new Point(x, height);
					maze.set(point, c);
				}				
			}
			fileInput.close();
			return maze;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	// Will save changes made to a maze
	public static void saveMaze(String fileName, Maze maze) {
		try (PrintWriter fileOutput = new PrintWriter(new File(fileName))) {
			fileOutput.println(maze.width + " " + maze.height);
			fileOutput.println(maze);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	// set a point at p to c 
	@Override
	public void set(Point p, char c) {
		if (p.x < 0 || p.y < 0 || p.x >= width || p.y >= height) {
			throw new PointOutOfBoundsException(p.toString());
		}
		maze[p.x][p.y] = c;
	}

	// gets the character of a point and will return that character is the point is in bounds
	@Override
	public char get(Point p) {
		if (p.x < 0 || p.y < 0 || p.x >= width || p.y >= height) {
			throw new PointOutOfBoundsException(p.toString());
		}
		return maze[p.x][p.y];
	}

	// returns the width
	@Override
	public int width() {
		return width;
	}

	// returns the height 
	@Override
	public int height() {
		return height;
	}

	// Will check if a point is in bounds and will return either true or false
	@Override
	public boolean inBounds(Point p) {
		if (p.x < 0 || p.y < 0 || p.x >= width || p.y >= height) {
			return false;
		} else {
			return true;
		}
	}

	// Will print the maze as a string 
	public String toString() {
		// printing the entire maze ; as a string itself
		// start with highest y value
		String printMaze = "";
		for (int y = height - 1; y >= 0; y--) {
			for (int x = 0; x < width(); x++) {
				printMaze += maze[x][y];
			}
			if(y > 0) {
				printMaze += "\n";
			}
		}
		return printMaze;
	}
}