import java.io.*;
import java.nio.*;
import java.util.*;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.embed.swing.JFXPanel;

public class Music {

    // Initializing a Dictionary 
    HashMap<String, String> songLibrary = new HashMap<String, String>();

    // Initializing an ArrayList for the song queue
    ArrayList<String> songQueue = new ArrayList<>();


    public Music() {

        // So user's don't have to enter file identifiers for song titles
        songLibrary.put("Cultural", "Cultural.mp3");
        songLibrary.put("Silencer", "Silencer.mp3");
        songLibrary.put("Topanga", "Topanga.mp3");
        songLibrary.put("Trophies", "Trophies.mp3");
        songLibrary.put("Untied", "Untied.mp3");
    }

    /**
     * Gets the song title from user.
     * @return a properly formatted string of the song title
     */
    public String selectSong() {

        // Scanner for taking in user input
        Scanner in = new Scanner(System.in);

        // Contains name of the user's selected song
        String selectedSong = in.nextLine().toLowerCase().trim();

        return selectedSong.substring(0,1).toUpperCase() + 
                selectedSong.substring(1).toLowerCase();
    }

    public void playNext() {

    }

    /**
     * 
     */
    public void addSongToQueue() {

        while (true) {

            // Inform user to add a song to the queue
            System.out.print("Which song would you like to add to the queue?\n--> ");

            // Contains title of song to be added to queue
            String songToAdd = selectSong();

            // Check if the song exists in the song library
            if (songLibrary.containsKey(songToAdd)) {

                // Add song to queue
                songQueue.add(songToAdd);

                // Inform user of the position of the song in queue
                System.out.printf("Song: \"%s\" was added to position %d in queue.\n\n", songToAdd, songQueue.size());
            
                break;	// Break out of while loop

            } else {
                
                System.out.printf("Sorry, \"%s\" does not exist in the song library.\n" + 
                                "This is the updated list of all songs in the library:\n", songToAdd);

                printSongLibrary();	// Print out all songs in the song library
            }
        }
    }

    /**
     * Prints out a list of all songs in the song library
     */
    public void printSongLibrary() {

        int count = 1;	// Used to numerically list songs in the library

        // Cycle through all songs in the song library and print them out
        for (String song : songLibrary.keySet()) {
            
            // Prints out the current song in the loop along with an incremented number for each song
            System.out.printf("  %d) %s\n", count, song);

            count++;	// Increment counter by one
        }

        System.out.println();	// blank line
    }

    /**
     * Downloads a specified song from the music player's library to the user.
     * @param song is the name of the song to be downloaded
     * @throws Exception
     */
    public void downloadSong() throws Exception {

        // Inform user to enter the song they would like downloaded
        System.out.print("Enter the title of the song to download: ");

        // Stores user selected song to be downloaded
        String song = selectSong();

        // File name concatenated with with the filetype identifier
        String fileName = songLibrary.get(song);
        
        // Store the located of the song library folder
        String songLocation = new File(".").getAbsolutePath() + "\\SongLibrary\\";

        // Creates a new file instance to be used
        File file = new File(songLocation + fileName);

        // Stores the bits of the file
        byte data[] = new byte[(int) fileName.length()];

        // Store the copied bits
        int songBytes;

        // The name of the folder to download song files to
        String downloadLocation = "\\MusicDownloadedFromServer\\" + fileName;

        // Inform user that their specific song is being downloaded to the folder
        System.out.printf("Downloading \"%s\" to \"%s\".\n", fileName, downloadLocation);

        // Creates a new folder and file to write to
        FileOutputStream fileOut = new FileOutputStream(new File(".").getAbsolutePath() + downloadLocation);

        // Opens a connection to the inputted file
        FileInputStream fileIn = new FileInputStream(file);

        // writes song file from the inputstream to the user's folder
        while ((songBytes = fileIn.read(data, 0, data.length)) != -1) {
            fileOut.write(data, 0, songBytes);
        }

        // Inform user that the download is completed
        System.out.println("Download completed!\n");

        // // Close resources
        fileIn.close();
        fileOut.close();
    }

    // Client side -----
    public static void main(String[] args) {
        try {
            // Create playe object
            Music player = new Music();

            Scanner in = new Scanner(System.in);
            final JFXPanel fxPanel = new JFXPanel();
            
            // Location of the folder of songs
            String songLocation = new File(".").getAbsolutePath() + "\\SongLibrary\\";

            // Inform the user to select a song
            System.out.print("Which song would you like to play?\n--> ");

            // Get selected song from user input
            String songChoice = player.selectSong();

            System.out.println("Now playing: " + songChoice);

            // Check to see if that song is in the library
            if (player.songLibrary.containsKey(songChoice)) {
                System.out.println("true");
            } else {
                System.out.println("Sorry, that song does not exist.");

                System.out.println("Exiting Music Player...");
                System.exit(0);
            }

            
            String songFile = songLocation + player.songLibrary.get(songChoice);
            System.out.println("");

            Media hit = new Media(new File(songFile).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(hit);
            mediaPlayer.play();

            while (true) {
                System.out.print("What would you like to do?\n--> ");
                
                String musicSelector = in.nextLine().toLowerCase().trim();

                if (musicSelector.equals("stop")) {
                    mediaPlayer.stop();
                }

                if (musicSelector.equals("play")) {
                    mediaPlayer.play();
                }

                if (musicSelector.equals("add")) {
                    player.addSongToQueue();
                }

                if (musicSelector.equals("download")) {
                    player.downloadSong();
                }

                if (musicSelector.equals("close")) {
                    mediaPlayer.stop();

                    in.close();
                    break;
                }
            }

            System.out.println("Exiting Music App");
            System.exit(0);

        } catch (Exception e) {
            e.getStackTrace();
            System.out.println(e);
        }
    } 	
}
