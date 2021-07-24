package mx.dev.shellcore.android.groovy

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import mx.dev.shellcore.android.groovy.MatcherUtils.nthChildOf
import org.hamcrest.core.AllOf.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PlaylistDetailsFeature : BaseUiTest() {

    @Test
    fun displaysPlaylistNameAndDetails() {
        onView(
            allOf(
                withId(R.id.playlist_image),
                isDescendantOfA(nthChildOf(withId(R.id.playlist_list), 0))))
            .perform(ViewActions.click())

        assertDisplayed("Hard Rock Cafe")

        assertDisplayed("Rock your senses with this timeless signature vibe list. \n\n - Poison \n - You shock me all night \n - Zombie \n - Rock'n Me \n - Thunderstruck \n - I hate myself for loving you \n - Crazy \n - Knockin' on heavens door")
    }
}