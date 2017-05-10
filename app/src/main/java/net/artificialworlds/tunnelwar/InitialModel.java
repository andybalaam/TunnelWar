package net.artificialworlds.tunnelwar;

import android.support.annotation.NonNull;

import net.artificialworlds.tunnelwar.Model.Tunnel.Segment;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;

import static net.artificialworlds.tunnelwar.FunctionUtils.list;
import static net.artificialworlds.tunnelwar.FunctionUtils.take;

class InitialModel
{
    private static final double initial_gap = 70.0;
    private static final double max_segment_change = 5.0;
    private static final double highest_allowed_segment_top = 5.0;
    private static final double lowest_allowed_segment_bottom = 95.0;
    private static final int num_segments = 100;

    static final double segment_width = Model.size.w / num_segments;

    static Model initialModel(EnabledPlayers enabledPlayers, RandomNumbers random)
    {
        return new Model(
            Arrays.asList(
                new Model.Player(
                    new Model.GameCoord(0, 0),
                    new Model.GameVelocity(0, 0),
                    Model.Player.State.IDLE
                )
            ),
            Collections.<Model.Bullet>emptyList(),
            initTunnel(num_segments, initial_gap, random),
            gameTimeNow()
        );
    }

    @NonNull
    static Model.GameTime gameTimeNow()
    {
        return new Model.GameTime(new Date().getTime());
    }

    @NonNull
    private static Model.Tunnel initTunnel(int numSegments, double gap, RandomNumbers random)
    {
        return new Model.Tunnel(
            list(take(numSegments, newSegments(gap, random))),
            gap
        );
    }

    private static Iterable<Segment> newSegments(
        final double gap, final RandomNumbers random)
    {
        return new Iterable<Segment>()
        {
            @Override
            public Iterator<Segment> iterator()
            {
                return new Iterator<Segment>()
                {
                    Segment currentSegment = new Segment(-1, 50 - (gap / 2), 50 + (gap / 2));

                    @Override
                    public boolean hasNext()
                    {
                        return true;
                    }

                    @Override
                    public Segment next()
                    {
                        Segment oldSegment = currentSegment;
                        currentSegment = makeSegment(oldSegment, gap, random);
                        return currentSegment;
                    }

                    @Override
                    public void remove()
                    {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }

    static Segment makeSegment(
        Segment oldSegment, double gap, RandomNumbers random
    )
    {
        double change = (random.nextDouble() * max_segment_change * 2) - max_segment_change;
        double newTopY = oldSegment.topY + change;
        if (newTopY < highest_allowed_segment_top || newTopY + gap > lowest_allowed_segment_bottom)
        {
            newTopY = oldSegment.topY - change;
        }
        return new Segment(oldSegment.x + segment_width, newTopY, newTopY + gap);
    }
}
