package org.pondar.pacmankotlin.Interfaces.DataTypes

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.graphics.drawable.shapes.RectShape


class Shape2D(dimensions: Vector2D, size: Vector2D, color: Int) {

    lateinit var shapeDrawable: ShapeDrawable


    var pos = dimensions
    var size = size
    var color = color
    var left = dimensions.x
    var top = dimensions.y
    var right = size.x
    var bottom = size.y



}