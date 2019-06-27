import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.ArrayList;

public class LeftPanel extends JPanel {
    private EJButton home;
    private EJButton others;
    private EJButton songs;
    private EJButton newPlayList;
    private JPopupMenu popupMenu;
    private JMenuItem addsong;
    private JPanel songsPanel;
    private JPanel playlistsPanel;
    private ArrayList<EJButton> playlistsButt;
    private ArrayList<Playlist> playlists;
    private ArrayList<String> playlistNames;
    private MainFrame mainFrame;
    private Play player;
    private ArrayList<Play> playingThreads;

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public LeftPanel(JPanel songsPanel, MainFrame mainFrame, Play player, ArrayList<Play> playingThreads) throws IOException, ClassNotFoundException {
        playlistNames = new ArrayList<>();
        this.playingThreads = playingThreads;
        this.player = player;
        this.mainFrame = mainFrame;
        playlists = new ArrayList<>();
        playlistsButt = new ArrayList<>();
        this.songsPanel = songsPanel;
        popupMenu = new JPopupMenu("others");
        addsong = new JMenuItem("Add song");
        popupMenu.add(addsong);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        others = new EJButton();
        home = new EJButton();
        songs = new EJButton();
        playlistsPanel = new JPanel();
        newPlayList = new EJButton();
        others.setPreferredSize(new Dimension(250, 40));
        home.setPreferredSize(new Dimension(250, 40));
        songs.setPreferredSize(new Dimension(250, 40));
//        playlistsPanel.setSize(new Dimension(500, 100));
        playlistsPanel.setLayout(new BoxLayout(playlistsPanel, BoxLayout.Y_AXIS));
        newPlayList.setPreferredSize(new Dimension(250, 90));
        home.setIcon(new ImageIcon("Icons\\home.png"));
        others.setIcon(new ImageIcon("Icons\\others.png"));
        songs.setIcon(new ImageIcon("Icons\\song1.png"));
        playlistsPanel.setBackground(new Color(18, 18, 18));
        newPlayList.setIcon(new ImageIcon("Icons\\newPlaylist.png"));
        Color leftPanelBackground = new Color(18, 18, 18);
        popupMenu.setPreferredSize(new Dimension(150, 40));
        this.setBackground(leftPanelBackground);

        this.add(others);
        this.add(home);
        this.add(songs);
        this.add(playlistsPanel);
        this.add(newPlayList);
        //Actionlistoners

        addsong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser a = new JFileChooser();
                int fasf = a.showOpenDialog(null);
                try {
                    Song song = new Song(a.getSelectedFile().getAbsolutePath());
                    ObjectOutputStream library = new ObjectOutputStream(new FileOutputStream("Saves\\library.ser"));
                    library.writeObject(song);
                    ((MainPanel) songsPanel).addsongFromButton(song);


                } catch (IOException | InvalidDataException | UnsupportedTagException ex) {
                    ex.printStackTrace();
                }
            }
        });

        others.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                popupMenu.show(others, 30, 40);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                others.setIcon(new ImageIcon("Icons\\othersEntered.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                others.setIcon(new ImageIcon("Icons\\others.png"));
            }
        });

        home.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                home.setIcon(new ImageIcon("Icons\\homeEntered1.png"));

            }

            @Override
            public void mouseExited(MouseEvent e) {
                home.setIcon(new ImageIcon("Icons\\home.png"));
            }
        });

        newPlayList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                newPlayList.setIcon(new ImageIcon("Icons\\newPlaylistEntered.png"));

            }

            @Override
            public void mouseExited(MouseEvent e) {
                newPlayList.setIcon(new ImageIcon("Icons\\newPlaylist.png"));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                JTextField playlistName = new JTextField();
                JFrame newPlaylistFrame = new JFrame("Please enter the name of your new playlist");
                newPlaylistFrame.setBounds(700, 400, 520, 90);
                newPlaylistFrame.setVisible(true);
                newPlaylistFrame.add(playlistName);
                playlistName.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Playlist newp = new Playlist(playlistName.getText(), mainFrame, player, playingThreads);
                        playlists.add(newp);
                        playlistNames.add(playlistName.getText());
                        EJButton playl = new EJButton(playlistName.getText());
                        FileOutputStream fop = null;
                        try {
                            fop = new FileOutputStream("Saves\\Name of the playlists.ser");
                        } catch (FileNotFoundException ex) {
                            ex.printStackTrace();
                        }
                        ObjectOutputStream oos = null;
                        try {
                            oos = new ObjectOutputStream(fop);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        try {
                            oos.writeObject(playlistNames);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        playl.addActionListener(e1 -> {
                            mainFrame.ChangePanel(newp);
                        });
                        playl.setForeground(Color.WHITE);
                        playl.setPreferredSize(new Dimension(250, 40));
                        playlistsButt.add(playl);
                        playlistsPanel.add(playl);

                        newPlaylistFrame.dispose();
                        mainFrame.revalidate();
                    }
                });

            }
        });

        songs.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                songs.setIcon(new ImageIcon("Icons\\songEntered1.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                songs.setIcon(new ImageIcon("Icons\\song1.png"));
            }
        });
        try {
            readPlaylists();
        } catch (Exception e) {

        }
    }

    public void readPlaylists() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("Saves\\Name of the playlists.ser");
        ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File("Saves\\Name of the playlists.ser"))));
        playlistNames = (ArrayList<String>) ois.readObject();
        for (int i = 0; i < playlistNames.size(); i++) {
            Playlist newp = new Playlist(playlistNames.get(i), mainFrame, player, playingThreads);
            playlists.add(newp);
            EJButton playl = new EJButton(playlistNames.get(i));
            playl.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mainFrame.ChangePanel(newp);
                }
            });
            playl.setForeground(Color.WHITE);
            playl.setPreferredSize(new Dimension(250, 40));
            playlistsButt.add(playl);
            playlistsPanel.add(playl);
            mainFrame.revalidate();
            System.out.println(playlistNames.get(i));
            System.out.println("ss");
        }
    }
}
