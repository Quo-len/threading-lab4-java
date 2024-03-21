import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class Fork {
    public final int id;
    private static int amount = 0;
    private final Semaphore semaphore;

    public Fork() {
        id = amount++;
        this.semaphore = new Semaphore(1);
    }

    public void take() throws InterruptedException {
        semaphore.acquire();
    }

    public void put() throws InterruptedException {
        semaphore.release();
    }

    public boolean tryTake() throws InterruptedException {
        return semaphore.tryAcquire();
    }
}
