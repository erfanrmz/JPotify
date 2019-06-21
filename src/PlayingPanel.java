import javax.swing.*;
import java.awt.*;

public class PlayingPanel extends JPanel {
    private EJButton play;
    private EJButton nextMusic;
    private EJButton previousMusic;
    private EJSlider volumeBar;

    public PlayingPanel()
    {
        this.setPreferredSize(new Dimension(1600,100));
        //this.setLayout(new BorderLayout());
        play = new EJButton();
        nextMusic = new EJButton();
        previousMusic = new EJButton();
        play.setIcon(new ImageIcon("Icons\\play50.png"));
        play.setPreferredSize(new Dimension(50,50));
        previousMusic.setIcon(new ImageIcon("Icons\\previous30.png"));
        previousMusic.setPreferredSize(new Dimension(30,30));
        nextMusic.setIcon(new ImageIcon("Icons\\next30.png"));
        nextMusic.setPreferredSize(new Dimension(30,30));
        volumeBar = new EJSlider();
        volumeBar.setPreferredSize(new Dimension(100,10));
        this.setBackground(new Color(40,40,40));
        this.setSize(new Dimension(1600,100));
        this.add(volumeBar);
        this.add(previousMusic);
        this.add(play);
        this.add(nextMusic);


    }

}
