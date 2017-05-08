package net.artificialworlds.tunnelwar;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;

class ActuallyDrawGraphics
{
    static Paint paint = new Paint();
    static
    {
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    public static void actuallyDrawGraphics(VisRep visRep, Canvas canvas)
    {
        ScreenSize canvasSize = new ScreenSize(canvas.getWidth(), canvas.getHeight());
        canvas.drawColor(Color.BLACK);

        for (VisRep.Rect rect : visRep.rects)
        {
            ScreenCoord topLeft = game2Screen(rect.topLeft, canvasSize);
            ScreenSize size = game2Screen(rect.size, canvasSize);
            canvas.drawRect(topLeft.x, topLeft.y, topLeft.x + size.w, topLeft.x + size.h, paint);
        }
    }

    private static ScreenCoord game2Screen(Model.GameCoord topLeft, ScreenSize screenSize)
    {
        Model.GameSize relativeTopLeft = topLeft.minus(Model.topLeft);
        double x = screenSize.w * (relativeTopLeft.w / Model.size.w);
        double y = screenSize.h * (relativeTopLeft.h / Model.size.h);
        return new ScreenCoord((int)x, (int)y);
    }

    private static ScreenSize game2Screen(Model.GameSize size, ScreenSize screenSize)
    {
        double w = screenSize.w * (size.w / Model.size.w);
        double h = screenSize.h * (size.h / Model.size.h);
        return new ScreenSize((int)w, (int)h);
    }

}
