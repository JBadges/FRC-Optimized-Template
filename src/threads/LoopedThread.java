package threads;

public abstract class LoopedThread implements Runnable {

    private volatile Thread thread;
    // In seconds
    private double desiredLoopTime;
    private volatile boolean shouldLoop = false;
    // In seconds
    private long dt = 0;

    public LoopedThread() {
        this(0);
    }

    /**
     *
     * @param desired_dt - the minimum/goal delta time per loop.
     *                   Thus, set to 0 for looping as fast as possible
     */
    public LoopedThread(double desired_dt) {
        this.desiredLoopTime = desired_dt;
        thread = new Thread(this);
    }

    /**
     *
     * @param dt - seconds since last call
     */
    public abstract void update(double dt);

    @Override
    public void run() {
        long lastCall = System.nanoTime();
        double sleepTill = lastCall;
        while (shouldLoop) {
            long time = System.nanoTime();
            if(time > sleepTill) {
                sleepTill = desiredLoopTime * 1e9 + time;
                dt = time - lastCall;
                update(dt * 1e-9);
                lastCall = System.nanoTime();
            }
        }
    }

    public void start() {
        shouldLoop = true;
        if(thread == null || !thread.isAlive()) {
            thread = new Thread(this);
            thread.start();
        }
    }

    public void stop() {
        shouldLoop = false;
    }

    /**
     * May show exceptions in the console even when caught
     *
     * ForceStop calls the threads interrupt to try to end the
     * thread as quickly as possible. NOTE: THIS DOES NOT MEAN
     * THE THREAD WILL STOP IMMEDIATELY AFTER THIS METHOD IS CALLED.
     */
    public void interrupt() {
        shouldLoop = false;
        try {
            thread.interrupt();
        } catch (Exception e) {}
    }

}
