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

        SwingUtilities.invokeLater(TitleScreen::new);
    }
    //--------------------------------------------------------------

        TitleScreen f = new TitleScreen();

    }

