package com.example.myapplication1.presentation.game.ui

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import kotlin.random.Random

class PlayingFieldUI : IElementUI {

    private val takes = mutableListOf<TakeUI>()
    private val bgPaint = Paint().apply { color = Color.BLUE }

    var width: Int = 0
    var height: Int = 0

    init {

        val random = Random(System.currentTimeMillis())
        for (i in 1..100)
            takes.add(TakeUI().apply {
                state = random.nextInt(3)
            })
    }

    override fun render(canvas: Canvas) {

        canvas.drawRect(Rect(0, 0, width, height), bgPaint)

        var row = 0
        var col = 0
        val itemWidth = width / 10
        val itemHeight = height / 10

        for (take in takes) {

            take.x = col * itemWidth
            take.y = row * itemHeight

            take.width = itemWidth
            take.height = itemHeight

            take.render(canvas)

            if (++col == 10) {
                col = 0
                if (++row == 10)
                    return
            }
        }
    }
}