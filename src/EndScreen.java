
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class EndScreen extends JFrame {

    private JFrame frame;
    private GameControl gc;
    private JScrollPane scrollPane;
    private JTextField textField;
    private DefaultListModel<String> model;
    private JList<String> list;
    private String playerName = null;

    public EndScreen(GameControl gc) {
        this.gc = gc;
        this.frame = new JFrame("Pexeso");
        model = new DefaultListModel<>();
        list = new JList<>(model);
        scrollPane = new JScrollPane(list);
        textField = new JTextField();
        init();
        refreshList();
    }

    private void init() {

        // ===================== NASTAVENÍ OKNA =====================
        frame.setSize(500, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);          // vycentruje okno na obrazovce
        frame.setResizable(false);                  // okno nejde měnit velikost
        frame.setLayout(new BorderLayout());        // okno je rozděleno na NORTH, CENTER, SOUTH
        frame.setVisible(true);

        // ===================== IKONA OKNA =====================
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource("/icon.png"));
            frame.setIconImage(icon.getImage());
        } catch (Exception e) {
            System.out.println("icon obrazek nenalezen");
        }

        // ===================== NADPIS (NORTH) =====================
        JPanel northPanel = new JPanel(new GridBagLayout());
        northPanel.setBackground(Color.lightGray);
        JLabel title = new JLabel("KONEC", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 40));
        title.setBorder(BorderFactory.createEmptyBorder(10, 0, 1, 0)); // mezera: 10px nahoře, 5px dole
        northPanel.add(title);
        frame.add(northPanel, BorderLayout.NORTH);

        //==========================POCET POKUSU==========================
        JPanel attempts = new JPanel();
        attempts.setLayout(new BoxLayout(attempts, BoxLayout.Y_AXIS));

        JLabel attemptsText = new JLabel("Počet tahů:", SwingConstants.LEFT);
        attemptsText.setFont(new Font("Arial", Font.BOLD, 20));
        attemptsText.setBorder(BorderFactory.createEmptyBorder(40, 20, 0, 0)); // mezera: 10px nahoře, 5px dole
        attempts.add(attemptsText);

        JLabel attemptsNumber = new JLabel(" " + gc.getAttempts(), SwingConstants.CENTER);
        attemptsNumber.setFont(new Font("Comic Sans", Font.BOLD, 70));
        attemptsNumber.setBorder(BorderFactory.createEmptyBorder(2, 10, 1, 0)); // mezera: 10px nahoře, 5px dole
        attempts.add(attemptsNumber);

        JLabel diff = new JLabel("Obtížnost: " + gc.getBoard().getDiff().name());
        diff.setFont(new Font("Arial", Font.BOLD, 20));
        diff.setBorder(BorderFactory.createEmptyBorder(40, 20, 5, 5));
        attempts.add(diff);

        frame.add(attempts, BorderLayout.WEST);

        //==========================TABULKA NEJLEPSICH=====================
        JPanel table = new JPanel();
        table.setLayout(new BorderLayout());

        table.add(scrollPane, BorderLayout.CENTER);

        JButton button = new JButton("Přidat");
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(button, BorderLayout.EAST);

        textField.setForeground(Color.GRAY);
        textField.setText("Zadejte jméno");

        textField.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (textField.getText().equals("Zadejte jméno")) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setForeground(Color.GRAY);
                    textField.setText("Zadejte jméno");
                }
            }
        });

        panel.add(textField, BorderLayout.CENTER);

        // ===================== ZADÁNÍ JMÉNA =====================
        // text field s placeholderem a tlačítko pro přidání hráče
        button.addActionListener(e -> {
            String text = textField.getText();

            if (playerName == null && !text.isEmpty() && !text.equals("Zadejte jméno")) {
                playerName = text;
                model.addElement(text);
                gc.addScore(text);
                refreshList();
                textField.disable();
            }

            textField.setText("Jméno zadáno");
        });

        panel.setBorder(BorderFactory.createEmptyBorder(2, 0, 3, 0));
        table.add(panel, BorderLayout.NORTH);

        // ===================== PŘIDÁNÍ HRÁČE =====================
        // přidání jména do seznamu pomocí tlačítka nebo ENTERU
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String text = textField.getText();
                    if (playerName == null && !text.isEmpty() && !text.equals("Zadejte jméno")) {
                        playerName = text;
                        model.addElement(text);
                        gc.addScore(text);
                        refreshList();
                        textField.disable();
                    }

                    textField.setText("Jméno zadáno");
                }
            }
        });

        frame.add(table, BorderLayout.CENTER);
    }

    // nová metoda – smaže model a znovu ho naplní z gc.getScores()
    private void refreshList() {
        model.clear();
        for (Score s : gc.getScores()) {
            model.addElement(s.getName() + " – " + s.getAttempts() + " tahů");
        }
    }

}
