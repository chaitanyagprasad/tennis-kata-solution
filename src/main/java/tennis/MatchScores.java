package tennis;

import enums.TennisScore;
import lombok.Data;

/**
 * Holds actual score
 */
@Data
public class MatchScores {

    private TennisScore playerOneScore;
    private TennisScore playerTwoScore;

    public MatchScores(TennisScore playerOneScore, TennisScore playerTwoScore) {
        this.playerOneScore = playerOneScore;
        this.playerTwoScore = playerTwoScore;
    }
}
