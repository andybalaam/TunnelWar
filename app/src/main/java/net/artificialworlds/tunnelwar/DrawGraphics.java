package net.artificialworlds.tunnelwar;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

class DrawGraphics
{
    private final SurfaceHolder surfaceHolder;

    public DrawGraphics(SurfaceHolder surfaceHolder)
    {
        this.surfaceHolder = surfaceHolder;
    }

    public void drawGraphics(VisRep visRep)
    {
        Canvas canvas = surfaceHolder.lockCanvas();
        if (canvas == null)
        {
            return;
        }

        try
        {
            synchronized (surfaceHolder)
            {
                ActuallyDrawGraphics.actuallyDrawGraphics(visRep, canvas);
            }
        }
        finally
        {
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }
}
