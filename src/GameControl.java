import java.util.ArrayList;

public class GameControl {

    private GameBoard board;
    private boolean end;
    private int attempts;

    public GameControl(GameBoard board) {
        this.attempts = 0;
        this.end = false;
        this.board = board;
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

    public void end(){
        ....
    }

    public void attemptMatch(Card card, Card card2){

        if (match(card, card2)){
            attempts++;

            card.setMatched(true);
            card2.setMatched(true);

            board.setCardNull(card);
            board.setCardNull(card2);

            if(checkEnd()){
                enddddddd
            }
        }

        card.setFlipped(false);
        card2.setFlipped(false);
        attempts++;
    }


}
