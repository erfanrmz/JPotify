import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;

public class LeftPanel extends JPanel {
    private EJButton home;
    private EJButton others;
    private EJButton songs;
    public LeftPanel()
    {
        setLayout (new BoxLayout (this, BoxLayout.Y_AXIS));
        others = new EJButton();
        home = new EJButton();
        songs = new EJButton();
        others.setPreferredSize(new Dimension(250,40));
        home.setPreferredSize(new Dimension(250,40));
        songs.setPreferredSize(new Dimension(250,40));
        home.setIcon(new ImageIcon("Icons\\home.png"));
        others.setIcon(new ImageIcon("Icons\\others.png"));
        songs.setIcon(new ImageIcon("Icons\\song1.png"));
        Color leftPanelBackground = new Color(18,18,18);

        this.setBackground(leftPanelBackground);

        this.add(others);
        this.add(home);
        this.add(songs);
        //Actionlistoners

        others.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                others.setIcon(new ImageIcon("Icons\\othersEntered.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                others.setIcon(new ImageIcon("Icons\\others.png"));
            }
        });
        home.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                home.setIcon(new ImageIcon("Icons\\homeEntered1.png"));

            }

            @Override
            public void mouseExited(MouseEvent e) {
                home.setIcon(new ImageIcon("Icons\\home.png"));
            }
        });
        songs.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                songs.setIcon(new ImageIcon("Icons\\songEntered1.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                songs.setIcon(new ImageIcon("Icons\\song1.png"));
            }
        });


    }
}
