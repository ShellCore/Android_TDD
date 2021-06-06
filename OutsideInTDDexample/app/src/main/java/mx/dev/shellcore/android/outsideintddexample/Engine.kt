package mx.dev.shellcore.android.outsideintddexample

import android.util.Log
import kotlinx.coroutines.delay

class Engine(
    var isTurnedOn: Boolean = false,
    var temp: Int = 15)
{

    suspend fun turnOn() {
        isTurnedOn = true
        delay(6000)
        temp = 95
        Log.d("COURSE", "Engine has turned on")
    }
}
