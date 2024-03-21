import java.util.concurrent.Semaphore;

public class PhilosopherRoom extends Thread {
    static int amount = 0;
    private final int id;
    public final Fork left;
    public final Fork right;
    static final Semaphore room = new Semaphore(4);

    public PhilosopherRoom(int id, Fork f1, Fork f2) {
        this.id = id;
        this.left = f1;
        this.right = f2;
        PhilosopherRoom.amount++;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 100; i++) {
                System.out.println("Philosopher " + id + " is trying to pick up forks " + left.id + " and " + right.id);
                room.acquire();
                left.take();
                right.take();
                System.out.println("Philosopher " + id + " is eating");
                left.put();
                right.put();
                room.release();
                System.out.println("Philosopher " + id + " puts down forks " + left.id + " and " + right.id);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
