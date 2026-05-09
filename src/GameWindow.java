import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameWindow extends JFrame {

    private JFrame frame;
    private GameControl gc;
    private JLabel moveCounter;
    private JButton[][] buttons;          // mřížka tlačítek odpovídající boardu
    private Card firstCard = null;        // první vybraná karta v tahu
    private JButton firstButton = null;   // tlačítko první karty
    private boolean waiting = false;      // blokuje klikání při animaci neshody


    private static final Color COLOR_BACK    = new Color(70, 130, 180);   // modrá – líc dolů
    private static final Color COLOR_FLIPPED = new Color(255, 248, 220);  // krémová – líc nahoru
    private static final Color COLOR_MATCHED = new Color(144, 238, 144);  // zelená – spárováno


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

        // ===================== IKONA OKNA =====================
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource("/resources/icon.png"));
            frame.setIconImage(icon.getImage());
        } catch (Exception e) {
            System.out.println("icon obrazek nenalezen");
        }


        // ===================== HORNÍ PANEL =====================
        JPanel upperPanel = new JPanel(new BorderLayout());
        upperPanel.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        upperPanel.setBackground(new Color(240, 240, 240));

        moveCounter = new JLabel("Počet tahů: " + gc.getAttempts());
        moveCounter.setFont(new Font("Arial", Font.BOLD, 20));
        upperPanel.add(moveCounter, BorderLayout.WEST);

        JLabel diff = new JLabel(gc.getBoard().getDiff().name());
        diff.setFont(new Font("Arial", Font.BOLD, 28));
        upperPanel.add(diff, BorderLayout.EAST);

        frame.add(upperPanel, BorderLayout.NORTH);

        // ===================== HERNÍ MŘÍŽKA =====================
        Difficulty difficulty = gc.getBoard().getDiff();
        int rows = difficulty.getRows();
        int cols = difficulty.getCols();

        buttons = new JButton[rows][cols];

        JPanel gridPanel = new JPanel(new GridLayout(rows, cols, 6, 6));
        gridPanel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        gridPanel.setBackground(new Color(200, 200, 200));

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                JButton btn = createCardButton(r, c);
                buttons[r][c] = btn;
                btn.setFocusable(false);
                gridPanel.add(btn);
            }
        }

        frame.add(gridPanel, BorderLayout.CENTER);




        // ===================== VÝPOČET VELIKOSTI OKNA =====================
        // každá karta ~100x100, mřížka + horní panel
        int cardSize = 100;
        int windowW = cols * cardSize + (cols + 1) * 6 + 24 + 16;   // mezery + border + dekorace
        int windowH = rows * cardSize + (rows + 1) * 6 + 24 + 70;   // + horní panel + dekorace

        frame.setSize(Math.max(windowW, 500), Math.max(windowH, 400));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }





    // ===================== VYTVOŘENÍ TLAČÍTKA KARTY =====================
    private JButton createCardButton(int row, int col) {
        JButton btn = new JButton();
        btn.setBackground(COLOR_BACK);
        btn.setBorderPainted(true);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Arial", Font.BOLD, 22));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));


        btn.addActionListener(e -> onCardClick(btn, row, col));
        return btn;
    }


    // ===================== LOGIKA KLIKNUTÍ NA KARTU =====================
    private void onCardClick(JButton btn, int row, int col) {

        if (waiting) return;

        Card card = gc.getBoard().getBoard().get(row).get(col);
        if (card.isFlipped() || card.isMatched()) return;

        card.setFlipped(true);
        showCardFace(btn, card);

        if (firstCard == null) {
            firstCard = card;
            firstButton = btn;
        } else {
            waiting = true;

            Card second = card;
            JButton secondBtn = btn;

            if (gc.attemptMatch(firstCard, second)) {
                firstButton.setBackground(COLOR_MATCHED);
                secondBtn.setBackground(COLOR_MATCHED);
                firstButton.setEnabled(false);
                secondBtn.setEnabled(false);

                updateCounter();
                firstCard = null;
                firstButton = null;
                waiting = false;

                if (gc.isEnd()) {
                    frame.setVisible(false);
                    EndScreen end = new EndScreen(gc);
                }
            } else {
                updateCounter();

                Timer timer = new Timer(800, ev -> {
                    hideCard(firstButton);
                    hideCard(secondBtn);
                    firstCard = null;
                    firstButton = null;
                    waiting = false;
                });
                timer.setRepeats(false);
                timer.start();
            }
        }
    }


    // ===================== ZAKRYTÍ KARTY =====================
    private void hideCard(JButton btn) {
        btn.setBackground(COLOR_BACK);
        btn.setIcon(null);
        btn.setText("");
    }

    // ===================== AKTUALIZACE POČÍTADLA TAHŮ =====================
    private void updateCounter() {
        moveCounter.setText("Počet tahů: " + gc.getAttempts());
    }

    // ===================== VÝSLEDKOVÉ OKNO =====================
    private void showEndScreen() {
        EndScreen endScreen = new EndScreen(gc);
    }

    // ===================== ZOBRAZENÍ LÍCE KARTY =====================
    private void showCardFace(JButton btn, Card card) {
        btn.setBackground(COLOR_FLIPPED);

        if (card.getImage() != null) {
            // Pokud máš obrázek, zobraz ho
            Image scaled = card.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
            btn.setIcon(new ImageIcon(scaled));
            btn.setText("");
        } else {
//             Fallback – zobraz číslo ID
            btn.setIcon(null);
            btn.setText(String.valueOf(card.getID() + 1)); // +1 aby čísla začínala od 1
        }
    }

}
