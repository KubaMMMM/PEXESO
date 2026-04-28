import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class GameBoard {
    private ArrayList<ArrayList<Card>> board;
    private Difficulty diff;

    public GameBoard(Difficulty diff) {
        this.diff = diff;
        board = new ArrayList<>();
        createBoard();
    }

    public void createBoard(){

        int count = diff.getCardCount();
        ArrayList<Card> cards = new ArrayList<>();

        for(int i = 0; i < count/2; i++){

            for(int e = 0; e < 2; e++){

                cards.add(new Card(i, null));
                //TODO: obrazky
            }

        }

        Collections.shuffle(cards);

        for(int i = 0; i < diff.getRows(); i++){

            ArrayList<Card> row = new ArrayList<>();

            for(int e = 0; e < diff.getCols(); e++){

                row.add(cards.getFirst());
                cards.removeFirst();
            }

            board.add(row);
        }
    }

    public ArrayList<ArrayList<Card>> getBoard() {
        return board;
    }

    public void setBoard(ArrayList<ArrayList<Card>> board) {
        this.board = board;
    }

    public Difficulty getDiff() {
        return diff;
    }

    public void setDiff(Difficulty diff) {
        this.diff = diff;
    }

    public void setCardNull(Card card) {

        for (ArrayList<Card> rows : board) {

            for (Card cardd : rows) {

                if (cardd.equals(card)) {
                    rows.remove(cardd);
                }
            }

        }
    }


}
