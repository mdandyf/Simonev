package simonev.mitrais.com.simonev.espresso;

import android.content.Intent;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.runner.AndroidJUnit4;
import simonev.mitrais.com.simonev.R2;
import simonev.mitrais.com.simonev.view.MainActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.matcher.ViewMatchers.hasImeAction;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static java.util.concurrent.CompletableFuture.allOf;

@RunWith(AndroidJUnit4.class)
public class TestIntent {
    @Rule
    public IntentsTestRule<MainActivity> mActivityRule =
            new IntentsTestRule<>(MainActivity.class);

    @Test
    public void triggerIntentTest() {
        onView(withId(R2.id.sign_in)).perform(click());
        /*intended(allOf(hasAction(Intent.ACTION_MAIN)))*/
    }

}
