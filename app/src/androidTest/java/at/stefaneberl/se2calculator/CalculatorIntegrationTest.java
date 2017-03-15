package at.stefaneberl.se2calculator;

import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.CoreMatchers.not;

/**
 * Integration test.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class CalculatorIntegrationTest {

    @Rule
    public ActivityTestRule<CalculatorActivity> mActivityRule = new ActivityTestRule<>(
            CalculatorActivity.class);

    @Test
    public void outputViewsAreNotVisibleByDefault() {

        // then
        onView(withId(R.id.textResult))
                .check(matches(withEffectiveVisibility(Visibility.INVISIBLE)));
        onView(withId(R.id.textOutput))
                .check(matches(withEffectiveVisibility(Visibility.INVISIBLE)));
    }

    @Test
    public void divideIsNotVisibleUnlessDividendAndDivisorAreGiven() {

        // when no input is given, then
        onView(withId(R.id.buttonDivide))
                .check(matches(not(isEnabled())));

        // when only dividend is given
        onView(withId(R.id.editDividend))
                .perform(typeText("10"), ViewActions.closeSoftKeyboard());
        // then
        onView(withId(R.id.buttonDivide))
                .check(matches(not(isEnabled())));

        // when only divisor is given
        onView(withId(R.id.editDividend))
                .perform(clearText(), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.editDivisor))
                .perform(typeText("10"), ViewActions.closeSoftKeyboard());
        // then
        onView(withId(R.id.buttonDivide))
                .check(matches(not(isEnabled())));

        // when both dividend and divisor are given
        onView(withId(R.id.editDividend))
                .perform(typeText("10"), ViewActions.closeSoftKeyboard());

        // then
        onView(withId(R.id.buttonDivide))
                .check(matches(isEnabled()));
    }

    @Test
    public void calculate() throws Exception {

        // when
        onView(withId(R.id.editDividend))
                .perform(typeText("10"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.editDivisor))
                .perform(typeText("2"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.buttonDivide))
                .perform(click());

        // then
        onView(withId(R.id.textResult))
                .check(matches(withText("5.0")));
        onView(withId(R.id.textOutput))
                .check(matches(withText(R.string.result)));
    }

    @Test
    public void calculateWithDivisionByZero() throws Exception {

        // when
        onView(withId(R.id.editDividend))
                .perform(typeText("10"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.editDivisor))
                .perform(typeText("0"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.buttonDivide))
                .perform(click());

        // then
        onView(withId(R.id.textResult))
                .check(matches(withText(R.string.divisor_zero)))
                .check(matches(withEffectiveVisibility(Visibility.VISIBLE)));
        onView(withId(R.id.textOutput))
                .check(matches(withText(R.string.error)))
                .check(matches(withEffectiveVisibility(Visibility.VISIBLE)));
    }
}
