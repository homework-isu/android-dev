fun findPeriod(divided: Int, divider: Int): Int {
    var res = 0
    var div = divided
    val rems = IntArray(divider)

    while (div != 0 && rems[div] == 0) {
        rems[div] = res
        div = div * 10 % divider
        res++
    }

    return if (div == 0) 0 else res - rems[div]

}

fun main() {
    var maxLen = 0
    var maxNum = 1
    for (i in 2..1000) {
        val len = findPeriod(1, i)
        if (len > maxLen) {
            maxLen = len
            maxNum = i
        }
    }
    println("1/$maxNum produce max period with length: $maxLen")
}
