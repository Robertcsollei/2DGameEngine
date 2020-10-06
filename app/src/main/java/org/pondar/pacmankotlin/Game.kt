package org.pondar.pacmankotlin

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.widget.TextView
import org.pondar.pacmankotlin.Interfaces.DataTypes.Object2D
import org.pondar.pacmankotlin.Interfaces.DataTypes.Shape2D
import org.pondar.pacmankotlin.Interfaces.DataTypes.Vector2D
import org.pondar.pacmankotlin.Interfaces.GameActions.Collider
import org.pondar.pacmankotlin.Interfaces.Objects.GoldCoin
import java.util.ArrayList


/**
 *
 * This class should contain all your game logic
 */

class Game(private var context: Context,view: TextView) {

        private var pointsView: TextView = view
        private var points : Int = 0
        //bitmap of the pacman
        var pacBitmap: Bitmap
        var pacPos = Vector2D()
        var initial = Vector2D()
        var GesturePos = Vector2D()
        var speed = 2F
        var SwipeTrashhold = 50
        var isMoving = false
        //did we initialize the coins?
        var coinsInitialized = false
        //the list of goldcoins - initially empty

        var GameObjects = ArrayList<Object2D>()

        var goldTexture = R.drawable.coin

        var coins = ArrayList<GoldCoin>()
        var coinRadius = 80.0
        var delCoin = -1
        //a reference to the gameview
        private var gameView: GameView? = null
        var screenXY = Vector2D()


    init {
        pacBitmap = BitmapFactory.decodeResource(context.resources, R.drawable.pacman)

    }

    fun setGameView(view: GameView) {
        this.gameView = view
    }

    fun initializeGoldcoins() {
        coinsInitialized = true

        if(coinsInitialized) {

            var counter = 0

            var offsetX = 200
            var offsetY = 200

            for (x in 0..4) {
                for (y in 0..4) {

                    val dimension = Vector2D((offsetX * x).toFloat(), (offsetY * y).toFloat())
                    val size = Vector2D()

                    GameObjects.add(GoldCoin(context, Shape2D(dimension, size, goldTexture )))
                    counter++
                }
            }
        }

    }

    fun newGame() {
        pacPos = Vector2D(50F, 400F)

        //reset the points
        coinsInitialized = false
        points = 0
        pointsView.text = "${context.resources.getString(R.string.points)} $points"
        gameView?.invalidate() //redraw screen
    }

    fun setSize(h: Int, w: Int) {
        screenXY = Vector2D(w.toFloat(), h.toFloat())
    }

    fun setPacPosition() {
        if(!(GesturePos.x.toInt() in SwipeTrashhold downTo -SwipeTrashhold && GesturePos.y.toInt() in SwipeTrashhold downTo -SwipeTrashhold)) {

            var Direct = GesturePos.Normalize(speed)

            pacPos.x += Direct.x
            pacPos.y += Direct.y

            Log.d("MATRIX", pacPos.x.toString() + " NORMAL " + pacPos.y.toString())
        }

        coins.forEachIndexed { index, goldCoin ->
            doCollisionCheck(goldCoin, index)
        }
        if(delCoin > 0){
            coins.removeAt(delCoin)
            delCoin = -1
        }
        gameView!!.invalidate()

    }

    fun doCollisionCheck(Object: Object2D, index: Int) {
    var collider = Collider(Object, pacPos)

        if(Object.isCollectable){
            if(Object.isCollected) {
                if (collider.isColliding()) {
                    Object.OnCollison()

                }
            }
        }else{


            if (collider.isColliding()) {
                GesturePos = Vector2D()
            }
        }
        delCoin = index

    }


}