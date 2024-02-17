import java.util.Random;

class Philosopher extends Thread {
    private Chopstick left, right;
    private Random random;
    //private int thinkCount;
    private int id;
    private int cycles;
    private int maxThinkingTime;
    private int maxEatingTime;
    private boolean rightHanded;

    public Philosopher(int id, Chopstick left, Chopstick right, int cycles, int maxThinkingTime, int maxEatingTime, boolean rightHanded) {
        this.id = id;
        this.left = left;
        this.right = right;
        this.cycles = cycles;
        this.maxThinkingTime = maxThinkingTime;
        this.maxEatingTime = maxEatingTime;
        this.rightHanded = rightHanded;
        random = new Random();
    }

    @Override
    public void run() {
        try {
            for (int i = 0; cycles == 0 || i < cycles; i++) {
                think();
                eat();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void think() throws InterruptedException {
        System.out.println("Philosopher " + id + " thinks for " + random.nextInt(maxThinkingTime) + " units");
        Thread.sleep(random.nextInt(maxThinkingTime)); // Think for a while
    }

    private void eat() throws InterruptedException {
        System.out.println("Philosopher " + id + " wants " + (rightHanded ? "right" : "left") + " chopstick");
        synchronized (rightHanded ? right : left) { // Grab right/left chopstick
            System.out.println("Philosopher " + id + " has " + (rightHanded ? "right" : "left") + " chopstick");
            synchronized (rightHanded ? left : right) { // Grab left/right chopstick
                System.out.println("Philosopher " + id + " has " + (rightHanded ? "left" : "right") + " chopstick");
                System.out.println("Philosopher " + id + " eats for " + random.nextInt(maxEatingTime) + " units");
                Thread.sleep(random.nextInt(maxEatingTime)); // Eat for a while
            }
        }
    }
}