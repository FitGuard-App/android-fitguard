package com.devforge.fitguard.utils

class RepetitionCounter(
    private val movement: String,
    private val thresholdDown: Float,
    private val thresholdUp: Float
) {
    private var isDown = false
    var count = 0
        private set

    fun update(angle: Float) {
        if (!isDown && angle < thresholdDown) {
            isDown = true
        }

        if (isDown && angle > thresholdUp) {
            count++
            isDown = false
        }
    }

    fun reset() {
        isDown = false
        count = 0
    }
}