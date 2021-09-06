package enums;

import lombok.Data;
import lombok.Getter;


/**
 * represents possible scores in a set
 */
@Getter
public enum TennisScore {
    WIN("WIN", null),
    ADVANTAGE("A", WIN),
    FORTY("40", ADVANTAGE),
    THIRTY("30", FORTY),
    FIFTEEN("15", THIRTY),
    ZERO("0", FIFTEEN);

    private String stringScore;
    private TennisScore nextScore;

    TennisScore(String stringScore, TennisScore nextScore) {
        this.stringScore = stringScore;
        this.nextScore = nextScore;
    }
}
