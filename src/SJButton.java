import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

public class SJButton extends JButton {
    private Song song;
    private Play player;
    private Play[] player2;
    private int press;
    private MainFrame mainFrame;
    private ArrayList<Play> playingThreads;
    private JPopupMenu popupMenu;
    private JMenuItem addToPlayList;

    public Play getPlayer() {
        return player;
    }

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
                    mainFrame.getLeftPanel().getMusicPlayingArtWork().setIcon(song.getImageIcon());

                    try {
                        for (int i = 0; i < playingThreads.size(); i++) {
                            playingThreads.get(i).stop();
                        }

                        System.out.println("stopped");
                    } catch (Exception e1) {
                        System.out.println("SHIT");
                    }
                    SJButton.this.player = new Play();
                    SJButton.this.player.setPlayingSong(song);
                    SJButton.this.player.start();
                    playingThreads.add(SJButton.this.player);


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
}
