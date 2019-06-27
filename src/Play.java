import com.mpatric.mp3agic.Mp3File;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Play extends Thread {
    private boolean isPause;
    private Player playMP3;
    private Song playingSong;
    private BufferedInputStream file;
    private boolean stopMusic;

    public Play() {
        stopMusic = false;
        isPause = false;
//        playingSong = song;

    }

    public void setPlayingSong(Song song) {
        playingSong = song;
        stopMusic = false;
        try {
            file = new BufferedInputStream(new FileInputStream(new File(playingSong.getAddress())));
            System.out.println("music changed");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        while (!stopMusic)
        {
            try {
                playMP3 = new Player(file);
                while (playMP3.play(1 ) && !stopMusic ) {
                    if (this.isPause) {
                        synchronized (playMP3) {
                            playMP3.wait();
                        }
                    }
                }

            }
            //JFileChooser a = new JFileChooser();
            //int fasf = a.showOpenDialog(null);
            //if (fasf == JFileChooser.APPROVE_OPTION) {
            catch (Exception q) {
                System.out.print(q);
            }

        }
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

        }catch (Exception e)
        {
            System.out.println("fuck off :D");
        }

    }
//    public void mp3seek(int pos) throws JavaLayerException {
//        playMP3.close();
//        playMP3 = new AdvancedPlayer(file);
//        playMP3.play(pos,1000);
//    }
}
