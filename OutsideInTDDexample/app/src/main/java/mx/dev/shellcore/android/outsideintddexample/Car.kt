package mx.dev.shellcore.android.outsideintddexample

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class Car(var fuel: Double, val engine: Engine) {

    companion object {
        private const val FUEL_CONSUMPSION = 0.5
    }

    fun turnOn() {
        fuel -= FUEL_CONSUMPSION
        CoroutineScope(Dispatchers.Main).launch {
            engine.turnOn().collect { actualTemp ->
                Log.d("COURSE", "Collected engine temperature: $actualTemp")
            }
        }
    }
}
