package by.test.game.ui.entity;

import java.util.Comparator;

/**
 * Created by kirila on 28.2.17.
 */

public class ScoreItem implements Comparator<ScoreItem> {

    private String mScore;

    public ScoreItem() {

    }

    public ScoreItem(String score) {
        mScore = score;
    }

    public String getScore() {
        return mScore;
    }

    public void setScore(String score) {
        mScore = score;
    }

    @Override
    public int compare(ScoreItem scoreItem, ScoreItem t1) {
        return Integer.parseInt(t1.getScore()) - Integer.parseInt(scoreItem.getScore());
    }
}
