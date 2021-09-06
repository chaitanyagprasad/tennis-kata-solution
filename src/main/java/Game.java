import enums.TennisScore;
import tennis.MatchScores;
import tennis.Player;
import tennis.SetScore;

public class Game {

    static Player playerOne;
    static Player playerTwo;

    public static MatchScores startGame() {
        playerOne = new Player("Federer");
        playerTwo = new Player("Djockovic");
        return new MatchScores(
                playerOne.getScore(),
                playerTwo.getScore()
        );
    }

    public static MatchScores pointToFirst() {
        if( playerTwo.hasAdvantage() ) {
            playerTwo.loseAdvantage();
            return SetScore.of(playerOne.getScore(), playerTwo.getScore());
        }
        playerOne.winPoint();
        return SetScore.of(playerOne.getScore(), playerTwo.getScore());
    }

    public static MatchScores pointToSecond() {
        if( playerOne.hasAdvantage() ) {
            playerOne.loseAdvantage();
            return SetScore.of(playerOne.getScore(), playerTwo.getScore());
        }
        playerTwo.winPoint();
        return SetScore.of(playerOne.getScore(), playerTwo.getScore());
    }

}
