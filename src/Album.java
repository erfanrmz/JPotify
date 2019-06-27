import javax.swing.*;
import java.util.ArrayList;

public class Album extends JPanel{
    private String name;
    private String artist;
    private String year;
    private ImageIcon imageIcon;
    private ArrayList<Song> songs;
    public Album(String name,String artist,String year , ImageIcon imageIcon)
    {
        songs = new ArrayList<Song>();
        this.name = name;
        this.artist = artist;
        this.year = year;
        this.imageIcon = imageIcon;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }
}
