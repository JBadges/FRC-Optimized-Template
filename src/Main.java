import threads.LoopedThread;

import java.util.Scanner;

public class Main {

    public static void main (String[] args) throws InterruptedException {

        LoopedThread lt = new LoopedThread(1) {
            @Override
            public void update(double dt) {
                System.out.println(dt);
            }
        };

        lt.start();
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
    }

}
