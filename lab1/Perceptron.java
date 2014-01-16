//import java.util.*;

class Perceptron{
    Vector2D x;
    Vector2D w;
    int threshold;

    public Perceptron(Vector2D x, Vector2D w, int th){
        this.x = x;
        this.w = w;
        this.threshold = th;
    }

    public boolean success(){
        boolean s = false;
        if(x.dot(w) > threshold){
            s = true;
        }
        return s;
    }
}
