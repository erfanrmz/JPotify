import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class MainPanel extends JPanel {
    private MainFrame mainFrame;
    private Play player;
    private JPanel box1;
    private JPanel box2;
    private JPanel box3;
    private int count;
    private ArrayList<Song> songs;

    public MainPanel(MainFrame mainFrame, Play player) throws ClassNotFoundException, UnsupportedTagException, InvalidDataException, IOException {
        songs = new ArrayList<>();
        count = 0;
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
        }catch (Exception e){
        }
//        this.setLayout(new FlowLayout(FlowLayout.LEFT));
    }

    public void addsongFromButton(Song song) throws IOException {
        songs.add(song);
        SJButton songadded = new SJButton(song.getTitle(), song.getImageIcon(), song, player);
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
        FileOutputStream fop = new FileOutputStream("library.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fop);
        oos.writeObject(songs);

    }

    public void readSongs() throws InvalidDataException, IOException, UnsupportedTagException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("library.ser");
        ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File("library.ser"))));
        songs = (ArrayList<Song>) ois.readObject();
        for (int i = 0; i < songs.size(); i++) {
            addsongFromSer(songs.get(i));
        }
    }

    public void addsongFromSer(Song song) {
        SJButton songadded = new SJButton(song.getTitle(), song.getImageIcon(), song, player);
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
}
