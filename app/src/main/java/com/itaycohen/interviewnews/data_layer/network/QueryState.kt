package com.itaycohen.interviewnews.data_layer.network

import android.util.Log
import androidx.annotation.IntDef

@Suppress("DataClassPrivateConstructor")
data class QueryState private constructor(

    /**
     * query state code
     */
    @QueryStateType
    val state: @QueryStateType Int,

    /**
     * query error, if exists.
     */
    val err: Throwable? = null
) {

    fun getStatusAsText() =
        when (state){
            STATE_FAILED -> "STATUS_FAILED"
            STATE_RUNNING -> "STATUS_RUNNING"
            STATE_SUCCESS -> "STATUS_SUCCESS"
            STATE_IDLE -> "STATE_IDLE"
            else -> "Not a valid status"
        }

    fun printStatus(logTag:String = "qwe") {
        Log.d(logTag, getStatusAsText())
    }

    @Target(AnnotationTarget.TYPE, AnnotationTarget.PROPERTY)
    @Retention(AnnotationRetention.SOURCE)
    @IntDef(STATE_SUCCESS, STATE_RUNNING, STATE_FAILED, STATE_IDLE)
    annotation class QueryStateType

    companion object {

        const val STATE_IDLE = -30
        const val STATE_SUCCESS = -565656
        const val STATE_RUNNING = -121212
        const val STATE_FAILED = -787878

        val IDLE = QueryState(STATE_IDLE)
        val LOADED = QueryState(STATE_SUCCESS)
        val LOADING = QueryState(STATE_RUNNING)

        fun error (err: Throwable?) = QueryState(STATE_FAILED, err)
    }
}