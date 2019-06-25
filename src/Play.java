import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

public class Play extends Thread {
    private boolean isPause;
    private Player playMP3;
    private Song playingSong;

    public Play() {
        isPause = false;
//        playingSong = song;
    }
    public void setPlayingSong(Song song)
    {
        playingSong = song;
    }

    @Override
    public void run() {


            try {
                BufferedInputStream file = new BufferedInputStream(new FileInputStream(new File(playingSong.getAddress())));
                try {
                    playMP3 = new Player(file);
                    while (playMP3.play(1)) {
                        if (this.isPause) {
                            synchronized (playMP3) {
                                playMP3.wait();
                            }
                        }
                    }

                }
                catch (Exception q) {
                    System.out.print(q);
                }
            }
            catch (Exception q) {
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
}
