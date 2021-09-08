package tennis;

import enums.GameScore;
import lombok.Data;

@Data
public class Player {

    private String name;
    private SetScore setScore;

    public Player(String name) {
        this.name = name;
        this.setScore = new SetScore();
    }

    public SetScore getSetScore(){
        return this.setScore;
    }

    public void winGame() {
        this.setScore.winGame();
    }

    public void winPoint() {
        setScore.winPoint();
    }

    public void loseAdvantage() {
        this.setScore.setGameScore(GameScore.FORTY);
    }

    public boolean hasAdvantage() {
        return this.setScore.getGameScore().equals(GameScore.ADVANTAGE);
    }

    public SetScore resetScore() {
        return this.setScore.resetScore();
    }
}
