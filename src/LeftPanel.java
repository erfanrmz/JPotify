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
    private EJButton libraryTitle;
    private EJButton playListTitle;
    private EJButton musicPlayingArtWork;
    private EJButton albumsButton;
    private EJButton favoriteButtton;
    private JPopupMenu popupMenu;
    private JMenuItem addsong;
    private JPanel songsPanel;
    private JPanel playlistsPanel;
    private JPanel yourLibrary;
    private JScrollPane yourLibraryScrollPane;
    private ArrayList<EJButton> playlistsButt;
    private ArrayList<Playlist> playlists;
    private ArrayList<String> playlistNames;
    private MainFrame mainFrame;
    private Play player;
    private ArrayList<Play> playingThreads;
    private ArrayList<Album> albums;
    private ArrayList<Song> songs1;

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }


    public LeftPanel(JPanel songsPanel, MainFrame mainFrame, Play player, ArrayList<Play> playingThreads, ArrayList<Album> albums, ArrayList<Song> songs1) {
        playlistNames = new ArrayList<>();
        this.playingThreads = playingThreads;
        this.player = player;
        this.mainFrame = mainFrame;
        this.songs1 = songs1;
        this.setPreferredSize(new Dimension(250, 800));
        this.setMaximumSize(new Dimension(250, 800));
        this.setMinimumSize(new Dimension(250, 800));
        playlists = new ArrayList<>();
        playlistsButt = new ArrayList<>();
        yourLibrary = new JPanel();
        albumsButton = new EJButton("     Albums");
        albumsButton.setForeground(new Color(179, 179, 179));
        albumsButton.setFont(new Font(" ", Font.BOLD, 20));
        favoriteButtton = new EJButton("     Favorite Songs");
        favoriteButtton.setForeground(new Color(179, 179, 179));
        favoriteButtton.setFont(new Font(" ", Font.BOLD, 20));
        libraryTitle = new EJButton("     YOUR LIBRARY");
        libraryTitle.setFont(new Font("", Font.CENTER_BASELINE, 15));
        libraryTitle.setForeground(new Color(141, 141, 141));
        playListTitle = new EJButton("     PLAYLISTS");
        playListTitle.setFont(new Font("", Font.CENTER_BASELINE, 15));
        playListTitle.setForeground(new Color(141, 141, 141));
        musicPlayingArtWork = new EJButton();
        musicPlayingArtWork.setIcon(new ImageIcon("Icons\\cover1.png"));
        musicPlayingArtWork.setPreferredSize(new Dimension(250, 250));
        yourLibrary.setLayout(new BoxLayout(yourLibrary, BoxLayout.Y_AXIS));
        yourLibrary.setBackground(new Color(18, 18, 18));
        yourLibraryScrollPane = new JScrollPane(yourLibrary, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        yourLibraryScrollPane.setBorder(null);
        yourLibrary.setMinimumSize(new Dimension(230, 230));
        yourLibrary.setMaximumSize(new Dimension(230, 230));
        this.albums = albums;
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
        others.setPreferredSize(new Dimension(250, 50));
        home.setPreferredSize(new Dimension(250, 50));
        songs.setPreferredSize(new Dimension(250, 70));
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
        yourLibrary.add(libraryTitle);
        yourLibrary.add(favoriteButtton);
        yourLibrary.add(albumsButton);
        yourLibrary.add(playListTitle);
        yourLibrary.add(playlistsPanel);

        this.add(yourLibraryScrollPane);
//        this.add(playlistsPanel);
        this.add(newPlayList);
        this.add(musicPlayingArtWork);

        //Actionlistoners

        addsong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int dialog = fileChooser.showOpenDialog(null);
                try {
                    Song song = new Song(fileChooser.getSelectedFile().getAbsolutePath());
                    songs1.add(song);
                    System.out.println(songs1.size());
                    ObjectOutputStream library = new ObjectOutputStream(new FileOutputStream("Saves\\library.ser"));
                    library.writeObject(song);
                    ((MainPanel) songsPanel).addSongFromButton(song);
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
                        Playlist newP = new Playlist(playlistName.getText(), mainFrame, player, playingThreads);
                        playlists.add(newP);
                        playlistNames.add(playlistName.getText());
                        EJButton playL = new EJButton("     " + playlistName.getText());
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
                        playL.setFont(new Font(" ", Font.BOLD, 20));
                        playL.setForeground(new Color(179, 179, 179));
                        playL.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseEntered(MouseEvent e) {
                                playL.setForeground(Color.WHITE);
                            }

                            @Override
                            public void mouseExited(MouseEvent e) {
                                playL.setForeground(new Color(179, 179, 179));
                            }
                        });
                        playL.setPreferredSize(new Dimension(250, 40));
                        playlistsButt.add(playL);
                        playlistsPanel.add(playL);
                        newPlaylistFrame.dispose();
                        mainFrame.revalidate();
                        playL.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                if (e.getButton() == MouseEvent.BUTTON1) {
                                    mainFrame.ChangePanel(newP);
                                }
                                if (e.getButton() == MouseEvent.BUTTON3) {
                                    JPopupMenu popForDel = new JPopupMenu("Delete Playlist");
                                    JMenuItem deletePlaylist = new JMenuItem("Delete Playlist");
                                    popForDel.add(deletePlaylist);
                                    deletePlaylist.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            playlists.remove(newP);
                                            playlistNames.remove(playlistName.getText());
                                            playlistsButt.remove(playL);
                                            playlistsPanel.remove(playL);
                                            LeftPanel.this.revalidate();
                                            LeftPanel.this.repaint();
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
                                        }
                                    });
                                    popForDel.show(playL, e.getX(), e.getY());
                                }
                            }
                        });
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
        albumsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    mainFrame.setAlbumPanel();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                albumsButton.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                albumsButton.setForeground(new Color(179, 179, 179));
            }
        });
        favoriteButtton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                favoriteButtton.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                favoriteButtton.setForeground(new Color(179, 179, 179));
            }
        });
        try {
            readPlaylists();
        } catch (Exception e) {
        }
    }

    public void readPlaylists() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File("Saves\\Name of the playlists.ser"))));
        playlistNames = (ArrayList<String>) ois.readObject();
        for (int i = 0; i < playlistNames.size(); i++) {
            Playlist newP = new Playlist(playlistNames.get(i), mainFrame, player, playingThreads);
            playlists.add(newP);
            EJButton playL = new EJButton("     " + playlistNames.get(i));
            playL.setFont(new Font(" ", Font.BOLD, 20));
            playL.setForeground(new Color(179, 179, 179));
            playL.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseEntered(MouseEvent e) {
                    playL.setForeground(Color.WHITE);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    playL.setForeground(new Color(179, 179, 179));
                }
            });
            playL.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    mainFrame.ChangePanel(newP);
                }
            });
            playL.setPreferredSize(new Dimension(250, 40));
            playlistsButt.add(playL);
            playlistsPanel.add(playL);
            mainFrame.revalidate();
            playL.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getButton() == MouseEvent.BUTTON1)
                        mainFrame.ChangePanel(newP);
                    else if (e.getButton() == MouseEvent.BUTTON3) {
                        JPopupMenu popForDel = new JPopupMenu("Delete Playlist");
                        JMenuItem deletePlaylist = new JMenuItem("Delete Playlist");
                        popForDel.add(deletePlaylist);
                        deletePlaylist.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                playlists.remove(newP);
                                playlistNames.remove(newP.getName());
                                playlistsButt.remove(playL);
                                playlistsPanel.remove(playL);
                                LeftPanel.this.revalidate();
                                LeftPanel.this.repaint();
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
                            }
                        });
                        popForDel.show(playL, e.getX(), e.getY());
                    }
                }
            });
        }
    }
    public EJButton getMusicPlayingArtWork() {
        return musicPlayingArtWork;
    }
}
