import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.util.Map;

public class UserPanel extends JPanel {
    private JLabel name;
    private EJButton user;
    public UserPanel(String username)
    {
        user = new EJButton();
        name = new JLabel();
        name.setText(username);
        name.setFont(new Font(" ",Font.BOLD,19));
        name.setForeground(Color.WHITE);
        name.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Font font = name.getFont();
                Map attributes = font.getAttributes();
                attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                name.setFont(font.deriveFont(attributes));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                name.setFont(new Font(" ",Font.BOLD,19));
            }
        });
        user.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Font font = name.getFont();
                Map attributes = font.getAttributes();
                attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                name.setFont(font.deriveFont(attributes));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                name.setFont(new Font(" ",Font.BOLD,19));
            }
        });
        user.setIcon(new ImageIcon("Icons\\user40.png"));
        user.setPreferredSize(new Dimension(40,40));

        this.setBackground(new Color(24,24,24));
        this.setLayout(new FlowLayout(FlowLayout.RIGHT));
        this.add(user);
        this.add(name);

    }
}
