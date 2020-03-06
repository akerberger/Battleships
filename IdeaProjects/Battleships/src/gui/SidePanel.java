package gui;

import javax.swing.*;

public class SidePanel extends JPanel {


    private JLabel label = new JLabel("sidopanel");

    public SidePanel() {
        add(label);
    }

    public void setLabelText(String text) {
        label.setText(text);
    }
}
