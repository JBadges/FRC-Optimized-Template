package threads;

public abstract class LoopedThread {

    private Thread thread;
    private double lastExecutionTime;
    // In seconds
    private double desiredLoopTime;
    private boolean shouldLoop = false;
    private long dt = 0;

    public LoopedThread() {

    }

    public LoopedThread(double desired_dt) {
        this.desiredLoopTime = desired_dt;
    }

    /**
     *
     * @param dt - seconds since last call
     */
    public abstract void update(double dt);

    public synchronized void start() {
        shouldLoop = true;
        loop();
    }

    public synchronized void stop() {
        shouldLoop = false;
    }

    public void loop() {
        while (shouldLoop) {
            long time = System.currentTimeMillis();
            update(dt/1000.0);
            dt = System.currentTimeMillis() - time;
            try {
                Thread.sleep((long) Math.max(0, desiredLoopTime*1000-dt));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            dt = System.currentTimeMillis() - time;
        }
    }

}
