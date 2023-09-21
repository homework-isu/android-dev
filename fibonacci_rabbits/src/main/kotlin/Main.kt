import java.util.*

fun findPopulation(n: Int, m: Int): Long {
    var population: Long = 0
    val rabbitsInAges = LongArray(m)

    rabbitsInAges[0] = 1
    for (i in 2..n) {
        val rabbitsGrown = LongArray(m)

        for (j in 1 until m) {
            rabbitsGrown[j] = rabbitsInAges[j - 1]
            rabbitsGrown[0] += rabbitsInAges[j]
        }

        for (c in 0 until m) {
            rabbitsInAges[c] = rabbitsGrown[c]
        }
    }
    for (value in rabbitsInAges) population += value
    return population
}


fun main() {
    val scanner = Scanner(System.`in`)

    print("input the count of months: ")
    val n: Int = scanner.nextInt()

    print("input duration of rabbit's live: ")
    val m: Int = scanner.nextInt()

    println("rabbit's population on the last month: ${findPopulation(n, m)}")
}