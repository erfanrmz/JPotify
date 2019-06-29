import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class Main {
    public static void main(String arg[]) throws IOException, ClassNotFoundException {
        Time test = new Time(255);
        test.setTime(255);
        System.out.println(test.getSec());
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        JFrame signIn =new JFrame("Sign In");
        JPanel sign = new JPanel();
        signIn.add(sign);
        EJButton logIn = new EJButton();
        EJButton logo = new EJButton();
        JTextField user = new JTextField("Username") {
            @Override public void setBorder(Border border) {
                // No!
            }
        };
        user.addFocusListener(new FocusListener() {
                                  @Override
                                  public void focusGained(FocusEvent e) {
                                      user.setBackground(new Color(64,64,64));
                                      user.setText("");
                                      user.setForeground(Color.WHITE);
                                  }

                                  @Override
                                  public void focusLost(FocusEvent e) {
                                      user.setBackground(new Color(51,51,51));

                                  }
        });
        JPanel textfield = new JPanel();
        textfield.setPreferredSize(new Dimension(200,30));
        textfield.setMinimumSize(new Dimension(200,30));
        textfield.setMaximumSize(new Dimension(200,30));
        textfield.setLayout(new BoxLayout(textfield,BoxLayout.Y_AXIS));
        user.setBackground(new Color(51,51,51));
        textfield.setBackground(new Color(51,51,51));
        user.setForeground(new Color(179,179,179));
        user.setFont(new Font(" ", Font.BOLD,15));
        textfield.add(user);
        JTextField IP = new JTextField("IP") {
            @Override public void setBorder(Border border) {
                // No!
            }
        };
        IP.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                IP.setBackground(new Color(64,64,64));
                IP.setText("");
                IP.setForeground(Color.WHITE);
            }

            @Override
            public void focusLost(FocusEvent e) {
                IP.setBackground(new Color(51,51,51));

            }
        });
        JPanel textFieldIp = new JPanel();
        textFieldIp.setPreferredSize(new Dimension(200,30));
        textFieldIp.setMinimumSize(new Dimension(200,30));
        textFieldIp.setMaximumSize(new Dimension(200,30));
        textFieldIp.setLayout(new BoxLayout(textFieldIp,BoxLayout.Y_AXIS));
        IP.setBackground(new Color(51,51,51));
        textFieldIp.setBackground(new Color(51,51,51));
        IP.setForeground(new Color(179,179,179));
        IP.setFont(new Font(" ", Font.BOLD,15));
        textFieldIp.add(IP);
        logo.setIcon(new ImageIcon("Icons\\jpotifySignIn.png"));
        signIn.setBounds(600,200,600,400);
        sign.setBackground(new Color(12,12,12));
        logIn.setPreferredSize(new Dimension(150,50));
        logIn.setIcon(new ImageIcon("Icons\\logIn.png"));
        logIn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                logIn.setIcon(new ImageIcon("Icons\\logIn.png"));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                logIn.setIcon(new ImageIcon("Icons\\logInEntered.png"));
            }
        });
        JPanel box1 = new JPanel();
        box1.setLayout(new FlowLayout(FlowLayout.CENTER));
        box1.setBackground(new Color(12,12,12));
        box1.add(logo);
        JPanel box2 = new JPanel();
        box2.setLayout(new FlowLayout(FlowLayout.CENTER));
        box2.setBackground(new Color(12,12,12));
        box2.add(textfield);
        JPanel box3 = new JPanel();
        box3.setLayout(new FlowLayout(FlowLayout.CENTER));
        box3.setBackground(new Color(12,12,12));
        box3.add(textFieldIp);
        JPanel box4 = new JPanel();
        box4.setLayout(new FlowLayout(FlowLayout.CENTER));
        box4.setBackground(new Color(12,12,12));
        box4.add(logIn);
        sign.setLayout(new BoxLayout(sign,BoxLayout.Y_AXIS));
        sign.add(box1);
        sign.setLayout(new BoxLayout(sign,BoxLayout.Y_AXIS));
        sign.add(box1);
        sign.add(box2);
        sign.add(box3);
        sign.add(box4);
        signIn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon spotify = new ImageIcon("Icons\\Jpotify.png");
        signIn.setIconImage(spotify.getImage());
        signIn.setVisible(true);
        logIn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1)
                {
                    System.out.println(IP.getText());
                    signIn.dispose();
                    MainFrame mainFrame = new MainFrame(user.getText(),IP.getText());

                }
            }
        });

    }
}
