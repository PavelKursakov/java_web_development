import by.epam.lab.threads.Consumer;
import by.epam.lab.services.Drop;
import by.epam.lab.threads.Producer;

public class Runner {
    public static void main(String[] args) {
        Drop drop = new Drop();
        (new Thread(new Producer(drop))).start();
        (new Thread(new Consumer(drop))).start();
    }
}
