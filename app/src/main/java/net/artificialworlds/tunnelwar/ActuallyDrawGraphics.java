package net.artificialworlds.tunnelwar;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.HashMap;
import java.util.Map;

class ActuallyDrawGraphics
{
    // To allow us to draw slightly-off-screen
    private static final double horizontal_stretch = 1.01;

    private static Map<VisRep.Color, Paint> paints = makePaints();

    private static Map<VisRep.Color, Paint> makePaints()
    {
        HashMap<VisRep.Color, Paint> ret = new HashMap<>();
        ret.put(VisRep.Color.RED, makePaint(Color.RED));
        ret.put(VisRep.Color.WHITE, makePaint(Color.WHITE));
        return ret;
    }

    private static Paint makePaint(int color)
    {
        Paint ret = new Paint();
        ret.setColor(color);
        ret.setStyle(Paint.Style.FILL_AND_STROKE);
        return ret;
    }

    static void actuallyDrawGraphics(VisRep visRep, Canvas canvas)
    {
        ScreenSize canvasSize = new ScreenSize(canvas.getWidth(), canvas.getHeight());
        canvas.drawColor(Color.BLACK);

        for (VisRep.Rect rect : visRep.rects)
        {
            ScreenCoord topLeft = game2Screen(rect.topLeft, canvasSize);
            ScreenSize size = game2Screen(rect.size, canvasSize);
            canvas.drawRect(
                topLeft.x,
                topLeft.y,
                topLeft.x + size.w,
                topLeft.y + size.h,
                paints.get(rect.color)
            );
        }
    }

    private static ScreenCoord game2Screen(Model.GameCoord topLeft, ScreenSize screenSize)
    {
        Model.GameSize relativeTopLeft = topLeft.minus(Model.topLeft);
        double x = horizontal_stretch * screenSize.w * (relativeTopLeft.w / Model.size.w);
        double y = screenSize.h * (relativeTopLeft.h / Model.size.h);
        return new ScreenCoord((int)x, (int)y);
    }

    private static ScreenSize game2Screen(Model.GameSize size, ScreenSize screenSize)
    {
        double w = horizontal_stretch * screenSize.w * (size.w / Model.size.w);
        double h = screenSize.h * (size.h / Model.size.h);
        return new ScreenSize((int)w, (int)h);
    }

}
