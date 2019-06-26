import javazoom.jl.player.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainFrame extends JFrame {
    private MainPanel mainPanel;
    private PlayingPanel playingPanel;
    private LeftPanel leftPanel;
    private  Play player;
    private JScrollPane songsScrollPane;
    private ArrayList<Play> playingThreads;
    public MainFrame()
    {
        playingThreads = new ArrayList<Play>();
        player = new Play();
        ImageIcon spotify = new ImageIcon("Icons\\Jpotify.png");
        this.setIconImage(spotify.getImage());
        mainPanel = new MainPanel(this , player ,playingThreads);
        leftPanel = new LeftPanel(mainPanel);
        playingPanel = new PlayingPanel(player);
        int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED ;
        int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED ;
        songsScrollPane = new JScrollPane(mainPanel, v, h ) ;
        songsScrollPane.setBorder(null);
        this.setTitle("Jpotify");
        this.setSize(new Dimension(1600,900));
        this.setLayout(new BorderLayout());
        this.add(songsScrollPane,BorderLayout.CENTER);
        this.add(playingPanel,BorderLayout.PAGE_END);
        this.add(leftPanel,BorderLayout.LINE_START);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    void Playernew()
    {
        player = new Play();
    }
}