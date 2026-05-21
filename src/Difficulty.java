public enum Difficulty {
    EASY(4,4),      // 4×4 = 16 karet
    MEDIUM(4, 5),   // 4×5 = 20 karet
    HARD(6, 6);     // 6×6 = 36 karet

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
