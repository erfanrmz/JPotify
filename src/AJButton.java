import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AJButton extends JButton {
    private Album album;
    private MainFrame mainFrame;

    public AJButton(String text, Icon icon, Album album , MainFrame mainFrame) {
        super(text, icon);
        this.album = album;
        this.mainFrame = mainFrame;
        this.setPreferredSize(new Dimension(250, 250));
        this.setSize(new Dimension(260, 260));
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setVerticalTextPosition(SJButton.BOTTOM);
        this.setHorizontalTextPosition(SJButton.CENTER);
        this.setForeground(Color.white);
        this.setFont(new Font("", Font.BOLD, 14));
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    Album albumPanel = new Album(album.getName(), album.getArtist(), album.getYear(), album.getImageIcon(), album.getMainFrame(), album.getSongsOfAlbum());
                    albumPanel.updateAlbum();
                    album.getMainFrame().setAlbumPanel1(albumPanel);
                    mainFrame.setPlaylistPlaying(albumPanel.getSongsOfAlbum());
                    mainFrame.setInAlbum(true);
                }
            }
        });
    }

    public Album getAlbum() {
        return album;
    }
}
