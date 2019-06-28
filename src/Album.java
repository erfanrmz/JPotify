import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Album extends JPanel{
    private JPanel box1;
    private JPanel box2;
    private JPanel box3;
    private String name;
    private String artist;
    private String year;
    private MainFrame mainFrame;
    private ImageIcon imageIcon;
    private ArrayList<Song> songsOfAlbum;
    public Album(String name,String artist,String year , ImageIcon imageIcon , MainFrame mainFrame , ArrayList<Song> songsOfAlbum)

    {
        this.songsOfAlbum = songsOfAlbum;
        this.mainFrame = mainFrame;
        box1 = new JPanel();
        box2 = new JPanel();
        box3 = new JPanel();
        box1.setBackground(new Color(24, 24, 24));
        box2.setBackground(new Color(24, 24, 24));
        box3.setBackground(new Color(24, 24, 24));
        this.setLayout(new GridLayout(1, 3));
        this.add(box1);
        this.add(box2);
        this.add(box3);
        box1.setLayout(new BoxLayout(box1, BoxLayout.Y_AXIS));
        box2.setLayout(new BoxLayout(box2, BoxLayout.Y_AXIS));
        box3.setLayout(new BoxLayout(box3, BoxLayout.Y_AXIS));
        songsOfAlbum = new ArrayList<Song>();
        this.name = name;
        this.artist = artist;
        this.year = year;
        this.imageIcon = imageIcon;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Song> getSongsOfAlbum() {
        return songsOfAlbum;
    }

    public String getArtist() {
        return artist;
    }

    public String getYear() {
        return year;
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }
    public void updateAlbum()
    {
        System.out.println("Size in album"  + songsOfAlbum.size());
        for (int i = 0; i < songsOfAlbum.size() ; i++)
        {
           SJButton songAdded = new SJButton(songsOfAlbum.get(i).getTitle(), songsOfAlbum.get(i).getImageIcon(), songsOfAlbum.get(i),mainFrame.getPlayer(),mainFrame,mainFrame.getPlayingThreads());
            if (i % 3 == 0) {
                box1.add(songAdded);
                box1.setLayout(new BoxLayout(box1, BoxLayout.Y_AXIS));
            } else if (i % 3 == 1) {
                box2.add(songAdded);
                box2.setLayout(new BoxLayout(box2, BoxLayout.Y_AXIS));
            } else if (i % 3 == 2) {
                box3.add(songAdded);
                box3.setLayout(new BoxLayout(box3, BoxLayout.Y_AXIS));
            }
            this.revalidate();
        }
    }
}
