package simonev.mitrais.com.simonev.espresso;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.contrib.NavigationViewActions;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import androidx.test.uiautomator.UiDevice;
import simonev.mitrais.com.simonev.R;
import simonev.mitrais.com.simonev.R2;
import simonev.mitrais.com.simonev.view.LoginActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class TestUserChangeTabs {
    private String mStringUsername;
    private String mStringPassword;

    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule
            = new ActivityTestRule<>(LoginActivity.class);

    @Before
    public void init() {
        mStringUsername = "user@mitrais.com";
        mStringPassword = "user1";
    }

    @Test
    public void userSelectTabsTest() {
        // Type text and then press the button.
        onView(withId(R2.id.email))
                .perform(typeText(mStringUsername), closeSoftKeyboard());
        onView(withId(R2.id.password))
                .perform(typeText(mStringPassword), closeSoftKeyboard());

        //checking if email & password form has been filled
        onView(withId(R2.id.email))
                .check(matches(withText(mStringUsername)));
        onView(withId(R2.id.password))
                .check(matches(withText(mStringPassword)));

        //click login button
        onView(withId(R.id.sign_in)).perform(click());

        //open nav detail
        onView(withId(R.id.nav_view))
                .perform(click());
        onView(withText("Detail")).perform(click());

        // press back to the main
        UiDevice mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        mDevice.pressBack();
        mDevice.pressBack();
    }


}
