import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class LeftPanel extends JPanel {
    private EJButton home;
    private EJButton others;
    private EJButton songs;
    private JPopupMenu popupMenu;
    private JMenuItem addsong;
    private JPanel songsPanel;
    public LeftPanel(JPanel songsPanel)
    {
        this.songsPanel = songsPanel;
        popupMenu = new JPopupMenu("others");
        addsong = new JMenuItem("Add song");
        popupMenu.add(addsong);
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
        popupMenu.setPreferredSize(new Dimension(150, 40));
        this.setBackground(leftPanelBackground);

        this.add(others);
        this.add(home);
        this.add(songs);

        //Actionlistoners

        addsong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser a = new JFileChooser();
                int fasf = a.showOpenDialog(null);
                try {
                    Song song = new Song(a.getSelectedFile().getAbsolutePath());
                    ObjectOutputStream library = new ObjectOutputStream(new FileOutputStream("library.ser"));
                    library.writeObject(song);
                    ((MainPanel)songsPanel).addsongFromButton(song);


                } catch (IOException | InvalidDataException | UnsupportedTagException ex) {
                    ex.printStackTrace();
                }
            }
        });

        others.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                popupMenu.show(others,30,40);
            }
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
