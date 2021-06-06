package mx.dev.shellcore.android.outsideintddexample.acceptancetest

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runBlockingTest
import mx.dev.shellcore.android.outsideintddexample.Car
import mx.dev.shellcore.android.outsideintddexample.Engine
import mx.dev.shellcore.android.outsideintddexample.utils.MainCoroutineScopeRule
import org.junit.Rule
import org.junit.Test

class CarFeatures {

    @get:Rule
    var coroutinesTestRule = MainCoroutineScopeRule()

    private val engine = Engine()
    private val car = Car(6.0, engine)

    @Test
    fun carIsLoosingFuelWhenItTurnsOn() = runBlockingTest {
        car.turnOn()

        assertEquals(5.5, car.fuel)
    }

    @Test
    fun carIsTurningOnItsEngineAndIncreasesTheTemperature() = runBlockingTest {
         car.turnOn()

        coroutinesTestRule.advanceTimeBy(6001)

        assertEquals(95, car.engine.temp)
        assertTrue(car.engine.isTurnedOn)
    }
}