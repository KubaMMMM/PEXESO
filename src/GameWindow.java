import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {

    private JFrame frame;
    private GameControl gc;



    public GameWindow( GameControl gc){
        this.frame = new JFrame("Pexeso");
        this.gc = gc;
        init();
    }

    public void init(){

        // ===================== NASTAVENÍ OKNA =====================
        frame.setSize(1000, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);          // vycentruje okno na obrazovce
        frame.setResizable(false);                  // okno nejde měnit velikost
        frame.setLayout(new BorderLayout());        // okno je rozděleno na NORTH, CENTER, SOUTH
        frame.setVisible(true);

        // ===================== IKONA OKNA =====================
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource("/resources/icon.png"));
            frame.setIconImage(icon.getImage());
        } catch (Exception e) {
            System.out.println("icon obrazek nenalezen");
        }


        JPanel upperPanel = new JPanel(new BorderLayout());
        upperPanel.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20)); // odsazení od okrajů

        JLabel moveCounter = new JLabel("počet tahů: " + gc.getAttempts());
        moveCounter.setFont(new Font("Arial",Font.BOLD, 20));
        upperPanel.add(moveCounter, BorderLayout.WEST); // vlevo

        JLabel diff = new JLabel(gc.getBoard().getDiff().name());
        diff.setFont(new Font("Arial", Font.BOLD, 40));
        upperPanel.add(diff, BorderLayout.EAST); // vpravo

        frame.add(upperPanel, BorderLayout.NORTH);



    }
}
