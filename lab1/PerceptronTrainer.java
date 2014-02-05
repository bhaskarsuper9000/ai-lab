import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;


class PerceptronTrainer{

    //Hard coded truth table for the time being
    ArrayList <Vector2D> tt;
    Perceptron p;

    /*public PerceptronTrainer(Perceptron p){
        //this.tt = tt;
        this.p = p;
    }*/

    public void train(ArrayList <Vector2D> tt){
        //1. Accept tt
        this.tt = tt;
        //2. Augment it(skip if already augmented)
        
        //3. Negate zero classes
        for(int i=0; i<tt.size(); i++){
            Vector2D v = tt.get(i);
            if(v.v.get( v.v.size()-1 ) == 0){
                v.negate();
            }else{
                v.v.set( v.v.size()-1, -1);
            }
            System.out.println("Negating "+v.v);
        }
        //4. Assume random w
        Vector2D w = new Vector2D(tt.get(0).v.size());
        
        //5. Iterate over all xi
        //6.a. If test succeeds, return w
        //6.b. If test fails, w = w + xi(fail) & goto step 5
        boolean done = false, cycleDetected = false;
        int count = 0;

        //detect cycles using HashMap
        Set set = new HashSet<Vector2D>();

        while( !done ){
            done = true;
            for(int i=0; i<tt.size(); i++){
                Perceptron p = new Perceptron(tt.get(i), w, 0);
                if(!p.success()){
                    done = false;
                    w.add(tt.get(i));
                    count ++;
                    //System.out.println(w.v);
                    if( set.contains(w) ){
                        cycleDetected = true;
                        System.out.println("Cycle Detected in "+count+" iterations");
                        System.exit(1);
                    }
                    set.add(new Vector2D(w)); 
                    break;
                }
            }

        }

        System.out.println(w.v + " was found in " +count + " iterations");
    }

    public static void main(String args[]){
        //Generate truth table
        ArrayList <Vector2D> tt = new ArrayList <Vector2D>();

        Vector2D v1 = new Vector2D(3);
        v1.v.set(0,0);
        v1.v.set(1,0);
        v1.v.set(2,0);

        Vector2D v2 = new Vector2D(3);
        v2.v.set(0,0);
        v2.v.set(1,1);
        v2.v.set(2,1);

        Vector2D v3 = new Vector2D(3);
        v3.v.set(0,1);
        v3.v.set(1,0);
        v3.v.set(2,1);

        Vector2D v4 = new Vector2D(3);
        v4.v.set(0,1);
        v4.v.set(1,1);
        v4.v.set(2,0);


        tt.add(v1);
        tt.add(v2);
        tt.add(v3);
        tt.add(v4);

        PerceptronTrainer pt = new PerceptronTrainer();
        //pt.train(tt);

        pt = new PerceptronTrainer();
        //the parameter to the constructor is the no of input variables
        TruthTableGenerator tt_gen= new TruthTableGenerator(7);

        //setFuncType allows you to either choose from a set of 
        //well known functions like AND, OR, NAND, NOR, XOR, XNOR, 
        //PALINDROME, MAJORITY, EVEN_PARITY & MANUAL
        tt_gen.setFuncType(TruthTableGenerator.DIGIT_ONE);
        tt = tt_gen.generateTT();
        pt.train(tt);
        
    }
}
