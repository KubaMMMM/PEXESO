import java.awt.*;
import java.io.*;
import java.util.ArrayList;


public class GameControl {

    private static int playerCounter;
    private static final String SAVE_FILE = "resources/players.dat";
    private GameBoard board;
    private boolean end;
    private int attempts;

    public GameControl(GameBoard board) {
        this.attempts = 0;
        this.end = false;
        this.board = board;
        loadPlayerCounter();
        playerCounter++;
        savePlayerCounter();


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
