import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class Friend extends JPanel implements Serializable {
    private String name;
    private String IP;
    private int port;
//    private Song listening;
    private JLabel listeningName;
    private JLabel listeningArtist;
    private JLabel nameText;
    public Friend(String name , String IP,int port)
    {
        this.port = port;
        listeningName = new JLabel();
        listeningArtist = new JLabel();
        nameText = new JLabel();
        nameText.setText(name);
        this.name = name;
        this.IP = IP;
        this.setPreferredSize(new Dimension(250,200));
        this.setMaximumSize(new Dimension(250,200));
        this.setMinimumSize(new Dimension(250,200));
        listeningArtist.setText("None");
        listeningName.setText("None");
        listeningArtist.setForeground(Color.white);
        nameText.setForeground(Color.white);
        listeningName.setForeground(Color.white);
        this.setBackground(new Color(18,18,18));
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.add(nameText);
        this.add(listeningName);
        this.add(listeningArtist);
    }

    public String getIP() {
        return IP;
    }

    public int getPort() {
        return port;
    }

    public JLabel getListeningName() {
        return listeningName;
    }

    public JLabel getListeningArtist() {
        return listeningArtist;
    }
}
