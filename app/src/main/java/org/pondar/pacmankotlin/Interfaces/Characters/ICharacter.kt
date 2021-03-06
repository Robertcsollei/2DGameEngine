package org.pondar.pacmankotlin.Interfaces.Characters

import android.graphics.Bitmap
import android.view.MotionEvent
import android.view.View
import org.pondar.pacmankotlin.Game
import org.pondar.pacmankotlin.Interfaces.Adapters.BitMapConverter
import org.pondar.pacmankotlin.Interfaces.DataTypes.Vector2D

interface ICharacter {

    var Xunit: Int
    var Yunit :Int
    val life: Int
    var Initial: Vector2D
    var Direction:Vector2D
    var speed : Float
    var ResizeBitmap: BitMapConverter

    fun move(initial: Vector2D, game: Game, view: View)



}