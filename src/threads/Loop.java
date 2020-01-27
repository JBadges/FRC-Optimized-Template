package threads;

public interface Loop {

    /**
     * Called at the end of robot init
     * It is not guaranteed to be called before any teleop or autonomous init
     * commands if not enough time is given for the threads to start
     */
    void onFirstStart();

    /**
     * Loops periodically no matter the state of the robot
     * @param dt - in seconds
     */
    void allLoop(double dt);

    /**
     * Called when the robot is enabled
     */
    void onEnable();

    /**
     * Loops periodically if the DriverStation object returns that the robot is enabled
     * @param dt - in seconds
     */
    void enabledLoop(double dt);

    /**
     * Called at the start of every init method that will disable the robot
     */
    void onDisable();

    /**
     * Loops periodically if the DriverStation object returns that the robot is disabled
     * @param dt - in seconds
     */
    void disabledLoop(double dt);
}