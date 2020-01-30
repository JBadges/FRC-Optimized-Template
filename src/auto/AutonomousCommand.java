package auto;

public abstract class AutonomousCommand {

    public abstract void init();

    public abstract void update(double dt);

    public abstract boolean isDone();

    public abstract boolean done();

}
