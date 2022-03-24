import java.util.Scanner;

public class MazeSolver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean keepGoing = true;
		boolean point = true;
		Scanner myObj = new Scanner(System.in);
		while (keepGoing) {
			System.out.println("Enter the name of file"); 
			String question2 = myObj.nextLine();
			Maze maze = Maze.loadMaze(question2);
			if (maze == null) {
				System.out.println("File was unable to load.");
			} else {
				keepGoing = false;
				while(point) {
					System.out.println(maze.toString());
					System.out.println("Please enter start coordinate for maze");
					int startX = myObj.nextInt();
					int startY = myObj.nextInt();
					Point start = new Point(startX, startY);
					System.out.println("Please enter end coordinate for maze");
					int endX = myObj.nextInt();
					int endY = myObj.nextInt();
					Point end = new Point(endX, endY);
					if (!maze.inBounds(start) || !maze.inBounds(end)) {
						System.out.println("Invalid point, please try again.");
					} else {
						point = false;
						System.out.println(solveMaze(maze, start, end));
						System.out.println(maze.toString());
					}
				}

			}
		}

	}


	public static boolean solveMaze(Maze maze , Point start, Point end) {
		maze.set(end, TextMaze.GOAL);
		maze.set(start, TextMaze.START);
		boolean startPoint = solveMazeHelper(maze, start);
		return startPoint;

	}

	public static boolean solveMazeHelper(Maze maze , Point location) {

		if (maze.get(location) == TextMaze.GOAL) {
			return true; 
		}
		if(maze.get(location) != TextMaze.EMPTY) {
			return false; 			
		}
		maze.set(location, TextMaze.PATH);
		Point north = new Point(location.x, location.y+1);
		if(maze.inBounds(north))
		{
			if (solveMazeHelper(maze, north)) {
				return true;
			} 
		}
		Point south = new Point(location.x, location.y-1);
		if(maze.inBounds(south))
		{
			if (solveMazeHelper(maze, south)) {
				return true;
			} 
		}
		Point east = new Point(location.x-1, location.y);
		if(maze.inBounds(east))
		{
			if (solveMazeHelper(maze, east)) {
				return true;
			} 
		}
		Point west = new Point(location.x+1, location.y);
		if(maze.inBounds(east))
		{
			if (solveMazeHelper(maze, west)) {
				return true;
			} 
		}
		maze.set(location, TextMaze.VISITED); 
		return false;
	}
}
