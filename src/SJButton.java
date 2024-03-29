import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This is a button for the musics in the main(song) & album panel.
 *
 * @author Erfan Ramezani & Amir Mojtaba Kiasat
 * @version 1.0
 * @since 6-21-2019
 */
public class SJButton extends JButton {
    private Song song;
    private Play player;
    private Play[] player2;
    private int press;
    private MainFrame mainFrame;
    private ArrayList<Play> playingThreads;
    private JPopupMenu popupMenu;
    private JMenuItem addToPlayList;

    public SJButton(String text, Icon icon, Song song, Play player, MainFrame mainFrame, ArrayList<Play> playingThreads) {
        super(text, icon);
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
        popupMenu.add(addToPlayList);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    System.out.println(song.toString());
                    song.isFavorite();
                    mainFrame.getPlayingPanel().getPlayingTime().setTime(0);
                    mainFrame.getPlayingPanel().getMusicSeek().setValue(0);
                    for (int i = 0; i < mainFrame.getJSliderSeeks().size(); i++) {
                        mainFrame.getJSliderSeeks().get(i).stop();
                    }
                    JSliderSeek jSliderSeek = new JSliderSeek(mainFrame, mainFrame.getPlayingPanel().getMusicSeek());
                    mainFrame.getJSliderSeeks().add(jSliderSeek);
                    jSliderSeek.start();
//<<<<<<< HEAD
//=======
//                mainFrame.getLeftPanel().getMusicPlayingArtWork().setIcon(SJButton.this.song.getImageIcon());
//>>>>>>> Recently_played
                    mainFrame.getPlayingPanel().getPlay().setIcon(new ImageIcon("Icons\\pause50.png"));
                    mainFrame.getPlayingPanel().getPlay().setPressed(1);
                    try {
                        for (int i = 0; i < playingThreads.size(); i++) {
                            playingThreads.get(i).stop();
                        }
                        System.out.println("stopped");
                    } catch (Exception e1) {
                        System.out.println("SHIT");
                    }
//<<<<<<< HEAD

                    SJButton.this.player = new Play(0, mainFrame);
//                    SJButton.this.player.setPlayingSong(song);
//=======
//                    try {
//                        Mp3File playingSong = new Mp3File(SJButton.this.song.getAddress());
//                        mainFrame.getPlayingPanel().getMusicSeek().setMaximum((int) playingSong.getLengthInSeconds());
//                        System.out.println(mainFrame.getPlayingPanel().getMusicSeek().getMaximum() + " " + playingSong.getFrameCount());
//                    } catch (IOException e1) {
//                        e1.printStackTrace();
//                    } catch (UnsupportedTagException e1) {
//                        e1.printStackTrace();
//                    } catch (InvalidDataException e1) {
//                        e1.printStackTrace();
//                    }
//                    SJButton.this.player = new Play(0);
                    SJButton.this.player.setPlayingSong(SJButton.this.song);
//>>>>>>> Recently_played
                    SJButton.this.player.start();
                    playingThreads.add(SJButton.this.player);

                    int numOfSong = 0;
                    for (int i = 0; i < mainFrame.getSongs().size(); i++)
                        if (SJButton.this.song.getAddress().equals(mainFrame.getSongs().get(i).getAddress()))
                            numOfSong = i;
                    for (int j = 0; j < numOfSong; j++)
                        Collections.swap(mainFrame.getSongs(), j, numOfSong);
                    if (!mainFrame.getInAlbum())
                        ((MainPanel) mainFrame.getSongPanel()).modifyPanel();

                    int numOfAlbum = 0;
                    for (int i = 0; i < mainFrame.getAlbums().size(); i++)
                        if (SJButton.this.song.getAlbum().equals(mainFrame.getAlbums().get(i).getName()))
                            numOfAlbum = i;
                    for (int j = 0; j < numOfAlbum; j++)
                        Collections.swap(mainFrame.getAlbums(), j, numOfAlbum);
                    mainFrame.getAlbumsPanel().updateAlbums();
                }

                if (e.getButton() == MouseEvent.BUTTON3) {
                    popupMenu.show(SJButton.this, e.getX(), e.getY());
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
