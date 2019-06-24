import javazoom.jl.player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

public class PlayingPanel extends JPanel {
    private EJButton play;
    private EJButton nextMusic;
    private EJButton previousMusic;
    private EJButton repeat;
    private EJButton shuffle;
    private EJSlider volume;
    private EJSlider musicSeek;
    private JPanel buttonsBar;
    private JPanel volumeBar;
    private JPanel musicSeekPanel;

    public PlayingPanel()
    {
        this.setPreferredSize(new Dimension(1600,100));
        this.setLayout(new BorderLayout());
        play = new EJButton();
        nextMusic = new EJButton();
        previousMusic = new EJButton();
        repeat = new EJButton();
        shuffle = new EJButton();
        musicSeekPanel = new JPanel();
        buttonsBar = new JPanel();
        volumeBar = new JPanel();
        volume = new EJSlider(0,100,100);
        musicSeek = new EJSlider(0,100,50);
        volume.setPreferredSize(new Dimension(100,10));
        musicSeek.setPreferredSize(new Dimension(600,10));
        musicSeekPanel.add(musicSeek);
        //buttons bar
        buttonsBar.add(shuffle);
        buttonsBar.add(previousMusic);
        buttonsBar.add(play);
        buttonsBar.add(nextMusic);
        buttonsBar.add(repeat);
        buttonsBar.setLayout(new FlowLayout(FlowLayout.CENTER));
        //volume Bar
        volumeBar.setLayout(new GridLayout(3,1));
        volumeBar.add(volume);

        this.setBackground(new Color(40,40,40));
        buttonsBar.setBackground(new Color(40,40,40));
        volumeBar.setBackground(new Color(40, 40, 40));
        musicSeekPanel.setBackground(new Color(40,40,40));
        //icons
        play.setIcon(new ImageIcon("Icons\\play50.png"));
        play.setPreferredSize(new Dimension(75,50));
        play.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e)
            {
                if(play.getPressed() % 2 == 0)
                {

                    play.setIcon(new ImageIcon("Icons\\playEntered50.png"));
                }
                else if (play.getPressed()%2 == 1)
                {
                    play.setIcon(new ImageIcon("Icons\\pauseEntered50.png"));
                }

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if(play.getPressed() % 2 == 0)
                {

                    play.setIcon(new ImageIcon("Icons\\playEntered50.png"));
                }
                else if (play.getPressed()%2 == 1)
                {
                    play.setIcon(new ImageIcon("Icons\\pauseEntered50.png"));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if(play.getPressed() % 2 == 0)
                {
                    play.setIcon(new ImageIcon("Icons\\play50.png"));
                }
                else if (play.getPressed()%2 == 1)
                {
                    play.setIcon(new ImageIcon("Icons\\pause50.png"));
                }
            }
        });
        previousMusic.setIcon(new ImageIcon("Icons\\previous30.png"));
        previousMusic.setPreferredSize(new Dimension(75,30));
        nextMusic.setIcon(new ImageIcon("Icons\\next30.png"));
        nextMusic.setPreferredSize(new Dimension(75,30));
        repeat.setIcon(new ImageIcon("Icons\\repeat20.png"));
        repeat.setPreferredSize(new Dimension(75,30));
        shuffle.setIcon(new ImageIcon("Icons\\shuffle20.png"));
        shuffle.setPreferredSize(new Dimension(75,30));
        //
        this.setSize(new Dimension(1600,100));
        //this.add(volumeBar,BorderLayout.LINE_END);
        this.add(buttonsBar,BorderLayout.NORTH);
        this.add(musicSeekPanel,BorderLayout.CENTER);

        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Play play = new Play();
                //play.run();
                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        JFileChooser a = new JFileChooser();
                        int fasf = a.showOpenDialog(null);
                        if (fasf == JFileChooser.APPROVE_OPTION) {
                            try {
                                BufferedInputStream file = new BufferedInputStream(new FileInputStream(new File(a.getSelectedFile().getAbsolutePath())));
                                try {
                                    Player playMP3 = new Player(file);
                                    playMP3.play();
                                } catch (Exception q) {
                                    System.out.print(q);
                                }
                            } catch (Exception q) {
                                System.out.print(q);
                            }
                        }
                    }
                };
                Thread t = new Thread(r);
                t.start();
            }
        });
    }

}
