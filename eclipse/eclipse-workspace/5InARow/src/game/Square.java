package game;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class Square extends JLabel {

	private final int ROW, COLUMN;

	public Square(int row, int column) {
		this.ROW = row;
		this.COLUMN = column;
		setText("");
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}

	public int getRow() {
		return ROW;
	}

	public int getColumn() {
		return COLUMN;
	}

	public void markSquare(boolean isHumanTurn) {

		if (isHumanTurn) {
			setText(Game.HUMAN_MARKER);
		} else {
			setText(Game.CPU_MARKER);
		}

	}
	
	public boolean isAvailable() {
		return getText() == "";
	}

	public boolean isHumanMarker() {
		return getText()==Game.HUMAN_MARKER;
	}







}
