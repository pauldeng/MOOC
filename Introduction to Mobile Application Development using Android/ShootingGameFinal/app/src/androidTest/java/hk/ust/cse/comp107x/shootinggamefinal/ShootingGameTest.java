package hk.ust.cse.comp107x.shootinggamefinal;

import android.content.Context;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;
import android.util.Log;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by muppala on 13/7/15.
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class ShootingGameTest{


    @Rule
    public ActivityTestRule<ShootingGame> mActivityRule = new ActivityTestRule<>(ShootingGame.class);

    @Test
    public void testNumSounds() throws Exception {
        Context context = mActivityRule.getActivity().getBaseContext();
        SoundEffects.INSTANCE.setContext(context);
        int numSounds = SoundEffects.INSTANCE.getNumSounds();
        Log.i("Test ", "numsounds is " + numSounds);
        assertThat(numSounds, is(equalTo(3)));
    }

}