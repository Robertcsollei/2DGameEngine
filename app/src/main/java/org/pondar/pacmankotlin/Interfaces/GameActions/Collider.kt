package org.pondar.pacmankotlin.Interfaces.GameActions

import org.pondar.pacmankotlin.Interfaces.DataTypes.Object2D
import org.pondar.pacmankotlin.Interfaces.DataTypes.Vector2D

class Collider (Object: Object2D, pacPos: Vector2D){

    var pacPos = pacPos
    var Object: Object2D = Object
    var Xmid = Object.Size.x / 2
    var Ymid = Object.Size.y / 2

    fun isColliding(): Boolean{
        return pacPos.x in Object.Pos.x - Xmid..Object.Pos.x + Xmid && pacPos.y in Object.Pos.y - Ymid..Object.Pos.y + Ymid
    }
}