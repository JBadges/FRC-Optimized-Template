package auto;

public abstract class AutonomousRoutine {

    public abstract void routine();

    public void runCommand(AutonomousCommand command) {
        command.init();

        long lastCall = System.nanoTime();
        while(!command.isDone()) {
            double dt = (System.nanoTime() - lastCall) * 1e-9;
            command.update(dt);
            lastCall = System.nanoTime();
        }

        command.done();
    }

}
