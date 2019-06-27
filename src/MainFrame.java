import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class MainFrame extends JFrame {
    private JPanel mainPanel;
    private PlayingPanel playingPanel;
    private LeftPanel leftPanel;
    private Play player;
    private JScrollPane songsScrollPane;
    private ArrayList<Play> playingThreads;

    public MainFrame() throws IOException, ClassNotFoundException {
        playingThreads = new ArrayList<Play>();
        player = new Play();
        ImageIcon spotify = new ImageIcon("Icons\\Jpotify.png");
        this.setIconImage(spotify.getImage());
        mainPanel = new MainPanel(this, player, playingThreads);
        leftPanel = new LeftPanel(mainPanel, this, player, playingThreads);
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

    void Playernew() {
        player = new Play();
    }

    public PlayingPanel getPlayingPanel() {
        return playingPanel;
    }

    public LeftPanel getLeftPanel() {
        return leftPanel;
    }

    public Play getPlayer() {
        return player;
    }

    public JScrollPane getSongsScrollPane() {
        return songsScrollPane;
    }

    public ArrayList<Play> getPlayingThreads() {
        return playingThreads;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
    public void ChangePanel(Playlist playlist)
    {
        mainPanel.removeAll();
        mainPanel.add(playlist);
        mainPanel.revalidate();
        this.revalidate();
        this.repaint();
    }
    //    public void chageMainPanel(JPanel mainPanel) {
//        this.mainPanel = mainPanel;
//    }
}