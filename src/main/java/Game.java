import enums.GameScore;
import exception.MatchOverException;
import tennis.MatchScores;
import tennis.Player;

public class Game {

    static Player playerOne;
    static Player playerTwo;

    public static String winner;

    static boolean isTieBreaker = false;

    public static MatchScores startGame() {
        playerOne = new Player("Federer");
        playerTwo = new Player("Djockovic");
        winner = new String();
        return MatchScores.of(playerOne.getSetScore(), playerOne.getSetScore());
    }

    public static MatchScores resolve(Player pointWinner, Player pointLoser) {
        if( !winner.isBlank() || !winner.isEmpty()   ) {
            throw new MatchOverException(
                    "The match is over and winner is " + winner
            );
        }
        if( isTieBreaker ) {
            pointWinner.winTieBreakGame();
            if( isThereWinner(playerOne, playerTwo) ) {
                return null;
            }
            return MatchScores.of(pointWinner.getSetScore(), pointLoser.getSetScore());
        }
        if( pointLoser.hasAdvantage() ) {
            pointLoser.loseAdvantage();
            return MatchScores.of(pointWinner.getSetScore(), pointLoser.getSetScore());
        }
        if( !isDeuce() && (pointWinner.getSetScore().getGameScore().equals(GameScore.FORTY) ||
                pointWinner.getSetScore().getGameScore().equals(GameScore.ADVANTAGE)) ) {
            pointWinner.winGame();
            if( isThereWinner(pointWinner, pointLoser) ) {
                return null;
            }
            if( pointWinner.getSetScore().getGameWins() == 6 && pointLoser.getSetScore().getGameWins() == 6 ) {
                isTieBreaker = true;
            }
            return MatchScores.of(
                    pointWinner.getSetScore(),
                    pointLoser.resetScore()
            );
        }
        pointWinner.winPoint();
        return MatchScores.of(pointWinner.getSetScore(), pointLoser.getSetScore());
    }


    private static boolean isDeuce() {
        return playerOne.getSetScore().getGameScore().equals(GameScore.FORTY) &&
                playerTwo.getSetScore().getGameScore().equals(GameScore.FORTY);
    }

    private static boolean isThereWinner(Player playerOne, Player playerTwo) {
        if( playerOne.getSetScore().getGameWins() >= 6 && playerTwo.getSetScore().getGameWins() <=4 ) {
            winner = playerOne.getName();
            return true;
        }
        if(     playerOne.getSetScore().getGameWins() >= 6 &&
                playerOne.getSetScore().getGameWins() > playerTwo.getSetScore().getGameWins() &&
                        playerOne.getSetScore().getGameWins() - playerTwo.getSetScore().getGameWins() > 1
        ) {
            winner = playerOne.getName();
            return true;
        }
        if( playerTwo.getSetScore().getGameWins() >= 6 && playerOne.getSetScore().getGameWins() <=4 ) {
            winner = playerTwo.getName();
            return true;
        }
        if(     playerTwo.getSetScore().getGameWins() >= 6 &&
                playerTwo.getSetScore().getGameWins() > playerOne.getSetScore().getGameWins() &&
                        playerTwo.getSetScore().getGameWins() -
                        playerOne.getSetScore().getGameWins() > 1
        ) {
            winner = playerTwo.getName();
            return true;
        }
        return false;
    }

}
