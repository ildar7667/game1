package com.example.myapplication1.presentation.game.ui

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.widget.Toast
import kotlinx.android.synthetic.main.gameoffline.*
import kotlin.random.Random

class PlayingFieldUI : IElementUI {

    private val takes = mutableListOf<TakeUI>()
    private val bgPaint = Paint().apply { color = Color.BLUE }

    var width: Int = 0
    var height: Int = 0

    var k: Int = 0

    init {

        val random = Random(System.currentTimeMillis())
        for (i in 1..100)
            takes.add(TakeUI().apply {
                state = 0//random.nextInt(3)

            })

      //  for (i in 11..20)
         //   takes[i-1].state=0
    //    setshipsfour(5)

    }


    fun setshipsfour (n: Int) {
        var kol:Int

       for (kol in 1..1) {
          // takes[kol].state=2

          // val randome = Random(System.currentTimeMillis())
           val randome = Random(System.nanoTime())
           val n: Int = randome.nextInt(99)
         //  takes[n].state = 2


   //        val randome = Random(System.currentTimeMillis())
            if (randome.nextInt(2) == 0)
            {//вертикальный

                var ran: Int = randome.nextInt(69)
                takes[ran].state = 2
                takes[ran + 10].state = 2
                takes[ran + 20].state = 2
                takes[ran + 30].state = 2


                val x:Int = ran/10+1
                val y:Int = ran%10+1

             // val part1 = PartShips (x,y,1)
               // val listpar : List<Ships> =
                val listpart = listOf(PartShips (x,y,1),PartShips (x,y+1,1),PartShips (x,y+2,1),PartShips (x,y+3,1))
              val shipsfour = Ships(4, 1,listpart )
               // return shipsfour



            } else {//горизонтальный

                var ranx: Int = randome.nextInt(7)
                var rany: Int = randome.nextInt(10)
                takes[ranx + 10 * rany].state = 2
                takes[ranx + 10 * rany + 1].state = 2
                takes[ranx + 10 * rany + 2].state = 2
                takes[ranx + 10 * rany + 3].state = 2

                val x:Int = ranx+1
                val y:Int = rany+1

                //val part1 = PartShips (x,y,1)
                val listpart = listOf(PartShips (x,y,1),PartShips (x+1,y,1),PartShips (x+2,y,1),PartShips (x+3,y,1))
                val shipsfour = Ships(4, 1,listpart )
               // return shipsfour
            }
            // for (i in 1..100)
            //     takes[i].state=1

        }

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