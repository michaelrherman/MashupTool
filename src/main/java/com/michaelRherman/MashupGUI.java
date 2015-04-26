package com.michaelRherman;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class MashupGUI extends JFrame {
    private JPanel rootPanel;
    private JTextField artistField;
    private JLabel artistLabel;
    private JButton okayButton;
    private JTextField songField;
    private JLabel songLabel;

    private static String artistSearch;
    private static String songSearch;

    protected MashupGUI() {
        super("Mashup Helper");
        setContentPane(rootPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        okayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                artistSearch = artistField.getText();
                songSearch = songField.getText();
                System.out.println(artistSearch);
                System.out.println(songSearch);
                try {
                    Database.insertSearch(artistSearch, songSearch); //Passes the search terms thru to the database to be stored
                    EchoNest.prepareEchoNest(artistSearch, songSearch);
                } catch (SQLException se) {
                    System.out.println(se);
//                } catch (URISyntaxException ue) {
//                    System.out.println(ue);
                } catch (Exception oe) {
                    System.out.println(oe);
                }
            }
        });
    }
}