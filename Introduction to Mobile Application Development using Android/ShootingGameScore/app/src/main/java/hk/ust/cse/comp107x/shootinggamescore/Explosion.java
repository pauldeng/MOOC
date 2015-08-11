package hk.ust.cse.comp107x.shootinggamescore;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * Created by muppala on 24/5/15.
 */
public class Explosion {
    float x; // Ball's center (x,y)
    float y;
    int count = 30;

    private static Bitmap explosion;


    private Context mContext;

    // Constructor
    public Explosion(int color, Context c, float loc_x, float loc_y) {

        mContext = c;

        explosion = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.explosion),50,50, false);

        x = loc_x;
        y = loc_y;

    }

    // Explosion is drawn for about 1 second on the screen (30 times) before being removed.
    public boolean draw(Canvas canvas) {

        if (count-- == 0)
            return false;
        else {
            canvas.drawBitmap(explosion, x, y, null);
            return true;
        }
    }
}
