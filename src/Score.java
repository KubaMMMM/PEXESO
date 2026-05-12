import java.io.Serializable;

public class Score implements Serializable, Comparable<Score>{

    private String name;
    private int attempts;

    public Score(String name, int attempts) {
        this.name = name;
        this.attempts = attempts;
    }


    public String getName() {
        return name;
    }

    public int getAttempts() {
        return attempts;
    }

    @Override
    public String toString() {
        return name + " - " + attempts;
    }

    @Override
    public int compareTo(Score o) {
       return attempts  - o.attempts;
    }
}