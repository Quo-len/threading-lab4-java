import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class PhilosopherArbitrator extends Thread {
    static int amount = 0;
    private final int id;
    public final Fork left;
    public final Fork right;
    static final Semaphore arbitrator = new Semaphore(2);

    public PhilosopherArbitrator(int id, Fork f1, Fork f2) {
        this.id = id; 
        this.left = f1;
        this.right = f2;
        PhilosopherArbitrator.amount++;
    }

    void pickUpBothForks() throws InterruptedException {
        arbitrator.acquire();
        while (true) {
            if (left.tryTake()) {
                if (!right.tryTake()) {
                    left.put();
                }
                else {
                    arbitrator.release();
                    break;
                }
            }
        }
    }

    void putDownBothForks() throws InterruptedException {
        left.put();
        right.put();
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 100; i++) {
                System.out.println("Philosopher " + id + " is trying to pick up forks " + left.id + " and " + right.id);
                pickUpBothForks();
                System.out.println("Philosopher " + id + " is eating");
                putDownBothForks();
                System.out.println("Philosopher " + id + " puts down forks " + left.id + " and " + right.id);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
