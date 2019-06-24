import java.io.File;
import java.io.FileInputStream;

public class Song {
    private String address;
    private int songSeek;
    private String title;
    private String artist;
    private String album;
    private String year;

    public Song(String address) {
        this.address = address;
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
    }

    @Override
    public String toString() {
        return "Song{" +
                "address='" + address + '\'' +
                ", songSeek=" + songSeek +
                ", title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", album='" + album + '\'' +
                ", year='" + year + '\'' +
                '}';
    }
}
