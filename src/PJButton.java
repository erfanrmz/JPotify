import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

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

    public Play getPlayer() {
        return player;
    }

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
                    mainFrame.getLeftPanel().getMusicPlayingArtWork().setIcon(song.getImageIcon());

                    try {
                        for (int i = 0; i < playingThreads.size(); i++) {
                            playingThreads.get(i).stop();
                        }

                        System.out.println("stopped");
                    } catch (Exception e1) {
                        System.out.println("SHIT");
                    }
                    PJButton.this.player = new Play();
                    PJButton.this.player.setPlayingSong(song);
                    PJButton.this.player.start();
                    playingThreads.add(PJButton.this.player);


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
                                mainFrame.getLeftPanel().getPlaylists().get(finalI).addsongFromButton(song);
                            } catch (IOException ex) {
                            }
                            playlistFrame.dispose();
                        }
                    });
                }
            }
        });
    }
}

