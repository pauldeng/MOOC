package hk.ust.cse.comp107x.shootinggamescore;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by muppala on 23/5/15.
 */
public class DrawView extends View {

    private int width, height;

    Context mContext;

    // We can have multiple bullets and explosions
    // keep track of them in ArrayList
    ArrayList<Bullet> bullets;
    ArrayList<Explosion> explosions;
    Cannon cannon;
    AndroidGuy androidGuy;
    Score score;


    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;

        // create a cannon object
        cannon = new Cannon(Color.BLUE,mContext);

        // create arraylists to keep track of bullets and explosions
        bullets = new ArrayList<Bullet> ();
        explosions = new ArrayList<Explosion>();

        // create the falling Android Guy
        androidGuy = new AndroidGuy(Color.RED, mContext);

        // TODO create a new score object with Color.BLACK color


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawGameBoard(canvas);

        // Delay for a short while before forcing another redraw of the screen
        // If we update the screen every 30 ms, it's sufficiently fast 33 fps > 24 fps
        // needed for the human eye to see the motion as continuous.
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
        }

        // A call to invalidate causes the Android framework to call the onDraw
        // method of the DrawView
        // Thus every time the screen is refreshed, the framework is again
        // forced to call the onDraw
        // method. This creates the animation on the screen to simulate the game
        // playing
        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        width = w;
        height = h;

        cannon.setBounds(0,0,width,height);
        androidGuy.setBounds(0,0,width,height);
        for (int i = 0; i < bullets.size(); i++ ) {
            bullets.get(i).setBounds(0,0,width,height);
        }

    }

    public void drawGameBoard(Canvas canvas) {
        canvas.drawColor(Color.WHITE);     //if you want another background color
        // Draw the cannon
        cannon.draw(canvas);

        // Draw all the bullets
        for (int i = 0; i < bullets.size(); i++ ) {
            if (bullets.get(i) != null) {
                bullets.get(i).draw(canvas);

                if (bullets.get(i).move() == false) {
                    bullets.remove(i);
                }
            }
        }

        // Draw all the explosions, at those locations where the bullet
        // hits the Android Guy
        for (int i = 0; i < explosions.size(); i++ ) {
            if (explosions.get(i) != null) {
                if (explosions.get(i).draw(canvas) == false) {
                    explosions.remove(i);
                }
            }
        }

        // If the Android Guy is falling, check to see if any of the bullets
        // hit the Guy. Draw the Android Guy
        if (androidGuy != null) {
            androidGuy.draw(canvas);

            RectF guyRect = androidGuy.getRect();

            for (int i = 0; i < bullets.size(); i++ ) {

                // The rectangle surrounding the Guy and Bullet intersect, then it's a collision
                // Generate an explosion at that location and delete the Guy and bullet. Generate
                // a new Android Guy to fall from the top.
                if (RectF.intersects(guyRect, bullets.get(i).getRect())) {
                    explosions.add(new Explosion(Color.RED,mContext, androidGuy.getX(), androidGuy.getY()));
                    androidGuy.reset();
                    bullets.remove(i);
                    // TODO increment score
                    break;
                }

            }

            if (androidGuy.move() == false) {
                androidGuy = null;
            }
        }
        // TODO show the score on the screen

    }

    // Move the cannon left or right
    public void moveCannonLeft() {
        cannon.moveLeft();
    }

    public void moveCannonRight() {
        cannon.moveRight();
    }

    // Whenever the user shoots a bullet, create a new bullet moving upwards
    public void shootCannon() {

        bullets.add(new Bullet(Color.RED, mContext, cannon.getPosition(), (float) (height-40)));

    }
}
