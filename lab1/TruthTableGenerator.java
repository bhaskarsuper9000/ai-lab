import java.util.ArrayList;
import java.util.Scanner;

class TruthTableGenerator {

    ArrayList <Vector2D> tt;
    int n;
    int funcType;

    final static int AND  = 0;
    final static int OR   = 1;
    final static int NAND = 2;
    final static int NOR  = 3;
    final static int XOR  = 4;
    final static int XNOR = 5; 
    final static int PALINDROME  = 6; 
    final static int EVEN_PARITY  = 7;
    final static int MAJORITY = 8;

    //seven segment entries
    final static int DIGIT_ZERO  = 9;
    final static int DIGIT_ONE   = 10;
    final static int DIGIT_TWO   = 11;
    final static int DIGIT_THREE = 12;
    final static int DIGIT_FOUR  = 13;
    final static int DIGIT_FIVE  = 14;
    final static int DIGIT_SIX   = 15;
    final static int DIGIT_SEVEN = 16;
    final static int DIGIT_EIGHT = 17;
    final static int DIGIT_NINE  = 18;

    final static int MANUAL = 100;
    
    //for n boolean input variables
    public TruthTableGenerator(int n){
        this.n = n;
        if(tt != null)
            tt.clear();
        //tt = new ArrayList <Vector2D>();

        setFuncType(NAND);

        //tt = generateTT();
    }    
    
    ArrayList<Vector2D> generateTT(){
        ArrayList<Vector2D> ttn = new ArrayList<Vector2D>();
        for(int i=0; i<(int)Math.pow(2,n); i++){
            Vector2D vn = new Vector2D(n);

            int temp = i;
            for(int j=0; j<n; j++){
                vn.v.set(n-1-j, temp%2);
                temp = temp/2;
            }

            vn.v.add( func(vn) );
            System.out.println(vn.v);
            ttn.add(vn);
        }
        return ttn;
    }

