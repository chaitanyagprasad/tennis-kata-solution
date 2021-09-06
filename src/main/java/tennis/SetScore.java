package tennis;

import enums.TennisScore;


/**
 * Holds a set score for players
 */
public class SetScore extends MatchScores {
    public SetScore(TennisScore playerOneScore, TennisScore playerTwoScore) {
        super(playerOneScore, playerTwoScore);
    }

    public static SetScore of(TennisScore firstScore, TennisScore secondScore) {
        return new SetScore(firstScore, secondScore);
    }

}
