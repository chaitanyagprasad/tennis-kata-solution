import enums.TennisScore;
import org.junit.jupiter.api.Test;
import tennis.MatchScores;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void startGame() {
        MatchScores matchScores = Game.startGame();

        assertThat(matchScores.getPlayerOneScore()).isEqualTo(TennisScore.ZERO);
        assertThat(matchScores.getPlayerTwoScore()).isEqualTo(TennisScore.ZERO);
    }

    @Test
    void pointIncrement() {
        Game.startGame();
        MatchScores matchScores = Game.pointToFirst();

        assertThat(matchScores.getPlayerOneScore()).isEqualTo(TennisScore.FIFTEEN);
        assertThat(matchScores.getPlayerTwoScore()).isEqualTo(TennisScore.ZERO);

        matchScores = Game.pointToSecond();
        assertThat(matchScores.getPlayerOneScore()).isEqualTo(TennisScore.FIFTEEN);
        assertThat(matchScores.getPlayerTwoScore()).isEqualTo(TennisScore.FIFTEEN);
    }

    @Test
    void loseAdvantage() {
        Game.startGame();

        for( int i = 0; i < 3; i++ ) {
            Game.pointToFirst();
            Game.pointToSecond();
        }
        // advantage to player one
        Game.pointToFirst();
        assertThat(Game.playerOne.getScore()).isEqualTo(TennisScore.ADVANTAGE);
        assertThat(Game.playerTwo.getScore()).isEqualTo(TennisScore.FORTY);

        Game.pointToSecond();
        assertThat(Game.playerOne.getScore()).isEqualTo(TennisScore.FORTY);
        assertThat(Game.playerTwo.getScore()).isEqualTo(TennisScore.FORTY);



    }
}