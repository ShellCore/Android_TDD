package mx.dev.shellcore.android.groovy

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PlaylistFeature {

    @get:Rule var rule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun displayScreenTitle() {
        assertDisplayed("Playlists")
    }
}