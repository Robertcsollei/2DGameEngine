package org.pondar.pacmankotlin.Interfaces.DataTypes

interface Object2D {
    var shape: Shape2D
    var Pos: Vector2D
    var Size: Vector2D
    var isStatic: Boolean
    var isCollectable: Boolean
    var isCollected: Boolean
    fun OnCollison()
}