import java.util.Scanner;

class DigitRecognizer
{
    public static void main(String args[]){
        Vector2D input = new Vector2D(7);
        Perceptron p[] = new Perceptron[9];

        Scanner s = new Scanner(System.in);
        for(int i=0; i<input.v.size(); i++){
            input.v.set( i, s.nextInt() );
        }
        
        Vector2D vn = new Vector2D(7);
        vn.v.set(0,-2);
        vn.v.set(1,7);
        vn.v.set(2,6);
        vn.v.set(3,5);
        vn.v.set(4,3);
        vn.v.set(5,2);
        vn.v.set(6,2);
        
        p[0]= new Perceptron(input, vn, 24);

        vn.v.set(0,-2);
        vn.v.set(1,7);
        vn.v.set(2,6);
        vn.v.set(3,5);
        vn.v.set(4,3);
        vn.v.set(5,2);
        vn.v.set(6,2);

        vn.v.set(0,-2);
        vn.v.set(1,7);
        vn.v.set(2,6);
        vn.v.set(3,5);
        vn.v.set(4,3);
        vn.v.set(5,2);
        vn.v.set(6,2);
       
        vn.v.set(0,-2);
        vn.v.set(1,7);
        vn.v.set(2,6);
        vn.v.set(3,5);
        vn.v.set(4,3);
        vn.v.set(5,2);
        vn.v.set(6,2);
 
        vn.v.set(0,-2);
        vn.v.set(1,7);
        vn.v.set(2,6);
        vn.v.set(3,5);
        vn.v.set(4,3);
        vn.v.set(5,2);
        vn.v.set(6,2);

        vn.v.set(0,-2);
        vn.v.set(1,7);
        vn.v.set(2,6);
        vn.v.set(3,5);
        vn.v.set(4,3);
        vn.v.set(5,2);
        vn.v.set(6,2);
        
        vn.v.set(0,-2);
        vn.v.set(1,7);
        vn.v.set(2,6);
        vn.v.set(3,5);
        vn.v.set(4,3);
        vn.v.set(5,2);
        vn.v.set(6,2);

        
        if(p[0].success())
        {
            System.out.println("0 Detected");
        }else
        {
            System.out.println("0 Not Found");

        }
//p[i] = new Perceptron();

        
    }
}
