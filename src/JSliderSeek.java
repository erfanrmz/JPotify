import javax.swing.*;

public class JSliderSeek extends Thread {
    private JSlider seekSlider;
    private boolean isPause;
    private MainFrame mainFrame;

    public JSliderSeek(MainFrame mainFrame, JSlider seekSlider) {
        isPause = false;
        this.mainFrame = mainFrame;
        this.seekSlider = seekSlider;
    }

    @Override
    public void run() {
        while (true) {
            while (seekSlider.getValue() <= seekSlider.getMaximum()) {
                if (!this.isPause) {
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    seekSlider.setValue(seekSlider.getValue() + 1);
                    mainFrame.getPlayingPanel().getPlayingTime().setTime(seekSlider.getValue());
                }
            }
        }
    }
}
