package hk.ust.cse.comp107x.shootinggamescore;

/**
 * Created by muppala on 2/7/15.
 */

import android.graphics.Color;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.SmallTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * JUnit4 unit tests for the Score class logic.
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class ScoreTest {


    private Score score;

    @Before
    public void setUp() {
        score = new Score(Color.BLACK);
    }

    @Test
    public void getInitialScore () {
        int scoreval = score.getScore();
        assertThat(scoreval, is(equalTo(0)));
    }

    @Test
    public void incrementAndGetScore () {
        score.incrementScore();
        int scoreval = score.getScore();
        assertThat(scoreval, is(equalTo(1)));
    }

    @Test
    public void decrementAndGetScore () {
        score.decrementScore();
        int scoreval = score.getScore();
        assertThat(scoreval, is(equalTo(-1)));
    }

}
