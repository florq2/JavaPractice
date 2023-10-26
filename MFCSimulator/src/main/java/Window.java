import java.util.Random;
import java.util.concurrent.Semaphore;

public class Window{
    String name;
    boolean isBusy = false;
    public Window(String name) {
        this.name = name;
    }
    public boolean isBusy() {
        return isBusy;
    }

    Semaphore semaphore = new Semaphore(1);
    public synchronized void serveClient(Customer customer) {
        isBusy = true;

        System.out.println(name + " окно обслуживает клиента: " + customer.getClientType());
        try {
            Thread.sleep(new Random().nextInt(2000)); // Имитация обслуживания
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + " окно завершил обслуживание клиента: " + customer.getClientType());

        isBusy = false;
    }
}
