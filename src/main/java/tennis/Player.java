package tennis;

import enums.GameScore;
import lombok.Data;

@Data
public class Player {

    private String name;
//    private GameScore score;
    private SetScore setScore;

    public Player(String name) {
        this.name = name;
//        this.score = GameScore.ZERO;
        this.setScore = new SetScore();
    }

    /*public SetScore getScore() {
        return this.score;
    }*/

    public SetScore getSetScore(){
        return this.setScore;
    }

    public void winGame() {
        Integer currentGameWins = this.setScore.getGameWins();
        this.setScore.setGameWins(++currentGameWins);
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
}
