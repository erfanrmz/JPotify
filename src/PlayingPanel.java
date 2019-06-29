
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
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
    private JPanel playingSongInformation;
    private JPanel playingSongLikeName;
    private JLabel playingSongArtist;
    private JLabel playingSongName;
    private Play player;
    private ArrayList<Play> playingThreads;
    private MainFrame mainFrame;
    private Time playingTime;
    private Time musicTime;

    public PlayingPanel(Play player, ArrayList<Play> playingThreads , MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.playingThreads = playingThreads;
        this.player = player;
        this.setPreferredSize(new Dimension(1600, 100));
        this.setLayout(new BorderLayout());
        playingTime = new Time(0);
        musicTime = new Time(0);
        play = new EJButton();
        nextMusic = new EJButton();
        previousMusic = new EJButton();
        repeat = new EJButton();
        shuffle = new EJButton();
        playingSongArtist = new JLabel();
        playingSongName = new JLabel();
        playingSongLikeName = new JPanel();
        playingSongInformation = new JPanel();
        playingSongInformation.setPreferredSize(new Dimension(200,100));
        playingSongInformation.setMaximumSize(new Dimension(200,100));
        playingSongInformation.setMinimumSize(new Dimension(200,100));
        playingSongInformation.setLayout(new BoxLayout(playingSongInformation,BoxLayout.Y_AXIS));
        playingSongInformation.add(playingSongName);
        playingSongInformation.add(playingSongArtist);
        playingSongName.setFont(new Font("",Font.BOLD,18));
        playingSongArtist.setFont(new Font("",Font.BOLD,18));
        playingSongName.setForeground(new Color(179,179,179));
        playingSongArtist.setForeground(Color.WHITE);
        musicSeekPanel = new JPanel();
        buttonsBar = new JPanel();
        volumeBar = new JPanel();
        volume = new EJSlider(0, 100, 100);
        musicSeek = new EJSlider(0, 100, 0);
        volume.setPreferredSize(new Dimension(100, 10));
        musicSeek.setPreferredSize(new Dimension(600, 10));
        musicSeekPanel.add(playingTime);
        musicSeekPanel.add(musicSeek);
        musicSeekPanel.add(musicTime);
        musicSeekPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        //buttons bar
        buttonsBar.add(shuffle);
        buttonsBar.add(previousMusic);
        buttonsBar.add(play);
        buttonsBar.add(nextMusic);
        buttonsBar.add(repeat);
        buttonsBar.setLayout(new FlowLayout(FlowLayout.CENTER));
        //volume Bar
        JPanel volumeBar1 = new JPanel();
        volumeBar1.setPreferredSize(new Dimension(100,20));
        volumeBar1.setMaximumSize(new Dimension(100,20));
        volumeBar1.setMinimumSize(new Dimension(100,20));
        volumeBar1.add(volume);
        volumeBar.setPreferredSize(new Dimension(200,100));
        volumeBar.setMaximumSize(new Dimension(200,100));
        volumeBar.setMinimumSize(new Dimension(200,100));
        volumeBar.setLayout(new GridLayout(3, 1));
        volumeBar.add(new EJButton());
        volumeBar.add(volumeBar1);
        //color
        Color playingPanelColor = new Color(40,40,40);
        this.setBackground(playingPanelColor);
        buttonsBar.setBackground(playingPanelColor);
        volumeBar.setBackground(playingPanelColor);
        volumeBar1.setBackground(playingPanelColor);
        musicSeekPanel.setBackground(playingPanelColor);
        playingSongInformation.setBackground(playingPanelColor);
        playingSongLikeName.setBackground(playingPanelColor);
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
                    JSliderSeek jSliderSeek = new JSliderSeek(mainFrame,mainFrame.getPlayingPanel().getMusicSeek());
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
                mainFrame.getPlayingPanel().getPlayingTime().setTime(musicSeek.getValue());
                int frame = musicSeek.getValue()*38;
                Play player = new Play(frame,mainFrame);
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
        JPanel ButtonsAndSeekPanel = new JPanel();
        ButtonsAndSeekPanel.setBackground(playingPanelColor);
        ButtonsAndSeekPanel.setLayout(new BoxLayout(ButtonsAndSeekPanel,BoxLayout.Y_AXIS));
        ButtonsAndSeekPanel.add(buttonsBar);
        ButtonsAndSeekPanel.add(musicSeekPanel);

        this.setSize(new Dimension(1600, 100));
        this.add(volumeBar, BorderLayout.LINE_END);
//        this.add(buttonsBar, BorderLayout.NORTH);
        this.add(ButtonsAndSeekPanel, BorderLayout.CENTER);
        this.add(playingSongInformation,BorderLayout.LINE_START);

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
        nextMusic.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1)
                {
                   Song playing = mainFrame.getPlayingThreads().get(mainFrame.getPlayingThreads().size()-1).getPlayingSong();
                    int size = playingThreads.size();
                    for (int i = 0 ; i < size;i++)
                    {
                        playingThreads.get(i).stop();
                    }
                    mainFrame.getPlayingPanel().getPlayingTime().setTime(0);
                    mainFrame.getPlayingPanel().getMusicSeek().setValue(0);
                    for (int i = 0 ; i < mainFrame.getjSliderSeeks().size() ;i++)
                    {
                        mainFrame.getjSliderSeeks().get(i).stop();
                    }
                    JSliderSeek jSliderSeek = new JSliderSeek(mainFrame,mainFrame.getPlayingPanel().getMusicSeek());
                    mainFrame.getjSliderSeeks().add(jSliderSeek);
                    jSliderSeek.start();

                   for (int i = 0 ; i<mainFrame.getPlaylistPlaying().size();i++)
                   {
                       if (playing.getTitle().equals(mainFrame.getPlaylistPlaying().get(i).getTitle()))
                       {
                           if (i+1 == mainFrame.getPlaylistPlaying().size())
                           {
                               playing = mainFrame.getPlaylistPlaying().get(0);
                               break;
                           }
                           else
                           {
                               playing = mainFrame.getPlaylistPlaying().get(i+1);
                               break;
                           }
                       }
                   }

                    Play player = new Play(0,mainFrame);
                    playingThreads.add(player);
                    player.setPlayingSong(playing);
                    player.start();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                nextMusic.setIcon(new ImageIcon("Icons\\nextEntered30.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                nextMusic.setIcon(new ImageIcon("Icons\\next30.png"));
            }
        });
        previousMusic.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                previousMusic.setIcon(new ImageIcon("Icons\\previousEntered30.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                previousMusic.setIcon(new ImageIcon("Icons\\previous30.png"));
            }

            @Override
            public void mouseClicked(MouseEvent e) {

                if (e.getButton() == MouseEvent.BUTTON1)
                {
                    Song playing = mainFrame.getPlayingThreads().get(mainFrame.getPlayingThreads().size()-1).getPlayingSong();
                    int size = playingThreads.size();
                    for (int i = 0 ; i < size;i++)
                    {
                        playingThreads.get(i).stop();
                    }
                    mainFrame.getPlayingPanel().getPlayingTime().setTime(0);
                    mainFrame.getPlayingPanel().getMusicSeek().setValue(0);
                    for (int i = 0 ; i < mainFrame.getjSliderSeeks().size() ;i++)
                    {
                        mainFrame.getjSliderSeeks().get(i).stop();
                    }
                    JSliderSeek jSliderSeek = new JSliderSeek(mainFrame,mainFrame.getPlayingPanel().getMusicSeek());
                    mainFrame.getjSliderSeeks().add(jSliderSeek);
                    jSliderSeek.start();

                    for (int i = 0 ; i<mainFrame.getPlaylistPlaying().size();i++)
                    {
                        if (playing.getTitle().equals(mainFrame.getPlaylistPlaying().get(i).getTitle()))
                        {
                            if (i == 0)
                            {
                                playing = mainFrame.getPlaylistPlaying().get(mainFrame.getPlaylistPlaying().size()-1);
                                break;
                            }
                            else
                            {
                                playing = mainFrame.getPlaylistPlaying().get(i-1);
                                break;
                            }
                        }
                    }
                    Play player = new Play(0,mainFrame);
                    playingThreads.add(player);
                    player.setPlayingSong(playing);
                    player.start();
                }
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

    public Time getPlayingTime() {
        return playingTime;
    }

    public Time getMusicTime() {
        return musicTime;
    }

    public JLabel getPlayingSongArtist() {
        return playingSongArtist;
    }

    public JLabel getPlayingSongName() {
        return playingSongName;
    }

    public JPanel getPlayingSongLikeName() {
        return playingSongLikeName;
    }

    public JPanel getPlayingSongInformation() {
        return playingSongInformation;
    }
}