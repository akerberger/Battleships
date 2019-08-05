package game;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import gui.GUI;


//hejdär


public class Game {

	public static final String HUMAN_MARKER = "X";

	public static final String CPU_MARKER = "O";

	private final int DIMENSION = 30;

	private GUI gui;

	private Square[][] board;

	private SquareListener listener = new SquareListener();

	private boolean isHumanTurn = true;

	public Game() {

		board = new Square[DIMENSION][DIMENSION];
		gui = new GUI(DIMENSION);
		setUpSquares();

	}

	private class SquareListener extends MouseAdapter {
		public void mouseClicked(MouseEvent m) {
			if (m.getSource() instanceof Square) {

				Square s = (Square) m.getSource();

				if (s.isAvailable()) {
					s.markSquare(isHumanTurn);
					checkIfGameOver(s);
					isHumanTurn = !isHumanTurn;

				}

				// System.out.println(s.getRow()+" "+s.getColumn());
			}
		}
	}

	private void setUpSquares() {

		for (int row = 0; row < DIMENSION; row++) {
			for (int column = 0; column < DIMENSION; column++) {

				Square s = new Square(row, column);
				s.addMouseListener(listener);
				board[row][column] = s;
				gui.addSquare(s);

			}
		}

		gui.updateFrame();

	}

	// s = the square most recently marked
	private void checkIfGameOver(Square s) {
		if (checkHorizontals(s) || checkVertical(s) || checkDiagonals(s)) {
			System.out.println("GameOver!");
		}

	}

	private boolean checkDiagonals(Square s) {

		return checkDownToLeft(s) || checkDownToRight(s);

	}

	private boolean checkDownToRight(Square s) {

		Square temp;

		int count = 0;

		int rowToCheck = s.getRow() - 4;

		int columnToCheck = s.getColumn() - 4;

		
		for (int i = 0; i < 9; i++) {
			if (isWithinBoard(rowToCheck, columnToCheck)) {
				temp = board[rowToCheck][columnToCheck];
				
				if (!temp.isAvailable() && (temp.isHumanMarker() == s.isHumanMarker())) {
					
					count++;
					
					
					if (count >= 5) {
						return true;
					}
				}else {
					count = 0;
				}
				
				
			}
			rowToCheck++;
			columnToCheck++;
			
		}

		return false;
	}

	private boolean checkDownToLeft(Square s) {

		Square temp;

		int count = 0;

		int rowToCheck = s.getRow() - 4;

		int columnToCheck = s.getColumn() + 4;

		for (int i = 0; i < 9; i++) {
			if (isWithinBoard(rowToCheck, columnToCheck)) {
				temp = board[rowToCheck][columnToCheck];

				if (!temp.isAvailable() && temp.isHumanMarker() == s.isHumanMarker()) {
					count++;
					if (count >= 5) {
						return true;
					}
				} else {
					count = 0;
				}
			}

			rowToCheck++;
			columnToCheck--;

		}

		return false;
	}

	private boolean isWithinBoard(int row, int column) {
		return row >= 0 && row < DIMENSION && column >= 0 && column < DIMENSION;
	}

	private boolean checkVertical(Square s) {

		Square temp;

		int count = 0;

		// loop through the squares four steps up and down from s (vertically).
		for (int rowToCheck = s.getRow() - 4; rowToCheck < s.getRow() + 5; rowToCheck++) {

			// rowToCheck is within the board
			if (rowToCheck >= 0 && rowToCheck < DIMENSION) {
				temp = board[rowToCheck][s.getColumn()];

				if (!temp.isAvailable() && (temp.isHumanMarker() == s.isHumanMarker())) {
					count++;

					if (count >= 5) {
						return true;
					}
				} else
					count = 0;
			}
		}

		return false;
	}

	// returns true if a "5 in a row", that includes s, is found horizontally
	private boolean checkHorizontals(Square s) {

		Square temp;

		int count = 0;

		for (int columnToCheck = s.getColumn() - 4; columnToCheck < s.getColumn() + 5; columnToCheck++) {

			// columnToCheck is within the board
			if (columnToCheck >= 0 && columnToCheck < DIMENSION) {
				temp = board[s.getRow()][columnToCheck];

				// the square being checked is marked same as s
				if (!temp.isAvailable() && (temp.isHumanMarker() == s.isHumanMarker())) {
					count++;

					if (count >= 5) {
						return true;
					}
					// reset count if opponents marker or empty square is found during the
					// checking-loop
				} else {
					count = 0;
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {
		new Game();
	}
}
