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
						solveMaze(maze, start, end);
					}
				}
				
			}
		}
		
	}
	
	
	public static void solveMaze(Maze maze , Point start, Point end) {
		
	}
	
	public static void solveMazeHelper(Maze maze , Point location) {
		// if (maze.get(location) == GOAL)
	}
}
