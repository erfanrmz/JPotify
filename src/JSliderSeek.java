import javax.swing.*;

public class JSliderSeek extends Thread{
    private JSlider seekslider;
    private boolean isPause;
    public JSliderSeek(JSlider seekSlider)
    {
        isPause = false;
        this.seekslider = seekSlider;
    }
    @Override
    public void run() {
        while(true)
        {
            while(seekslider.getValue() <= seekslider.getMaximum())
            {
                if (!this.isPause) {
                    seekslider.setValue(seekslider.getValue() + 1);
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }



    }



}
