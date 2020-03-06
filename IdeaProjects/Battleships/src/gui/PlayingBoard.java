package gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PlayingBoard extends JPanel {

    private GameWindow gameWindow;

    private String whos;

    private BoardListener listener = new BoardListener();

    public PlayingBoard(GameWindow gameWindow, String whos) {
        this.gameWindow = gameWindow;
        this.whos=whos;

        setLayout(new GridLayout(10,10));

        Border raisedBevele = BorderFactory.createRaisedBevelBorder();
        JPanel square;
        for(int i = 0; i<100; i++) {
            square = new JPanel();
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
        }
    }

    public void addMouseListener(){
        addMouseListener(listener);
    }

    public void removeMouseListener(){

    }
}
