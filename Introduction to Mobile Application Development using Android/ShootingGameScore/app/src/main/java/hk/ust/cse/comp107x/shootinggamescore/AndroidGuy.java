package hk.ust.cse.comp107x.shootinggamescore;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * Created by muppala on 23/5/15.
 */
public class AndroidGuy {
    float x; // Guy's top left corner (x,y)
    float y;
    float stepX = 10; // Guy's step in (x,y) direction
    float stepY = 5; // gives speed of motion, larger means faster speed
    int lowerX, lowerY, upperX, upperY; // boundaries

    Bitmap android_guy;


    private Context mContext;

    // Constructor
    public AndroidGuy(int color, Context c) {

        mContext = c;

        // create a bitmap from the supplied image (the image is the icon that is part of the app)
        android_guy = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(mContext.getResources(),
                R.mipmap.ic_launcher),50,50, false);

    }

    public void setBounds(int lx, int ly, int ux, int uy) {
        lowerX = lx;
        lowerY = ly;
        upperX = ux;
        upperY = uy;

        x = (float) ((upperX-50)*Math.random());
        y = 0;
    }

    public boolean move() {
        // Get new (x,y) position. Movement is always in vertical direction downwards
        y += stepY;
        // Detect when the guy reaches the bottom of the screen
        // restart at a random location at the top of the screen
        if (y + 50 > upperY) {
            x = (float) ((upperX-50)*Math.random());
            y = 0;
            return true;
        }
        else
            return true;
    }

    // When you reset, starts the Android Guy from a random X co-ordinate location
    // at the top of the screen again
    public void reset() {
        x = (float) ((upperX-50)*Math.random());
        y = 0;
    }

    // Returns the rectangle enclosing the Guy. Used for collision detection
    public RectF getRect() {
        return new RectF(x,y,x+50,y+50);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void draw(Canvas canvas) {

        canvas.drawBitmap(android_guy, x, y, null);
    }
}
