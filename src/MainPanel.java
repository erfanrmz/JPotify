import javax.swing.*;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This is a class for the main(central) panel of the program.
 *
 * @author Erfan Ramezani & Amir Mojtaba Kiasat
 * @version 1.0
 * @since 6-21-2019
 */
public class MainPanel extends JPanel {
    private MainFrame mainFrame;
    private Play player;
    private JPanel box1;
    private JPanel box2;
    private JPanel box3;
    private int count;
    private ArrayList<Song> songs;
    private ArrayList<Play> playingThreads;

    public MainPanel(MainFrame mainFrame, Play player, ArrayList<Play> playingThreads, ArrayList<Song> songs) {
        this.songs = songs;
        System.out.println(songs.size() + "MainPanel SIze");
        count = 0;
        this.playingThreads = playingThreads;
        box1 = new JPanel();
        box2 = new JPanel();
        box3 = new JPanel();
        box1.setBackground(new Color(24, 24, 24));
        box2.setBackground(new Color(24, 24, 24));
        box3.setBackground(new Color(24, 24, 24));
        this.mainFrame = mainFrame;
        this.player = player;
        this.setBackground(new Color(24, 24, 24));
        this.setLayout(new GridLayout(1, 3));
        this.add(box1);
        this.add(box2);
        this.add(box3);

        for (int i = 0; i < songs.size(); i++) {
            addSongFromSer(songs.get(i));
        }
    }

    /**
     * adds the song by pressing the add button
     *
     * @param song the song that the user wants to add
     * @throws IOException
     */
    public void addSongFromButton(Song song) throws IOException {
//        songs.add(song);
        SJButton songAdded = new SJButton(song.getTitle(), song.getImageIcon(), song, player, mainFrame, playingThreads);
        if (count % 3 == 0) {
            box1.add(songAdded);
            box1.setLayout(new BoxLayout(box1, BoxLayout.Y_AXIS));
            count++;
        } else if (count % 3 == 1) {
            box2.add(songAdded);
            box2.setLayout(new BoxLayout(box2, BoxLayout.Y_AXIS));
            count++;
        } else if (count % 3 == 2) {
            box3.add(songAdded);
            box3.setLayout(new BoxLayout(box3, BoxLayout.Y_AXIS));
            count++;
        }
        this.revalidate();
        FileOutputStream fop = new FileOutputStream("Saves\\" + mainFrame.getUser() + "'s library.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fop);
        oos.writeObject(songs);

    }

    /**
     * adds the song form the saved file
     *
     * @param song the song that we want to add
     */
    public void addSongFromSer(Song song) {
        SJButton songAdded = new SJButton(song.getTitle(), song.getImageIcon(), song, player, mainFrame, playingThreads);
        if (count % 3 == 0) {
            box1.add(songAdded);
            box1.setLayout(new BoxLayout(box1, BoxLayout.Y_AXIS));
            count++;
        } else if (count % 3 == 1) {
            box2.add(songAdded);
            box2.setLayout(new BoxLayout(box2, BoxLayout.Y_AXIS));
            count++;
        } else if (count % 3 == 2) {
            box3.add(songAdded);
            box3.setLayout(new BoxLayout(box3, BoxLayout.Y_AXIS));
            count++;
        }
        this.revalidate();
    }

    /**
     * Regenerates the songs panel
     */
    public void modifyPanel() {
        this.removeAll();
        count = 0;
        box1 = new JPanel();
        box2 = new JPanel();
        box3 = new JPanel();
        box1.setBackground(new Color(24, 24, 24));
        box2.setBackground(new Color(24, 24, 24));
        box3.setBackground(new Color(24, 24, 24));
        this.setBackground(new Color(24, 24, 24));
        this.setLayout(new GridLayout(1, 3));
        this.add(box1);
        this.add(box2);
        this.add(box3);
        for (int i = 0; i < songs.size(); i++) {
            SJButton songAdded = new SJButton(songs.get(i).getTitle(), songs.get(i).getImageIcon(), songs.get(i), player, mainFrame, playingThreads);
            if (count % 3 == 0) {
                box1.add(songAdded);
                box1.setLayout(new BoxLayout(box1, BoxLayout.Y_AXIS));
                count++;
            } else if (count % 3 == 1) {
                box2.add(songAdded);
                box2.setLayout(new BoxLayout(box2, BoxLayout.Y_AXIS));
                count++;
            } else if (count % 3 == 2) {
                box3.add(songAdded);
                box3.setLayout(new BoxLayout(box3, BoxLayout.Y_AXIS));
                count++;
            }
        }
        this.revalidate();
        this.repaint();
    }

    /**
     * makes the panel to get the recently played song to the first of the list
     *
     * @param song the recently played song
     */
    public void RecentlyPlayed(Song song) {
        int numOfSong = 0;
        for (int i = 0; i < mainFrame.getSongs().size(); i++)
            if (song == mainFrame.getSongs().get(i))
                numOfSong = i;
        for (int j = 0; j < numOfSong; j++)
            Collections.swap(mainFrame.getSongs(), j, numOfSong);

        int numOfAlbum = 0;
        for (int i = 0; i < mainFrame.getAlbums().size(); i++)
            if (song.getAlbum().equals(mainFrame.getAlbums().get(i).getName())) {
                numOfAlbum = i;
            }
        for (int j = 0; j < numOfAlbum; j++)
            Collections.swap(mainFrame.getAlbums(), j, numOfAlbum);
        mainFrame.getAlbumsPanel().updateAlbums();
    }
}


