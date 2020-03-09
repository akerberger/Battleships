package gui;

import game.GameController;
import game.GameState;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;

public class PlayingBoard extends JPanel {

    private GameWindow gameWindow;

    private String whos;

    private BoardListener listener = new BoardListener();

//    private Map<Integer, Integer> marked

    public PlayingBoard(GameWindow gameWindow, String whos) {
        this.gameWindow = gameWindow;
        this.whos = whos;


        setLayout(new GridLayout(10,10));

        Border raisedBevele = BorderFactory.createRaisedBevelBorder();
        JPanel square;
        for(int i = 0; i<100; i++) {
            square = new JPanel();
            //kanske setOpac(true) för att få den genomskinlig så att kryss och annat syns?
            //eller håll en matris här över Squares... Så att man kan accessa dem en och en...
            square.setPreferredSize(new Dimension(30,30));
            add(square);
            square.setBorder(raisedBevele);
        }
    }

    public String whos() {
        return whos;
    }

    private class BoardListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            gameWindow.sendClick(e.getX(), e.getY(), whos);
//            removeMouseListener();
        }
    }

    public void placeShipOnMyBoard(int startColumn, int startRow, int shipSize) {
        //just nu ska, i SETUP_PHASE, en båt som är tre rutor lång markeras horisontellt
        System.out.println("SKA MARKERA SKEPP PÅ RUTA: " + startColumn + " " + startRow + " " + shipSize + " rutor stort");
        //lägg till en svart JPanel
//        JPanel marker = new JPanel();
//        marker.setPreferredSize(new Dimension(30, 30));
//        marker.setBackground(Color.BLACK);
//
//        marker.setLocation(4, 4);
//        add(marker);
//        repaint();


    }

    public void addMouseListener() {
        addMouseListener(listener);
    }

    public void removeMouseListener() {
        removeMouseListener(listener);
    }

//    @Override
//    public void paintComponent(Graphics g){
//        super.paintComponent(g);
//
//
//    }

}
