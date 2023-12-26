internal abstract class Shape : Comparable<Shape> {
    internal abstract fun area(): Double
    override fun compareTo(s: Shape) = this.area().compareTo(s.area())
}

internal class Square(val a: Double) : Shape() {
    override fun area() = (a * a)
}

internal class Rect(val a: Double, val b: Double) : Shape() {
    override fun area() = (a * b)
}

internal class Circle (val r: Double) : Shape() {
    override fun area() = Math.PI * r * r
}

fun main() {
    val n = readLine()!!.toInt()
    var s = 0.0
    for (i in 0 until n) {
        val l = readLine()!!.split(" ")

        when (l[0]) {
            "C" -> {
                val r = l[1].toDouble()
                val fig = Circle(r)
                s += fig.area()
            }
            "R" -> {
                val a = l[1].toDouble()
                val b = l[2].toDouble()
                val fig = Rect(a, b)
                s += fig.area()
            }
            "S" -> {
                val a = l[1].toDouble()
                val fig = Square(a)
                s += fig.area()
            }
        }
    }
    println("%.2f".format(s))
}