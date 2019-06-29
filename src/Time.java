import javax.swing.*;
import java.awt.*;

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
