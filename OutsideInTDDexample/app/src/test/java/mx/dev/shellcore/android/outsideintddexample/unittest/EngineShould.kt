package mx.dev.shellcore.android.outsideintddexample.unittest

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runBlockingTest
import mx.dev.shellcore.android.outsideintddexample.Engine
import mx.dev.shellcore.android.outsideintddexample.utils.MainCoroutineScopeRule
import org.junit.Rule
import org.junit.Test

class EngineShould {

    @get:Rule
    var coroutinesTestRule = MainCoroutineScopeRule()

    private val engine = Engine()

    @Test
    fun turnOn() = runBlockingTest {
        engine.turnOn()

        assertTrue(engine.isTurnedOn)
    }

    @Test
    fun raiseTheTemperatureWhenTurnsOn() = runBlockingTest {
        engine.turnOn()

        assertEquals(95, engine.temp)
    }
}