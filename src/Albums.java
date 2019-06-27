import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Albums extends JPanel{
    private JPanel box1;
    private JPanel box2;
    private JPanel box3;
    private ArrayList<Album> albums;

    public Albums(ArrayList<Album> albums)
    {
        box1 = new JPanel();
        box2 = new JPanel();
        box3 = new JPanel();
        box1.setBackground(new Color(186, 112, 200));
        box2.setBackground(new Color(24, 24, 24));
        box3.setBackground(new Color(24, 24, 24));
        this.albums = albums;
        this.setLayout(new GridLayout(1,3));
        this.add(box1);
        this.add(box2);
        this.add(box3);
        box1.setLayout(new BoxLayout(box1, BoxLayout.Y_AXIS));
        box2.setLayout(new BoxLayout(box2, BoxLayout.Y_AXIS));
        box3.setLayout(new BoxLayout(box3, BoxLayout.Y_AXIS));
        for (int i = 0 ; i < albums.size() ; i++)
        {
            AJButton albumButton = new AJButton(albums.get(i));
            if (i % 3 == 0) {
                box1.add(albumButton);
            } else if (i % 3 == 1) {
                box2.add(albumButton);
            } else if (i % 3 == 2) {
                box3.add(albumButton);
            }
            this.revalidate();

    }
    }

}
