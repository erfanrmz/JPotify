import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Play extends Thread {
    private boolean isPause;
    private AdvancedPlayer playMP3;
    private Song playingSong;
    private FileInputStream file;
    private boolean stopMusic;
    private int frame;

    public Play(int frame) {
        stopMusic = false;
        isPause = false;
//        playingSong = song;
        this.frame =frame;
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
//        while (!stopMusic) {
            try {
                playMP3 = new AdvancedPlayer(file);
                playMP3.play(frame,frame+1);
                while (playMP3.play(1)) {
                    if (this.isPause) {
                        synchronized (playMP3) {
                            playMP3.wait();
                        }
                    }
                }
                System.out.println("END");
            }
            //JFileChooser a = new JFileChooser();
            //int fasf = a.showOpenDialog(null);
            //if (fasf == JFileChooser.APPROVE_OPTION) {
            catch (Exception q) {
                System.out.print(q);
            }

//        }
        playMP3.close();

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
    //    public void mp3seek(int pos) throws JavaLayerException {
//        playMP3.close();
//        playMP3 = new AdvancedPlayer(file);
//        playMP3.play(pos,1000);
//    }
}
