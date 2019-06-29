import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

/**
 * This is a class for creating a playlist.
 *
 * @author Erfan Ramezani & Amir Mojtaba Kiasat
 * @version 1.0
 * @since 6-21-2019
 */
public class Playlist extends JPanel {
    private String name;
    private MainFrame mainFrame;
    private Play player;
    private JPanel box1;
    private JPanel box2;
    private JPanel box3;
    private int count;
    private ArrayList<Song> songs;
    private ArrayList<Play> playingThreads;

    public Playlist(String name, MainFrame mainFrame, Play player, ArrayList<Play> playingThreads) {
        songs = new ArrayList<>();
        count = 0;
        this.name = name;
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
        try {
            readSongs();
        } catch (Exception e) {
        }
    }

    /**
     * adds the song to playlist by pressing the add button
     *
     * @param song the song that the user wants to add to playlist
     * @throws IOException
     */
    public void addSongFromButton(Song song) throws IOException {
        songs.add(song);
        PJButton songAdded = new PJButton(song.getTitle(), song.getImageIcon(), song, player, mainFrame, playingThreads, this);
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
        FileOutputStream fop = new FileOutputStream("Saves\\" + mainFrame.getUser() + "'s " + name + ".ser");
        ObjectOutputStream oos = new ObjectOutputStream(fop);
        oos.writeObject(songs);

    }

    /**
     * adds the song to playlist form the saved file
     *
     * @param song the song that we want to be added to playlist
     */
    public void addSongFromSer(Song song) {
        PJButton songAdded = new PJButton(song.getTitle(), song.getImageIcon(), song, player, mainFrame, playingThreads, this);
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
     * Reads the songs of the playlist from seved file
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void readSongs() throws
            IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("Saves\\" + mainFrame.getUser() + "'s " + name + ".ser");
        ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File("Saves\\" + mainFrame.getUser() + "'s " + name + ".ser"))));
        songs = (ArrayList<Song>) ois.readObject();
        for (int i = 0; i < songs.size(); i++)
            addSongFromSer(songs.get(i));
    }

    public String getName() {
        return name;
    }

    /**
     * Regenerates the playlist panel
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
            PJButton songAdded = new PJButton(songs.get(i).getTitle(), songs.get(i).getImageIcon(), songs.get(i), player, mainFrame, playingThreads, this);
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
     * Remove the song from the playlist
     *
     * @param song the song that need to be removed
     * @throws IOException
     */
    public void removeSong(Song song) throws IOException {
        songs.remove(song);
        FileOutputStream fop = new FileOutputStream("Saves\\" + mainFrame.getUser() + "'s " + name + ".ser");
        ObjectOutputStream oos = new ObjectOutputStream(fop);
        oos.writeObject(songs);
        modifyPanel();
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }
}


