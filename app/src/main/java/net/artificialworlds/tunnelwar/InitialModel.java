package net.artificialworlds.tunnelwar;

import android.support.annotation.NonNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

class InitialModel
{
    static Model initialModel(EnabledPlayers enabledPlayers)
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
            initTunnel(100, 0.5),
            gameTimeNow()
        );
    }

    @NonNull
    static Model.GameTime gameTimeNow()
    {
        return new Model.GameTime(new Date().getTime());
    }

    @NonNull
    private static Model.Tunnel initTunnel(int numStacks, double gap)
    {
        return new Model.Tunnel();
    }
}
