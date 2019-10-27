import java.io.*;
import java.nio.*;
import java.util.*;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.embed.swing.JFXPanel;

public class Music {

    /**
	 * Gets the song title from user.
	 * @return a properly formatted string of the song title
	 */
	public String selectSong() {

		// Scanner for taking in user input
		Scanner in = new Scanner(System.in);

		// Inform the user to select a song
		System.out.print("Which song would you like to play?\n--> ");

		// Contains name of the user's selected song
		String selectedSong = in.nextLine().toLowerCase().trim();

		// Close resources
		in.close();

		return selectedSong.substring(0,1).toUpperCase() + 
				selectedSong.substring(1).toLowerCase();
	}

    /**
	 * Downloads a specified song from the music player's library to the user.
	 * @param song is the name of the song to be downloaded
	 * @throws Exception
	 */
	public void downloadSong(String song) throws Exception {

		// File name concatenated with with the filetype identifier
		String fileName = song + ".mp3";

		// Creates a new file instance to be used
		File file = new File(fileName);

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

		// // Close resources
		fileIn.close();
		fileOut.close();
	}

    // public static void main(String args[]) {
        
    //     Music o = new Music();
    //     String song = o.selectSong();

    //     System.out.println(song);
    // }
}
