import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class TitleScreen extends JFrame {
    private JFrame frame;
    private GameControl gc;

    public TitleScreen(GameControl gc) {
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

        // ===================== IKONA OKNA =====================
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource("/resources/icon.png"));
            frame.setIconImage(icon.getImage());
        } catch (Exception e) {
            System.out.println("icon obrazek nenalezen");
        }

        // ===================== NADPIS (NORTH) =====================
        JLabel title = new JLabel("PEXESO", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setBorder(BorderFactory.createEmptyBorder(10, 0, 1, 0)); // mezera: 10px nahoře, 5px dole
        frame.add(title, BorderLayout.NORTH);

        // ===================== TLAČÍTKA OBTÍŽNOSTÍ =====================
        JButton easy = new JButton("LEHKA");
        JButton medium = new JButton("STREDNI");
        JButton hard = new JButton("TEZKA");

        easy.setPreferredSize(new Dimension(70, 30));
        medium.setPreferredSize(new Dimension(70, 30));
        hard.setPreferredSize(new Dimension(70, 30));


        easy.setFocusPainted(false);    // schová modrý rámeček při kliknutí (výchozí chování Javy)
        medium.setFocusPainted(false);
        hard.setFocusPainted(false);

        // Border = rámeček okolo tlačítka
        Border defaultBorder = BorderFactory.createLineBorder(Color.GRAY, 2); // 2px šedá
        Border selectedBorder = BorderFactory.createLineBorder(Color.BLUE, 2); // 2px modrá


        easy.setBorder(selectedBorder);
        medium.setBorder(defaultBorder);
        hard.setBorder(defaultBorder);

        // Po kliknutí na obtížnost: vybrané tlačítko dostane modrý border, ostatní se resetují
        easy.addActionListener(e -> {
            easy.setBorder(selectedBorder);
            medium.setBorder(defaultBorder);
            hard.setBorder(defaultBorder);

            gc.getBoard().setDiff(Difficulty.EASY);
        });
        medium.addActionListener(e -> {
            easy.setBorder(defaultBorder);
            medium.setBorder(selectedBorder);
            hard.setBorder(defaultBorder);

            gc.getBoard().setDiff(Difficulty.MEDIUM);


        });
        hard.addActionListener(e -> {
            easy.setBorder(defaultBorder);
            medium.setBorder(defaultBorder);
            hard.setBorder(selectedBorder);

            gc.getBoard().setDiff(Difficulty.HARD);
        });

        // FlowLayout řadí tlačítka vedle sebe, 5px mezera mezi nimi
        JPanel diffs = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 0));
        diffs.add(easy);
        diffs.add(medium);
        diffs.add(hard);
        diffs.setAlignmentX(Component.CENTER_ALIGNMENT); // vycentrování v BoxLayout

        // ===================== TLAČÍTKO START =====================
        JButton startButton = new JButton("START");
        startButton.setFocusPainted(false);
        startButton.setAlignmentX(CENTER_ALIGNMENT);
        startButton.setPreferredSize(new Dimension(170, 20)); // požadovaná velikost
        startButton.setMaximumSize(new Dimension(170, 20));   // BoxLayout by ho jinak natáhl na celou šířku

        startButton.addActionListener(e -> {
            GameWindow gw = new GameWindow(gc);
            frame.setVisible(false);

        });

        // ===================== STŘED OKNA (CENTER) =====================
        // inner drží obtížnosti a START těsně u sebe (BoxLayout = řadí pod sebe)
        JPanel inner = new JPanel();
        inner.setLayout(new BoxLayout(inner, BoxLayout.Y_AXIS));
        inner.add(diffs);
        inner.add(Box.createVerticalStrut(20)); // 5px mezera mezi obtížnostmi a STARTem
        inner.add(startButton);

        // center vycentruje inner doprostřed okna
        // GridBagLayout bez parametrů automaticky vycentruje svůj obsah
        JPanel center = new JPanel(new GridBagLayout());
        center.add(inner);
        frame.add(center, BorderLayout.CENTER);

        // ===================== INFO DOLE (SOUTH) =====================
        JLabel info = new JLabel("počet hráčů před vámi: " + GameControl.getPlayerCounter(), SwingConstants.CENTER);
        info.setBorder(BorderFactory.createEmptyBorder(0, 0, 8, 0)); // 8px mezera dole
        frame.add(info, BorderLayout.SOUTH);
    }
}