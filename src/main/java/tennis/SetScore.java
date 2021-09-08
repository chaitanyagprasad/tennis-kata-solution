package tennis;

import enums.GameScore;
import lombok.Data;
import tennis.MatchScores;

@Data
public class SetScore {
    private Integer gameWins;
    private GameScore gameScore;

    public SetScore() {
        this.gameWins = 0;
        this.gameScore = GameScore.ZERO;
    }

    public void winPoint() {
        gameScore = gameScore.getNextScore();
    }

    public void winGame() {
        ++ this.gameWins;
    }
}
