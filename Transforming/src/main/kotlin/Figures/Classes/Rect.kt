package Figures.Classes

import Interfaces.Movable
import Interfaces.Transforming
import java.lang.Exception

class Rect(var x: Int, var y: Int, var width: Float, var height: Float) :  Transforming, Movable, Figure(0) {

    override fun area() = width * height

    override fun rotate(direction: RotateDirection, centerX: Int, centerY: Int) {
        if (centerX == x && centerY == y)
            return

        val times = if (direction == RotateDirection.Clockwise) 1 else 3

        for (i in 0 until times) {
            val (newX, newY) = Pair((y - centerY) + centerX, (x - centerX) * -1 + centerY)
            x = newX
            y = newY

            val (newH, newW) = Pair(width, height)
            width = newW
            height = newH
        }
    }

    override fun resize(zoom: Int) {
        if (zoom < 0) throw Exception("zoom must be bigger zero, got: $zoom")
        width *= zoom
        height *= zoom
    }

    override fun move(dx: Int, dy: Int) {
        x += dx
        y += dy
    }
    override fun toString(): String {
        return "Rect x: $x, y: $y, width: $width, height: $height"
    }
}