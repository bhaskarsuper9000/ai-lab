import java.util.Set;
import java.util.HashSet;

class HashMapTest{
    public static void main(String args[]){
        Set set = new HashSet <Vector2D>();

        set.add( new Vector2D(3) );

        Vector2D v = new Vector2D(3);
        v.v.set(0,1);
        v.v.set(1,2);
        v.v.set(2,-3);

        set.add( v );

        System.out.println( set.contains(v) );
        System.out.println( set.contains(new Vector2D(3)) );
        System.out.println( set.contains(new Vector2D(3)) );

        System.out.println( set.contains("YO"));
        System.out.println( set.contains("YO"));
        System.out.println( set.contains("YO"));
        System.out.println( set.contains("YO"));
    }

}
