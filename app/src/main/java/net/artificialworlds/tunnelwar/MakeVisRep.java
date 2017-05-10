package net.artificialworlds.tunnelwar;

import net.artificialworlds.tunnelwar.Model.Player;
import net.artificialworlds.tunnelwar.VisRep.Rect;
import net.artificialworlds.tunnelwar.VisRep.Poly;
import net.artificialworlds.tunnelwar.FunctionUtils.Function;

import java.util.Collections;

import static net.artificialworlds.tunnelwar.FunctionUtils.list;
import static net.artificialworlds.tunnelwar.FunctionUtils.map;

class MakeVisRep
{
    private static Function<Player, Rect> playerShape()
    {
        return new Function<Player, Rect>()
        {
            @Override
            public Rect apply(Player player)
            {
                Model.GameSize size = new Model.GameSize(10, 10);
                Model.GameSize halfSize = size.dividedBy(2);
                return new VisRep.Rect(player.pos.minus(halfSize), size);
            }
        };
    }

    static VisRep makeVisRep(Model model)
    {
        return new VisRep(
            map(playerShape(), model.players),
            Collections.<Poly>emptyList()
        );
    }
}
