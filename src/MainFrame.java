import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private MainPanel mainPanel;
    private PlayingPanel playingPanel;
    private LeftPanel leftPanel;
    public MainFrame()
    {
        leftPanel = new LeftPanel();
        playingPanel = new PlayingPanel();
        mainPanel = new MainPanel();
        this.setTitle("Jpotify");
        this.setSize(new Dimension(1600,900));
        this.setLayout(new BorderLayout());
        this.add(mainPanel,BorderLayout.CENTER);
        this.add(playingPanel,BorderLayout.PAGE_END);
        this.add(leftPanel,BorderLayout.LINE_START);
        this.setVisible(true);
    }
}
