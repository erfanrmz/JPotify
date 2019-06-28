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

//    public MainFrame() throws IOException, ClassNotFoundException {
//        playingThreads = new ArrayList<Play>();
//        player = new Play();
//        ImageIcon spotify = new ImageIcon("Icons\\Jpotify.png");
//        this.setIconImage(spotify.getImage());
//        mainPanel = new MainPanel(this, player, playingThreads);
//        leftPanel = new LeftPanel(mainPanel, this, player, playingThreads);
//        playingPanel = new PlayingPanel(player, playingThreads);
//        songsScrollPane = new JScrollPane(mainPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//        songsScrollPane.setBorder(null);
//        this.setTitle("Jpotify");
//        this.setSize(new Dimension(1600, 900));
//        this.setLayout(new BorderLayout());
//        this.add(songsScrollPane, BorderLayout.CENTER);
//        this.add(playingPanel, BorderLayout.PAGE_END);
//        this.add(leftPanel, BorderLayout.LINE_START);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setVisible(true);
//=======

    public MainFrame() {
        songs = new ArrayList<Song>();
        albums = new ArrayList<Album>();
        playingThreads = new ArrayList<Play>();
        try {
            readSongs();
        } catch (Exception e2) {
            System.out.println(e2);
        }
        System.out.println(songs.size());
        player = new Play();
        ImageIcon spotify = new ImageIcon("Icons\\Jpotify.png");
        this.setIconImage(spotify.getImage());
        mainPanel = new MainPanel(this, player, playingThreads, songs);
        leftPanel = new LeftPanel(mainPanel, this, player, playingThreads, albums, songs);
        albumsPanel = new Albums(albums, songs, this);
        playingPanel = new PlayingPanel(player, playingThreads);
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

    public void ChangePanel(Playlist playlist) {
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
    }

}