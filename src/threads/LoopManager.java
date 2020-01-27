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
    private double wanted_dt;

    public LoopManager() {
        this(0);
    }

    public LoopManager(double dt) {
        this.wanted_dt = dt;
        this.running = false;
        this.loops = new ArrayList<>();
        this.calledEnable = false;
        this.calledDisable = false;
        this.isEnabled = true;
        this.isDisabled = true;
        this.lt = new LoopedThread(wanted_dt) {
            @Override
            public void update(double dt) {
                for (Loop loop : loops) {
                    loop.allLoop(dt);
                }

                if (isEnabled) {
                    for (Loop loop : loops) {
                        loop.enabledLoop(dt);
                    }
                }

                if (isDisabled) {
                    for (Loop loop : loops) {
                        loop.disabledLoop(dt);
                    }
                }
            }
        };
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
            isEnabled = false;
            isDisabled = true;
            calledEnable = false;
            calledDisable = true;
            for (Loop loop : loops) {
                loop.onDisable();
            }
        }
    }

    public synchronized void start() {
        if (!running) {
            for (Loop loop : loops) {
                loop.onFirstStart();
            }
            running = true;
            lt.start();
        }
    }

    public synchronized void stop() {
        running = false;
        lt.stop();
    }

}