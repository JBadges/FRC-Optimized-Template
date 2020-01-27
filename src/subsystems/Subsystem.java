package subsystems;

import threads.Loop;

public abstract class Subsystem implements Loop {

    /**
     * Override the constructor if you do not want the subsystem
     * being adding automatically into the subsystem thread.
     * If overridden to not add, you may create extra LoopManagers
     * to run in a separate thread along side the automatically
     * generated Subsystem loop or controlled manually
     */
    public Subsystem() {
        SubsystemManager.getInstance().addSubsystem(this);
    }

}
