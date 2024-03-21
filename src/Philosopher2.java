import java.util.concurrent.atomic.AtomicInteger;

public class Philosopher2 extends Thread {
    static int amount = 0;
    static AtomicInteger token = new AtomicInteger(0);
    private final int id;
    public final Fork left;
    public final Fork right;

    public Philosopher2(int id, Fork f1, Fork f2) {
        this.id = id;
        this.left = f1;
        this.right = f2;
        Philosopher2.amount++;
    }

    @Override
    public void run() {
        try {
            int hasEaten = 0;
            do
                if (token.get() == id) {
                    System.out.println("Philosopher " + id + " is trying to pick up forks " + left.id + " and " + right.id);
                    left.take();
                    right.take();
                    System.out.println("Philosopher " + id + " is eating");
                    hasEaten++;
                    token.set((id + 2) % amount);
                    left.put();
                    right.put();
                    System.out.println("Philosopher " + id + " puts down forks " + left.id + " and " + right.id);
                } while (hasEaten != 100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
