package mx.dev.shellcore.android.groovy.utlis

import androidx.annotation.CheckResult
import androidx.annotation.NonNull
import androidx.test.espresso.IdlingResource
import okhttp3.Dispatcher
import okhttp3.OkHttpClient

class OkHttp3IdlingResource private constructor(private val name: String, private val dispatcher: Dispatcher) : IdlingResource {

    @Volatile
    var callback: IdlingResource.ResourceCallback? = null
    override fun getName() = name

    override fun isIdleNow() = dispatcher.runningCallsCount() == 0

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback) {
        this.callback = callback
    }

    companion object {

        @CheckResult
        @NonNull
        fun create(@NonNull name: String?, @NonNull client: OkHttpClient?): OkHttp3IdlingResource {
            if (name == null) throw NullPointerException("name == null")
            if (client == null) throw NullPointerException("client == null")
            return OkHttp3IdlingResource(name, client.dispatcher())
        }
    }

    init {
        dispatcher.setIdleCallback {
            val callback = callback
            callback?.onTransitionToIdle()
        }
    }
}
