package com.michaelRherman;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URISyntaxException;
import java.sql.SQLException;

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
                searchTerm = searchField.getText();
               System.out.println(searchTerm);
                try {
                    Database.insertSearch(searchTerm); //Passes the search term thru to the database to be stored
                    Main.openDefaultBrowser(searchTerm);
                } catch (SQLException se) {
                    System.out.println(se);
                } catch (URISyntaxException ue) {
                    System.out.println(ue);
                } catch (Exception oe) {
                    System.out.println(oe);
                }
            }
        });
    }

    public static String getSearchTerm() {
        return searchTerm;
    }
}