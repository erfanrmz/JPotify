import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This is a button for the musics in a playlist list.
 *
 * @author Erfan Ramezani & Amir Mojtaba Kiasat
 * @version 1.0
 * @since 6-21-2019
 */
public class PJButton extends JButton {
    private Song song;
    private Play player;
    private Play[] player2;
    private int press;
    private MainFrame mainFrame;
    private ArrayList<Play> playingThreads;
    private JPopupMenu popupMenu;
    private JMenuItem addToPlayList;
    private JMenuItem removeSong;
    private Playlist playlist;

    public PJButton(String text, Icon icon, Song song, Play player, MainFrame mainFrame, ArrayList<Play> playingThreads, Playlist playlist) {
        super(text, icon);
        this.playlist = playlist;
        this.song = song;
        this.setSize(new Dimension(260, 260));
        press = 0;
        this.mainFrame = mainFrame;
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setVerticalTextPosition(SJButton.BOTTOM);
        this.setHorizontalTextPosition(SJButton.CENTER);
        this.setForeground(Color.white);
        this.setFont(new Font("", Font.BOLD, 14));
        this.player = player;
        player2 = new Play[2];
        this.playingThreads = playingThreads;
        popupMenu = new JPopupMenu("Add to Playlist");
        addToPlayList = new JMenuItem("Add To Playlist");
        removeSong = new JMenuItem("Remove Song");
        popupMenu.add(addToPlayList);
        popupMenu.add(removeSong);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    mainFrame.getPlayingPanel().getPlayingTime().setTime(0);
                    mainFrame.getPlayingPanel().getMusicSeek().setValue(0);
                    for (int i = 0; i < mainFrame.getJSliderSeeks().size(); i++) {
                        mainFrame.getJSliderSeeks().get(i).stop();
                    }
                    JSliderSeek jSliderSeek = new JSliderSeek(mainFrame, mainFrame.getPlayingPanel().getMusicSeek());
                    mainFrame.getJSliderSeeks().add(jSliderSeek);
                    jSliderSeek.start();
//                    mainFrame.getLeftPanel().getMusicPlayingArtWork().setIcon(song.getImageIcon());
                    mainFrame.getPlayingPanel().getPlay().setIcon(new ImageIcon("Icons\\pause50.png"));
                    mainFrame.getPlayingPanel().getPlay().setPressed(1);
                    try {
                        for (int i = 0; i < playingThreads.size(); i++) {
                            playingThreads.get(i).stop();
                        }
                        System.out.println("stopped");
                    } catch (Exception e1) {
                    }
//                    try {
//                        Mp3File playingSong = new Mp3File(song.getAddress());
//                        mainFrame.getPlayingPanel().getMusicTime().setTime((int)playingSong.getLengthInSeconds());
//                        mainFrame.getPlayingPanel().getMusicSeek().setMaximum((int)playingSong.getLengthInSeconds());
//                        System.out.println((int)playingSong.getLengthInSeconds());
//                        System.out.println( mainFrame.getPlayingPanel().getMusicSeek().getMaximum() + " "  + playingSong.getFrameCount());
//                    } catch (IOException e1) {
//                        e1.printStackTrace();
//                    } catch (UnsupportedTagException e1) {
//                        e1.printStackTrace();
//                    } catch (InvalidDataException e1) {
//                        e1.printStackTrace();
//                    }
                    PJButton.this.player = new Play(0, mainFrame);
                    PJButton.this.player.setPlayingSong(song);
                    PJButton.this.player.start();
                    playingThreads.add(PJButton.this.player);
                    ((MainPanel) mainFrame.getSongPanel()).RecentlyPlayed(PJButton.this.song);

                }
                if (e.getButton() == MouseEvent.BUTTON3) {
                    popupMenu.show(PJButton.this, e.getX(), e.getY());
                }
            }
        });
        removeSong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    playlist.removeSong(song);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        addToPlayList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame playlistFrame = new JFrame();
                playlistFrame.setLayout(new FlowLayout());
                playlistFrame.setBounds(700, 400, 500, 500);
                playlistFrame.setVisible(true);
                for (int i = 0; i < mainFrame.getLeftPanel().getPlaylists().size(); i++) {
                    JButton playlistBut = new JButton(mainFrame.getLeftPanel().getPlaylists().get(i).getName());
                    playlistFrame.add(playlistBut);
                    int finalI = i;
                    playlistBut.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                mainFrame.getLeftPanel().getPlaylists().get(finalI).addSongFromButton(song);
                            } catch (IOException ex) {

                            }
                            playlistFrame.dispose();
                        }
                    });
                }
            }
        });
    }

    public Play getPlayer() {
        return player;
    }

}

