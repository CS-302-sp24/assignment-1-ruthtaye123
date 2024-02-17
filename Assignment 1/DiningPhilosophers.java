public class DiningPhilosophers {
public static void main(String[] args) throws InterruptedException {
        // Parse command-line arguments
        if (args.length != 5) {
            System.err.println("Usage: java DiningPhilosophers np nc tt et rl");
            return;
        }

        int np = Integer.parseInt(args[0]); // Number of philosophers (and forks)
        int nc = Integer.parseInt(args[1]); // Number of cycles
        int tt = Integer.parseInt(args[2]); // Maximum thinking time (milliseconds)
        int et = Integer.parseInt(args[3]); // Maximum eating time (milliseconds)
        int rl = Integer.parseInt(args[4]); // Right-handed or left-handed

        Chopstick[] chopsticks = new Chopstick[np];
        Philosopher[] philosophers = new Philosopher[np];

        // Create chopsticks
        for (int i = 0; i < np; i++) {
            chopsticks[i] = new Chopstick(i);
        }

        // Create philosophers
        for (int i = 0; i < np; i++) {
            boolean rightHanded = rl == 0 || (rl == 1 && i % 2 == 0);
            philosophers[i] = new Philosopher(i, chopsticks[i], chopsticks[(i + 1) % np], nc, tt, et, rightHanded);
            philosophers[i].start();
        }

        // Join philosophers
        for (Philosopher philosopher : philosophers) {
            philosopher.join();
        }
    }
  }
  