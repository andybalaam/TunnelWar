package net.artificialworlds.tunnelwar;

import java.util.List;

/**
 * The state of the game at a moment.
 */
final class Model
{
    final static GameCoord topLeft = new GameCoord(0, 0);
    private final static GameCoord bottomRight = new GameCoord(100, 100);
    final static GameSize size = bottomRight.minus(topLeft);

    static final class GameTime
    {
        // Milliseconds since 1970-01-01 00:00+0000
        final long millis;

        GameTime(long millis)
        {
            this.millis = millis;
        }

        boolean gte(GameTime other)
        {
            return millis >= other.millis;
        }

        GameTime plus(GameTimeDelta delta)
        {
            return new GameTime(millis + delta.millis);
        }

        GameTimeDelta minus(GameTime other)
        {
            return new GameTimeDelta(millis - other.millis);
        }
    }

    static final class GameTimeDelta
    {
        // Milliseconds duration
        final long millis;

        GameTimeDelta(long millis)
        {
            this.millis = millis;
        }

        GameTimeDelta minus(GameTimeDelta other)
        {
            return new GameTimeDelta(millis - other.millis);
        }
    }

    static final class GameCoord
    {
        final double x;
        final double y;

        GameCoord(double x, double y)
        {
            this.x = x;
            this.y = y;
        }

        GameSize minus(GameCoord other)
        {
            return new GameSize(x - other.x, y - other.y);
        }

        GameCoord minus(GameSize other)
        {
            return new GameCoord(x - other.w, y - other.h);
        }

        public GameCoord plus(GameSize other)
        {
            return new GameCoord(x + other.w, y + other.h);
        }
    }

    static final class GameSize
    {
        final double w;
        final double h;

        GameSize(double w, double h)
        {
            this.w = w;
            this.h = h;
        }

        GameSize dividedBy(double scalar)
        {
            return new GameSize(w / scalar, h / scalar);
        }
    }

    static final class GameVelocity
    {
        final double dx;
        final double dy;

        GameVelocity(double dx, double dy)
        {
            this.dx = dx;
            this.dy = dy;
        }
    }

    static final class Player
    {
        enum State
        {
            IDLE,
            FIRING,
            JETTING
        }

        final GameCoord pos;
        final GameVelocity vel;
        final State state;

        Player(GameCoord pos, GameVelocity vel, State state)
        {
            this.pos = pos;
            this.vel = vel;
            this.state = state;
        }
    }

    static final class Bullet
    {

    }

    static final class Tunnel
    {
        static final class Segment
        {
            final double topY;
            final double bottomY;
            final double x;

            Segment(double x, double topY, double bottomY)
            {
                this.x = x;
                this.topY = topY;
                this.bottomY = bottomY;
            }
        }

        final List<Segment> segments;
        final double newSegmentGap;

        Tunnel(List<Segment> segments, double newSegmentGap)
        {
            this.segments = segments;
            this.newSegmentGap = newSegmentGap;
        }

    }

    final List<Player> players;
    final List<Bullet> bullets;
    final Tunnel tunnel;
    final GameTime time;

    Model(List<Player> players, List<Bullet> bullets, Tunnel tunnel, GameTime time)
    {
        this.players = players;
        this.bullets = bullets;
        this.tunnel = tunnel;
        this.time = time;
    }
}
