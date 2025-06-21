package com.devforge.fitguard.utils

class RepetitionCounter(
    private val movement: String,
    private val thresholdDown: Float,
    private val thresholdUp: Float
) {
    private var isDown = false
    var count = 0
        private set

    fun update(angleleft: Float, angleright: Float) {
        if (!isDown && angleright < thresholdDown) {
            isDown = true
        }

        if (isDown && angleright > thresholdUp) {
            count++
            isDown = false
        }
    }
}