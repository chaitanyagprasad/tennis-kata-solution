package enums;

import lombok.Getter;


/**
 * represents possible scores in a game
 */
@Getter
public enum GameScore {
    WIN("WIN", null),
    ADVANTAGE("A", WIN),
    FORTY("40", ADVANTAGE),
    THIRTY("30", FORTY),
    FIFTEEN("15", THIRTY),
    ZERO("0", FIFTEEN);

    private String stringScore;
    private GameScore nextScore;

    GameScore(String stringScore, GameScore nextScore) {
        this.stringScore = stringScore;
        this.nextScore = nextScore;
    }
}
