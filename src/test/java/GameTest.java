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
}