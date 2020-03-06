package gui;

import javax.swing.*;
import java.awt.*;

public class SidePanel extends JPanel {


    private NorthPanel northPanel = new NorthPanel();
    private SouthPanel southPanel= new SouthPanel();

    public SidePanel() {
        setLayout(new BorderLayout());
        add(northPanel, BorderLayout.NORTH);
        add(southPanel, BorderLayout.SOUTH);
    }

    private class NorthPanel extends JPanel{
        JLabel northLabel = new JLabel("Waiting...");

        public NorthPanel(){
            add(northLabel);
        }

        void setLabelText(String text){
            northLabel.setText(text);
        }
    }

    private class SouthPanel extends JPanel{

        JTextArea textArea = new JTextArea("");
        JLabel southLabel2 = new JLabel("SOUTH LABEL2");


        public SouthPanel(){
            setLayout(new GridLayout(2,1));
            textArea.setEditable(false);
            add(textArea);
            add(southLabel2);
        }
        void setLabelText(String text){
            southLabel2.setText(text);
        }
        void setTextAreaText(String text){
            textArea.setText(text);
        }
    }

    public void setupPhase(){
        northPanel.setLabelText("SETUP PHASE!");
        southPanel.setLabelText("LABEL");
        southPanel.setTextAreaText("<- Klick on the square \nof your board where \nyour ship should be placed");
    }


    public void setLabelText(String text) {
        northPanel.setLabelText(text);
    }
}
