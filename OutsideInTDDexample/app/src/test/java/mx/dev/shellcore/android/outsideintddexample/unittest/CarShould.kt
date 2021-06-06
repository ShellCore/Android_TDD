package mx.dev.shellcore.android.outsideintddexample.unittest

import  junit.framework.TestCase.assertEquals
import mx.dev.shellcore.android.outsideintddexample.Car
import mx.dev.shellcore.android.outsideintddexample.Engine
import org.junit.Test
import org.mockito.Mockito.*

class CarShould {

    private val engine = mock(Engine::class.java)
    private val car = Car(5.0, engine)

    @Test
    fun looseFuelWhenItTurnsOn() {
        car.turnOn()
        assertEquals(4.5, car.fuel)
    }

    @Test
    fun turnInItsEngine() {
        car.turnOn()
        verify(engine, times(1)).turnOn()
    }
}