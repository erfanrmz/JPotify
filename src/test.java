import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.swing.*;
import java.io.IOException;

public class test {
    public static void main(String arg[]){
        Song s = null;
        try {
            s = new Song("D:\\Musics\\Amir Tataloo - Shab Ke Mishe [320].mp3");
        } catch (IOException | UnsupportedTagException | InvalidDataException e) {
            e.printStackTrace();
        }
        System.out.println(s);
        JFrame frame = new JFrame();
        frame.setSize(500,500);
        JButton but = new JButton();
        but.setIcon(s.getImageIcon());
        frame.add(but);
        frame.setVisible(true);
    }
}
