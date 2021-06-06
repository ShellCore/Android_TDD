package mx.dev.shellcore.android.outsideintddexample

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Car(var fuel: Double, val engine: Engine) {

    companion object {
        private const val FUEL_CONSUMPSION = 0.5
    }

    fun turnOn() {
        fuel -= FUEL_CONSUMPSION
        CoroutineScope(Dispatchers.Main).launch {
            engine.turnOn()
        }
    }
}
