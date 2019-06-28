import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;

public class Song implements Serializable {
    private int songSeekPos;
    private String address;
    private String title;
    private String artist;
    private String album;
    private String year;
    private ImageIcon imageIcon;
    private boolean favorite;

    public Song(String address) throws IOException, InvalidDataException, UnsupportedTagException {
        favorite = false;
        this.address = address;
        songSeekPos = 0;
        try {
            File song = new File(address);
            FileInputStream inputStream = new FileInputStream(song);
            inputStream.skip(song.length() - 128);
            byte[] last128 = new byte[128];
            inputStream.read(last128);
            byte[] titleB = new byte[30];
            byte[] artistB = new byte[30];
            byte[] albumB = new byte[30];
            byte[] yearB = new byte[4];
            for (int i = 0; i < 30; i++) {
                titleB[i] = last128[i + 3];
                artistB[i] = last128[i + 33];
                albumB[i] = last128[i + 63];
            }
            for (int i = 0; i < 4; i++)
                yearB[i] = last128[i + 93];
            title = new String(titleB);
            artist = new String(artistB);
            album = new String(albumB);
            year = new String(yearB);

        } catch (Exception e) {
            System.out.println("Error ? " + e.toString());
        }
        Mp3File mp3File = new Mp3File(address);
        try {
            imageIcon = new ImageIcon(mp3File.getId3v2Tag().getAlbumImage());
            Image img = imageIcon.getImage();
            Image resizedImage = img.getScaledInstance(250, 250, java.awt.Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(resizedImage);
        } catch (Exception e) {
            imageIcon = new ImageIcon("Icons\\cover1.png");
            System.out.println("No artwork");
        }
    }

    public String getAddress() {
        return address;
    }

    public int getSongSeekPos() {
        return songSeekPos;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }

    public String getYear() {
        return year;
    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    public void setSongSeekPos(int songSeekPos) {
        this.songSeekPos = songSeekPos;
    }

    @Override
    public String toString() {
        return "Song{" +
                "address='" + address + '\'' +
                ", songSeek=" + songSeekPos +
                ", title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", album='" + album + '\'' +
                ", year='" + year + '\'' +
                '}';
    }
}
