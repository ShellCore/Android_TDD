package mx.dev.shellcore.android.outsideintddexample.acceptancetest

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import mx.dev.shellcore.android.outsideintddexample.Car
import mx.dev.shellcore.android.outsideintddexample.Engine
import org.junit.Test

class CarFeatures {

    private val engine = Engine()
    private val car = Car(6.0, engine)

    @Test
    fun carIsLoosingFuelWhenItTurnsOn() {
        car.turnOn()

        assertEquals(5.5, car.fuel)
    }

    @Test
    fun carIsTurningOnItsEngineAndIncreasesTheTemperature() {
         car.turnOn()

        assertEquals(95, car.engine.temp)
        assertTrue(car.engine.isTurnedOn)
    }
}