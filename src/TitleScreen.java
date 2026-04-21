import javax.swing.*;
import java.awt.*;

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
        ImageIcon icon = new ImageIcon("icon.png");
        setIconImage(icon.getImage());

        //uvodni text
        JLabel title = new JLabel("PEXESO", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 32));
        frame.add(title, BorderLayout.NORTH);


        //obtiznosti
        JPanel diffs = new JPanel();
        diffs.setLayout((new BoxLayout(diffs, BoxLayout.X_AXIS)));
        diffs.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        Dimension size = new Dimension(120, 40);
        JButton easy = new JButton("Easy 4x4");
        easy.setPreferredSize(size);
        JButton medium = new JButton("Medium 4x5");
        medium.setPreferredSize(size);
        JButton hard = new JButton("Hard 4x6");
        hard.setPreferredSize(size);
        easy.setAlignmentX(CENTER_ALIGNMENT);
        medium.setAlignmentX(CENTER_ALIGNMENT);
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
