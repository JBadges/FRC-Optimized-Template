package threads;

import java.util.ArrayList;
import java.util.List;

public class LoopManager {

    private LoopedThread lt;
    private Object lock = new Object();
    private List<Loop> loops;
    private boolean running;
    private boolean calledEnable, calledDisable;
    private boolean isEnabled, isDisabled;

    public LoopManager() {
        running = false;
        loops = new ArrayList<>();
        calledEnable = false;
        calledDisable = false;
        isEnabled = true;
        isDisabled = true;
    }

    public synchronized void addLoop(Loop loop) {
        synchronized (lock) {
            if (loop != null) {
                loops.add(loop);
            }
        }
    }

    public void callOnEnable() {
        if (!calledEnable) {
            isEnabled = true;
            isDisabled = false;
            calledEnable = true;
            calledDisable = false;
            for (Loop loop : loops) {
                loop.onEnable();
            }
        }
    }

    public void callOnDisable() {
        if (!calledDisable) {
            calledDisable = true;
            calledEnable = false;
            isEnabled = false;
            isDisabled = true;
            for (Loop loop : loops) {
                loop.onDisable();
            }
        }
    }

    public synchronized void start() {
        if (!running) {
            synchronized (lock) {
                for (Loop loop : loops) {
                    loop.onFirstStart();
                }
                running = true;
            }
            lt.start();
        }
    }

    public synchronized void stop() {
        if (running) {
            lt.stop();
            synchronized (lock) {
                running = false;
            }
        }
    }

}