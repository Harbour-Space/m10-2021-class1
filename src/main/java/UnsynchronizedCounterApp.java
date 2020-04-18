
public class UnsynchronizedCounterApp {
    static class Counter {
        private int value = 0;

        int incrementAndGet() {
            int oldValue = value;
            int newValue = oldValue + 1;
            value = newValue;
            return newValue;
        }

        int get() {
            return value;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Counter c = new Counter();

        Runnable runnable = () -> {
            String name = Thread.currentThread().getName();
            System.out.printf("Hi, this is %s!%n", name);
            for (int i = 0; i < 1_000_000; i++) {
                c.incrementAndGet();
            }
            System.out.printf("%s is done.%n", name);
        };

        Thread alice = new Thread(runnable);
        alice.setName("Alice");

        Thread bob = new Thread(runnable);
        bob.setName("Bob");

        alice.start();
        bob.start();

        alice.join();
        bob.join();

        System.out.printf("Final counter value is %d.%n", c.get());
    }
}
