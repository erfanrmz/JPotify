import com.mpatric.mp3agic.Mp3File;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

public class Play extends Thread {
    private boolean isPause;
    private Player playMP3;
    private BufferedInputStream file;

    public Play() {
        isPause = false;
    }

    @Override
    public void run() {
        //JFileChooser a = new JFileChooser();
        //int fasf = a.showOpenDialog(null);
        //if (fasf == JFileChooser.APPROVE_OPTION) {
        try {
            Mp3File f = new Mp3File("D:\\Musics\\owl City feat. Hanson - Unbelievable.mp3");
            file = new BufferedInputStream(new FileInputStream(new File("D:\\Musics\\owl City feat. Hanson - Unbelievable.mp3")));
            try {
                playMP3 = new Player(file);
                while (playMP3.play(1)) {
                    if (this.isPause) {
                        System.out.println(playMP3.getPosition());
                        synchronized (playMP3) {
                            playMP3.wait();
                        }
                    }
                }

            } catch (Exception q) {
                System.out.print(q);
            }
        } catch (Exception q) {
            System.out.print(q);
        }
        //}
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

    public void mp3seek(int pos) throws JavaLayerException {
//        playMP3.close();
//        playMP3 = new AdvancedPlayer(file);
//        playMP3.play(pos,1000);
    }
}
