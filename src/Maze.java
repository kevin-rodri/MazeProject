import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Maze implements TextMaze {

	private char[][] maze;
	private int width, height;

	public Maze(int width, int height) {
		maze = new char[width][height];
		for (int x = 0; x < maze.length; x++) {
			for (int y = 0; y < maze[x].length; y++) {
				maze[x][y] = EMPTY;
			}
		}
	}

	public static Maze loadMaze(String fileName) {
		try {
			File namesFile = new File(fileName);
			Scanner fileInput = new Scanner(namesFile);
			int width = fileInput.nextInt();
			int height = fileInput.nextInt();
			Maze maze = new Maze(width, height);
			for (int y = height - 1; y >= 0; y--) {
				for (int x = 0; x < width; x++) {
					Point point = new Point(width, height);
					maze.get(point);
				}
				String line = fileInput.nextLine();
			}
			fileInput.close();
			return maze;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	public static void saveMaze(String fileName, Maze maze) {
		try (PrintWriter fileOutput = new PrintWriter(new File(fileName))) {
			fileOutput.println(maze.width() + maze.height());
			fileOutput.println(maze);
		} catch (FileNotFoundException e) {
			// Just a placeholder because I don't know what have to be returned if nothing's
			// found
			return;
		}
	}

	@Override
	public void set(Point p, char c) {
		// TODO Auto-generated method stub
		if (p.x < 0 && p.y < 0) {
			throw new PointOutOfBoundsException(p.toString());
		}
		maze[p.x][p.y] = c;
	}

	@Override
	public char get(Point p) {
		// TODO Auto-generated method stub
		if (p.x < 0 && p.y < 0) {
			throw new PointOutOfBoundsException(p.toString());
		}
		return maze[p.x][p.y];
	}

	@Override
	public int width() {
		// TODO Auto-generated method stub
		return width;
	}

	@Override
	public int height() {
		// TODO Auto-generated method stub
		return height;
	}

	@Override
	public boolean inBounds(Point p) {
		// TODO Auto-generated method stub
		if (p.x < 0 && p.y < 0) {
			return false;
		} else {
			return true;
		}
	}

	public String toString() {
		// printing the entire maze ; as a string itself
		// start with highest y value
		String printMaze = " ";
		for (int y = height - 1; y >= 0; y--) {
			for (int x = 0; x < maze[y].length; x++) {
				printMaze += maze[x][y];
			}
			printMaze += "\n";
		}
		return printMaze;
	}
}
