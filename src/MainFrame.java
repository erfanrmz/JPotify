import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class MainFrame extends JFrame {
    private JPanel mainPanel;
    private PlayingPanel playingPanel;
    private Albums albumsPanel;
    private LeftPanel leftPanel;
    private Play player;
    private JScrollPane songsScrollPane;
    private ArrayList<Play> playingThreads;
    private ArrayList<Album> albums;
    private ArrayList<Song> songs;
    private ArrayList<JSliderSeek> jSliderSeeks;
    private ArrayList<Song> playlistPlaying;


    public MainFrame() {
        playlistPlaying = new ArrayList<Song>();
        jSliderSeeks = new ArrayList<JSliderSeek>();
        songs = new ArrayList<Song>();
        albums = new ArrayList<Album>();
        playingThreads = new ArrayList<Play>();
        playlistPlaying = songs;

        try {
            readSongs();
        } catch (Exception e2) {
            System.out.println(e2);
        }
        System.out.println(songs.size());
        player = new Play(0,this);
        ImageIcon spotify = new ImageIcon("Icons\\Jpotify.png");
        this.setIconImage(spotify.getImage());
        mainPanel = new MainPanel(this, player, playingThreads, songs);
        leftPanel = new LeftPanel(mainPanel, this, player, playingThreads, albums, songs);
        albumsPanel = new Albums(albums, songs, this);
        playingPanel = new PlayingPanel(player, playingThreads,this);
        songsScrollPane = new JScrollPane(mainPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        songsScrollPane.setBorder(null);
        this.setTitle("Jpotify");
        this.setSize(new Dimension(1600, 900));
        this.setLayout(new BorderLayout());
        this.add(songsScrollPane, BorderLayout.CENTER);
        this.add(playingPanel, BorderLayout.PAGE_END);
        this.add(leftPanel, BorderLayout.LINE_START);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public LeftPanel getLeftPanel() {
        return leftPanel;
    }

    public Play getPlayer() {
        return player;
    }

    public ArrayList<Play> getPlayingThreads() {
        return playingThreads;
    }

    public void ChangePanel(JPanel playlist) {
        mainPanel.removeAll();
        mainPanel.add(playlist);
        mainPanel.revalidate();
        this.revalidate();
        this.repaint();
    }

    public void setAlbumPanel() {
        mainPanel.removeAll();
        albumsPanel.updateAlbums();
        mainPanel.add(albumsPanel);
        mainPanel.revalidate();
        mainPanel.repaint();
        this.revalidate();
        this.repaint();
    }

    public void setAlbumPanel1(Album albumPanel) {
        mainPanel.removeAll();
        mainPanel.add(albumPanel);
        mainPanel.revalidate();
        mainPanel.repaint();
        this.revalidate();
        this.repaint();
    }

    public void readSongs() throws IOException, ClassNotFoundException {

        ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File("Saves\\library.ser"))));
        songs = (ArrayList<Song>) ois.readObject();
        playlistPlaying = songs;
    }

    public PlayingPanel getPlayingPanel() {
        return playingPanel;
    }

    public ArrayList<JSliderSeek> getjSliderSeeks() {
        return jSliderSeeks;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }


    public ArrayList<Song> getPlaylistPlaying() {
        return playlistPlaying;
    }

    public void setPlaylistPlaying(ArrayList<Song> playlistPlaying) {
        this.playlistPlaying = playlistPlaying;
    }

    public ArrayList<Album> getAlbums() {
        return albums;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public Albums getAlbumsPanel() {
        return albumsPanel;
    }
}