import java.util.ArrayList;

class Vector2D{
    ArrayList<Integer> v;

    public Vector2D(Vector2D x){
	v = new ArrayList<Integer>( x.v.size());
        for(int i=0; i<x.v.size(); i++){
            v.add( x.v.get(i) );
        }
    }
        
    public Vector2D(int n){
        v = new ArrayList<Integer>(n);
        for(int i=0; i<n; i++){
            v.add(0);
        }
    }

    public void add(Vector2D x){
        for(int i=0; i<x.v.size(); i++){
            int temp = v.get(i) + x.v.get(i);
            v.set(i, temp);
        }
    }

    public void negate(){
        for(int i=0; i<v.size(); i++){
            int temp = -1 * v.get(i);
            v.set(i, temp);
        }
        v.set( v.size()-1, 1);
    }

    public int dot(Vector2D x){
        int sum = 0;
        //System.out.println(x.v.size());
        for(int i=0; i<x.v.size(); i++){
            sum += v.get(i) * x.v.get(i);
        }
        //System.out.println(sum);
        return sum;
    }

    public int hashCode(){
        int hash = 0;
        for(int i=0; i<v.size(); i++){
            hash += v.get(i).hashCode();
        }
        return hash;
    }

    public boolean equals(Object o){
        if( o instanceof Vector2D ){
            Vector2D x = (Vector2D)o;
            for(int i=0; i<x.v.size(); i++){
                if(v.get(i) != x.v.get(i)){
                    return false;
                }
            }
            return true;
        }
        else
            return false;
    }
}
