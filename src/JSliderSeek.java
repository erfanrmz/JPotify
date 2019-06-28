import javax.swing.*;

public class JSliderSeek extends Thread{
    private JSlider seekslider;
    private boolean isPause;
    private MainFrame mainFrame;
    public JSliderSeek(MainFrame mainFrame,JSlider seekSlider)
    {
        isPause = false;
        this.mainFrame = mainFrame;
        this.seekslider = seekSlider;
    }
    @Override
    public void run() {
        while(true)
        {
            while(seekslider.getValue() <= seekslider.getMaximum())
            {
                if (!this.isPause) {
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                   ;
                    seekslider.setValue(seekslider.getValue() + 1);
                    mainFrame.getPlayingPanel().getPlayingTime().setTime(seekslider.getValue());
                }
            }
        }



    }



}
