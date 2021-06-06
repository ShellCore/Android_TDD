package mx.dev.shellcore.android.outsideintddexample

class Car(var fuel: Double, val engine: Engine) {

    companion object {
        private const val FUEL_CONSUMPSION = 0.5
    }

    fun turnOn() {
        fuel -= FUEL_CONSUMPSION
        engine.turnOn()
    }

}
