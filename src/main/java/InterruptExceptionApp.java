
public class InterruptExceptionApp {
    static class Alice extends Thread {
        @Override
        public void run() {
            System.out.println("Hi, this is Alice!");

            long before = System.currentTimeMillis();

            try {
                System.out.println("Alice is going to sleep... z-Z-Z...");
                Thread.sleep(1000);
                System.out.println("Alice is waking up... slowly...");
            } catch (InterruptedException exception) {
                System.out.println("Alice's sleep was interrupted! What a shame!");
            }

            long after = System.currentTimeMillis();

            System.out.printf("Alice was sleeping for %dms.%n", after - before);
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
