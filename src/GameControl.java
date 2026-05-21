import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;


public class GameControl {

    private static int playerCounter;
    private static final String SAVE_FILE = "resources/players.dat";
    private GameBoard board;
    private boolean end;
    private int attempts;
    private ArrayList<Score> scores;

    public GameControl(GameBoard board) {
        this.attempts = 0;
        this.end = false;
        this.board = board;
        loadPlayerCounter();    // načte aktuální počet hráčů ze souboru
        playerCounter++;        // přičte aktuálního hráče
        savePlayerCounter();    // uloží zpět
        scores = new ArrayList<>();



    }


    // ===================== NAČTENÍ Z SOUBORU =====================
    public static void loadPlayerCounter() {
        File file = new File(SAVE_FILE);

        if (!file.exists()) {
            // soubor neexistuje – vytvoř ho s hodnotou 1
            playerCounter = 0;
            savePlayerCounter();
            return;
        }

        try (DataInputStream dis = new DataInputStream(
                new FileInputStream(file))) {
            playerCounter = dis.readInt();
        } catch (IOException e) {
            System.out.println("Nelze načíst počet hráčů: " + e.getMessage());
            playerCounter = 0;
        }
    }


    //==========================NACTENI SKORE====================
    public void loadScores() {

        File file = new File("resources/skore.dat");

        if (!file.exists()) {
            return;
        }

        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(file))) {

            scores = (ArrayList<Score>) ois.readObject();

        } catch (Exception e) {
            System.out.println("Chyba pri nacitani score");
        }
    }

    //==========================ULOZENI SKORE====================
    public void saveScores() {

        try (ObjectOutputStream oos =
                     new ObjectOutputStream(
                             new FileOutputStream("resources/skore.dat"))) {

            oos.writeObject(scores);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addScore(String name) {

        scores.add(new Score(name, attempts));

        Collections.sort(scores);

        saveScores();
    }


    // ===================== ULOŽENÍ DO SOUBORU =====================
    public static void savePlayerCounter() {
        File file = new File(SAVE_FILE);

        // vytvoř složku resources pokud neexistuje
        file.getParentFile().mkdirs();

        try (DataOutputStream dos = new DataOutputStream(
                new FileOutputStream(file))) {
            dos.writeInt(playerCounter);
        } catch (IOException e) {
            System.out.println("Nelze uložit počet hráčů: " + e.getMessage());
        }
    }

    public static int getPlayerCounter() {
        return playerCounter;
    }



    // ===================== POROVNÁNÍ KARET =====================
    public boolean match(Card card, Card card2){

        if(card.getID() == card2.getID()){
            return true;
        }
        return false;
    }

    public Card flipCard(int colum, int row){
        Card card = board.getBoard().get(row).get(colum);
        card.setFlipped(true);
        return card;
    }

    // ===================== KONTROLA KONCE HRY =====================
    // vrátí true pokud jsou všechny karty spárovány
    public Boolean checkEnd(){
        for (ArrayList<Card> rows : board.getBoard()) {
            for (Card cardd : rows) {
                if (!cardd.isMatched()) {
                    return false;
                }
            }
        }
        return true;
    }

    public String getResoult(){

        StringBuilder sb = new StringBuilder();

        sb.append("Počet pokusů: ").append(attempts).append("\n");
        sb.append("Obtížnost: ").append(board.getDiff()).append("\n");

        return sb.toString();
    }

    // ===================== POKUS O SPÁROVÁNÍ =====================
    // zavolá se po otočení druhé karty – vrátí true při shodě
    public boolean attemptMatch(Card card, Card card2){

        attempts++; // OPRAVA: počítáme jednou (bylo dvakrát)

        if (match(card, card2)){
            card.setMatched(true);
            card2.setMatched(true);

            board.setCardNull(card);
            board.setCardNull(card2);

            if(checkEnd()){
                end = true;
                System.out.println(getResoult());
            }

            return true;
        }

        // neshoda – karty se vrátí lícem dolů
        card.setFlipped(false);
        card2.setFlipped(false);
        return false;
    }


    public static void setPlayerCounter(int playerCounter) {
        GameControl.playerCounter = playerCounter;
    }

    public GameBoard getBoard() {
        return board;
    }

    public void setBoard(GameBoard board) {
        this.board = board;
    }

    public boolean isEnd() {
        return end;
    }

    public ArrayList<Score> getScores() {
        return scores;
    }

    public void setScores(ArrayList<Score> scores) {
        this.scores = scores;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }
}
