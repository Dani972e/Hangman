package game.hangman.data.dto;


import java.io.Serializable;

public class HighscoreDTO implements Comparable<HighscoreDTO>, Serializable {


    private String name;
    private int highscore;

    public HighscoreDTO(int highscore, String name) {
        this.name = name;
        this.highscore = highscore;
    }

    public String getName() {
        return name;
    }

    public int getHighscore() {
        return highscore;
    }

    @Override
    public int compareTo(HighscoreDTO other) {
        if (this.highscore == other.highscore)
            return 0;
        else if (this.highscore < other.highscore)
            return 1;
        else
            return -1;
    }
}
