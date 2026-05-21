import java.awt.*;

public class Card {

    private int ID;             // ID páru – dvě karty se stejným ID tvoří pár
    private Image image;
    private boolean isFlipped;  // karta je momentálně otočená lícem nahoru
    private boolean isMatched;  // karta byla úspěšně spárována

    public Card(int ID, Image image) {
        this.ID = ID;
        this.isMatched = false;
        this.image = image;
        this.isFlipped = false;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public boolean isFlipped() {
        return isFlipped;
    }

    public void setFlipped(boolean flipped) {
        isFlipped = flipped;
    }

    public boolean isMatched() {
        return isMatched;
    }

    public void setMatched(boolean matched) {
        isMatched = matched;
    }
}
