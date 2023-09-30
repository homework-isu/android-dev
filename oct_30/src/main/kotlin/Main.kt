import java.util.Scanner

enum class Direction {
    UP, DOWN, LEFT, RIGHT
}

class Robot(x: Int, y: Int) {
    var direction: Direction = Direction.RIGHT
        get() = field

    var x: Int = x
        get() = field

    var y: Int = y
        get() = field

    public fun turnLeft() {
        this.direction = Direction.LEFT
    }

    public fun turnRight() {
        this.direction = Direction.RIGHT
    }

    public fun turnUp() {
        this.direction = Direction.UP
    }

    public fun turnDown() {
        this.direction = Direction.DOWN
    }

    public fun moveForward() {
        when (direction) {
            Direction.DOWN -> y--
            Direction.UP -> y++
            Direction.LEFT -> x--
            Direction.RIGHT -> x++
        }
    }

    public fun displayPosition() = println("x: ${x}, y: ${y}, direction: ${direction}")
}

fun moveRobotToPoint(r: Robot, x: Int, y: Int) {
    println("start position")
    r.displayPosition()
    while (r.x != x || r.y != y) {
        when {
            r.x < x -> {
                if (r.direction != Direction.RIGHT) r.turnRight()
                r.moveForward()
                r.displayPosition()
            }

            r.x > x -> {
                if (r.direction != Direction.LEFT) r.turnLeft()
                r.moveForward()
                r.displayPosition()
            }
        }

        when {
            r.y < y -> {
                if (r.direction != Direction.UP) r.turnUp()
                r.moveForward()
                r.displayPosition()
            }

            r.y > y -> {
                if (r.direction != Direction.DOWN) r.turnDown()
                r.moveForward()
                r.displayPosition()
            }
        }
    }
    println("finish position")
    r.displayPosition()
}

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    print("Input start x: ")
    val x = scanner.nextInt()
    print("Input start y: ")
    val y = scanner.nextInt()

    print("Input goal x: ")
    val toX = scanner.nextInt()
    print("Input goal y: ")
    val toY = scanner.nextInt()

    val robot = Robot(x, y)

    moveRobotToPoint(robot, toX, toY)
}