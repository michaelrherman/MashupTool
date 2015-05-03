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
    private JButton favoriteButton1;
    private JButton favoriteButton2;

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

        //This is the OK button
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
                    ResponseOne = JSON.getEchonestResponseInitial(EchoNest.prepareEchoNest(artistSearch1, songSearch1));
                    ResponseTwo = JSON.getEchonestResponseInitial(EchoNest.prepareEchoNest(artistSearch2, songSearch2));
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

                if (artistField1.getText().equals("Bruno Mars") && songField1.getText().equals("Uptown Funk")) {
                    JOptionPane.showMessageDialog(null, "Warning: Uptown Funk is by Mark Ronson, not Bruno Mars.",
                            "Uptown Funk", JOptionPane.ERROR_MESSAGE);
                } else if (artistField2.getText().equals("Bruno Mars") && songField2.getText().equals("Uptown Funk")) {
                    JOptionPane.showMessageDialog(null, "Warning: Uptown Funk is by Mark Ronson, not Bruno Mars.",
                            "Uptown Funk", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        /*Adapted from https://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html &
        * https://docs.oracle.com/javase/8/docs/api/javax/swing/JOptionPane.html &
        * https://docs.oracle.com/javase/8/docs/api/javax/swing/JDialog.html*/

        //This is the Compare button
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

                String selectionLeft = (String) result1List.getSelectedValue(); //getSelectedValue returns an object
                String selectionRight = (String) result2List.getSelectedValue(); //so these are cast to String
                String[] selectionLeftArray = selectionLeft.split(":");
                String[] selectionRightArray = selectionRight.split(":");

                try {
                    SongDetails leftSongDetails = JSON.getEchonestResponseDetails(EchoNest.echonestInfo(selectionLeftArray[3]));
                    SongDetails rightSongDetails = JSON.getEchonestResponseDetails(EchoNest.echonestInfo(selectionRightArray[3]));
//                    System.out.println(leftSongDetails.getArtistName());
//                    System.out.println(rightSongDetails.getArtistName());
                    danceabilityMatch = Compare.compareDoubles(leftSongDetails.getDanceability(), rightSongDetails.getDanceability());
                    durationMatch = Compare.compareDoubles(leftSongDetails.getDuration(), rightSongDetails.getDuration());
                    energyMatch = Compare.compareDoubles(leftSongDetails.getEnergy(), rightSongDetails.getEnergy());
                    harmonicKeyMatch = Compare.compareLong(leftSongDetails.getHarmonicKey(), rightSongDetails.getHarmonicKey()); //Will be simple match
                    modeMatch = Compare.compareLong(leftSongDetails.getMode(), rightSongDetails.getMode()); //Will be simple match
                    tempoMatch = Compare.compareDoubles(leftSongDetails.getTempo(), rightSongDetails.getTempo());
                    timeSignatureMatch = Compare.compareLong(leftSongDetails.getTimeSignature(), rightSongDetails.getTimeSignature()); //Will be simple match
                } catch (Exception e) {
                    System.out.println(e);
                }

                JOptionPane.showMessageDialog(null, "Comparison between "+selectionLeftArray[1]+" "+selectionLeftArray[2]+
                        " and "+selectionRightArray[1]+" "+selectionRightArray[2]+" \n Danceability: "+danceabilityMatch+"\n Duration: "+durationMatch+"\n Energy: "+energyMatch
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
            String songString = x+": "+song.getArtistName()+": \""+song.getSongTitle()+"\" :"+song.getSongID()+":"+song.getArtistID();
            songDefaultListModelOne.addElement(songString);
            x++;
        }

        songDefaultListModelTwo = new DefaultListModel<String>();
        result2List.setModel(songDefaultListModelTwo);
        result2List.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        x = 1;
        for (Song song: responseTwo) {
            String songString = x+": "+song.getArtistName()+": \""+song.getSongTitle()+"\" :"+song.getSongID()+":"+song.getArtistID();
            songDefaultListModelTwo.addElement(songString);
            x++;
        }
    }
}