import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
}
