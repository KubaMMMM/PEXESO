import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class TitleScreen extends JFrame {
    private JFrame frame;
    private GameControl gm;

    public TitleScreen() {
        this.frame = new JFrame("Pexeso");
        init();

        //TODO: diff
//        gm = new GameControl(new GameBoard(Difficulty.))
    }

    private void init() {
        frame.setSize(800, 600);
        frame.setPreferredSize(new Dimension(800,600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());


        //ikona okna
        try {
            ImageIcon icon = new ImageIcon(
                    getClass().getResource("/resources/icon.png")

            );
            frame.setIconImage(icon.getImage());

        }catch (Exception e){
            System.out.println("icon obrazek nenalezen");
        }



        //uvodni text
        JLabel title = new JLabel("PEXESO", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 40));
        frame.add(title, BorderLayout.NORTH);


        //obtiznosti
        JPanel diffs = new JPanel();
        diffs.setLayout((new BoxLayout(diffs, BoxLayout.X_AXIS)));
        diffs.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));

        Dimension size = new Dimension(200, 300);

        JButton easy = new JButton("Easy 4x4");
        easy.setPreferredSize(size);
        easy.setFocusPainted(false);  //smazani ramecku okolo textu
        easy.setAlignmentX(CENTER_ALIGNMENT);


        JButton medium = new JButton("Medium 4x5");
        medium.setPreferredSize(size);
        medium.setFocusPainted(false);  //smazani ramecku okolo textu
        medium.setAlignmentX(CENTER_ALIGNMENT);

        JButton hard = new JButton("Hard 4x6");
        hard.setPreferredSize(size);
        hard.setFocusPainted(false);  //smazani ramecku okolo textu
        hard.setAlignmentX(CENTER_ALIGNMENT);


        diffs.add(easy);
        diffs.add(Box.createHorizontalStrut(15));   //mezery
        diffs.add(medium);
        diffs.add(Box.createHorizontalStrut(15));
        diffs.add(hard);
        diffs.setAlignmentX(Component.CENTER_ALIGNMENT);

        //TODO: udelat spravne vedle sebe tlacitka

        JButton startButton = new JButton("START");
        startButton.setAlignmentX(CENTER_ALIGNMENT);

        diffs.add(startButton);
        frame.add(diffs, BorderLayout.CENTER);

        //info dole
        JLabel info = new JLabel("Hráč číslo: "+GameControl.getPlayerCounter(), SwingConstants.CENTER);
        frame.add(info,BorderLayout.SOUTH);


    }

}