    public void setFuncType(int n){
        funcType = n;
    }
    public int func(Vector2D vn){
        boolean result = false;
        int count0 = 0;

        switch(funcType){
            case NAND:
            //boolean a[vn.size()];
            result = (vn.v.get(0)==1?true:false);
            for(int i=1; i<vn.v.size(); i++){
                result &= (vn.v.get(i)==1?true:false);
            }
            result = !result;

            break;
            
            case AND:
            result = (vn.v.get(0)==1?true:false);
            for(int i=1; i<vn.v.size(); i++){
                result &= (vn.v.get(i)==1?true:false);
            }
 
            break;
            
            case OR:
            result = (vn.v.get(0)==1?true:false);
            for(int i=1; i<vn.v.size(); i++){
                result |= (vn.v.get(i)==1?true:false);
            }
            break;
            
            case NOR:
            result = (vn.v.get(0)==1?true:false);
            for(int i=1; i<vn.v.size(); i++){
                result |= (vn.v.get(i)==1?true:false);
            }
            result = !result;
            break;
            
            case XOR:
            result = (vn.v.get(0)==1?true:false);
            for(int i=1; i<vn.v.size(); i++){
                result ^= (vn.v.get(i)==1?true:false);
            }
            break;

            case XNOR:
            result = (vn.v.get(0)==1?true:false);
            for(int i=1; i<vn.v.size(); i++){
                result ^= (vn.v.get(i)==1?true:false);
            }

            result = !result;
            break;

            case PALINDROME:
            result = true;
            for(int i=0; i<vn.v.size()/2; i++){
                if( vn.v.get(i) != vn.v.get(n-1-i) ){
                    result = false;
                    break;
                }
            }

            break;

            case EVEN_PARITY:
            result = (vn.v.get(0)==1?false:true);
            for(int i=1; i<vn.v.size(); i++){
                result = (vn.v.get(i)==1?result^true:result);
            }
      
            break;

            case MAJORITY:
            result = false;
            int count1 = 0;
            for(int i=0; i<vn.v.size(); i++){
                if(vn.v.get(i) == 1)
                    count1++;
            }
            if( count1 > (vn.v.size()/2) )
                result = true;
            break;

            case DIGIT_ZERO:
            if(vn.v.size()<6){
                throw new RuntimeException("Input Vector should have at least 7 inputs");               
            }
            result = false;
            //int count0 = 0;
            if(vn.v.get(0) == 0 && vn.v.get(1)== 1 && vn.v.get(2)== 1 && vn.v.get(3)== 1 
                && vn.v.get(4)== 1 && vn.v.get(5)== 1 && vn.v.get(6)== 1 )
                result = true;
            break;

            case DIGIT_ONE:
            if(vn.v.size()<6){
                throw new RuntimeException("Input Vector should have at least 7 inputs");               
            }
            result = false;
            //int count0 = 0;
            if(vn.v.get(0) == 0 && vn.v.get(1)== 0 && vn.v.get(2)== 0 && vn.v.get(3)== 1 
                && vn.v.get(4)== 1 && vn.v.get(5)== 0 && vn.v.get(6)== 0 )
                result = true;
            break;
            
            case DIGIT_TWO:
            if(vn.v.size()<6){
                throw new RuntimeException("Input Vector should have at least 7 inputs");               
            }
            result = false;
            if(vn.v.get(0) == 1 && vn.v.get(1)== 0 && vn.v.get(2)== 1 && vn.v.get(3)== 1 
                && vn.v.get(4)== 0 && vn.v.get(5)== 1 && vn.v.get(6)== 1 )
                result = true;

            break;

            case DIGIT_THREE:
            if(vn.v.size()<6){
                throw new RuntimeException("Input Vector should have at least 7 inputs");               
            }
            result = false;
            if(vn.v.get(0) == 1 && vn.v.get(1)== 0 && vn.v.get(2)== 1 && vn.v.get(3)== 1 
                && vn.v.get(4)== 1 && vn.v.get(5)== 1 && vn.v.get(6)== 0 )
                result = true;

            break;
           
            case DIGIT_FOUR:
            if(vn.v.size()<6){
                throw new RuntimeException("Input Vector should have at least 7 inputs");               
            }
            result = false;
            if(vn.v.get(0) == 1 && vn.v.get(1)== 1 && vn.v.get(2)== 0 && vn.v.get(3)== 1 
                && vn.v.get(4)== 1 && vn.v.get(5)== 0 && vn.v.get(6)== 0 )
                result = true;

            break;
            
            case DIGIT_FIVE:
            if(vn.v.size()<6){
                throw new RuntimeException("Input Vector should have at least 7 inputs");               
            }
            result = false;
            if(vn.v.get(0) == 1 && vn.v.get(1)== 1 && vn.v.get(2)== 1 && vn.v.get(3)== 0
                && vn.v.get(4)== 1 && vn.v.get(5)== 1 && vn.v.get(6)== 0 )
                result = true;

            break;
 
            case DIGIT_SIX:
            if(vn.v.size()<6){
                throw new RuntimeException("Input Vector should have at least 7 inputs");               
            }
            result = false;
            if(vn.v.get(0) == 0 && vn.v.get(1)== 1 && vn.v.get(2)== 1 && vn.v.get(3)== 0 
                && vn.v.get(4)== 1 && vn.v.get(5)== 1 && vn.v.get(6)== 1 )
                result = true;

            break;

            case DIGIT_SEVEN:
            if(vn.v.size()<6){
                throw new RuntimeException("Input Vector should have at least 7 inputs");               
            }
            result = false;
            if(vn.v.get(0) == 0 && vn.v.get(1)== 0 && vn.v.get(2)== 1 && vn.v.get(3)== 1 
                && vn.v.get(4)== 1 && vn.v.get(5)== 0 && vn.v.get(6)== 0 )
                result = true;

            break;

            case DIGIT_EIGHT:
            if(vn.v.size()<6){
                throw new RuntimeException("Input Vector should have at least 7 inputs");               
            }
            result = false;
            if(vn.v.get(0) == 1 && vn.v.get(1)== 1 && vn.v.get(2)== 1 && vn.v.get(3)== 1 
                && vn.v.get(4)== 1 && vn.v.get(5)== 1 && vn.v.get(6)== 1 )
                result = true;

            break;

            case DIGIT_NINE:
            if(vn.v.size()<6){
                throw new RuntimeException("Input Vector should have at least 7 inputs");               
            }
            result = false;
            if(vn.v.get(0) == 1 && vn.v.get(1)== 1 && vn.v.get(2)== 1 && vn.v.get(3)== 1 
                && vn.v.get(4)== 1 && vn.v.get(5)== 1 && vn.v.get(6)== 0 )
                result = true;

            break;

            default:
            Scanner s = new Scanner(System.in);
            System.out.print("Enter the truth value for "+vn.v+" :");
            int i = s.nextInt();
            
            result = (i==0?false:true);
            break;
        }
        return result?1:0;
    }
}
