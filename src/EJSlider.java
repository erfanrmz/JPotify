import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * This is a class for making a JSlider look cooler! :)
 *
 * @author Erfan Ramezani & Amir Mojtaba Kiasat
 * @version 1.0
 * @since 6-21-2019
 */
class EJSlider extends JSlider {


    private UIDefaults d;
    private UIDefaults z;

    public EJSlider(int min, int max, int value) {
        super(min, max, value);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Point p = e.getPoint();
                double percent = p.x / ((double) getWidth());
                int range = getMaximum() - getMinimum();
                double newVal = range * percent;
                int result = (int) (getMinimum() + newVal);
                setValue(result);
            }
        });
        this.setOpaque(false);
        d = new UIDefaults();
        d.put("Slider:SliderTrack[Enabled].backgroundPainter", new Painter<EJSlider>() {
            @Override
            public void paint(Graphics2D g, EJSlider c, int w, int h) {
                int arc = 10;
                int trackHeight = 8;
                int trackWidth = w - 2;
                int fillTop = 4;
                int fillLeft = 1;

                g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g.setStroke(new BasicStroke(1.5f));
                g.setColor(Color.GRAY);
                g.fillRoundRect(fillLeft, fillTop, trackWidth, trackHeight, arc, arc);

                int fillBottom = fillTop + trackHeight;
                int fillRight = xPositionForValue(
                        c.getValue(), c,
                        new Rectangle(fillLeft, fillTop, trackWidth, fillBottom - fillTop));

                g.setColor(new Color(30, 215, 96));
                g.fillRect(fillLeft + 1, fillTop + 1, fillRight - fillLeft, fillBottom - fillTop);

                g.setColor(Color.GRAY);
                g.drawRoundRect(fillLeft, fillTop, trackWidth, trackHeight, arc, arc);
            }

            //@see javax/swing/plaf/basic/BasicSliderUI#xPositionForValue(int value)
            protected int xPositionForValue(int value, EJSlider slider, Rectangle trackRect) {
                int min = slider.getMinimum();
                int max = slider.getMaximum();
                int trackLength = trackRect.width;
                double valueRange = (double) max - (double) min;
                double pixelsPerValue = (double) trackLength / valueRange;
                int trackLeft = trackRect.x;
                int trackRight = trackRect.x + (trackRect.width - 1);
                int xPosition;

                xPosition = trackLeft;
                xPosition += Math.round(pixelsPerValue * ((double) value - min));

                xPosition = Math.max(trackLeft, xPosition);
                xPosition = Math.min(trackRight, xPosition);

                return xPosition;
            }
        });
        //
        //
        //
        //
        //
        //
        //
        //
        z = new UIDefaults();
        z.put("Slider:SliderTrack[Enabled].backgroundPainter", new Painter<EJSlider>() {
            @Override
            public void paint(Graphics2D g, EJSlider c, int w, int h) {
                int arc = 10;
                int trackHeight = 8;
                int trackWidth = w - 2;
                int fillTop = 4;
                int fillLeft = 1;

                g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                g.setStroke(new BasicStroke(1.5f));
                g.setColor(Color.GRAY);
                g.fillRoundRect(fillLeft, fillTop, trackWidth, trackHeight, arc, arc);

                int fillBottom = fillTop + trackHeight;
                int fillRight = xPositionForValue(
                        c.getValue(), c,
                        new Rectangle(fillLeft, fillTop, trackWidth, fillBottom - fillTop));

                g.setColor(new Color(179, 179, 179));
                g.fillRect(fillLeft + 1, fillTop + 1, fillRight - fillLeft, fillBottom - fillTop);

                g.setColor(Color.GRAY);
                g.drawRoundRect(fillLeft, fillTop, trackWidth, trackHeight, arc, arc);
            }

            //@see javax/swing/plaf/basic/BasicSliderUI#xPositionForValue(int value)
            protected int xPositionForValue(int value, EJSlider slider, Rectangle trackRect) {
                int min = slider.getMinimum();
                int max = slider.getMaximum();
                int trackLength = trackRect.width;
                double valueRange = (double) max - (double) min;
                double pixelsPerValue = (double) trackLength / valueRange;
                int trackLeft = trackRect.x;
                int trackRight = trackRect.x + (trackRect.width - 1);
                int xPosition;

                xPosition = trackLeft;
                xPosition += Math.round(pixelsPerValue * ((double) value - min));

                xPosition = Math.max(trackLeft, xPosition);
                xPosition = Math.min(trackRight, xPosition);

                return xPosition;
            }
        });

        putClientProperty("Nimbus.Overrides", z);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                putClientProperty("Nimbus.Overrides", d);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                putClientProperty("Nimbus.Overrides", z);

            }

        });

    }


}