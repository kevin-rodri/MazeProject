import java.util.Scanner;

public class MazeSolver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean keepGoing = true;
		boolean point = true;
		try (Scanner myObj = new Scanner(System.in)) {
			while (keepGoing) {
				System.out.println("Enter the name of file"); 
				String filename = myObj.nextLine();
				Maze maze = Maze.loadMaze(filename);
				
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
							System.out.println(maze.inBounds(start));
							System.out.println(maze.inBounds(end));
							System.out.println("Invalid point, please try again.");
						} else {
							point = false;
							System.out.println(solveMaze(maze, start, end));
							System.out.println(maze.toString());
							Maze.saveMaze(filename + ".solved", maze);
						}
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
		if (!maze.inBounds(location)) {
			return false;
		}
		
		if (maze.get(location) == TextMaze.GOAL) {
			return true; 
		}
		
		if(maze.get(location) != TextMaze.EMPTY
				&& maze.get(location) != TextMaze.START) {
			return false; 			
		}
		
		if (maze.get(location) != TextMaze.START) {
			maze.set(location, TextMaze.PATH);
		}
		
		return solveMazeHelper(maze, new Point(location.x, location.y - 1))
				|| solveMazeHelper(maze, new Point(location.x, location.y + 1))
				|| solveMazeHelper(maze, new Point(location.x + 1, location.y))
				|| solveMazeHelper(maze, new Point(location.x - 1, location.y)); 
	}
}
