public enum Difficulty {
    EASY(4,4),
    MEDIUM(4, 5),
    HARD(6, 6);

    private int rows;
    private int colms;

    Difficulty(int rows, int colms) {
        this.rows = rows;
        this.colms = colms;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return colms;
    }

    public int getCardCount() {
        return rows * colms;
    }
}
