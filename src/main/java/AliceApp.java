public class AliceApp {
    static class Alice extends Thread {
        @Override
        public void run() {
            System.out.println("Hi, this is Alice!");
        }
    }

    public static void main(final String[] args) throws InterruptedException {
        Thread alice = new Alice();
        alice.start();
        alice.join();
        System.out.println("Alice is done.");
    }
}
