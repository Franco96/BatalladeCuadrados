package com.carranza.lautaro.batalladecuadrados.GameObjects;


/**
 * Created by Lautaro on 29/06/2017.
 */

public class GameThread extends Thread {
    static final long FPS = 30;
    private GameObjectGraphics view;
    private boolean running = false;

    public GameThread(GameObjectGraphics view) {
        this.view = view;
    }
    public void setRunning(boolean run) {
        running = run;
    }
    @Override
    public void run() {
        long ticksPS = 1000 / FPS;
        long startTime;
        long sleepTime;
        while (running) {
            startTime = System.currentTimeMillis();
            view.postInvalidate();
            sleepTime = ticksPS-(System.currentTimeMillis() - startTime);
            try {
                if (sleepTime > 0)
                    sleep(sleepTime);
            } catch (Exception e) {}
        }
    }
}
