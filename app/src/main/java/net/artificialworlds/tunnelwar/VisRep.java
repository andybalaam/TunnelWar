package net.artificialworlds.tunnelwar;

class VisRep
{
    final Rect[] rects;
    final Poly[] polys;

    public VisRep(Rect[] rects, Poly[] polys)
    {
        this.rects = rects;
        this.polys = polys;
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
