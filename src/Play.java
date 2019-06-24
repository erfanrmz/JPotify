import javazoom.jl.player.Player;

import javax.swing.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

public class Play implements Runnable{
    public Play()
    {

    }

    @Override
    public void run() {
        JFileChooser a = new JFileChooser();
        int fasf = a.showOpenDialog(null);
        if (fasf == JFileChooser.APPROVE_OPTION) {
            try {
                BufferedInputStream file = new BufferedInputStream(new FileInputStream(new File(a.getSelectedFile().getAbsolutePath())));
                try {
                    Player playMP3 = new Player(file);
                    playMP3.play();
                } catch (Exception q) {
                    System.out.print(q);
                }
            } catch (Exception q) {
                System.out.print(q);
            }
        }
    }
}
