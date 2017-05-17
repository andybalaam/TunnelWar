package net.artificialworlds.tunnelwar;

import net.artificialworlds.tunnelwar.FunctionUtils.Function;
import net.artificialworlds.tunnelwar.Model.GameCoord;
import net.artificialworlds.tunnelwar.Model.GameTime;
import net.artificialworlds.tunnelwar.Model.Player;
import net.artificialworlds.tunnelwar.Model.Tunnel;
import net.artificialworlds.tunnelwar.Model.Tunnel.Segment;

import java.util.List;

import static net.artificialworlds.tunnelwar.FunctionUtils.list;
import static net.artificialworlds.tunnelwar.FunctionUtils.map;

class Update
{
    private static final int max_allowed_skips = 10;
    private static final Model.GameTimeDelta time_step = new Model.GameTimeDelta(20);
    private static final double initial_segment_velocity_units_per_milli = -0.02;
    private static final double min_gap_size = 40;

    static Model update(Model model, Input input, Model.GameTime gameTimeNow, RandomNumbers random)
    {
        Model ret = model;

        for (int skipped = 0; skipped < max_allowed_skips; ++skipped)
        {
            if (ret.time.gte(gameTimeNow))
            {
                break;
            }
            ret = updateOnce(ret, input, random);
        }

        return ret;
    }

    private static Model updateOnce(Model model, Input input, RandomNumbers random)
    {
        return new Model(
            list(map(movePlayer(model.time), model.players)),
            model.bullets,
            moveTunnel(model.tunnel, random),
            model.time.plus(time_step)
        );
    }

    private static Tunnel moveTunnel(Tunnel tunnel, RandomNumbers random)
    {
        final double segmentV = initial_segment_velocity_units_per_milli * time_step.millis;

        Function<Segment, Segment> moveSegment = new Function<Segment, Segment>()
        {
            @Override
            public Segment apply(Segment segment)
            {
                return new Segment(segment.x + segmentV, segment.topY, segment.bottomY);
            }
        };

        List<Segment> segments = list(map(moveSegment, tunnel.segments));

        if (segments.get(0).x < -InitialModel.segment_width)
        {
            Segment lastSegment = segments.get(segments.size() - 1);
            segments.remove(0);
            segments.add(InitialModel.makeSegment(lastSegment, tunnel.newSegmentGap, random));
        }
        else if (
            segments.get(segments.size() - 1).x
            > (segments.size() + 1) * InitialModel.segment_width
        )
        {
            Segment firstSegment = segments.get(0);
            segments.remove(segments.size() - 1);
            segments.add(
                0,
                InitialModel.makeSegment(firstSegment, tunnel.newSegmentGap, random, -1)
            );
        }

        double newGap = tunnel.newSegmentGap * (1 - (0.00001 * time_step.millis));
        if (newGap < min_gap_size)
        {
            newGap = min_gap_size;
        }
        return new Tunnel(segments, newGap);
    }

    private static FunctionUtils.Function<Player, Player> movePlayer(final GameTime time)
    {
        return new FunctionUtils.Function<Player, Player>()
        {
            @Override
            public Player apply(Player player)
            {
                return new Player(
                    new GameCoord(50 + 20 * Math.sin(time.millis / 100.0), 50),
                    player.vel,
                    player.state
                );
            }
        };
    }
}
