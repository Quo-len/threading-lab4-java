import java.util.concurrent.Semaphore;

public class Philosopher extends Thread {
    static int amount = 0;
    private final int id;
    public final Fork left;
    public final Fork right;
    static final Semaphore arbitrator1 = new Semaphore(1);
    static final Semaphore arbitrator2 = new Semaphore(1);


    public Philosopher(int id, Fork f1, Fork f2) {
        this.id = id;
        this.left = f1;
        this.right = f2;
        Philosopher.amount++;
    }

    void PickUpBothForks()
    {


    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println("Philosopher " + id + " is trying to pick up forks " + left.id + " and " + right.id);
                left.take();
                right.take();
                System.out.println("Philosopher " + id + " is eating");
                left.put();
                right.put();
                System.out.println("Philosopher " + id + " puts down forks " + left.id + " and " + right.id);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
