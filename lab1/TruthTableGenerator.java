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
