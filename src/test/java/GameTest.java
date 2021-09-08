import enums.GameScore;
import org.junit.jupiter.api.Test;
import tennis.MatchScores;

import static org.assertj.core.api.Assertions.assertThat;

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
}