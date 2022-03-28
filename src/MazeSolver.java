import java.util.Scanner;

public class MazeSolver {

	public static void main(String[] args) {
		// Main method that will run the entire program. This will ask the user for a a
		// maze and will solve the maze (or least try to) and save it to another maze.
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
					while (point) {
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
							Maze.saveMaze(filename, maze);
							System.out.println();
							System.out.println(maze.toString());
							Maze.saveMaze(filename + ".solved", maze);

						}
					}

				}
			}
		}
	}

	// Will set the end point to the GOAL character, call the solveMazeHelper method
	// , set the beginning of maze to the start character and will return whether
	// the maze is solved or not.
	public static boolean solveMaze(Maze maze, Point start, Point end) {
		maze.set(end, TextMaze.GOAL);
		boolean startPoint = solveMazeHelper(maze, start);
		solveMazeHelper(maze, start);
		maze.set(start, TextMaze.START);
		return startPoint;
	}

	// will attempt to solve the maze and will attempt to reach the goal character
	// by recursively going through the maze. Will return true or false whether the
	// maze is actually solved depending on the conditionals inside this method.
	public static boolean solveMazeHelper(Maze maze, Point location) {
		if (!maze.inBounds(location)) {
			return false;
		}
		if (maze.get(location) == TextMaze.GOAL) {
			return true;
		}
		if (maze.get(location) != TextMaze.EMPTY && maze.get(location) != TextMaze.START) {
			return false;
		}
		if (maze.get(location) != TextMaze.START) {
			maze.set(location, TextMaze.PATH);
		}
		boolean solvedMaze = solveMazeHelper(maze, new Point(location.x, location.y - 1))
				|| solveMazeHelper(maze, new Point(location.x, location.y + 1))
				|| solveMazeHelper(maze, new Point(location.x + 1, location.y))
				|| solveMazeHelper(maze, new Point(location.x - 1, location.y));
		if (!solvedMaze) {
			maze.set(location, TextMaze.VISITED);
		}
		return solvedMaze;
	}
}