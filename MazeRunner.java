import java.util.Scanner;

public class MazeRunner {
	private static Maze myMap = new Maze(); // Step 3 The "Class"
	private static int userSteps = 0;

	public static void main(String[] args) {

		intro(); // Step 4 The "Intro"

		while (myMap.didIWin() == false) {

			String userDirection = userMove();

			if (userDirection.equals("R") || userDirection.equals("L") || userDirection.equals("U")
					|| userDirection.equals("D"))
				navigatePit(userDirection);
		}
		if (myMap.didIWin() == true) {
			System.out.print("Congratulations, you made it out alive!\n and you did it in " + MazeRunner.userSteps + " "
					+ "moves");
		}
	}

	public static void intro() {
		System.out.println("Welcome to Maze Runner!\n Here is your current position:"); // Step 4 The "Intro". The
																						// welcome and print
		myMap.printMap();
	}

	public static void movesMessage(int moves) {
		if (moves == 50) {
			System.out.println("Warning: You have made 50 moves, you have 50 remaining before the maze exit closes");
		} else if (moves == 75) {
			System.out.println("Alert! You have made 75 moves, you only have 25 moves left to escape.");
		} else if (moves == 90) {
			System.out.println("DANGER! You have made 90 moves, you only have 10 moves left to escape!!");
		} else if (moves == 100) {
			System.out.println("Oh no! You took too long to escape, and now the maze exit is closed FOREVER >:[");

		}
	}

	public static String userMove() {                                 // Step 5 The "userMove Method"
		Scanner scanner = new Scanner(System.in);
		String direction = " ";
		do {
			if (MazeRunner.userSteps != 101) {
				System.out.print("Where would you like to move? (R, L, U, D)    "); // Step 5 the question for user
				direction = scanner.next();
			}

			if (direction.equals("R") || direction.equals("L") || direction.equals("U") || direction.equals("D")) {

				movesMessage(++MazeRunner.userSteps);

				if (myMap.canIMoveRight() == true && direction.equals("R")) {
					myMap.moveRight();
				} else if (myMap.canIMoveLeft() == true && direction.equals("L")) {
					myMap.moveLeft();
				} else if (myMap.canIMoveUp() == true && direction.equals("U")) {
					myMap.moveUp();
				} else if (myMap.canIMoveDown() == true && direction.equals("D")) {
					myMap.moveDown();
				} else {
					if (MazeRunner.userSteps != 101) {
						System.out.println("Sorry, you've hit a wall.");
						System.out.print("Where would you like to move? (R, L, U, D)    ");
						direction = scanner.next();

						movesMessage(++MazeRunner.userSteps);
					}
				}

				myMap.printMap();
				break;
			}
		} while (direction.matches("R,L,U,D") == false);

		return direction;
	}

	public static void navigatePit(String userDirection) {
		Scanner scanner = new Scanner(System.in);
		if (myMap.isThereAPit(userDirection) == true) {
			System.out.print("Watch out! There's a pit ahead, jump it?  ");
			String jump = scanner.next();
			if (jump.equalsIgnoreCase("yes") || jump.equalsIgnoreCase("y"))
				myMap.jumpOverPit(userDirection);
			else {
				System.out.println("Sorry, but you didn't jump- you lose!");
				System.exit(0);
			}
		} else {
			System.out.println("Sorry, you've hit a wall.");
		}
	}
}