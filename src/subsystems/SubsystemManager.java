package subsystems;

import threads.LoopManager;

import java.util.ArrayList;
import java.util.List;

public class SubsystemManager {

    private List<Subsystem> subsystems = new ArrayList<>();
    private LoopManager lm = new LoopManager();

    private static SubsystemManager sm = null;

    public static SubsystemManager getInstance() {
        if (sm == null)
            sm = new SubsystemManager();
        return sm;
    }

    private SubsystemManager() {}

    public void addSubsystem(Subsystem s) {
        this.subsystems.add(s);
        lm.addLoop(s);
    }

    public void onEnable() {
        lm.callOnEnable();
    }

    public void onDisable() {
        lm.callOnDisable();
    }

}
