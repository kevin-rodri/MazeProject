import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Maze implements TextMaze {
	
	private char[][] maze;
	private int width, height;
	
	public static void main(String[] args) {
		Scanner myObj = new Scanner(System.in);
		System.out.println("Enter the name of file"); 
		String question2 = myObj.nextLine();
		loadMaze(question2);
	}
	
	
	public Maze(int width, int height) {
		maze = new char[width][height];
		for (int row = 0; row < maze.length; row++) {
			for (int col = 0; col < maze[row].length; col++) {
				maze[row][col] = EMPTY;
			}
		}
	}
	
	public static void loadMaze(String name) {
		File namesFile = new File(name);
		Scanner fileInput = null; 
		try {
			 fileInput = new Scanner(namesFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("your file was not found!");
			main(null); 
		}
		
		while (fileInput.hasNextLine())
		{
		   System.out.println(fileInput.nextLine());
		}
	}
	
	public static void saveMaze() {
		
		
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
		for(int y = height - 1; y >= 0; y--) {
			for (int x = 0; x < maze[y].length; x++) {
				printMaze +=  maze[x][y];
			}	
			printMaze += "\n";
		}
		return printMaze;
	}
}
