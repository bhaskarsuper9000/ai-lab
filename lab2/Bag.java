import java.util.ArrayList;

class Bag {
    //private int contents;
    private boolean available = false;
    public ArrayList<String> a;

    public synchronized void check( boolean b ) {
        while (available == b) {
            try {
                wait();
            }
            catch (InterruptedException e) {
            }
        }
        available = !available;
        notifyAll();
        //return contents;
    }
}
