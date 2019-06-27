import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AJButton extends JButton {
    private Album album;
        public AJButton(Album album) {
            this.album = album;
            this.setPreferredSize(new Dimension(250,250));
            this.setIcon(album.getImageIcon());
        }

    public Album getAlbum() {
        return album;
    }
}
