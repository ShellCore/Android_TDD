package mx.dev.shellcore.android.outsideintddexample.unittest

import  junit.framework.TestCase.assertEquals
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import mx.dev.shellcore.android.outsideintddexample.Car
import mx.dev.shellcore.android.outsideintddexample.Engine
import mx.dev.shellcore.android.outsideintddexample.utils.MainCoroutineScopeRule
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

class CarShould {

    @get:Rule
    var coroutinesTestRule = MainCoroutineScopeRule()

    private val engine = mock(Engine::class.java)
    private val car: Car

    init {
        runBlockingTest {
            `when`(engine.turnOn()).thenReturn(flow {
                delay(2000)
                emit(25)
                delay(2000)
                emit(50)
                delay(2000)
                emit(95)
            })
        }

        car = Car(5.0, engine)
    }

    @Test
    fun looseFuelWhenItTurnsOn() = runBlockingTest {
        car.turnOn()
        assertEquals(4.5, car.fuel)
    }

    @Test
    fun turnInItsEngine() = runBlockingTest {
        car.turnOn()
        verify(engine, times(1)).turnOn()
    }
}