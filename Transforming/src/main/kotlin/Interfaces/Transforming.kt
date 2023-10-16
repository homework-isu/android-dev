package Interfaces

import Figures.Classes.RotateDirection

interface Transforming {
    fun rotate(direction: RotateDirection, centerX: Int, centerY: Int)

    fun resize(zoom: Int)
}