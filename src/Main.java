public class Main {
    public static void main(String[] args) throws InterruptedException {
        int amount = 5;
        Fork[] forks = new Fork[amount];
        for (int i = 0; i < forks.length; i++) {
            forks[i] = new Fork();
        }

        PhilosopherArbitrator[] philosophers = new PhilosopherArbitrator[amount];
        for (int i = 0; i < philosophers.length; i++) {
            philosophers[i] = new PhilosopherArbitrator(i, forks[i], forks[((i + 1) % philosophers.length)]);
            System.out.println(forks[i].id + " " + forks[((i + 1) % philosophers.length)].id);

        }

        for (PhilosopherArbitrator p : philosophers) {
            p.start();
        }

        for (PhilosopherArbitrator p : philosophers) {
            p.join();
        }

    }
}