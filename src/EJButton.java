import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * This is a class for making a Jbutton look cooler! :)
 *
 * @author Erfan Ramezani & Amir Mojtaba Kiasat
 * @version 1.0
 * @since 6-21-2019
 */
public class EJButton extends JButton {
    private int pressed;

    public EJButton() {
        pressed = 0;
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                pressed++;
                System.out.println(pressed);
            }
        });
    }

    public EJButton(String text) {
        super(text);
        pressed = 0;
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                pressed++;
                System.out.println(pressed);
            }
        });
    }

    public int getPressed() {
        return pressed;
    }

    public void setPressed(int pressed) {
        this.pressed = pressed;
    }
}
