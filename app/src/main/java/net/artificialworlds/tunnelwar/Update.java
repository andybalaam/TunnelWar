package net.artificialworlds.tunnelwar;

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
            model.players,
            model.bullets,
            model.tunnel,
            model.time.plus(time_step)
        );
    }
}
