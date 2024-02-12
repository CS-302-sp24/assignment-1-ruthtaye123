
class Philosopher extends Thread {
  private Chopstick first, second;
  private Random random;
  private int thinkCount;

  public Philosopher(Chopstick first, Chopstick second) {
      this.first = first;
      this.second = second;
      random = new Random();
  }

  public void run() {
      try {
          while (true) {
              ++thinkCount;
              if (thinkCount % 10 == 0)
                  System.out.println("Philosopher " + this + " has thought " + thinkCount + " times");
              Thread.sleep(random.nextInt(1000)); // Think for a while
              synchronized (first) { // Grab first chopstick
                  synchronized (second) { // Grab second chopstick
                      Thread.sleep(random.nextInt(1000)); // Eat for a while
                  }
              }
          }
      } catch (InterruptedException e) {
          Thread.currentThread().interrupt(); // Restore interrupted status
          return;
      }
  }
}