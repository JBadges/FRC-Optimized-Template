package auto;

public class AutonomousExecutor {

    private Thread thread;
    private AutonomousRoutine routine;

    public void setAutonomousRoutine(AutonomousRoutine ar) {
        this.routine = ar;
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                routine.routine();
            }
        });
    }

    public void start() {
        if(thread != null) {
            thread.start();
        }
    }

}
