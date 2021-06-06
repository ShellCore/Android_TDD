package mx.dev.shellcore.android.outsideintddexample.unittest

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import mx.dev.shellcore.android.outsideintddexample.Engine
import org.junit.Test

class EngineShould {

    private val engine = Engine()

    @Test
    fun turnOn() {
        engine.turnOn()

        assertTrue(engine.isTurnedOn)
    }

    @Test
    fun raiseTheTemperatureWhenTurnsOn() {
        engine.turnOn()

        assertEquals(95, engine.temp)
    }
}