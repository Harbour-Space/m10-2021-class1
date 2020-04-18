import java.util.Comparator;
import java.util.Objects;

public class CharlieApp {
    private static void threadAwareRoutine() {
        Thread t = Thread.currentThread();
        System.out.printf("Call from thread #%d named '%s'%n", t.getId(), t.getName());
    }

    static class EitherAliceOrBob extends Thread {
        private final long millis;

        public EitherAliceOrBob(String name, long millis) {
            this.setName(name);
            this.millis = millis;
        }

        @Override
        public void run() {
            Thread.yield();
            try {
                Thread.sleep(millis);
            } catch (InterruptedException ignored) {
            }
            threadAwareRoutine();
            System.out.printf("Hi, this is %s!%n", this.getName());
        }
    }

    static class Charlie extends Thread {
        private final long delayMs;

        public Charlie(long delayMs) {
            this.setName("Eye");
            this.setDaemon(true);
            this.delayMs = delayMs;
        }

        @Override
        public void run() {
            while (true) {
                StringBuilder sb = new StringBuilder();
                sb.append("<[[\n");
                Thread.getAllStackTraces().entrySet().stream()
                        .sorted((o1, o2) -> Objects.compare(o1.getKey().getId(),
                                o2.getKey().getId(), Comparator.naturalOrder()))
                        .forEach(threadEntry -> {
                            Thread thread = threadEntry.getKey();
                            StackTraceElement[] stackTraceElements = threadEntry.getValue();
                            sb.append("   <*| Thread ").append('#').append(thread.getId())
                                    .append(' ').append('[').append(thread.getName()).append(']')
                                    .append(" in state ").append('[').append(thread.getState())
                                    .append(']');
                            if (stackTraceElements.length > 0) {
                                sb.append(" in ").append(stackTraceElements[0].getClassName())
                                        .append(':').append(stackTraceElements[0].getMethodName())
                                        .append(" at ").append(stackTraceElements[0].getFileName())
                                        .append(':').append(stackTraceElements[0].getLineNumber());
                            }
                            sb.append('\n');
                        });
                sb.append("<]]\n");
                System.out.print(sb.toString());
                try {
                    Thread.sleep(delayMs);
                } catch (InterruptedException e) {
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Charlie(100).start();

        threadAwareRoutine();

        Thread alice = new EitherAliceOrBob("Alice", 400);
        Thread bob = new EitherAliceOrBob("Bob", 200);

        alice.start();
        bob.start();
        alice.join();
        bob.join();
    }
}
