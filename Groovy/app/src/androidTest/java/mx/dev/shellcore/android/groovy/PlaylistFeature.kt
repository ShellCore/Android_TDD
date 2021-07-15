package mx.dev.shellcore.android.groovy

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.schibsted.spain.barista.assertion.BaristaRecyclerViewAssertions.assertRecyclerViewItemCount
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed
import com.schibsted.spain.barista.internal.matcher.DrawableMatcher.Companion.withDrawable
import mx.dev.shellcore.android.groovy.playlist.idlingResource
import org.hamcrest.CoreMatchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PlaylistFeature: BaseUiTest() {

    @get:Rule
    var rule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun displayScreenTitle() {
        assertDisplayed("Playlists")
    }

    @Test
    fun displayListOfPlaylists() {
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
                isDescendantOfA(MatcherUtils.nthChildOf(withId(R.id.playlist_list), 1))))
            .check(matches(withDrawable(R.mipmap.playlist)))
            .check(matches(isDisplayed()))
    }

    @Test
    fun displaysLoaderWhileFetchingThePlaylists() {
        IdlingRegistry.getInstance().unregister(idlingResource)
        assertDisplayed(R.id.loader)
    }

    @Test
    fun hidesLoader() {
        assertNotDisplayed(R.id.loader)
    }

    @Test
    fun displayRockImageForRockListItems() {
        onView(
            allOf(
                withId(R.id.playlist_image),
                isDescendantOfA(MatcherUtils.nthChildOf(withId(R.id.playlist_list), 0))))
            .check(matches(withDrawable(R.mipmap.rock)))
            .check(matches(isDisplayed()))
        onView(
            allOf(
                withId(R.id.playlist_image),
                isDescendantOfA(MatcherUtils.nthChildOf(withId(R.id.playlist_list), 3))))
            .check(matches(withDrawable(R.mipmap.rock)))
            .check(matches(isDisplayed()))
    }

    @Test
    fun navigateToDetailScreen() {
        onView(
            allOf(
                withId(R.id.playlist_image),
                isDescendantOfA(MatcherUtils.nthChildOf(withId(R.id.playlist_list), 0))))
            .perform(click())

        assertDisplayed(R.id.playlist_detail_root)
    }
}