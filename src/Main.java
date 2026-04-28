import javax.swing.*;

public class Main {
    public static void main(String[] args) {



    //kod na nastaveni tema oken jako na windows
    //---------------------------------------------------------------
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            // Nejdřív vytvoříš kontroler
            GameControl gc = new GameControl(new GameBoard(Difficulty.EASY));

            // Pak ho předáš do TitleScreen
            TitleScreen ts = new TitleScreen(gc);
            // ts.setVisible(true); // Pokud ho nemáš visible v konstruktoru
        });
    }
    //--------------------------------------------------------------

    Game g = new Game();



    }

