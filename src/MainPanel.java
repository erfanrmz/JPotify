import javax.swing.*;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

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
//        this.setLayout(new FlowLayout(FlowLayout.LEFT));

        for (int i = 0; i < songs.size(); i++) {
            addSongFromSer(songs.get(i));
        }
    }

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
        FileOutputStream fop = new FileOutputStream("Saves\\library.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fop);
        oos.writeObject(songs);

    }

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
//    public void readSongs () throws
//            InvalidDataException, IOException, UnsupportedTagException, ClassNotFoundException {
//        FileInputStream fis = new FileInputStream("Saves\\library.ser");
//        ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File("Saves\\library.ser"))));
//        System.out.println(songs.size() + "REad song before Equal");
//        songs = (ArrayList<Song>) ois.readObject();
//        System.out.println(songs.size() + "after Equal");
//
//    }
}


