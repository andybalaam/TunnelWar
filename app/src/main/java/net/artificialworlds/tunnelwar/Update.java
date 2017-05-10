package net.artificialworlds.tunnelwar;

import net.artificialworlds.tunnelwar.Model.GameCoord;
import net.artificialworlds.tunnelwar.Model.GameSize;
import net.artificialworlds.tunnelwar.Model.GameTime;
import net.artificialworlds.tunnelwar.Model.Player;

import static net.artificialworlds.tunnelwar.FunctionUtils.list;
import static net.artificialworlds.tunnelwar.FunctionUtils.map;

class Update
{
    private static final int max_allowed_skips = 10;
    private static final Model.GameTimeDelta time_step = new Model.GameTimeDelta(5);

    static Model update(Model model, Input input, Model.GameTime gameTimeNow)
    {
        Model ret = model;

        for (int skipped = 0; skipped < max_allowed_skips; ++skipped)
        {
            if (ret.time.gte(gameTimeNow))
            {
                break;
            }
            ret = updateOnce(ret, input);
        }

        return ret;
    }

    private static Model updateOnce(Model model, Input input)
    {
        return new Model(
            list(map(movePlayer(model.time), model.players)),
            model.bullets,
            model.tunnel,
            model.time.plus(time_step)
        );
    }

    private static FunctionUtils.Function<Player, Player> movePlayer(final GameTime time)
    {
        return new FunctionUtils.Function<Player, Player>()
        {
            @Override
            public Player apply(Player player)
            {
                return new Player(new GameCoord(50 + 20 * Math.sin(time.millis / 100.0), 50), player.vel, player.state);
            }
        };
    }
}
