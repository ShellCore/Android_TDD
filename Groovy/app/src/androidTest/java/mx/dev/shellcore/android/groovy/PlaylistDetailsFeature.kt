package mx.dev.shellcore.android.groovy

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotExist
import mx.dev.shellcore.android.groovy.MatcherUtils.nthChildOf
import mx.dev.shellcore.android.groovy.playlist.idlingResource
import org.hamcrest.core.AllOf.allOf
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PlaylistDetailsFeature : BaseUiTest() {

    @Test
    fun displaysPlaylistNameAndDetails() {
        navigateToPlaylistDetail(0)

        assertDisplayed("Hard Rock Cafe")

        assertDisplayed("Rock your senses with this timeless signature vibe list. \n\n - Poison \n - You shock me all night \n - Zombie \n - Rock'n Me \n - Thunderstruck \n - I hate myself for loving you \n - Crazy \n - Knockin' on heavens door")
    }

    @Test
    fun displaysLoaderWhileFetchingThePlaylistDetails() {
        IdlingRegistry.getInstance().unregister(idlingResource)
        Thread.sleep(3000)

        navigateToPlaylistDetail(0)

        assertDisplayed(R.id.playlist_detail_loader)
    }

    @Test
    fun hidesLoader() {
        navigateToPlaylistDetail(0)

        assertNotDisplayed(R.id.playlist_detail_loader)
    }

    @Test
    fun displayErrorMessagesWhenNetworkFails() {
        navigateToPlaylistDetail(1)

        assertDisplayed(R.string.generic_error)
    }

    @Test
    fun hidesErrorMessage() {
        navigateToPlaylistDetail(1)

        Thread.sleep(3000)
        assertNotExist(R.string.generic_error)
    }

    private fun navigateToPlaylistDetail(childPosition: Int) {
        onView(
            allOf(
                withId(R.id.playlist_image),
                isDescendantOfA(nthChildOf(withId(R.id.playlist_list), childPosition))
            )
        )
            .perform(ViewActions.click())
    }
}