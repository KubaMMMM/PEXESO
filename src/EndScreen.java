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
        frame.setSize(500, 400);
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

        // ===================== NADPIS (NORTH) =====================
        JLabel title = new JLabel("KONEC", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setBorder(BorderFactory.createEmptyBorder(10, 0, 1, 0)); // mezera: 10px nahoře, 5px dole
        frame.add(title, BorderLayout.NORTH);

        //==========================POCET POKUSU==========================

        JPanel attempts = new JPanel();
        attempts.setLayout(new BoxLayout(attempts, BoxLayout.Y_AXIS));

        JLabel attemptsText = new JLabel("Počet tahů:" , SwingConstants.LEFT);
        attemptsText.setFont(new Font("Arial", Font.BOLD, 25));
        attemptsText.setBorder(BorderFactory.createEmptyBorder(30, 10, 1, 0)); // mezera: 10px nahoře, 5px dole
        attempts.add(attemptsText);

        JLabel attemptsNumber = new JLabel(" "+gc.getAttempts() , SwingConstants.CENTER);
        attemptsNumber.setFont(new Font("Arial", Font.BOLD, 100));
        attemptsNumber.setBorder(BorderFactory.createEmptyBorder(1, 0, 1, 0)); // mezera: 10px nahoře, 5px dole
        attempts.add(attemptsNumber);

        frame.add(attempts, BorderLayout.WEST);

        //==========================TABULKA NEJLEPSICH=====================

        JPanel table = new JPanel();
        table.setLayout(new BoxLayout(table, BoxLayout.Y_AXIS));



    }

}
