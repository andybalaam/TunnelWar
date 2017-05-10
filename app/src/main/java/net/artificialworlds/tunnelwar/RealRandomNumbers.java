package net.artificialworlds.tunnelwar;

import java.util.Random;

class RealRandomNumbers implements RandomNumbers
{
    private final Random r = new Random();

    @Override
    public double nextDouble()
    {
        return r.nextDouble();
    }
}
