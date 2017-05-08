package net.artificialworlds.tunnelwar;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameSurfaceView extends SurfaceView implements SurfaceHolder.Callback
{
    private GameEngine gameEngine;
    private EnabledPlayers enabledPlayers;

    public GameSurfaceView(Context context)
    {
        super(context);

        gameEngine = null;
        enabledPlayers = null;

        getHolder().addCallback(this);
    }

    public void setEnabledPlayers(EnabledPlayers enabledPlayers)
    {
        this.enabledPlayers = enabledPlayers;
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder)
    {
        assert enabledPlayers != null;

        gameEngine = new GameEngine(surfaceHolder, enabledPlayers);
        gameEngine.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
    {
        // When does this happen?
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {
        if (gameEngine != null)
        {
            gameEngine.stop();
            gameEngine = null;
        }
    }
}
