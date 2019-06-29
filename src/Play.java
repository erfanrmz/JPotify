import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.player.advanced.AdvancedPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * This is a class for playing a music.
 *
 * @author Erfan Ramezani & Amir Mojtaba Kiasat
 * @version 1.0
 * @since 6-21-2019
 */
public class Play extends Thread {
    private MainFrame mainFrame;
    private boolean isPause;
    private AdvancedPlayer playMP3;
    private Song playingSong;
    private FileInputStream file;
    private boolean stopMusic;
    private int frame;

    public Play(int frame, MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        stopMusic = false;
        isPause = false;
//        playingSong = song;
        this.frame = frame;
    }

    public void setPlayingSong(Song song) {
        playingSong = song;
        stopMusic = false;
        try {
            file = new FileInputStream(playingSong.getAddress());
            System.out.println("music changed");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            Mp3File playingSong = new Mp3File(getPlayingSong().getAddress());
            mainFrame.getPlayingPanel().getMusicTime().setTime((int) playingSong.getLengthInSeconds());
            mainFrame.getPlayingPanel().getMusicSeek().setMaximum((int) playingSong.getLengthInSeconds());
            System.out.println((int) playingSong.getLengthInSeconds());
            System.out.println(mainFrame.getPlayingPanel().getMusicSeek().getMaximum() + " " + playingSong.getFrameCount());
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (UnsupportedTagException e1) {
            e1.printStackTrace();
        } catch (InvalidDataException e1) {
            e1.printStackTrace();
        }
        mainFrame.getLeftPanel().getMusicPlayingArtWork().setIcon(playingSong.getImageIcon());
//        while (!stopMusic) {
        mainFrame.getPlayingPanel().getPlayingSongArtist().setText(playingSong.getArtist());
        mainFrame.getPlayingPanel().getPlayingSongName().setText(playingSong.getTitle());
        mainFrame.getPlayingPanel().getPlayingSongLikeName().removeAll();
//            mainFrame.getPlayingPanel().getPlayingSongInformation().removeAll();
        mainFrame.getPlayingPanel().getPlayingSongInformation().add(mainFrame.getPlayingPanel().getPlayingSongLikeName());
//            mainFrame.getPlayingPanel().getPlayingSongInformation().add(mainFrame.getPlayingPanel().getPlayingSongArtist());
//            mainFrame.getPlayingPanel().getPlayingSongLikeName().add(mainFrame.getPlayingPanel().getPlayingSongName());
        EJButton like = new EJButton();
        like.setPreferredSize(new Dimension(25, 25));
        like.setIcon(new ImageIcon("Icons\\like25.png"));
        like.setPressed(0);
        if (playingSong.getFavorite()) {
            System.out.println("hello");
            like.setIcon(new ImageIcon("Icons\\liked25.png"));
            like.setPressed(1);

        }
        like.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    if (like.getPressed() % 2 == 0) {
                        like.setIcon(new ImageIcon("Icons\\like25.png"));
//                            mainFrame.getFavoriteSongs().remove(playingSong);

                    } else {
                        like.setIcon(new ImageIcon("Icons\\liked25.png"));
//                            mainFrame.getFavoriteSongs().add(playingSong);
                    }

                }
            }
        });
//            mainFrame.getPlayingPanel().getPlayingSongInformation().add(like);
//            mainFrame.getPlayingPanel().getPlayingSongLikeName().setLayout(new FlowLayout(FlowLayout.LEFT));
        try {
            playMP3 = new AdvancedPlayer(file);
            playMP3.play(frame, frame + 1);
            while (playMP3.play(1)) {
                if (this.isPause) {
                    synchronized (playMP3) {
                        playMP3.wait();
                    }
                }
            }
            System.out.println("END");
        } catch (Exception q) {
            System.out.print(q);
        }
    }

    public void mp3Pause() {
        this.isPause = true;
    }

    public void mp3Play() {
        this.isPause = false;
    }

    public void mp3Resume() {
        this.isPause = false;
        try {
            synchronized (playMP3) {
                playMP3.notifyAll();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void stopMusic() {
        try {
            stopMusic = true;
            System.out.println("stop music true");

        } catch (Exception e) {
            System.out.println("fuck off :D");
        }

    }

    public Song getPlayingSong() {
        return playingSong;
    }

}
