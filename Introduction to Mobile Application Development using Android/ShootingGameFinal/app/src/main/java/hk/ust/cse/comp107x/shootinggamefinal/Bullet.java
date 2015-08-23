package hk.ust.cse.comp107x.shootinggamefinal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * Created by muppala on 23/5/15.
 */
public class Bullet {
    float radius = 5; // Bullet's radius
    float x; // Bullet's center (x,y)
    float y;
    float stepX = 10; // Bullet's step of motion in (x,y) direction
    float stepY = 5; // gives speed of motion, larger means faster speed
    int lowerX, lowerY, upperX, upperY;
    private Paint paint; // The paint style, color used for drawing

    private Context mContext;

    // Constructor
    public Bullet(int color, Context c, float startx, float starty) {
        paint = new Paint();
        paint.setColor(color);

        mContext = c;
        x = startx;
        y = starty;
    }

    public void setBounds(int lx, int ly, int ux, int uy) {
        lowerX = lx;
        lowerY = ly;
        upperX = ux;
        upperY = uy;
    }

    // Rectangle enclosing the bullet. Used for collision detection with Guy
    public RectF getRect() {
        return new RectF(x-radius,y-radius,x+radius,y+radius);
    }

    // Move the bullet upwards by stepY every time. This creates the upward motion.
    public boolean move() {
        // Get new (x,y) position
        y -= stepY;
        // Detect when the bullet reaches the top ofhte screen
        // then remove the bullet
        if (y - radius < 0) {
            // TODO Make the sound corresponding to the bullet leaving from top of screen
            SoundEffects.INSTANCE.playSound(SoundEffects.SOUND_BULLET);
            return false;
        }
        else
            return true;
    }

    // draw the bullet on the canvas
    public void draw(Canvas canvas) {
        canvas.drawCircle(x, y, radius, paint);
    }
}
