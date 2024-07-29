package com.diberardino.jbomb.ui.utilities

import com.diberardino.jbomb.JBomb
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GradientCallbackHandler(
    private val start: Float,
    private val end: Float,
    private val nonNegativeStep: Float,
    private val p: (Float) -> Unit
) {
    private var currValue = start
    private val sign: Int = if (start > end) -1 else 1

    init {
        require(nonNegativeStep >= 0) { "step must be non-negative" }
    }

    fun execute() {
        // Launch the coroutine
        JBomb.match.scope.launch {
            while (true) {
                p(currValue)

                val step = sign * nonNegativeStep
                currValue += step

                val hasFinished = if (start >= end) currValue < end else currValue >= end

                if (hasFinished) {
                    break
                }

                delay(1L) // Equivalent to Timer's 1 ms delay
            }
        }
    }
}