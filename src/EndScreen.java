import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class EndScreen extends JFrame {
    private JFrame frame;
    private GameControl gc;

    public EndScreen(GameControl gc) {
        this.gc = gc;
        this.frame = new JFrame("Pexeso");
        init();
    }


    private void init() {

        // ===================== NASTAVENÍ OKNA =====================
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);          // vycentruje okno na obrazovce
        frame.setResizable(false);                  // okno nejde měnit velikost
        frame.setLayout(new BorderLayout());        // okno je rozděleno na NORTH, CENTER, SOUTH
        frame.setVisible(true);
    }

}
