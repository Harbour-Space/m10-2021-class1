public class BobApp {
    public static void main(String[] args) throws InterruptedException {
        Thread bob = new Thread(() -> System.out.println("Hi, this is an inlined Bob."));
        bob.start();
        System.out.println("Bob is started.");
        bob.join();
        System.out.println("Bob is done.");
    }
}
