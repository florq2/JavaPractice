import java.awt.datatransfer.Clipboard;
import java.util.Random;

public class Simulator {
    public static void main(String[] args) throws InterruptedException {

        int initialYoungCustomer = 10; // Изначальное кол-во клиентов. Для подсчета процентов.
        int initialElderyCustomer = 10;
        int initialBusinessCustomer = 4;

        int leftYoungCustomer = 0;
        int leftElderyCustomer = 0;
        int leftBusinessCustomer = 0;

        Window window1 = new Window("Общее");
        Window window2 = new Window("Пожилое");
        Window window3 = new Window("Бизнес");

        Random random = new Random();

        int youngCustomer = initialYoungCustomer; // Счетчик оставшихся клиентов.
        int elderyCustomer = initialElderyCustomer;
        int businessCustomer = initialBusinessCustomer;

        int totalCustomer = businessCustomer + youngCustomer + elderyCustomer;

        while (totalCustomer > 0) {

            Thread.sleep(200);

            Customer customer = new Customer();

            int i = random.nextInt(totalCustomer);

            if (i < youngCustomer) {
                customer.setClientType("Молодой");
                if (!window1.isBusy) {
                    customer.takeWindow(window1);

                    Thread customerThread = new Thread(customer);
                    customerThread.start();

                    youngCustomer--; // Уменьшаем кол-во оставшихся клиентов
                } else {
                    System.out.println(customer.getClientType() + " ушел");
                    youngCustomer--; // Уменьшаем кол-во оставшихся клиентов
                    leftYoungCustomer++; // Увеличиваем кол-во ушедших недовольных клиентов
                }
            } else if (i >= youngCustomer && i < youngCustomer + elderyCustomer) {
                customer.setClientType("Пожилой");
                if (!window2.isBusy) {
                    customer.takeWindow(window2);

                    Thread customerThread = new Thread(customer);
                    customerThread.start();

                    elderyCustomer--; // Уменьшаем кол-во оставшихся клиентов
                } else if (!window1.isBusy) {
                    customer.takeWindow(window1);

                    Thread customerThread = new Thread(customer);
                    customerThread.start();

                    elderyCustomer--; // Уменьшаем кол-во оставшихся клиентов
                } else {
                    System.out.println(customer.getClientType() + " ушел");
                    elderyCustomer--; // Уменьшаем кол-во оставшихся клиентов
                    leftElderyCustomer++; // Увеличиваем кол-во ушедших недовольных клиентов
                }
            } else if (i >= youngCustomer + elderyCustomer) {
                customer.setClientType("Бизнес");
                if (!window3.isBusy) {
                    customer.takeWindow(window3);

                    Thread customerThread = new Thread(customer);
                    customerThread.start();

                    businessCustomer--; // Уменьшаем кол-во оставшихся клиентов
                } else if (!window1.isBusy) {
                    customer.takeWindow(window1);

                    Thread customerThread = new Thread(customer);
                    customerThread.start();

                    businessCustomer--; // Уменьшаем кол-во оставшихся клиентов
                } else {
                    System.out.println(customer.getClientType() + " ушел");
                    businessCustomer--; // Уменьшаем кол-во оставшихся клиентов
                    leftBusinessCustomer++; // Увеличиваем кол-во ушедших недовольных клиентов
                }
            }
            totalCustomer = youngCustomer + elderyCustomer + businessCustomer;

            Thread.sleep(200);
        }

        System.out.println();

        System.out.println("Процент молодых клиентов: " + ((double) leftYoungCustomer / initialYoungCustomer * 100));
        System.out.println("Процент пожилых клиентов: " + ((double) leftElderyCustomer / initialElderyCustomer * 100));
        System.out.println("Процент бизнес клиентов: " + ((double) leftBusinessCustomer / initialBusinessCustomer * 100));
    }
}
