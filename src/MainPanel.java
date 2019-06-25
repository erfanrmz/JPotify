import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private MainFrame mainFrame;
    private Play player;
    public MainPanel(MainFrame mainFrame , Play player)
    {
        this.mainFrame = mainFrame;
        this.player = player;
        this.setBackground(new Color(24,24,24));
    }

    public void addsong(Song song)
    {
        SJButton songadded = new SJButton(song.getTitle(),song.getImageIcon(),song,player);
//        songadded.setPreferredSize(new Dimension(250,250));
        this.add(songadded);
        this.revalidate();
    }
}
