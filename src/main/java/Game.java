import enums.GameScore;
import tennis.MatchScores;
import tennis.Player;

public class Game {

    static Player playerOne;
    static Player playerTwo;

    public static MatchScores startGame() {
        playerOne = new Player("Federer");
        playerTwo = new Player("Djockovic");
        return MatchScores.of(playerOne.getSetScore(), playerOne.getSetScore());
    }

    public static MatchScores pointToFirst() {
        if( playerTwo.hasAdvantage() ) {
            playerTwo.loseAdvantage();
            return MatchScores.of(playerOne.getSetScore(), playerTwo.getSetScore());
        }
        if( !isDeuce() && playerOne.getSetScore().getGameScore().equals(GameScore.FORTY) ) {
            playerOne.winGame();
            return MatchScores.of(
                    playerOne.getSetScore(),
                    playerTwo.resetScore()
            );
        }
        playerOne.winPoint();
        return MatchScores.of(playerOne.getSetScore(), playerTwo.getSetScore());
    }

    public static MatchScores pointToSecond() {
        if( playerOne.hasAdvantage() ) {
            playerOne.loseAdvantage();
            return MatchScores.of(playerOne.getSetScore(), playerTwo.getSetScore());
        }
        if( !isDeuce() && playerTwo.getSetScore().getGameScore().equals(GameScore.FORTY) ) {
            playerTwo.winGame();
            return MatchScores.of(
                    playerOne.resetScore(),
                    playerTwo.getSetScore()
            );
        }
        playerTwo.winPoint();
        return MatchScores.of(playerOne.getSetScore(), playerTwo.getSetScore());
    }

    private static boolean isDeuce() {
        return playerOne.getSetScore().getGameScore().equals(GameScore.FORTY) &&
                playerTwo.getSetScore().getGameScore().equals(GameScore.FORTY);
    }

}
