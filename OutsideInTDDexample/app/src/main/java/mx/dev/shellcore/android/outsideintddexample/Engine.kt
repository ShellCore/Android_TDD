package mx.dev.shellcore.android.outsideintddexample

class Engine(
    var isTurnedOn: Boolean = false,
    var temp: Int = 15)
{

    fun turnOn() {
        isTurnedOn = true
        temp = 95
    }
}
