public class Customer implements Runnable {
    String clientType;
    Window window;
    public void setClientType(String clientType) {
        this.clientType = clientType;
    }
    public String getClientType() {
        return this.clientType;
    }
    public void takeWindow(Window window) {
        this.window =  window;
    }
    @Override
    public void run() {
        synchronized (window) {
            window.serveClient(this);
        }
    }
}
