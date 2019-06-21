import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
class EJSlider extends JSlider {

    public EJSlider() {
        super(0,100,100);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Point p = e.getPoint();
                double percent = p.x / ((double) getWidth());
                int range = getMaximum() - getMinimum();
                double newVal = range * percent;
                int result = (int)(getMinimum() + newVal);
                setValue(result);
            }
        });
        this.setOpaque(false);
    }
}