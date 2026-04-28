import java.util.ArrayList;

public class GameControl {

    private static int playerCounter;
    private GameBoard board;
    private boolean end;
    private int attempts;

    public GameControl(GameBoard board) {
        this.attempts = 0;
        this.end = false;
        this.board = board;

        //TODO: udelej nacitani hracu
        this.playerCounter = 0;

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

                if (cardd != null) {
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

    public void attemptMatch(Card card, Card card2){

        if (match(card, card2)){
            attempts++;

            card.setMatched(true);
            card2.setMatched(true);

            board.setCardNull(card);
            board.setCardNull(card2);

            if(checkEnd()){
                end = true;
                System.out.println(getResoult());

            }
        }

        card.setFlipped(false);
        card2.setFlipped(false);
        attempts++;
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
