package gui;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import game.Square;

public class GUI extends JFrame{
	
	private JPanel board;
	
	
	public GUI(int dimension) {
		
		super("5 In A Row");
		setSize(600,600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		board = new JPanel();
		board.setLayout(new GridLayout(dimension, dimension));
		
		add(board);
		

	}
	
	public void addSquare(Square s) {
		board.add(s);
		
		
	}
	
	public void updateFrame() {
		revalidate();
	}
	
	

}
