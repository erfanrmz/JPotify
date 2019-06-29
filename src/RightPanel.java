import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RightPanel extends JPanel {
    private EJButton addFriend;
    private JPanel friends;
    private MainFrame mainFrame;
    private JScrollPane friendsScrollPane;

    public RightPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        friends = new JPanel();
        addFriend = new EJButton();
        addFriend.setPreferredSize(new Dimension(250, 100));
        addFriend.setIcon(new ImageIcon("Icons\\addFriend.png"));
        addFriend.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                addFriend.setIcon(new ImageIcon("Icons\\addFriendEntered.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                addFriend.setIcon(new ImageIcon("Icons\\addFriend.png"));
            }
        });
//        friends.setPreferredSize(new Dimension(250, 650));
        friends.setLayout(new BoxLayout(friends,BoxLayout.Y_AXIS));
        friends.setBackground(new Color(18, 18, 18));
        this.setPreferredSize(new Dimension(250, 800));
        this.setMaximumSize(new Dimension(250, 800));
        this.setMinimumSize(new Dimension(0, 0));
        this.setBackground(new Color(18, 18, 18));
        this.setLayout(new BorderLayout());
        friendsScrollPane = new JScrollPane(friends, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        friendsScrollPane.setBorder(null);
        this.add(friendsScrollPane,BorderLayout.CENTER);
        this.add(addFriend,BorderLayout.PAGE_END);
        addFriend.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JTextField friendName = new JTextField();
                JTextField friendIp = new JTextField();
                JTextField friendPort = new JTextField();
                JButton add = new JButton("add");
                add.setPreferredSize(new Dimension(600,200));
                JFrame addFriend = new JFrame("Please enter Your friend`s Information");
                addFriend.setBounds(600,400,600,200);
                JPanel newFriend = new JPanel();
                newFriend.setPreferredSize(new Dimension(600, 400));
                newFriend.setLayout(new BoxLayout(newFriend,BoxLayout.Y_AXIS));
                newFriend.add(friendName);
                newFriend.add(friendIp);
                newFriend.add(friendPort);
                newFriend.add(add);
                addFriend.add(newFriend);
                addFriend.setVisible(true);
                add.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                       Friend friend = new Friend(friendName.getText(),friendIp.getText(),  Integer.parseInt(friendPort.getText()));
                       ServerThread server = new ServerThread(friend.getPort());
                       server.start();
                        mainFrame.getFriends().add(friend);
                        friends.add(friend);
                        addFriend.dispose();
                        RightPanel.this.revalidate();
                    }
                });

            }


        });
    }
}
