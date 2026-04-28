public class Game {

    private GameControl gc;
    private TitleScreen titleScreen;
    private GameBoard gb;

    public Game() {

        this.gb = new GameBoard(Difficulty.EASY);
        this.gc = new GameControl(gb);
        this.titleScreen = new TitleScreen(gc);
    }

    public GameControl getGc() {
        return gc;
    }

    public void setGc(GameControl gc) {
        this.gc = gc;
    }
}
