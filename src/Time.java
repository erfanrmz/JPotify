import javax.swing.*;
import java.awt.*;

/**
 * This is a class for showing time.
 *
 * @author Erfan Ramezani & Amir Mojtaba Kiasat
 * @version 1.0
 * @since 6-21-2019
 */
public class Time extends JLabel {
    private int min;
    private int sec;

    public Time(int sec) {
        min = sec / 60;
        this.sec = sec - (min * 60);
        if (min < 10 && this.sec < 10)
            this.setText("0" + min + ":" + "0" + this.sec);
        else if (min < 10 && this.sec >= 10)
            this.setText("0" + min + ":" + this.sec);
        else if (min >= 10 && this.sec < 10)
            this.setText(min + ":" + "0" + this.sec);
        else if (min >= 10 && this.sec >= 10)
            this.setText(min + ":" + this.sec);
        this.setForeground(Color.WHITE);
    }

    public void setTime(int sec) {
        min = sec / 60;
        this.sec = sec - (min * 60);
        if (min < 10 && this.sec < 10)
            this.setText("0" + min + ":" + "0" + this.sec);
        else if (min < 10 && this.sec >= 10)
            this.setText("0" + min + ":" + this.sec);
        else if (min >= 10 && this.sec < 10)
            this.setText(min + ":" + "0" + this.sec);
        else if (min >= 10 && this.sec >= 10)
            this.setText(min + ":" + this.sec);

    }

    public int getSec() {
        return sec;
    }
}
