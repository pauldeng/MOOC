package hk.ust.cse.comp107x.shootinggamefinal;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;

/**
 * Created by muppala on 30/6/15.
 */
public class Score {

    private Paint paint;
    private int score;

    // Constructor
    public Score(int color) {
        paint = new Paint();
        // Set the font face and size of drawing text
        paint.setTypeface(Typeface.MONOSPACE);
        paint.setTextSize(24);
        paint.setColor(color);

        score = 0;
    }

    public void incrementScore() {
        score++;
    }

    public void decrementScore() {
        score--;
    }

    public int getScore() { return score; }

    public void draw(Canvas canvas) {

        // draw text on the canvas. Position the text at (10,30).
        canvas.drawText("Score: " + score, 10, 30, paint);
    }
}
