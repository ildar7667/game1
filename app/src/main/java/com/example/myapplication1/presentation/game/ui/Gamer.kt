package com.example.myapplication1.presentation.game.ui

class Gamer (state:Int, ships: MutableList<Ships>) {
    var state: Int = state
    var ships: MutableList<Ships> = ships

    fun schet ():Int{
        var k:Int = 0

        for (i in 0..ships.size-1)
            if (ships[i].state==0) k++
        return k
    }

    fun numbership (x:Int, y:Int) :Pair <Int, Int> { //возвращает номер корабля, по которому попал выстрел от 0 по 9. И номер палубы
        var shotship:Int=0
        var shotpart:Int=0

        for (i in (0..ships.size-1))
            for (j in 0..(ships[i].part.size-1))
                if ((ships[i].part[j].x==x) && (ships[i].part[j].y==y))
                {shotship=i
                    shotpart=j
                    //listship[i].part[j].state=0
                }
        return Pair(shotship, shotpart)
    }



    fun shotondeck (x:Int,y:Int){  //выстрел по палубе с координатами x,y. Присваивает state=0
        ships[numbership(x, y).first].part[numbership(x,y).second].state=0
    }


    fun chekkillship (ship: Ships):Boolean{ //проверяет убит ли корабль, true- убит
        var  n: Int=0
        for (i in 0..(ship.part.size-1))
            if  (ship.part[i].state==0)
                n++
        if (ship.part.size==n)
        {
            ship.state=0
            return true
        }
        return false
    }

    fun chekkillshipxy (x:Int, y:Int):Boolean{ //проверяет убит ли корабль с координатами x,y, результат true- убит
        return (chekkillship(ships[numbership(x,y).first]))

    }

}

