import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

class AStar{
    protected HashSet<State> closedSet;
    protected PriorityQueue<State> openSet;
    protected HashMap<State, State> cameFrom;
    
    protected int ITERATIONS = 0;
    protected int PARENT_REDIRECTS = 0;
    protected int PATH_LENGTH = 0;
    
    void showStatus(){
    	System.out.println("No of Iterations = "+ ITERATIONS);
    	System.out.println("Path Length 	 = "+ PATH_LENGTH);
    	System.out.println("Parent Ptr Redir = "+ PARENT_REDIRECTS +" times");
    }

    Problem p;
    State current;

    AStar(){
        p = new Problem();
        closedSet = new HashSet<State> ();
        openSet   = new PriorityQueue<State> ();
        cameFrom  = new HashMap<State, State> ();
    }

    public ArrayList<State> aStarSearch(){
        Debug.println("start = " + p.start + "goal = "+ p. goal);
        return aStarSearch(p.start, p.goal);
    }

    public ArrayList<State> aStarSearch(State start, State goal){
        p.setStart(start);
        p.setGoal(goal);


        closedSet.clear();
        openSet.add(start);
        cameFrom.clear();

        start.gScore = 0;
        start.fScore = start.gScore + p.getHx(start);

        while(openSet.size() > 0){
        	ITERATIONS++;
        
            current = openSet.poll();
            p.setCurrState(current);
            Debug.println("Next move = "+current+" f(x)="+current.fScore);

            Debug.println("Checking for goal...");
            if( current.equals(goal) )
                return reconstructPath(cameFrom, current);

            if(current.cameFrom == null)    
                current.gScore = 0;
            else
                current.gScore = current.cameFrom.gScore + p.getGx();

            //current.fScore = current.gScore + p.getHx(current);


            closedSet.add(current);
            //State tempStates[] = neighbour(current);
            for( State neighbour : p.getNeighbours(current) ){
                Debug.println("neighbour: "+neighbour+ "f(x)="+neighbour.fScore);
                if(closedSet.contains(neighbour)){
                    Debug.println("Closed Set contains neighbour");
                    continue;
                }

                int tempScore = current.gScore + p.distBetween(current, neighbour);
                Debug.println("Dist = "+tempScore);


                if( openSet.contains(neighbour) ){
                    //Debug.println("openSet contains neighbour" + openSet + ": "+neighbour + "#");
                    if( neighbour.cameFrom == null || neighbour.cameFrom.gScore > current.gScore){
                        neighbour.cameFrom = current;
                        neighbour.gScore = current.gScore;
                        PARENT_REDIRECTS++;
                        Debug.println("[OSet]Parent pointer redirect");
                    }
                }else if( closedSet.contains(neighbour) ){
                    Debug.println("closedSet contains neighbour");
                    if( neighbour.cameFrom == null || neighbour.cameFrom.gScore > current.gScore){
                        neighbour.cameFrom = current;
                        neighbour.gScore = current.gScore;
                        PARENT_REDIRECTS++;
                        Debug.println("[CSet]Parent pointer redirect");
                    }

                    //for all descendents of neighbour, change parent
                    //if reqd
                    for( State des : p.getNeighbours(neighbour) ){
                        if( openSet.contains(des) ){
                            if(des.cameFrom.gScore > neighbour.gScore){
                                des.cameFrom = neighbour;
                                des.gScore = neighbour.gScore;
                                PARENT_REDIRECTS++;
                            }
                        }								
                    }

                }else{
                    Debug.println("neither contains neighbour");
                    neighbour.cameFrom = current;
                    openSet.add(neighbour);
                }
            }
        }
        return null;
    }

    //No of iter
    //Path length
    //bidirectional => common pt
    //parent ptr => ptr - child reln
    /*private ArrayList<State> path;*/

    protected ArrayList<State> reconstructPath( HashMap<State,State> cameFrom, State currentNode ){
        Debug.println("Inside reconstruct path");
        ArrayList<State> path = new ArrayList<State>();
        while ( currentNode != null ){          
            path.add(currentNode);
            currentNode = currentNode.cameFrom;
        }
        PATH_LENGTH = path.size();
        java.util.Collections.reverse(path);
        return path;
   }

}
