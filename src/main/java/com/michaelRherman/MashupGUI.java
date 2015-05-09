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
    private JComboBox favoriteBox1;
    private JComboBox favoriteBox2;

    private static String artistSearch1;
    private static String songSearch1;
    private static String artistSearch2;
    private static String songSearch2;

    private static final String Exact = "Exact";
    private static final String Low = "Low";
    private static final String Medium = "Medium";
    private static final String High = "High";
    protected static final String[] comboOptions = {Exact, Low, Medium, High};
    protected static LinkedList favoriteOptions;

    protected static LinkedList<Song> ResponseOne;
    protected static LinkedList<Song> ResponseTwo;

    private static boolean danceabilityMatch;
    private static boolean durationMatch;
    private static boolean energyMatch;
    private static boolean harmonicKeyMatch; //Will be simple match
    private static boolean modeMatch; //Will be simple match
    private static boolean tempoMatch;
    private static boolean timeSignatureMatch; //Will be simple match

    private static String spotifyID;

    protected MashupGUI() {
        super("Mashup Helper");
        setContentPane(rootPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        for (int x = 0; x < comboOptions.length; x++)
        setSway.addItem((String) Array.get(comboOptions, x));

        try {
            favoriteOptions = Database.getFavorites();
        } catch (SQLException e) {
            System.out.println(e);
        }

        //This is the favorites box on the left
        favoriteBox1.addItem("Favorites");
        for (int x = 0; x < favoriteOptions.size(); x++)
            favoriteBox1.addItem((String) favoriteOptions.get(x));

        //This is the favorites box on the left
        favoriteBox2.addItem("Favorites");
        for (int x = 0; x < favoriteOptions.size(); x++)
            favoriteBox2.addItem((String) favoriteOptions.get(x));

        //This is the OK button
        okayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (favoriteBox1.getSelectedItem().equals("Favorites")) {
                    artistSearch1 = artistField1.getText();
                    if (artistSearch1.length() == 0) {
                        artistSearch1 = null;
                    }
                    songSearch1 = songField1.getText();
                    if (songSearch1.length() == 0) {
                        songSearch1 = null;
                    }
                } else {
                    String favoriteBox1String = (String)favoriteBox1.getSelectedItem();
                    String[] favorite1array = favoriteBox1String.split("-");
                    artistSearch1 = favorite1array[0];
                    songSearch1 = favorite1array[1];
                }

                if (favoriteBox2.getSelectedItem().equals("Favorites")) {
                    artistSearch2 = artistField2.getText();
                    if (artistSearch2.length() == 0) {
                        artistSearch2 = null;
                    }
                    songSearch2 = songField2.getText();
                    if (songSearch2.length() == 0) {
                        songSearch2 = null;
                    }
                } else {
                    String favoriteBox2String = (String)favoriteBox2.getSelectedItem();
                    String[] favorite2array = favoriteBox2String.split("-");
                    artistSearch2 = favorite2array[0];
                    songSearch2 = favorite2array[1];
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
                String[] selectionLeftArray = selectionLeft.split("-");
                String[] selectionRightArray = selectionRight.split("-");

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
                String selectionLeft = (String) result1List.getSelectedValue();
                String[] selectionLeftArray = selectionLeft.split("-");
                try {
                    spotifyID = JSON.getSpotifyID(EchoNest.getSpotifyID(selectionLeftArray[1], selectionLeftArray[2]));
                    Main.openDefaultBrowser(spotifyID);
                } catch (NullPointerException npe) {
                    JOptionPane.showMessageDialog(null, "Please select a song to play.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } catch (IndexOutOfBoundsException oob) {
                    JOptionPane.showMessageDialog(null, "Song not playable in your area.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });

        //THIS IS THE PLAY BUTTON ON THE RIGHT
        playButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String selectionRight = (String) result2List.getSelectedValue();
                String[] selectionRightArray = selectionRight.split("-");
                try {
                    spotifyID = JSON.getSpotifyID(EchoNest.getSpotifyID(selectionRightArray[1],selectionRightArray[2]));
                    Main.openDefaultBrowser(spotifyID);
                } catch (NullPointerException npe) {
                    JOptionPane.showMessageDialog(null, "Please select a song to play.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } catch (IndexOutOfBoundsException oob) {
                    JOptionPane.showMessageDialog(null, "Song not playable in your area.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });

        //THIS IS THE LEFT FAVORITE BUTTON
        favoriteButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String selectionLeft = (String) result1List.getSelectedValue(); //getSelectedValue returns an object so cast to String
                String[] selectionLeftArray = selectionLeft.split("-");
                String Artist = selectionLeftArray[1];
                String Song = selectionLeftArray[2];
                String SongID = selectionLeftArray[3];
                String ArtistID = selectionLeftArray[4];
                Database.insertFavorite(Artist, Song, SongID, ArtistID);
            }
        });

        //THIS IS THE RIGHT FAVORITE BUTTON
        favoriteButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String selectionRight = (String) result2List.getSelectedValue(); //getSelectedValue returns an object so cast to String
                String[] selectionRightArray = selectionRight.split("-");
                String Artist = selectionRightArray[1];
                String Song = selectionRightArray[2];
                String SongID = selectionRightArray[3];
                String ArtistID = selectionRightArray[4];
                Database.insertFavorite(Artist, Song, SongID, ArtistID);
            }
        });
    }

    public void displaySongs(LinkedList<Song> responseOne, LinkedList<Song> responseTwo) {
        songDefaultListModelOne = new DefaultListModel<String>(); //Because we want to refresh the list each time,
        result1List.setModel(songDefaultListModelOne);//we initialize each time this method is called.
        result1List.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        int x = 1;
        for (Song song: responseOne) {
            String songString = x+"-"+song.getArtistName()+"-"+song.getSongTitle()+"-"+song.getSongID()+"-"+song.getArtistID();
            songDefaultListModelOne.addElement(songString);
            x++;
        }

        songDefaultListModelTwo = new DefaultListModel<String>();
        result2List.setModel(songDefaultListModelTwo);
        result2List.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        x = 1;
        for (Song song: responseTwo) {
            String songString = x+"-"+song.getArtistName()+"-"+song.getSongTitle()+"-"+song.getSongID()+"-"+song.getArtistID();
            songDefaultListModelTwo.addElement(songString);
            x++;
        }
    }
}