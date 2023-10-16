package Figures.Classes

import Interfaces.Movable
import kotlin.math.PI
import kotlin.math.pow
import Interfaces.Transforming
import java.lang.Exception

class Circle(var x: Int, var y: Int, var r: Float) : Transforming, Movable, Figure(0) {
    override fun area() = PI.toFloat() * r * r

    override fun rotate(direction: RotateDirection, centerX: Int, centerY: Int) {
        if (centerX == x && centerY == y)
            return

        val times = if (direction == RotateDirection.Clockwise) 1 else 3

        for (i in 0 until times) {
            val (newX, newY) = Pair((y - centerY) + centerX, (x - centerX) * -1 + centerY)
            x = newX
            y = newY
        }
    }

    override fun resize(zoom: Int) {
        if (zoom < 0) throw Exception("zoom must be bigger zero, got: $zoom")
        r *= zoom
    }

    override fun move(dx: Int, dy: Int) {
        x += dx
        y += dy
    }

    override fun toString(): String {
        return "Circle x: $x, y: $y, radius: $r"
    }
}