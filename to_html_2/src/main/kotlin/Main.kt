import java.io.File
import Message
import java.io.Console
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

fun generateStrongPass(len: Int, needSpecial: Boolean): String {
    var s = ""
    val charsUp = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    val charsLow = "abcdefghijklmnopqrstuvwxyz"
    val charsDig = "0123456789"
    val charsSpec = "~`!@#$%^&*()_-+={[}]|:;'<,>.?/"
    val all = charsDig + charsUp + charsLow + charsSpec

    var smallLetters = 0
    var bigLetters = 0
    var digits = 0
    var special = 0
    val rnd = Random()
    while (bigLetters < 1 || smallLetters < 1 || digits < 1 || s.length < len  || special < 1) {
            val c = all[rnd.nextInt(all.length)]
            when (c){
                in charsUp -> bigLetters ++
                in charsLow -> smallLetters ++
                in charsDig -> digits ++
                in charsSpec -> special ++
            }
            s += c
        }
        return s
}

fun countCollatz(number: Int): Int {
    var n = 0
    var num = number
    while (num != 1) {
        when (num % 2) {
            0 -> num /= 2
            1 -> num = num * 3 + 1
        }
        n++
    }
    return n
}

fun area(a: Int = 0, b: Int) {
    print("area: ${a * b}")
}

fun sumDigits(i: Int): Int {
    return  if (i < 10) i   else i % 10  + sumDigits(i / 10)
}

fun String.countSpaces(): Int {
    var n = 0
    for (c: Char in this) {
        if (c == ' ') {
            n++
        }
    }
    return n
}


internal abstract class Text {
    internal abstract fun letters(): Int
    internal fun isTextTooBig() = letters() >= 100500
}

internal class Book(val pages: Int) : Text() {
    init {
        require(pages >= 0)
    }
    override public fun letters() = pages * 1000
}

interface Rotatable {
    fun rotate()
}

internal class Point(var x:Int, var y:Int) : Rotatable {
    override public fun rotate() {
        val newX = y
        val newY = -x
        x = newX
        y = newY
    }
}



//fun main(args: Array<String>) {
//    var names = arrayOf<String>("one", "two", "three", "one", "four", "two", "five", "one", "two", "three", "four", "five")
//    val namesSet =  HashSet<String>()
//    val uniqueNames = ArrayList<String>()
//    for (n in  names) {
//        if ( n !in  uniqueNames) {
//            uniqueNames.add(n)
//            namesSet.add(n)
//        }
//    }
//    print(namesSet)
//}

internal abstract class Gameunit {
}
interface HasWeapon {
    val quantity: Int
    val weaponPower: Int
    fun firePower() = quantity * weaponPower
}

internal class Soldier(override val quantity: Int) : Gameunit(), HasWeapon {
    override val weaponPower: Int = 10
}

internal class Tank(override val quantity: Int) : Gameunit(), HasWeapon {
    override val weaponPower: Int = 20
}

internal class Doctor: Gameunit() {
}


fun main() {
    val units = arrayOf(Doctor(), Tank(3), Soldier(2))
    var totalpower = 0
    for (u in units) {
        totalpower += if (u is HasWeapon) u.firePower() else 0
    }
    println(totalpower)
}