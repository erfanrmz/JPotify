import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Albums extends JPanel {
    private JPanel box1;
    private JPanel box2;
    private JPanel box3;
    private MainFrame mainFrame;
    private ArrayList<Album> albums;
    private ArrayList<Song> songs;

    public Albums(ArrayList<Album> albums, ArrayList<Song> songs, MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.songs = songs;
        box1 = new JPanel();
        box2 = new JPanel();
        box3 = new JPanel();
        box1.setBackground(new Color(24, 24, 24));
        box2.setBackground(new Color(24, 24, 24));
        box3.setBackground(new Color(24, 24, 24));
        this.albums = albums;
        this.setLayout(new GridLayout(1, 3));
        this.add(box1);
        this.add(box2);
        this.add(box3);
        box1.setLayout(new BoxLayout(box1, BoxLayout.Y_AXIS));
        box2.setLayout(new BoxLayout(box2, BoxLayout.Y_AXIS));
        box3.setLayout(new BoxLayout(box3, BoxLayout.Y_AXIS));
        for (int i = 0; i < albums.size(); i++) {
            AJButton albumButton = new AJButton(albums.get(i).getName(), albums.get(i).getImageIcon(), albums.get(i), mainFrame);
            if (i % 3 == 0) {
                box1.add(albumButton);
            } else if (i % 3 == 1) {
                box2.add(albumButton);
            } else if (i % 3 == 2) {
                box3.add(albumButton);
            }
            this.revalidate();
        }
    }

    public void updateAlbums() {
        albums.removeAll(albums);
        box1.removeAll();
        box2.removeAll();
        box3.removeAll();
        for (int i = 0; i < songs.size(); i++) {
            if (albums.size() == 0) {
                ArrayList<Song> songsOfAlbum = new ArrayList<Song>();
                songsOfAlbum.add(songs.get(i));
                Album album = new Album(songs.get(i).getAlbum(), songs.get(i).getArtist(), songs.get(i).getYear(), songs.get(i).getImageIcon(), mainFrame, songsOfAlbum);
//                album.getSongsOfAlbum().add(songs.get(i));
                albums.add(album);
            } else {
                for (int j = 0; j < albums.size(); j++) {
                    if (songs.get(i).getAlbum().equals(albums.get(j).getName())) {
                        albums.get(j).getSongsOfAlbum().add(songs.get(i));
                        break;
                    } else if (j == albums.size() - 1 && !songs.get(i).getAlbum().equals(albums.get(j).getName())) {
                        ArrayList<Song> songsOfAlbum = new ArrayList<Song>();
                        songsOfAlbum.add(songs.get(i));
                        Album album = new Album(songs.get(i).getAlbum(), songs.get(i).getArtist(), songs.get(i).getYear(), songs.get(i).getImageIcon(), mainFrame, songsOfAlbum);
//                        albums.get(j).getSongsOfAlbum().add(songs.get(i));
                        albums.add(album);
                        break;
                    }
                }
            }
        }
        for (int i = 0; i < albums.size(); i++) {
            AJButton albumAdded = new AJButton(albums.get(i).getName(), albums.get(i).getImageIcon(), albums.get(i), mainFrame);
            if (i % 3 == 0) {
                box1.add(albumAdded);
                box1.setLayout(new BoxLayout(box1, BoxLayout.Y_AXIS));
            } else if (i % 3 == 1) {
                box2.add(albumAdded);
                box2.setLayout(new BoxLayout(box2, BoxLayout.Y_AXIS));
            } else if (i % 3 == 2) {
                box3.add(albumAdded);
                box3.setLayout(new BoxLayout(box3, BoxLayout.Y_AXIS));
            }
            this.revalidate();
        }
    }
}
