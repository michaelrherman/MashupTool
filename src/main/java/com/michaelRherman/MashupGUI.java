package com.michaelRherman;

import javax.swing.*;

public class MashupGUI extends JFrame {
    private JPanel rootPanel;
    private JTextField searchField;
    private JLabel searchLabel;
    private JButton okayButton;

    protected MashupGUI(){
        super("Mashup Helper");
        setContentPane (rootPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
