package hk.ust.cse.comp107x.chatclientnetworkfinal;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.ImageView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasSibling;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.Matchers.not;

/**
 * Created by muppala on 15/8/15.
 */
public class ContactsTest  extends ActivityInstrumentationTestCase2<Contacts> {

    private Contacts contacts;

    public ContactsTest() {
        super(Contacts.class);
    }


    @Before
    public void setUp() throws Exception {
        super.setUp();
        injectInstrumentation(getInstrumentation());
        contacts = getActivity();
    }

    public void testPicture() {

        // onView(withId(R.id.avatar))
                // .check(matches(withText("This is a test")));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Drawable defaultImage = contacts.getResources().getDrawable(R.mipmap.ic_launcher);

        onView(allOf(withId(R.id.avatar), hasSibling(withText("John"))))
                // .check(matches(withDrawable(R.mipmap.ic_launcher)));
                 .check(matches(not(isImageTheSame(defaultImage))));

        onView(allOf(withId(R.id.avatar), hasSibling(withText("Paul"))))
                // .check(matches(withDrawable(R.mipmap.ic_launcher)));
                .check(matches(not(isImageTheSame(defaultImage))));

        onView(allOf(withId(R.id.avatar), hasSibling(withText("George"))))
                // .check(matches(withDrawable(R.mipmap.ic_launcher)));
                .check(matches(not(isImageTheSame(defaultImage))));

        onView(allOf(withId(R.id.avatar), hasSibling(withText("Ringo"))))
                // .check(matches(withDrawable(R.mipmap.ic_launcher)));
                .check(matches(not(isImageTheSame(defaultImage))));

    }

    public static Matcher<View> isImageTheSame(final Drawable drawable) {
        return new BoundedMatcher<View, ImageView>(ImageView.class) {

            @Override
            public void describeTo(Description description) {
                description.appendText("is image the same as: ");
                description.appendValue(drawable);
            }

            @Override
            public boolean matchesSafely(ImageView view) {
                Bitmap bitmapCompare = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
                Drawable drawable = view.getDrawable();
                Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
                return bitmapCompare.sameAs(bitmap);
            }
        };
    }

}
