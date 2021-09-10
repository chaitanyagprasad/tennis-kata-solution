import enums.GameScore;
import exception.MatchOverException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import tennis.MatchScores;
import tennis.Player;

import java.util.function.BiConsumer;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.*;

class GameTest {

    @BeforeEach
    void setUp() {
        Game.startGame();
    }

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

        MatchScores matchScores = Game.resolve(
                Game.playerOne,
                Game.playerTwo
        );

        assertThat(
                matchScores.getPlayerOneScore().getGameScore()
        ).isEqualTo(GameScore.FIFTEEN);
        assertThat(
                matchScores.getPlayerTwoScore().getGameScore()
        ).isEqualTo(GameScore.ZERO);

        matchScores = Game.resolve(
                Game.playerTwo,
                Game.playerOne
        );
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
            Game.resolve(
                    Game.playerOne,
                    Game.playerTwo
            );
            Game.resolve(
                    Game.playerTwo,
                    Game.playerOne
            );
        }
        Game.resolve(
                Game.playerOne,
                Game.playerTwo
        );
        assertThat(
                Game.playerOne.getSetScore().getGameScore()
        ).isEqualTo(GameScore.ADVANTAGE);
        assertThat(
                Game.playerTwo.getSetScore().getGameScore()
        ).isEqualTo(GameScore.FORTY);

        Game.resolve(
                Game.playerTwo,
                Game.playerOne
        );
        assertThat(
                Game.playerOne.getSetScore().getGameScore()
        ).isEqualTo(GameScore.FORTY);
        assertThat(
                Game.playerTwo.getSetScore().getGameScore()
        ).isEqualTo(GameScore.FORTY);
    }

    @Test
    void winGame() {
        for( int i = 0; i < 3; i++ ) {
            Game.resolve(
                    Game.playerOne,
                    Game.playerTwo
            );
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

        MatchScores matchScores =  Game.resolve(
                Game.playerOne,
                Game.playerTwo
        );

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
            Game.resolve(
                    Game.playerOne,
                    Game.playerTwo
            );
            Game.resolve(
                    Game.playerTwo,
                    Game.playerOne
            );
        }

        assertThat(
                Game.playerOne.getSetScore().getGameScore()
        ).isEqualTo(GameScore.FORTY);
        assertThat(
                Game.playerTwo.getSetScore().getGameScore()
        ).isEqualTo(GameScore.FORTY);

        Game.resolve(
                Game.playerTwo,
                Game.playerOne
        );
        assertThat(
                Game.playerOne.getSetScore().getGameScore()
        ).isEqualTo(GameScore.FORTY);
        assertThat(
                Game.playerTwo.getSetScore().getGameScore()
        ).isEqualTo(GameScore.ADVANTAGE);

        Game.resolve(
                Game.playerOne,
                Game.playerTwo
        );
        assertThat(
                Game.playerOne.getSetScore().getGameScore()
        ).isEqualTo(GameScore.FORTY);
        assertThat(
                Game.playerTwo.getSetScore().getGameScore()
        ).isEqualTo(GameScore.FORTY);

        Game.resolve(
                Game.playerTwo,
                Game.playerOne
        );
        Game.resolve(
                Game.playerTwo,
                Game.playerOne
        );
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

        for(int i = 0; i < 24; i++) {
            Game.resolve(
                    Game.playerOne,
                    Game.playerTwo
            );
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
        for(int i = 0; i< 24; i++) {
            Game.resolve(
                    Game.playerTwo,
                    Game.playerOne
            );
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

        for(int i = 0; i < 24; i++) {
            Game.resolve(
                    Game.playerTwo,
                    Game.playerOne
            );
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
                .isThrownBy(() -> Game.resolve(
                        Game.playerOne,
                        Game.playerTwo
                ));

    }

    @Test
    void testTieBreaker() {
        for(int i = 0; i < 20; i++) {
            Game.resolve(
                    Game.playerTwo,
                    Game.playerOne
            );
        }

        for(int i = 0; i < 20; i++) {
            Game.resolve(
                    Game.playerOne,
                    Game.playerTwo
            );
        }

        for( int i = 0; i < 4; i++ ) {
            Game.resolve(
                    Game.playerOne,
                    Game.playerTwo
            );
        }

        assertThat(
                Game.winner
        ).isEmpty();

        for( int i = 0; i < 4; i++ ) {
            Game.resolve(
                    Game.playerTwo,
                    Game.playerOne
            );
        }
        assertThat(
                Game.isTieBreaker
        ).isTrue();
    }
}