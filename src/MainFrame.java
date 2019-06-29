import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

/**
 * This is a class for the main frame of the program.
 *
 * @author Erfan Ramezani & Amir Mojtaba Kiasat
 * @version 1.0
 * @since 6-21-2019
 */
public class MainFrame extends JFrame {
    private String name;
    private String myIP;
    private Server server;
    private ServerThread serverThread;
    private MainPanel songPanel;
    private JPanel mainPanel;
    private UserPanel userPanel;
    private PlayingPanel playingPanel;
    private Albums albumsPanel;
    private LeftPanel leftPanel;
    private RightPanel rightPanel;
    private Play player;
    private JScrollPane songsScrollPane;
    private ArrayList<Play> playingThreads;
    private ArrayList<Album> albums;
    private ArrayList<Song> songs;
    private ArrayList<JSliderSeek> jSliderSeeks;
    private ArrayList<Song> playlistPlaying;
    private ArrayList<Friend> friends;
    private Boolean inAlbum;
    private int port;

    public MainFrame(String name , String IP) {
        this.name = name;
        this.myIP = IP;
//        serverThread = new ServerThread();
//        serverThread.start();
        inAlbum = false;
        playlistPlaying = new ArrayList<Song>();
        jSliderSeeks = new ArrayList<JSliderSeek>();
        songs = new ArrayList<Song>();
        albums = new ArrayList<Album>();
        playingThreads = new ArrayList<Play>();
        friends = new ArrayList<Friend>();
        playlistPlaying = songs;

        try {
            readSongs();
        } catch (Exception e2) {
            System.out.println(e2);
        }
        System.out.println(songs.size());
        player = new Play(0, this);
        ImageIcon spotify = new ImageIcon("Icons\\Jpotify.png");
        this.setIconImage(spotify.getImage());
        mainPanel = new JPanel();
        userPanel = new UserPanel(name);
//<<<<<<< HEAD
//=======
        rightPanel = new RightPanel(this);
////=======
////>>>>>>> Bug_fixing
//>>>>>>> 63b211c4ff21d393fcb8a0e40d2bdd8f413f3587
        songPanel = new MainPanel(this, player, playingThreads, songs);
        leftPanel = new LeftPanel(songPanel, this, player, playingThreads, albums, songs);
        albumsPanel = new Albums(albums, songs, this);
        playingPanel = new PlayingPanel(player, playingThreads, this);
        songsScrollPane = new JScrollPane(songPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        songsScrollPane.setBorder(null);
        this.setTitle("Jpotify");
        this.setSize(new Dimension(1600, 900));
        this.setLayout(new BorderLayout());
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(songsScrollPane, BorderLayout.CENTER);
        mainPanel.add(userPanel, BorderLayout.PAGE_START);
        this.add(mainPanel, BorderLayout.CENTER);
        this.add(playingPanel, BorderLayout.PAGE_END);
        this.add(leftPanel, BorderLayout.LINE_START);
        this.add(rightPanel,BorderLayout.LINE_END);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    /**
     * Changes the main panel to playlist panel
     *
     * @param playlist the playlist that we want to switch to
     */
    public void ChangePanel(JPanel playlist) {
        songPanel.removeAll();
        songPanel.add(playlist);
        songPanel.revalidate();
        this.revalidate();
        this.repaint();
    }

    /**
     * Changes the main panel to album panel
     */
    public void setAlbumPanel() {
        songPanel.removeAll();
        albumsPanel.updateAlbums();
        songPanel.add(albumsPanel);
        songPanel.revalidate();
        songPanel.repaint();
        this.revalidate();
        this.repaint();
    }

    /**
     * Changes the main panel to albums panel
     */
    public void setAlbumPanel1(Album albumPanel) {
        songPanel.removeAll();
        songPanel.add(albumPanel);
        songPanel.revalidate();
        songPanel.repaint();
        this.revalidate();
        this.repaint();
    }

    /**
     * reads the songs from saved file
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void readSongs() throws IOException, ClassNotFoundException {

        ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File("Saves\\" + this.name + "'s library.ser"))));
        songs = (ArrayList<Song>) ois.readObject();
        playlistPlaying = songs;
    }

    public Play getPlayer() {
        return player;
    }

    public LeftPanel getLeftPanel() {
        return leftPanel;
    }

    public ArrayList<Play> getPlayingThreads() {
        return playingThreads;
    }

    public PlayingPanel getPlayingPanel() {
        return playingPanel;
    }

    public ArrayList<JSliderSeek> getJSliderSeeks() {
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

    public JPanel getSongPanel() {
        return songPanel;
    }

    public Albums getAlbumsPanel() {
        return albumsPanel;
    }

    public String getUser() {
        return name;
    }

    public void setInAlbum(Boolean inAlbum) {
        this.inAlbum = inAlbum;
    }

    public Boolean getInAlbum() {
        return inAlbum;
    }

    public ArrayList<Friend> getFriends() {
        return friends;
    }
    public void changeport()
    {
        port++;
    }

    public int getPort() {
        return port;
    }
}