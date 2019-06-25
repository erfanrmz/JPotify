import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MPlayer {
    AdvancedPlayer player;
    private static int pausedOnFrame = 0;
    private byte[] decrypted = null;
    private long audioLength;
    private AudioInputStream stream;

    private InputStream bytesToStream(byte[] in) {
        InputStream is = new ByteArrayInputStream(in);
        return is;
    }

    public MPlayer(String fname) throws IOException, UnsupportedAudioFileException, JavaLayerException {
        /* here file is encrypted to variable byte[] decrypted and then: */
        InputStream is = bytesToStream(decrypted);
        stream = AudioSystem.getAudioInputStream(is);
        audioLength = stream.getFrameLength();

        player = new AdvancedPlayer(stream);
        player.setPlayBackListener(new PlaybackListener() {
            @Override
            public void playbackFinished(PlaybackEvent event) {
                System.err.println(event.getFrame());
                pausedOnFrame = event.getFrame();
            }

        });
    }

    public void play() throws Exception {
        Thread th = new Thread() {
            public void run() {
                try {
                    player.play(MPlayer.pausedOnFrame, Integer.MAX_VALUE);
                } catch (JavaLayerException e) {
                    e.printStackTrace();
                }
            }
        };
        th.start();
    }

    public void fastforward() throws Exception {
        pausemusic();
        long nextFrame = (long) (pausedOnFrame + 0.02 * audioLength);
        if (nextFrame < audioLength)
            play();
    }

    public void rewind() throws Exception {
        pausemusic();
        long nextFrame = (long) (pausedOnFrame - 0.02 * audioLength);
        if (nextFrame > 0)
            play();
    }


    public void pausemusic() throws LineUnavailableException {
        player.stop();
    }

    public void stopmusic() throws LineUnavailableException {
        player.stop();
        pausedOnFrame = 0;
    }
}