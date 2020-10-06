package org.pondar.pacmankotlin

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import org.pondar.pacmankotlin.Interfaces.DataTypes.Shape2D
import org.pondar.pacmankotlin.Interfaces.DataTypes.Vector2D


//note we now create our own view class that extends the built-in View class
class GameView : View {

    private var game: Game? = null
    private var h: Int = 0
    private var w: Int = 0 //used for storing our height and width of the view





    fun setGame(game: Game?) {
        this.game = game
    }


    /* The next 3 constructors are needed for the Android view system,
	when we have a custom view.
	 */
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)


    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    //In the onDraw we put all our code that should be
    //drawn whenever we update the screen.
    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {



        //Here we get the height and weight
        h = canvas.height
        w = canvas.width
        //update the size for the canvas to the game.
        game?.setSize(h, w)


        //are the coins initiazlied?
        if (!(game!!.coinsInitialized))
            game?.initializeGoldcoins()



        //Making a new paint object
        val paint = Paint()
        canvas.drawColor(Color.WHITE) //clear entire canvas to white color

        fun drawRect(shape: Shape2D){

            return canvas.drawRect(shape.left, shape.top,shape.bottom, shape.right, paint)
        }

        fun drawBitmap(bitmap: Bitmap, position: Vector2D){
           return canvas.drawBitmap(bitmap, position.x, position.y, paint)
        }


        //draw the pacman
        drawBitmap(game!!.pacBitmap, game?.pacPos!!)

        if(game!!.coinsInitialized){
            for(coin in game?.coins!!){
                drawBitmap(coin.goldBitmap, coin.Pos)
            }
            var Wall = Shape2D(Vector2D(), Vector2D(100F,100F), 2)
            drawRect(Wall)
        }



        //TODO loop through the list of goldcoins and draw them.


        super.onDraw(canvas)
    }




}
