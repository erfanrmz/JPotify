import javax.swing.*;
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
        MainFrame mainFrame = new MainFrame();
    }
}
