package net.artificialworlds.tunnelwar;

import net.artificialworlds.tunnelwar.Model.GameCoord;
import net.artificialworlds.tunnelwar.Model.GameSize;
import net.artificialworlds.tunnelwar.Model.Player;
import net.artificialworlds.tunnelwar.Model.Tunnel.Segment;
import net.artificialworlds.tunnelwar.VisRep.Color;
import net.artificialworlds.tunnelwar.VisRep.Rect;
import net.artificialworlds.tunnelwar.VisRep.Poly;
import net.artificialworlds.tunnelwar.FunctionUtils.Function;

import static net.artificialworlds.tunnelwar.FunctionUtils.flatten;
import static net.artificialworlds.tunnelwar.FunctionUtils.list;
import static net.artificialworlds.tunnelwar.FunctionUtils.map;

class MakeVisRep
{
    private static Function<Player, Poly> playerShape()
    {
        return new Function<Player, Poly>()
        {
            @Override
            public Poly apply(Player player)
            {
                //Model.GameSize size = new Model.GameSize(10, 10);
                //Model.GameSize halfSize = size.dividedBy(2);
                //return new VisRep.Poly(player.pos.minus(halfSize), size);
                return new VisRep.Poly(Color.WHITE);
            }
        };
    }

    static VisRep makeVisRep(Model model)
    {
        return new VisRep(
            flatten(map(tunnelRects(model), model.tunnel.segments)),
            map(playerShape(), model.players)
        );
    }

    private static Function<Segment, Iterable<Rect>> tunnelRects(final Model model)
    {
        final double segmentWidth = 0.2 + model.size.w / model.tunnel.segments.size();

        return new Function<Segment, Iterable<Rect>>()
        {
            @Override
            public Iterable<Rect> apply(Segment segment)
            {
                return list(
                    new Rect(
                        Color.RED,
                        new GameCoord(segment.x, 0),
                        new GameSize(segmentWidth, segment.topY)
                    ),
                    new Rect(
                        Color.RED,
                        new GameCoord(segment.x, segment.bottomY),
                        new GameSize(segmentWidth, model.size.h - segment.bottomY)
                    )
                );
            }
        };
    }
}
