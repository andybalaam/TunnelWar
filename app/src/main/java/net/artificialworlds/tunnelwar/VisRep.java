package net.artificialworlds.tunnelwar;

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

        List<Rect> listRects = list(rects);
        List<Poly> listPolys = list(polys);

        this.rects = listRects.toArray(new Rect[listRects.size()]);
        this.polys = listPolys.toArray(new Poly[listPolys.size()]);
    }

    static final class Rect
    {
        final Model.GameCoord topLeft;
        final Model.GameSize size;

        Rect(Model.GameCoord topLeft, Model.GameSize size)
        {
            this.topLeft = topLeft;
            this.size = size;
        }
    }

    static final class Poly
    {
        private final Model.GameCoord[] points;

        Poly(Model.GameCoord... points)
        {
            this.points = points;
        }
    }
}
