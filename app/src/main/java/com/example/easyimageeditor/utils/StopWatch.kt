package com.example.easyimageeditor.utils

import android.util.Log

class StopWatch(val logTag: String = "STOPWATCH") {
    private var startTime: Long? = null

    private val _lapList = mutableMapOf<String, Long>()
    val lapList: Map<String, Long> get() = _lapList

    init {
        start()
    }

    fun start() {
        if (startTime != null) {
            reset()
        }
        startTime = System.currentTimeMillis()
    }

    fun reset() {
        startTime = null
        _lapList.clear()
    }

    fun lap(
        lapName: String = "Lap ${lapList.size + 1}",
        save: Boolean = true,
        log: Boolean = true
    ): Long? = startTime?.let { startTime ->
        val now = System.currentTimeMillis()
        val fromStart = now - startTime
        val fromLastLap = lapList.values.lastOrNull()?.let { fromStart - it }
        if (save) _lapList[lapName] = fromStart
        if (log) Log.v(
            logTag, "$lapName $fromStart $fromLastLap")
        fromStart
    }

}