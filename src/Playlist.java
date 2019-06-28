import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

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
//        this.setLayout(new FlowLayout(FlowLayout.LEFT));
    }

    public void addsongFromButton(Song song) throws IOException {
        songs.add(song);
        PJButton songadded = new PJButton(song.getTitle(), song.getImageIcon(), song, player, mainFrame, playingThreads,this);
        if (count % 3 == 0) {
            box1.add(songadded);
            box1.setLayout(new BoxLayout(box1, BoxLayout.Y_AXIS));
            count++;
        } else if (count % 3 == 1) {
            box2.add(songadded);
            box2.setLayout(new BoxLayout(box2, BoxLayout.Y_AXIS));
            count++;
        } else if (count % 3 == 2) {
            box3.add(songadded);
            box3.setLayout(new BoxLayout(box3, BoxLayout.Y_AXIS));
            count++;
        }
        this.revalidate();
        FileOutputStream fop = new FileOutputStream("Saves\\" + name + ".ser");
        ObjectOutputStream oos = new ObjectOutputStream(fop);
        oos.writeObject(songs);

    }

    public void addsongFromSer(Song song) {
        PJButton songadded = new PJButton(song.getTitle(), song.getImageIcon(), song, player, mainFrame, playingThreads,this);
        if (count % 3 == 0) {
            box1.add(songadded);
            box1.setLayout(new BoxLayout(box1, BoxLayout.Y_AXIS));
            count++;
        } else if (count % 3 == 1) {
            box2.add(songadded);
            box2.setLayout(new BoxLayout(box2, BoxLayout.Y_AXIS));
            count++;
        } else if (count % 3 == 2) {
            box3.add(songadded);
            box3.setLayout(new BoxLayout(box3, BoxLayout.Y_AXIS));
            count++;
        }
        this.revalidate();
    }

    public void readSongs() throws
            InvalidDataException, IOException, UnsupportedTagException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("Saves\\" + name + ".ser");
        ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File("Saves\\" + name + ".ser"))));
        songs = (ArrayList<Song>) ois.readObject();
        for (int i = 0; i < songs.size(); i++) {
            addsongFromSer(songs.get(i));
        }
    }

    public String getName() {
        return name;
    }

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
            PJButton songadded = new PJButton(songs.get(i).getTitle(), songs.get(i).getImageIcon(), songs.get(i), player, mainFrame, playingThreads, this);
            if (count % 3 == 0) {
                box1.add(songadded);
                box1.setLayout(new BoxLayout(box1, BoxLayout.Y_AXIS));
                count++;
            } else if (count % 3 == 1) {
                box2.add(songadded);
                box2.setLayout(new BoxLayout(box2, BoxLayout.Y_AXIS));
                count++;
            } else if (count % 3 == 2) {
                box3.add(songadded);
                box3.setLayout(new BoxLayout(box3, BoxLayout.Y_AXIS));
                count++;
            }
        }
        this.revalidate();
        this.repaint();
    }

    public void removeSong(Song song) throws IOException {
        songs.remove(song);
        FileOutputStream fop = new FileOutputStream("Saves\\" + name + ".ser");
        ObjectOutputStream oos = new ObjectOutputStream(fop);
        oos.writeObject(songs);
        modifyPanel();
    }
}


