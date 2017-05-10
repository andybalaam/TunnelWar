package net.artificialworlds.tunnelwar;

import android.view.SurfaceHolder;

class GameEngine
{
    private static class Runner implements Runnable
    {
        private final EnabledPlayers enabledPlayers;
        private final SurfaceHolder surfaceHolder;

        private boolean running;
        private static Model.GameTimeDelta frame_time = new Model.GameTimeDelta(20);

        Runner(SurfaceHolder surfaceHolder, EnabledPlayers enabledPlayers)
        {
            running = true;
            this.enabledPlayers = enabledPlayers;
            this.surfaceHolder = surfaceHolder;
        }

        @Override
        public void run()
        {
            RandomNumbers random = new RealRandomNumbers();
            Model model = InitialModel.initialModel(enabledPlayers, random);
            DrawGraphics drawGraphics = new DrawGraphics(surfaceHolder);

            while (running)
            {
                Input input = ProcessInput.processInput();
                model = Update.update(model, input, InitialModel.gameTimeNow(), random);
                drawGraphics.drawGraphics(MakeVisRep.makeVisRep(model));
                waitForNextFrame(model);
            }
        }

        private void waitForNextFrame(Model model)
        {
            try
            {
                Model.GameTime now = InitialModel.gameTimeNow();
                Model.GameTimeDelta timeAlreadySpent = now.minus(model.time);
                Model.GameTimeDelta timeToWait = frame_time.minus(timeAlreadySpent);
                if (timeToWait.millis > 0)
                {
                    Thread.sleep(timeToWait.millis);
                }
            }
            catch (InterruptedException e)
            {
                // Should never happen
                e.printStackTrace();
            }
        }

        void pleaseStop()
        {
            running = false;
        }
    }

    private final Runner runner;
    private final Thread thread;

    GameEngine(SurfaceHolder surfaceHolder, EnabledPlayers enabledPlayers)
    {
        runner = new Runner(surfaceHolder, enabledPlayers);
        thread = new Thread(runner);
    }

    void start()
    {
        thread.start();
    }

    void stop()
    {
        runner.pleaseStop();

        try
        {
            thread.join(1000);
        }
        catch (InterruptedException e)
        {
            // Should never happen
            e.printStackTrace();
        }

        if (thread.isAlive())
        {
            System.err.printf("Thread %s failed to stop\n", thread.getName());
        }
    }
}
