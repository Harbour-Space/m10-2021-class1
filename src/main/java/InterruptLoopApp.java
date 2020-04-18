
public class InterruptLoopApp {
    static class Alice extends Thread {
        @Override
        public void run() {
            System.out.println("Hi, this is Alice!");

            int counter = 0;
            while (!Thread.interrupted()) {
                if (counter % 100000 == 0) {
                    System.out.printf("You make my head go round and round... %d...%n", counter);
                }
                ++counter;
                Thread.yield();
            }

            System.out.printf("Alice is off at %d. Ciao!%n", counter);
        }
    }

    public static void main(final String[] args) throws InterruptedException {
        Thread alice = new Alice();
        alice.setName("Alice");
        System.out.println("Starting Alice.");
        alice.start();
        Thread.sleep(100);
        System.out.println("Interrupting Alice.");
        alice.interrupt();
        System.out.println("Joining Alice.");
        alice.join();
        System.out.println("Terminated.");
    }
}
