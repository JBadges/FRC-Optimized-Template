import subsystems.SubsystemManager;
import threads.LoopedThread;

import java.util.Scanner;

public class Main {

    public static void main (String[] args) throws InterruptedException {

        SubsystemManager sm = SubsystemManager.getInstance();

        sm.start();

        Scanner sc = new Scanner(System.in);
        sc.nextLine();
    }

}
