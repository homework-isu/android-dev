import Figures.Classes.Circle
import Figures.Classes.Rect
import Figures.Classes.Square
import Figures.Classes.RotateDirection

fun main(args: Array<String>) {
    val square = Square(4, 3, 4f)
    println(square.toString())
    square.rotate(RotateDirection.CounterClockwise, 3, -3)
    println(square.toString())
    println()

    val rect = Rect(10, 4, 2f, 5f)
    println(rect.toString())
    rect.rotate(RotateDirection.CounterClockwise, 3, 3)
    rect.resize(2)
    println(rect.toString())
    println()

    val circle = Circle(10, 4, 2.5f)
    println(circle.toString())
    circle.rotate(RotateDirection.Clockwise, 3, -3)
    rect.resize(3)
    println(circle.toString())
}