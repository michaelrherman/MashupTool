package com.michaelRherman;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.LinkedList;

public class MashupGUI extends JFrame {

    private DefaultListModel<String> songDefaultListModelOne;
    private DefaultListModel<String> songDefaultListModelTwo;
    private JPanel rootPanel;
    private JTextField artistField1;
    private JLabel artistLabel1;
    private JButton okayButton;
    private JTextField songField1;
    private JLabel songLabel1;
    private JLabel artistLabel2;
    private JTextField artistField2;
    private JTextField songField2;
    private JLabel songLabel2;
    private JList result1List;
    private JList result2List;
    private JScrollPane result1Scroll;
    private JScrollPane result2Scroll;
    private JPanel result1Panel;
    private JPanel result2Panel;
    private JButton compareButton;
    private JButton playButton1;
    private JButton playButton2;
    protected JComboBox<String> setSway;

    private static String artistSearch1;
    private static String songSearch1;
    private static String artistSearch2;
    private static String songSearch2;

    private static final String Exact = "Exact";
    private static final String Low = "Low";
    private static final String Medium = "Medium";
    private static final String High = "High";
    protected static final String[] comboOptions = {Exact, Low, Medium, High};

    protected static LinkedList<Song> ResponseOne;
    protected static LinkedList<Song> ResponseTwo;

    private static boolean danceabilityMatch;
    private static boolean durationMatch;
    private static boolean energyMatch;
    private static boolean harmonicKeyMatch; //Will be simple match
    private static boolean modeMatch; //Will be simple match
    private static boolean tempoMatch;
    private static boolean timeSignatureMatch; //Will be simple match

    protected MashupGUI() {
        super("Mashup Helper");
        setContentPane(rootPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        for (int x = 0; x < comboOptions.length; x++)
        setSway.addItem((String) Array.get(comboOptions, x));

        okayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                artistSearch1 = artistField1.getText();
                if (artistSearch1.length()==0) {
                    artistSearch1 = null;
                }
                songSearch1 = songField1.getText();
                if (songSearch1.length()==0) {
                    songSearch1 = null;
                }

                artistSearch2 = artistField2.getText();
                if (artistSearch2.length()==0) {
                    artistSearch2 = null;
                }
                songSearch2 = songField2.getText();
                if (songSearch2.length()==0) {
                    songSearch2 = null;
                }
//                System.out.println(artistSearch);
//                System.out.println(songSearch);
                try {
                    Database.insertSearch(artistSearch1, songSearch1); //Passes the search terms thru to the database to be stored
                    Database.insertSearch(artistSearch2, songSearch2);
                    ResponseOne = JSON.getEchonestResponse(EchoNest.prepareEchoNest(artistSearch1, songSearch1));
                    ResponseTwo = JSON.getEchonestResponse(EchoNest.prepareEchoNest(artistSearch2, songSearch2));
                    displaySongs(ResponseOne, ResponseTwo);
                } catch (SQLException se) {
                    System.out.println(se);
//                } catch (URISyntaxException ue) {
//                    System.out.println(ue);
                } catch (Exception oe) {
                    System.out.println(oe);
                }

                result1Panel.setVisible(true);
                result1Scroll.setVisible(true);
                result1List.setVisible(true);

                result2Panel.setVisible(true);
                result2Scroll.setVisible(true);
                result2List.setVisible(true);
            }
        });

        /*Adapted from https://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html &
        * https://docs.oracle.com/javase/8/docs/api/javax/swing/JOptionPane.html &
        * https://docs.oracle.com/javase/8/docs/api/javax/swing/JDialog.html*/

        compareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String toleranceSet = (String) setSway.getSelectedItem();

                if (toleranceSet.equals("Exact")) {
                    Compare.setSway(0);
//                    System.out.println(Compare.getSway());
                } else if (toleranceSet.equals("Low")) {
                    Compare.setSway(5);
//                    System.out.println(Compare.getSway());
                } else if (toleranceSet.equals("Medium")) {
                    Compare.setSway(15);
//                    System.out.println(Compare.getSway());
                } else if (toleranceSet.equals("High")) {
                    Compare.setSway(30);
//                    System.out.println(Compare.getSway());
                }

/*                danceabilityMatch = Compare.compareDoubles();
                durationMatch = Compare.compareDoubles();
                energyMatch = Compare.compareDoubles();
                harmonicKeyMatch = Compare.compareInteger(); //Will be simple match
                modeMatch = Compare.compareInteger(); //Will be simple match
                tempoMatch = Compare.compareDoubles();
                timeSignatureMatch = Compare.compareInteger(); //Will be simple match*/

                JOptionPane.showMessageDialog(null, "Comparison between "+artistSearch1+" \""+songSearch1+
                        "\" and "+artistSearch2+" \""+songSearch2+"\" \n Danceability: "+danceabilityMatch+"\n Duration: "+durationMatch+"\n Energy: "+energyMatch
                                +"\n Key: "+harmonicKeyMatch+"\n Major/Minor: "+modeMatch+"\n Tempo(BPM): "+tempoMatch+"\n Time Signature: "+timeSignatureMatch,
                        "Comparison", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        //THIS IS THE PLAY BUTTON ON THE LEFT
        playButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String spotifyID = "78J9MBkAoqfvyeEpQKJDzD";
                try {
                    Main.openDefaultBrowser(spotifyID);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });

        //THIS IS THE PLAY BUTTON ON THE RIGHT
        playButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String spotifyID = "78J9MBkAoqfvyeEpQKJDzD";
                try {
                    Main.openDefaultBrowser(spotifyID);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
    }

    public void displaySongs(LinkedList<Song> responseOne, LinkedList<Song> responseTwo) {
        songDefaultListModelOne = new DefaultListModel<String>(); //Because we want to refresh the list each time,
        result1List.setModel(songDefaultListModelOne);//we initialize each time this method is called.
        result1List.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        int x = 1;
        for (Song song: responseOne) {
            String songString = x+" "+song.getArtistName()+" \""+song.getSongTitle()+"\"";
            songDefaultListModelOne.addElement(songString);
            x++;
        }

        songDefaultListModelTwo = new DefaultListModel<String>();
        result2List.setModel(songDefaultListModelTwo);
        result2List.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        x = 1;
        for (Song song: responseTwo) {
            String songString = x+" "+song.getArtistName()+" \""+song.getSongTitle()+"\"";
            songDefaultListModelTwo.addElement(songString);
            x++;
        }
    }
}