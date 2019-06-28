
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


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
    private Play player;
    private ArrayList<Play> playingThreads;
    private MainFrame mainFrame;

    public PlayingPanel(Play player, ArrayList<Play> playingThreads , MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.playingThreads = playingThreads;
        this.player = player;
        this.setPreferredSize(new Dimension(1600, 100));
        this.setLayout(new BorderLayout());
        play = new EJButton();
        nextMusic = new EJButton();
        previousMusic = new EJButton();
        repeat = new EJButton();
        shuffle = new EJButton();
        musicSeekPanel = new JPanel();
        buttonsBar = new JPanel();
        volumeBar = new JPanel();
        volume = new EJSlider(0, 100, 100);
        musicSeek = new EJSlider(0, 100, 0);
        volume.setPreferredSize(new Dimension(100, 10));
        musicSeek.setPreferredSize(new Dimension(600, 10));
        musicSeekPanel.add(musicSeek);
        //buttons bar
        buttonsBar.add(shuffle);
        buttonsBar.add(previousMusic);
        buttonsBar.add(play);
        buttonsBar.add(nextMusic);
        buttonsBar.add(repeat);
        buttonsBar.setLayout(new FlowLayout(FlowLayout.CENTER));
        //volume Bar
        volumeBar.setLayout(new GridLayout(3, 1));
        volumeBar.add(volume);

        this.setBackground(new Color(40, 40, 40));
        buttonsBar.setBackground(new Color(40, 40, 40));
        volumeBar.setBackground(new Color(40, 40, 40));
        musicSeekPanel.setBackground(new Color(40, 40, 40));
        //icons
        play.setIcon(new ImageIcon("Icons\\play50.png"));
        play.setPreferredSize(new Dimension(75, 50));
        play.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (play.getPressed() % 2 == 0) {

                    play.setIcon(new ImageIcon("Icons\\playEntered50.png"));
                    playingThreads.get(playingThreads.size() - 1).mp3Pause();
                    mainFrame.getjSliderSeeks().get(mainFrame.getjSliderSeeks().size()-1).stop();

                } else if (play.getPressed() % 2 == 1) {
                    play.setIcon(new ImageIcon("Icons\\pauseEntered50.png"));
                    playingThreads.get(playingThreads.size() - 1).mp3Resume();
                    JSliderSeek jSliderSeek = new JSliderSeek(mainFrame.getPlayingPanel().getMusicSeek());
                    jSliderSeek.start();
                    mainFrame.getjSliderSeeks().add(jSliderSeek);
                }

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (play.getPressed() % 2 == 0) {

                    play.setIcon(new ImageIcon("Icons\\playEntered50.png"));
                } else if (play.getPressed() % 2 == 1) {
                    play.setIcon(new ImageIcon("Icons\\pauseEntered50.png"));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (play.getPressed() % 2 == 0) {
                    play.setIcon(new ImageIcon("Icons\\play50.png"));

                } else if (play.getPressed() % 2 == 1) {
                    play.setIcon(new ImageIcon("Icons\\pause50.png"));
                }
            }
        });
//        musicSeek.addChangeListener(new ChangeListener() {
//            @Override
//            public void stateChanged(ChangeEvent e) {
//                Song song = playingThreads.get(playingThreads.size()-1).getPlayingSong();
//                int size = playingThreads.size();
//                for (int i = 0 ; i < size;i++)
//                {
//                    playingThreads.get(i).stop();
//                    System.out.println("fuck stop");
//                }
//                int frame = musicSeek.getValue()*38;
//                Play player = new Play(frame);
//                playingThreads.add(player);
//                player.setPlayingSong(song);
//                player.start();
//            }
//        });
        musicSeek.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                Song song = playingThreads.get(playingThreads.size()-1).getPlayingSong();
                int size = playingThreads.size();
                for (int i = 0 ; i < size;i++)
                {
                    playingThreads.get(i).stop();
                    System.out.println("fuck stop");
                }
                int frame = musicSeek.getValue()*38;
                Play player = new Play(frame);
                playingThreads.add(player);
                player.setPlayingSong(song);
                player.start();
            }

        });
        previousMusic.setIcon(new ImageIcon("Icons\\previous30.png"));
        previousMusic.setPreferredSize(new Dimension(75, 30));
        nextMusic.setIcon(new ImageIcon("Icons\\next30.png"));
        nextMusic.setPreferredSize(new Dimension(75, 30));
        repeat.setIcon(new ImageIcon("Icons\\repeat20.png"));
        repeat.setPreferredSize(new Dimension(75, 30));
        shuffle.setIcon(new ImageIcon("Icons\\shuffle20.png"));
        shuffle.setPreferredSize(new Dimension(75, 30));
        //
        this.setSize(new Dimension(1600, 100));
        this.add(volumeBar, BorderLayout.LINE_END);
        this.add(buttonsBar, BorderLayout.NORTH);
        this.add(musicSeekPanel, BorderLayout.CENTER);

        volume.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Audio.setMasterOutputVolume((float) volume.getValue() / 100);
            }
        });
        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //    Play play = new Play();
                //    play.start();
            }
        });
//        musicSeek.addAncestorListener(new AncestorListener() {
//            @Override
//            public void ancestorAdded(AncestorEvent event) {
//                if (mp3player != null && !musicSeek.getValueIsAdjusting()) {
//                    int dAngle = musicSeek.getValue();
//                    try {
//                        mp3player.mp3seek(dAngle);
//                    } catch (JavaLayerException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//            @Override
//            public void ancestorRemoved(AncestorEvent event) {
//                if (mp3player != null && !musicSeek.getValueIsAdjusting()) {
//                    int dAngle = musicSeek.getValue();
//                    try {
//                        mp3player.mp3seek(dAngle);
//                    } catch (JavaLayerException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//            @Override
//            public void ancestorMoved(AncestorEvent event) {
//
//            }
//        });
    }

    public EJSlider getMusicSeek() {
        return musicSeek;
    }

    public EJButton getPlay() {
        return play;
    }
}