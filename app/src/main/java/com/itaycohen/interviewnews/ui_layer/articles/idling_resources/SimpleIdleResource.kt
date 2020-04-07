package com.itaycohen.interviewnews.ui_layer.articles.idling_resources

import androidx.test.espresso.IdlingResource
import java.util.concurrent.atomic.AtomicBoolean

/**
 * A very simple implementation of {@link IdlingResource}.
 */
class SimpleIdleResource : IdlingResource {

    @Volatile
    private var mCallback: IdlingResource.ResourceCallback? = null
    private val mIsIdleNow: AtomicBoolean = AtomicBoolean(true)

    override fun getName() = this::class.java.name

    override fun isIdleNow() = mIsIdleNow.get()

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
        mCallback = callback
    }

    /**
     * Sets the new idle state, if isIdleNow is true, it pings the {@link ResourceCallback}.
     * @param isIdleNow false if there are pending operations, true if idle.
     */
    fun setIdle(isIdle: Boolean) {
        mIsIdleNow.set(isIdle)
        if (isIdle)
            mCallback?.onTransitionToIdle()
    }

}