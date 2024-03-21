import java.util.concurrent.Semaphore;

public class PhilosopherAsymmetric extends Thread {
    static int amount = 0;
    private final int id;
    public final Fork left;
    public final Fork right;

    public PhilosopherAsymmetric(int id, Fork f1, Fork f2) {
        this.id = id;
        this.left = (this.id % 2 == 0) ? f1 : f2;
        this.right = (this.id % 2 == 0) ? f2 : f1;
        PhilosopherAsymmetric.amount++;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 100; i++) {
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