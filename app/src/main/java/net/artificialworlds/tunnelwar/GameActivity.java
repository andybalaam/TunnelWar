package net.artificialworlds.tunnelwar;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class GameActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        GameSurfaceView gameSurfaceView = new GameSurfaceView(this);
        gameSurfaceView.setEnabledPlayers(new EnabledPlayers(
            this.getIntent().getIntExtra("red_humans", 1),
            this.getIntent().getIntExtra("red_ais", 0),
            this.getIntent().getIntExtra("green_humans", 0),
            this.getIntent().getIntExtra("green_ais", 1)
        ));

        ConstraintLayout gameLayout = (ConstraintLayout) findViewById(R.id.constraintLayout);
        assert gameLayout != null;
        gameLayout.addView(gameSurfaceView);
    }
}
