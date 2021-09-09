import enums.GameScore;
import exception.MatchOverException;
import org.junit.jupiter.api.Test;
import tennis.MatchScores;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class GameTest {

    @Test
    void startGame() {
        MatchScores matchScores = Game.startGame();

        assertThat(matchScores.getPlayerOneScore().getGameScore()).isEqualTo(GameScore.ZERO);
        assertThat(matchScores.getPlayerTwoScore().getGameScore()).isEqualTo(GameScore.ZERO);

        assertThat(matchScores.getPlayerOneScore().getGameWins()).isEqualTo(0);
        assertThat(matchScores.getPlayerTwoScore().getGameWins()).isEqualTo(0);
    }

    @Test
    void pointIncrement() {
        Game.startGame();
        MatchScores matchScores = Game.pointToFirst();

        assertThat(
                matchScores.getPlayerOneScore().getGameScore()
        ).isEqualTo(GameScore.FIFTEEN);
        assertThat(
                matchScores.getPlayerTwoScore().getGameScore()
        ).isEqualTo(GameScore.ZERO);

        matchScores = Game.pointToSecond();
        assertThat(
                matchScores.getPlayerOneScore().getGameScore()
        ).isEqualTo(GameScore.FIFTEEN);
        assertThat(
                matchScores.getPlayerTwoScore().getGameScore()
        ).isEqualTo(GameScore.FIFTEEN);
    }

    @Test
    void loseAdvantage() {
        Game.startGame();

        for( int i = 0; i < 3; i++ ) {
            Game.pointToFirst();
            Game.pointToSecond();
        }
        Game.pointToFirst();
        assertThat(
                Game.playerOne.getSetScore().getGameScore()
        ).isEqualTo(GameScore.ADVANTAGE);
        assertThat(
                Game.playerTwo.getSetScore().getGameScore()
        ).isEqualTo(GameScore.FORTY);

        Game.pointToSecond();
        assertThat(
                Game.playerOne.getSetScore().getGameScore()
        ).isEqualTo(GameScore.FORTY);
        assertThat(
                Game.playerTwo.getSetScore().getGameScore()
        ).isEqualTo(GameScore.FORTY);
    }

    @Test
    void winGame() {
        Game.startGame();

        for( int i = 0; i < 3; i++ ) {
            Game.pointToFirst();
        }

        assertThat(
                Game.playerOne.getSetScore().getGameWins()
        ).isEqualTo(0);

        assertThat(
                Game.playerTwo.getSetScore().getGameWins()
        ).isEqualTo(0);

        assertThat(
                Game.playerOne.getSetScore().getGameScore()
        ).isEqualTo(GameScore.FORTY);

        assertThat(
                Game.playerTwo.getSetScore().getGameScore()
        ).isEqualTo(GameScore.ZERO);

        MatchScores matchScores =  Game.pointToFirst();

        assertThat(
                matchScores.getPlayerOneScore().getGameWins()
        ).isEqualTo(1);
        assertThat(
                matchScores.getPlayerTwoScore().getGameWins()
        ).isEqualTo(0);

    }

    @Test
    void deuceToWin() {

        Game.startGame();

        for( int i = 0; i < 3; i++ ) {
            Game.pointToFirst();
            Game.pointToSecond();
        }

        assertThat(
                Game.playerOne.getSetScore().getGameScore()
        ).isEqualTo(GameScore.FORTY);
        assertThat(
                Game.playerTwo.getSetScore().getGameScore()
        ).isEqualTo(GameScore.FORTY);

        Game.pointToSecond();
        assertThat(
                Game.playerOne.getSetScore().getGameScore()
        ).isEqualTo(GameScore.FORTY);
        assertThat(
                Game.playerTwo.getSetScore().getGameScore()
        ).isEqualTo(GameScore.ADVANTAGE);

        Game.pointToFirst();
        assertThat(
                Game.playerOne.getSetScore().getGameScore()
        ).isEqualTo(GameScore.FORTY);
        assertThat(
                Game.playerTwo.getSetScore().getGameScore()
        ).isEqualTo(GameScore.FORTY);

        Game.pointToSecond();
        Game.pointToSecond();
        assertThat(
                Game.playerOne.getSetScore().getGameWins()
        ).isEqualTo(0);
        assertThat(
                Game.playerTwo.getSetScore().getGameWins()
        ).isEqualTo(1);

    }

    @Test
    void playerOneDominates() {
        Game.startGame();

        for(int i = 0; i< 24; i++) {
            Game.pointToFirst();
        }

        assertThat(
                Game.playerOne.getSetScore().getGameWins()
        ).isEqualTo(6);
        assertThat(
                Game.playerTwo.getSetScore().getGameWins()
        ).isEqualTo(0);

        assertThat(
                Game.winner
        ).isEqualTo("Federer");

    }

    @Test
    void playerTwoDominates() {
        Game.startGame();

        for(int i = 0; i< 24; i++) {
            Game.pointToSecond();
        }

        assertThat(
                Game.playerTwo.getSetScore().getGameWins()
        ).isEqualTo(6);
        assertThat(
                Game.playerOne.getSetScore().getGameWins()
        ).isEqualTo(0);

        assertThat(
                Game.winner
        ).isEqualTo("Djockovic");

    }

    @Test
    void gameOverAndTriesToIncrementPoint() {

        Game.startGame();

        for(int i = 0; i< 24; i++) {
            Game.pointToSecond();
        }

        assertThat(
                Game.playerTwo.getSetScore().getGameWins()
        ).isEqualTo(6);
        assertThat(
                Game.playerOne.getSetScore().getGameWins()
        ).isEqualTo(0);

        assertThat(
                Game.winner
        ).isEqualTo("Djockovic");

        assertThatExceptionOfType(MatchOverException.class)
                .isThrownBy(Game::pointToSecond);

    }
}