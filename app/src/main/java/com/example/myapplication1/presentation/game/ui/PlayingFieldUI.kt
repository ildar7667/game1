package com.example.myapplication1.presentation.game.ui

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import com.example.myapplication1.game.Const
import com.example.myapplication1.game.model.GameState
import kotlin.random.Random


class PlayingFieldUI : IElementUI {

    companion object {

        private val bgPaint = Paint().apply { color=Color.parseColor("#ffffff")}
        //Color.CCCCCC   color = Color.BLUE
        private val linePaint = Paint().apply {
           // color = Color.BLACK
            color=Color.parseColor("#6600cc")
            strokeWidth = 10f
        }
    }


    private val takes = mutableListOf<TakeUI>()
    //val listShips =  mutableListOf<Ships>()
    //private val bgPaint = Paint().apply { color = Color.BLUE }

    var x: Int = 0
    var y: Int = 0

    var width: Int = 0
    var height: Int = 0



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

    fun identships(k: MutableList<Ships>){
        //listShips
        //for (i in 0..(k-1))
           // for (j in 0..listShips[i].size)
                //takes[listShips[i].part[j].x*10+listShips[i].part[j].y].state=2
           // takes[i].state=2
        for (i in 0..(k.size-1))
        {   for (j in 0..(k[i].part.size-1))
        {
            takes[k[i].part[j].x+k[i].part[j].y*10].state=2
        }

        }
    }

    fun shotaroundship (k: MutableList<PartShips>) {
        var x1: Int = k[0].x-1
        var x2: Int = k[k.size-1].x+1

        var y1: Int = k[0].y-1
        var y2: Int = k[k.size-1].y+1

        if (x1==-1) x1=0
        if (x2==10) x2=9

        if (y1==-1) y1=0
        if (y2==10) y2=9

        for (i in x1..x2)
            for (j in y1..y2)
                shotxy(i,j)


    }

    fun identhiddenships (k: MutableList<Ships>){
        //listShips
        //for (i in 0..(k-1))
        // for (j in 0..listShips[i].size)
        //takes[listShips[i].part[j].x*10+listShips[i].part[j].y].state=2
        // takes[i].state=2
        for (i in 0..(k.size-1))
        {   for (j in 0..(k[i].part.size-1))
        {
            takes[k[i].part[j].x+k[i].part[j].y*10].state=4
        }

        }
    }

    fun drawships(k: List<Ships>) {
        var x0: Int
        var x1: Int
        var y0: Int
        var y1: Int
        for (i in 0..(k.size - 1)) {
            x0 = k[i].part[0].x
            y0 = k[i].part[0].y

            x1 = k[i].part[k[i].part.size - 1].x
            y1 = k[i].part[k[i].part.size - 1].y

          //  drawShip(x0, y0, x1, y1)
            /*for (j in 0..(k[i].part.size-1))

            takes[k[i].part[j].x+k[i].part[j].y*10].state=2
        takes[i].part[0]

        }*/

        }
    }

    fun scanshipsx() : MutableList<Ships> {
        var k:Int =1
        // var listShips =  mutableListOf<Ships>()
        val listShips =  mutableListOf<Ships>()
        for (i in 0..99)
            if (takes[i].state == 2)
            {
                var listpart = mutableListOf(PartShips(i % 10, i / 10, 2))
                  takes[i].state=0
//x 0..9, y 0..9
               //горизонтальный
               if (i+1<100)
               if (takes[i+1].state==2) {

                       listpart.add(PartShips(i % 10 + 1, i / 10, 2))
                       takes[i + 1].state = 0

                       if (i + 2 < 100)
                       if (takes[i + 2].state == 2) {
                           listpart.add(PartShips(i % 10 + 2, i / 10, 2))
                           takes[i + 2].state = 0

                       if (i + 3 < 100)
                       if (takes[i + 3].state == 2) {
                           listpart.add(PartShips(i % 10 + 3, i / 10, 2))
                           takes[i + 3].state = 0
                       }
               }}

               //вертикальный
               if (i+10<100)
               if (takes[i+10].state==2) {
                   listpart.add(PartShips(i % 10, (i + 10) / 10, 2))
                   takes[i+10].state=0

               if (i+20<100)
               if (takes[i+20].state==2) {
                   listpart.add(PartShips(i % 10, (i + 20) / 10, 2))
                   takes[i+20].state=0

               if (i+30<100)
               if (takes[i+30].state==2) {
                   listpart.add(PartShips(i % 10, (i + 30) / 10, 2))
                   takes[i+30].state=0
               }}}


                listShips.add(Ships(1, 1, listpart))


            }
       else takes[i].state = 0

        k=listShips.size
        return listShips

    }


    fun onMoveSquare(x: Float, y: Float){
        var xf: Float = x
        var yf: Float = y

        var xx : Int = (xf/(width/10)).toInt()
        var yy : Int = (yf/(height/10)).toInt()
        //canvas.drawLine(x, y, x, y, linePaint)
    }


