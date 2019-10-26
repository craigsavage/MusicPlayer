import java.util.Scanner;

public class Music {

    Scanner in = new Scanner(System.in);

    public String selectSong() {

        // Inform the user to select a song
        System.out.print("Which song would you like to play?\n--> ");

        // Contains name of the user's selected song
        String selectedSong = in.nextLine().toLowerCase().trim();

                

        return selectedSong;
    }

    public static void main(String args[]) {
        
        Music o = new Music();
        String song = o.selectSong();

        System.out.println(song);
    }
}
