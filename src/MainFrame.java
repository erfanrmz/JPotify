import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainFrame extends JFrame {
    private MainPanel mainPanel;
    private PlayingPanel playingPanel;
    private LeftPanel leftPanel;
    private Play player;
    private JScrollPane songsScrollPane;
    public MainFrame() throws InvalidDataException, IOException, UnsupportedTagException, ClassNotFoundException {
        player = new Play();
        ImageIcon spotify = new ImageIcon("Icons\\Jpotify.png");
        this.setIconImage(spotify.getImage());
        mainPanel = new MainPanel(this , player);
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
}