package com.example.myapplication1.presentation.game.ui

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.media.Image

open class TakeUI : IElementUI {

    companion object {
        const val STATE_UNDEFINED = 0 //пусто
        const val STATE_CROSS = 1 //выстрел по пустому полю
        const val STATE_ZERO = 2 //стоит корабль
        const val STATE_PADDED = 3 //выстрел по кораблю

        val paintRed = Paint().apply {
            color = Color.RED
            this.strokeWidth = 4f
        }
        val paintBlue = Paint().apply { color = Color.BLUE }
        val paintYellow = Paint().apply { color = Color.YELLOW }
        val paintBlack = Paint().apply { color = Color.BLACK }
        val paintGreen = Paint().apply { color = Color.GREEN }
        val paintGray = Paint().apply { color = Color.GRAY }
        val paintPurple = Paint().apply { color=Color.parseColor("#6600ff")}
    }

    var x: Int = 0
    var y: Int = 0

    var width: Int = 0
    var height: Int = 0

    var state: Int = STATE_UNDEFINED


    override fun render(canvas: Canvas) {

        when (state) {
            STATE_CROSS -> renderCross(canvas)
            STATE_ZERO -> renderZero(canvas)
            STATE_UNDEFINED -> renderUnder(canvas)
            STATE_PADDED -> renderPadded(canvas)
        }
    }


     private fun renderPadded(canvas: Canvas){
         val x = x.toFloat()
         val y = y.toFloat()
         val w = width.toFloat()
         val h = height.toFloat()
         val hw = w * 0.5f
         val cx = x + hw
         val cy = y + h * 0.5f

         canvas.drawLine (x,y,x,y+w,paintRed)
         canvas.drawLine (x,y,x+h,y,paintRed)
         canvas.drawLine (x+h,y,x+h,y+w,paintRed)
         canvas.drawLine (x+h,y+w,x,y+w,paintRed)

         //canvas.drawCircle(cx, cy, hw, paintBlack)
         canvas.drawCircle(cx, cy, hw * 0.8f, paintGray)
         canvas.drawCircle(cx, cy, hw * 0.6f, paintBlack)
         canvas.drawCircle(cx, cy, hw * 0.2f, paintRed)

     }

    private fun renderUnder(canvas: Canvas) {
        val x = x.toFloat()
        val y = y.toFloat()
        val w = width.toFloat()
        val h = height.toFloat()


        canvas.drawLine (x,y,x,y+w,paintPurple)
        canvas.drawLine (x,y,x+h,y,paintPurple)
        canvas.drawLine (x+h,y,x+h,y+w,paintPurple)
        canvas.drawLine (x+h,y+w,x,y+w,paintPurple)
       //canvas.drawText("x",x,y, paintRed)

    }


    private fun renderCross(canvas: Canvas) {

        val x = x.toFloat()
        val y = y.toFloat()
        val w = width.toFloat()
        val h = height.toFloat()
        var k:Float
        canvas.drawLine (x,y,x,y+w,paintPurple)
        canvas.drawLine (x,y,x+h,y,paintPurple)
        canvas.drawLine (x+h,y,x+h,y+w,paintPurple)
        canvas.drawLine (x+h,y+w,x,y+w,paintPurple)
        k=width.toFloat()/4
        canvas.drawLine(x+k, y+k, x + w-k, y + h-k, paintRed)
        canvas.drawLine(x + w-k, y+k, x+k, y + h-k, paintRed)
    }

    private fun renderZero(canvas: Canvas) {

        val x = x.toFloat()
        val y = y.toFloat()
        val w = width.toFloat()
        val h = height.toFloat()
        canvas.drawLine (x,y,x,y+w,paintPurple)
        canvas.drawLine (x,y,x+h,y,paintPurple)
        canvas.drawLine (x+h,y,x+h,y+w,paintPurple)
        canvas.drawLine (x+h,y+w,x,y+w,paintPurple)
        /*val hw = w * 0.5f
        val cx = x + hw
        val cy = y + h * 0.5f

        canvas.drawCircle(cx, cy, hw, paintBlue)
        canvas.drawCircle(cx, cy, hw * 0.9f, paintYellow)*/
        canvas.drawLine (x,y,x,y+w, paintRed)
        canvas.drawLine (x,y,x+h,y,paintRed)
        canvas.drawLine (x+h,y,x+h,y+w,paintRed)
        canvas.drawLine (x+h,y+w,x,y+w,paintRed)

    }

    fun drawShip(x0:Int,y0:Int,x1:Int,y1:Int){

    }
}