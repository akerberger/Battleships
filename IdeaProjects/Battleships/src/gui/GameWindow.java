package gui;

import connection.BattleshipClient;
import connection.BattleshipServer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameWindow extends JFrame {

    private static final int WINDOW_WIDTH = 500;
    private static final int WINDOW_HEIGHT = 700;

    public static final double PLAYING_BOARD_SIZE = WINDOW_WIDTH * 0.65;

//    private final BattleshipServer server;

    private final BattleshipClient client;

    private final SidePanel SIDE_PANEL = new SidePanel();

    private final PlayingBoard own = new PlayingBoard(this, "down");
    private final PlayingBoard opponents = new PlayingBoard(this, "up");

    //    public GameWindow(BattleshipServer server) {
    public GameWindow(BattleshipClient client, String hostName, int port) {
        super((client.isHosting() ? "Hosting game at " : "Connected to game at ") +
                hostName + ", through port " + port);

        this.client = client;
        setLayout(new BorderLayout());
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        setUpWindowComponents();

        setVisible(true);

    }

    private void setUpWindowComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());


        own.setPreferredSize(new Dimension((int) PLAYING_BOARD_SIZE, (int) PLAYING_BOARD_SIZE));
        own.setPreferredSize(new Dimension((int) PLAYING_BOARD_SIZE, (int) PLAYING_BOARD_SIZE));

        panel.add(own, BorderLayout.SOUTH);
        panel.add(opponents, BorderLayout.NORTH);

        add(panel, BorderLayout.WEST);
        add(SIDE_PANEL, BorderLayout.EAST);
    }

    public void sendClick(int x, int y, String whos) {

//server.sendClick(x,y, whos);
        client.sendClick(x, y, whos);

//		int row = getColumnFromYCoordinate(x);
//		SIDE_PANEL.setLabelText( x + " " + y +" "+"from: "+(whos.equals("mine") ? "mine":"opponents"));
    }


    //coordinates[0] = x
    //coordinates[1] = y
    public void receiveClick(String[] coordinates) {


        SIDE_PANEL.setLabelText("mottaget: " + coordinates[0] + " " + coordinates[1]);
    }

    public void setSidePanelText(String text){
        SIDE_PANEL.setLabelText(text);
    }

    public void setupPhase(){
        SIDE_PANEL.setupPhase();
        own.addMouseListener();
    }

    public void placeShipOnMyBoard(int startColumn, int startRow, int shipSize){
        own.placeShipOnMyBoard(startColumn, startRow, shipSize);

        SIDE_PANEL.setLabelText("PLACERAR: "+startColumn+" "+startRow+" ");
    }

    // /10 eftersom matrisen är 10*10

    //denna är onödig, markera bara samma sak på "mitt" bräde som på motståndarens,
    //det kommer bli samma koordinater
//	private int getColumnFromYCoordinate(int x) {
//
//		if (x > 0 && x <= PLAYING_BOARD_SIZE / 10) {
//			return 1;
//		} else if (x > PLAYING_BOARD_SIZE / 10 && x <= PLAYING_BOARD_SIZE / 10 * 2) {
//			return 2;
//		} else if (x > PLAYING_BOARD_SIZE / 10 * 2 && x <= PLAYING_BOARD_SIZE / 10 * 3) {
//			return 3;
//		} else if (x > PLAYING_BOARD_SIZE / 10 * 3 && x <= PLAYING_BOARD_SIZE / 10 * 4) {
//			return 4;
//		} else if (x > PLAYING_BOARD_SIZE / 10 * 4 && x <= PLAYING_BOARD_SIZE / 10 * 5) {
//			return 5;
//		} else if (x > PLAYING_BOARD_SIZE / 10 * 5 && x <= PLAYING_BOARD_SIZE / 10 * 6) {
//			return 6;
//		} else if (x > PLAYING_BOARD_SIZE / 10 * 6 && x <= PLAYING_BOARD_SIZE / 10 * 7) {
//			return 7;
//		} else if (x > PLAYING_BOARD_SIZE / 10 * 7 && x <= PLAYING_BOARD_SIZE / 10 * 8) {
//			return 8;
//		} else if (x > PLAYING_BOARD_SIZE / 10 * 8 && x <= PLAYING_BOARD_SIZE / 10 * 9) {
//			return 9;
//		} else if (x > PLAYING_BOARD_SIZE / 10 * 9 && x <= PLAYING_BOARD_SIZE / 10 * 10) {
//			return 10;
//		}
//
//		return -1;
//	}

}
