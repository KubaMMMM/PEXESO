import java.awt.*;

public class Card {

    private int ID;
    private Image image;
    private boolean isFlipped;
    private boolean isMatched;

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
