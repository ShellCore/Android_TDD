package mx.dev.shellcore.android.groovy

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.schibsted.spain.barista.assertion.BaristaRecyclerViewAssertions.assertRecyclerViewItemCount
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed
import com.schibsted.spain.barista.internal.matcher.DrawableMatcher.Companion.withDrawable
import org.hamcrest.CoreMatchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PlaylistFeature {

    @get:Rule
    var rule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun displayScreenTitle() {
        assertDisplayed("Playlists")
    }

    @Test
    fun displayListOfPlaylists() {
        Thread.sleep(4000)

        assertRecyclerViewItemCount(R.id.playlist_list, 10)
        onView(
            allOf(
                withId(R.id.playlist_name),
                isDescendantOfA(MatcherUtils.nthChildOf(withId(R.id.playlist_list), 0))))
            .check(matches(withText("Hard Rock Cafe")))
            .check(matches(isDisplayed()))
        onView(
            allOf(
                withId(R.id.playlist_category),
                isDescendantOfA(MatcherUtils.nthChildOf(withId(R.id.playlist_list), 0))))
            .check(matches(withText("rock")))
            .check(matches(isDisplayed()))
        onView(
            allOf(
                withId(R.id.playlist_image),
                isDescendantOfA(MatcherUtils.nthChildOf(withId(R.id.playlist_list), 0))))
            .check(matches(withDrawable(R.mipmap.playlist)))
            .check(matches(isDisplayed()))
    }

    @Test
    fun displaysLoaderWhileFetchingThePlaylists() {
        assertDisplayed(R.id.loader)
    }

    @Test
    fun hidesLoader() {
        Thread.sleep(4000)
        assertNotDisplayed(R.id.loader)
    }
}