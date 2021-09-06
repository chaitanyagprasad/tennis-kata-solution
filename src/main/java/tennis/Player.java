package tennis;

import enums.TennisScore;
import lombok.Data;

@Data
public class Player {

    private String name;
    private TennisScore score;

    public Player(String name) {
        this.name = name;
        this.score = TennisScore.ZERO;
    }

    public TennisScore getScore() {
        return this.score;
    }

    public void winPoint() {
        score = score.getNextScore();
    }

    public void loseAdvantage() {
        score = TennisScore.FORTY;
    }

    public boolean hasAdvantage() {
        return this.score.equals(TennisScore.ADVANTAGE);
    }
}
