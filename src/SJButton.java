import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SJButton extends JButton {
    private Song song;
    private Play player;
    int press;

    public Play getPlayer() {
        return player;
    }

    public SJButton(String text, Icon icon, Song song , Play player) {
        super(text, icon);
        this.song = song;
        this.setSize(new Dimension(260,260));
        press = 0;
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setVerticalTextPosition(SJButton.BOTTOM);
        this.setHorizontalTextPosition(JButton.CENTER);
        this.setForeground(Color.white);
        this.setFont(new Font("",Font.BOLD,14));
        this.player = player;
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!Thread.currentThread().isAlive())
                {
                    System.out.println("FUck off :)");
                    Thread.currentThread().stop();
                    Play player = new Play();
//                    player
//                    player.setPlayingSong(song);

                }
                else
                {
                    player.setPlayingSong(song);
                    player.start();
                }

            }
        });



    }
}
