import enums.GameScore;
import exception.MatchOverException;
import tennis.MatchScores;
import tennis.Player;

public class Game {

    static Player playerOne;
    static Player playerTwo;

    public static String winner = "";

    public static MatchScores startGame() {
        playerOne = new Player("Federer");
        playerTwo = new Player("Djockovic");
        return MatchScores.of(playerOne.getSetScore(), playerOne.getSetScore());
    }

//    public static MatchScores resolve(Player pointWinner, Player pointLoser) {
//
//    }

    public static MatchScores pointToFirst() {
        if( !winner.isBlank() || !winner.isEmpty()   ) {
            throw new MatchOverException(
                    "The match is over and winner is" + winner
            );
        }
        if( playerTwo.hasAdvantage() ) {
            playerTwo.loseAdvantage();
            return MatchScores.of(playerOne.getSetScore(), playerTwo.getSetScore());
        }
        if( !isDeuce() && (playerOne.getSetScore().getGameScore().equals(GameScore.FORTY) ||
                playerOne.getSetScore().getGameScore().equals(GameScore.ADVANTAGE)) ) {
            playerOne.winGame();
            if( isThereWinner() ) {
                return null;
            }
            return MatchScores.of(
                    playerOne.getSetScore(),
                    playerTwo.resetScore()
            );
        }
        playerOne.winPoint();
        return MatchScores.of(playerOne.getSetScore(), playerTwo.getSetScore());
    }

    public static MatchScores pointToSecond() {
        if( !winner.isBlank() || !winner.isEmpty()   ) {
            throw new MatchOverException(
                    "The match is over and winner is" + winner
            );
        }
        if( playerOne.hasAdvantage() ) {
            playerOne.loseAdvantage();
            return MatchScores.of(playerOne.getSetScore(), playerTwo.getSetScore());
        }
        if( !isDeuce() && (playerTwo.getSetScore().getGameScore().equals(GameScore.FORTY) ||
                playerTwo.getSetScore().getGameScore().equals(GameScore.ADVANTAGE)) ) {
            playerTwo.winGame();
            if( isThereWinner() ) {
                return null;
            }
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

    private static boolean isThereWinner() {
        if( playerOne.getSetScore().getGameWins() >= 6 && playerTwo.getSetScore().getGameWins() <=4 ) {
            winner = playerOne.getName();
            return true;
        }
        if(
                playerOne.getSetScore().getGameWins() > playerTwo.getSetScore().getGameWins() &&
                        playerOne.getSetScore().getGameWins() >= 6 &&
                        playerOne.getSetScore().getGameWins() > 1
        ) {
            winner = playerOne.getName();
            return true;
        }
        if( playerTwo.getSetScore().getGameWins() >= 6 && playerOne.getSetScore().getGameWins() <=4 ) {
            winner = playerTwo.getName();
            return true;
        }
        if(
                playerTwo.getSetScore().getGameWins() > playerOne.getSetScore().getGameWins() &&
                        playerTwo.getSetScore().getGameWins() >= 6 &&
                        playerTwo.getSetScore().getGameWins() > 1
        ) {
            winner = playerTwo.getName();
            return true;
        }
        return false;
    }

}
