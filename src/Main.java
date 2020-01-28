import threads.LoopedThread;

public class Main {

    public static void main (String[] args) throws InterruptedException {

        LoopedThread lt = new LoopedThread(0.5) {
            @Override
            public void update(double dt) {
                System.out.println(dt);
                try {
                    Thread.sleep(4* 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("After");
            }
        };

        lt.start();
        Thread.sleep(3 * 1000);
        System.out.println("Stopped Thread");
        lt.interrupt();
        Thread.sleep(3 * 1000);
        System.out.println("Started Thread");
        lt.start();
        Thread.sleep(3 * 1000);
        System.out.println("Stopped Thread");
        lt.stop();
    }

}
