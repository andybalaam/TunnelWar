package net.artificialworlds.tunnelwar;

import net.artificialworlds.tunnelwar.Model.GameCoord;
import net.artificialworlds.tunnelwar.Model.GameSize;

import java.util.List;

import static net.artificialworlds.tunnelwar.FunctionUtils.list;

class VisRep
{
    final Rect[] rects;
    final Poly[] polys;

    public VisRep(Rect[] rects, Poly[] polys)
    {
        this.rects = rects;
        this.polys = polys;
    }

    public VisRep(Iterable<Rect> rects, Iterable<Poly> polys)
    {
        // In a sane world, this constructor would not exist
        this(makeRectArray(rects), makePolyArray(polys));
    }

    private static Rect[] makeRectArray(Iterable<Rect> rects)
    {
        List<Rect> listRects = list(rects);
        return listRects.toArray(new Rect[listRects.size()]);
    }

    private static Poly[] makePolyArray(Iterable<Poly> polys)
    {
        List<Poly> listPolys = list(polys);
        return listPolys.toArray(new Poly[listPolys.size()]);
    }

    enum Color
    {
        RED,
        WHITE
    }

    static final class Rect
    {
        final Color color;
        final Model.GameCoord topLeft;
        final Model.GameSize size;

        Rect(Color color, GameCoord topLeft, GameSize size)
        {
            this.color = color;
            this.topLeft = topLeft;
            this.size = size;
        }
    }

    static final class Poly
    {
        private final Model.GameCoord[] points;
        private final Color color;

        Poly(Color color, Model.GameCoord... points)
        {
            this.color = color;
            this.points = points;
        }
    }
}