    fun shotxy (x:Int, y:Int):Int {  //x 0..9, y 0..9
        if ((takes[y*10-1+x+1].state==2) || (takes[y*10-1+x+1].state==4))
        {
            takes[y * 10 - 1 + x + 1].state = 3
            return 1    //попал в корабль
        }

        if (takes[y*10-1+x+1].state==3)
            return 4  //попал в подбитый корабль

        if (takes[y * 10 - 1 + x + 1].state == 1)
            return 3 //попал повторно в пустое поле

        if (takes[y * 10 - 1 + x + 1].state == 0)
        {
            takes[y * 10 - 1 + x + 1].state = 1
            return 2 //попал в пустое поле
        }

        return 2
    }

    fun onClickSquare(x: Float, y: Float):Int {
        var xf: Float = x
        var yf: Float = y


        var xx : Int = (xf/(width/10)).toInt()
        var yy : Int = (yf/(height/10)).toInt()

        return shotxy(xx,yy)

       /* if (takes[yy*10-1+xx+1].state==2)
        takes[yy*10-1+xx+1].state=3
        else  takes[yy*10-1+xx+1].state=1*/
    }

    fun xyfloattointPF (x:Float,y: Float): Pair <Int,Int>
    {

        return   Pair((x/(width/10)).toInt(), (y/(height/10)).toInt())
    }

    fun setshipsx (k: Int, len: Int) {
       //k - количество кораблей
       //l - длина корабля

        var kol :Int =1

       while (kol<=k) {

           val randome = Random(System.nanoTime())
           var kx: Int = randome.nextInt(10 - (len - 1))
           var ky: Int = randome.nextInt(10 - (len - 1))
           var j: Int = randome.nextInt(2)


           if (j == 0) {
               //vertical
               if (chek(len, 0, kx, ky) == 0){
                   for (i in 1..len) {
                       takes[(ky + i - 1) * 10 + kx].state = 2

                   }
               kol++}
           } else {
               //gorizontal
               if (chek(len, 1, kx, ky) == 0){
               for (i in 1..len) {
                   takes[ky * 10 + kx + i - 1].state = 2

               }

            kol++}
               /*   if (chek(len,j, kx, ky)==0)
            {  takes[ky*10+kx].state=2
                kol++}*/

           }
       }
       }


    fun chek (len: Int, j: Int, x:Int, y: Int) : Int {
        //функция проверки возможности установить корабль в данном месте
        //len длина, j верт/гориз 0/1, x y координаты левого/верхнего квадрата корабля
        var s:Int = 0
         if (j==0) {
         //vertical
         var a:Int = x-1
         var b:Int = x+1
         var c:Int = y-1
         var d:Int = y+len
             if (a==-1) a++
             if (b==10) b--
             if (c==-1) c++
             if (d==10) d--

             for (i in a..b)
                 for (j in c..d)

             if (takes[j*10+i].state==2) s++
         }
        else
         {//gorizontal
             var a:Int = x-1
             var b:Int = x+len
             var c:Int = y-1
             var d:Int = y+1
             if (a==-1) a++
             if (b==10) b--
             if (c==-1) c++
             if (d==10) d--

             for (i in a..b)
                 for (j in c..d)

                     if (takes[j*10+i].state==2) s++

         }
        return s
    }



    override fun render(canvas: Canvas) {

        canvas.drawRect(Rect(0 + x, 0 + y, width + x, height + y), bgPaint)
        val padding = (width * 0.002).toInt()

        var row = 0
        var col = 0
        val itemWidth = width / 10
        val itemHeight = height / 10

        for (take in takes) {

            take.x = col * itemWidth + padding + x
            take.y = row * itemHeight + padding + y

            take.width = itemWidth - 2 * padding
            take.height = itemHeight - 2 * padding

            take.render(canvas)

            if (++col == 10) {
                col = 0
                if (++row == 10)
                    break
            }
        }

        val iw = itemWidth.toFloat()
        val ih = itemHeight.toFloat()
        val w = width.toFloat()
        val h = height.toFloat()

        canvas.drawLine( x.toFloat(), y.toFloat(), (x+width).toFloat(), y.toFloat(), linePaint)
        canvas.drawLine( x.toFloat(), y.toFloat(), x.toFloat(), (y+height).toFloat(), linePaint)

        canvas.drawLine( (x+width).toFloat(), y.toFloat(), (x+width).toFloat(), (y+height).toFloat(), linePaint)
        canvas.drawLine( x.toFloat(), (y+height).toFloat(), (x+width).toFloat(), (y+height).toFloat(), linePaint)

        // vertical
       // canvas.drawLine(iw + x, 0f, iw + x, h, linePaint)
       // canvas.drawLine(2 * iw + x, 0f, 2 * iw + x, h, linePaint)

        // horizontal
      //  canvas.drawLine(0f, ih + y, w + x, ih + y, linePaint)
      //  canvas.drawLine(0f, 2 * ih + y, w + x, 2 * ih + y, linePaint)

    }

    fun onClick(x: Float, y: Float): TakeUI? {
        return takes.firstOrNull { it.x < x && it.x + it.width >= x && it.y < y && it.y + it.height >= y }



    }

    fun setGameState(state: GameState) {

        val game = state.game.toTypedArray()
        for (i in 0 until 99)
            takes.get(i).state = when (game[i]) {
                Const.SELECT_TYPE_CROSS -> TakeUI.STATE_CROSS
                Const.SELECT_TYPE_ZERO -> TakeUI.STATE_ZERO
                else -> TakeUI.STATE_UNDEFINED
            }

        if (state.winner != null)
            println("WIN!")
    }
}