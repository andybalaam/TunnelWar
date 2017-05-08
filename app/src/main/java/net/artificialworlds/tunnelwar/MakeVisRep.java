package net.artificialworlds.tunnelwar;

class MakeVisRep
{
    static VisRep makeVisRep(Model model)
    {
        return new VisRep(
            new VisRep.Rect[]
            {
                new VisRep.Rect(new Model.GameCoord(20, 20), new Model.GameSize(10, 10))
            },
            new VisRep.Poly[]
            {
                new VisRep.Poly(new Model.GameCoord(5, 5), new Model.GameCoord(10, 5))
            }
        );
    }
}
