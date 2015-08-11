package hk.ust.cse.comp107x.shootinggamescore;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;


public class ShootingGame extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;

    private ImageButton moveLeftButton, moveRightButton, shootButton;
    private DrawView drawView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shooting_game);

        // Get a reference to the Custom View
        drawView = (DrawView) findViewById(R.id.drawView);

        // Get reference to the buttons and set their onClickListeners
        moveLeftButton = (ImageButton) findViewById(R.id.moveLeftButton);
        moveLeftButton.setOnClickListener(this);
        moveRightButton = (ImageButton) findViewById(R.id.moveRightButton);
        moveRightButton.setOnClickListener(this);
        shootButton = (ImageButton) findViewById(R.id.shootButton);
        shootButton.setOnClickListener(this);

        toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);                   // Setting toolbar as the ActionBar with setSupportActionBar() call
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_shooting_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        // Using the View's ID to distinguish which button was clicked
        switch(v.getId()) {

            case R.id.moveLeftButton:
                drawView.moveCannonLeft();
                break;

            case R.id.moveRightButton:
                drawView.moveCannonRight();
                break;
            case R.id.shootButton:
                drawView.shootCannon();
                break;
            default:
                break;
        }

    }
}
