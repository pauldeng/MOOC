package hk.ust.cse.comp107x.timegreetfriend;

import android.test.ActivityInstrumentationTestCase2;
import org.junit.Before;

import java.util.Calendar;
import java.util.Date;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by muppala on 1/5/15.
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private MainActivity mainActivity;

    public MainActivityTest() {
        super(MainActivity.class);
    }


    @Before
    public void setUp() throws Exception {
        super.setUp();
        injectInstrumentation(getInstrumentation());
        mainActivity = getActivity();
    }

    public void testChangeText() {
        // Type text and then press the button.
        onView(withId(R.id.editFriendName))
                .perform(typeText("John"), closeSoftKeyboard());
        onView(withId(R.id.greetButton)).perform(click());

        // Check that the text was changed.

        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int hour = cal.get(Calendar.HOUR_OF_DAY);

        if (hour < 6 || hour >= 21 )
            onView(withId(R.id.textMessage)).check(matches(withText(mainActivity.getString(R.string.goodnight) + "John!")));
        else if (hour >= 6 && hour < 12)
            onView(withId(R.id.textMessage)).check(matches(withText(mainActivity.getString(R.string.goodmorning) + "John!")));
        else if (hour >= 12 && hour < 17)
            onView(withId(R.id.textMessage)).check(matches(withText(mainActivity.getString(R.string.goodafternoon) + "John!")));
        else if (hour >= 17 && hour < 21)
            onView(withId(R.id.textMessage)).check(matches(withText(mainActivity.getString(R.string.goodevening) + "John!")));

    }

}