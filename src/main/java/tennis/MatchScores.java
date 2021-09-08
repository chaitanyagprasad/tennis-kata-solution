package tennis;

import lombok.Data;

/**
 * Holds actual score
 */
@Data
public class MatchScores {

    private SetScore playerOneScore;
    private SetScore playerTwoScore;

    private static MatchScores matchScores;

    private MatchScores(SetScore playerOneScore, SetScore playerTwoScore) {
        this.playerOneScore = playerOneScore;
        this.playerTwoScore = playerTwoScore;
    }

    public static MatchScores of(SetScore playerOneScore, SetScore playerTwoScore) {
        if( matchScores == null ) {
            return new MatchScores(playerOneScore, playerTwoScore);
        }
        matchScores.setPlayerOneScore(playerOneScore);
        matchScores.setPlayerTwoScore(playerTwoScore);
        return matchScores;
    }
}
