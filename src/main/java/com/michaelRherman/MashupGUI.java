package com.michaelRherman;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MashupGUI extends JFrame {
    private JPanel rootPanel;
    private JTextField searchField;
    private JLabel searchLabel;
    private JButton okayButton;

    private static String searchTerm;

    protected MashupGUI() {
        super("Mashup Helper");
        setContentPane(rootPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        okayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                try {
                searchTerm = searchField.getText();
                System.out.println(searchTerm);
/*                } catch (Exception e1) {
                    e1.printStackTrace();
                }*/
            }
        });
    }

    public static String getSearchTerm() {
        return searchTerm;
    }
}