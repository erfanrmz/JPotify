import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private MainFrame mainFrame;
    private Play player;
    private JPanel box1;
    private JPanel box2;
    private JPanel box3;
    private int count;
    public MainPanel(MainFrame mainFrame , Play player)
    {
        count = 0;
        box1 = new JPanel();
        box2 = new JPanel();
        box3 = new JPanel();
        box1.setBackground(new Color(24,24,24));
        box2.setBackground(new Color(24,24,24));
        box3.setBackground(new Color(24,24,24));

        this.mainFrame = mainFrame;
        this.player = player;
        this.setBackground(new Color(24,24,24));
        this.setLayout(new GridLayout(1, 3));
        this.add(box1);
        this.add(box2);
        this.add(box3);




//        this.setLayout(new FlowLayout(FlowLayout.LEFT));
    }

    public void addsong(Song song)
    {
        SJButton songadded = new SJButton(song.getTitle(),song.getImageIcon(),song,player);
        if(count % 3 == 0)
        {
            box1.add(songadded);
            box1.setLayout(new BoxLayout (box1, BoxLayout.Y_AXIS));
            count++;
        }
        else if (count % 3 == 1)
        {
            box2.add(songadded);
            box2.setLayout(new BoxLayout (box2, BoxLayout.Y_AXIS));
            count++;
        }
        else if(count % 3 == 2)
        {
            box3.add(songadded);
            box3.setLayout(new BoxLayout (box3, BoxLayout.Y_AXIS));
            count++;
        }
        this.revalidate();
    }
}
