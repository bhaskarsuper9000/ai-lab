import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

class BidirectionalAStar extends AStar implements Runnable{ 

	/******* Concurrency stuff *******/
	private Bag bag;
    private boolean flag = false;
    public  boolean running = true;
    private ArrayList<State> solnStates;	//For storing solution because u cant do it due to run method
    public boolean forcedExit = false;
    
    BidirectionalAStar(Bag b, boolean f){
    	bag = b;
    	flag = f;
    	//Thread t = Thread.start(this);
    }

	public void swapStartGoal(){
		State t = p.start;
		p.start = p.goal;
		p.goal = t;
	}

	public String printSolution(){
		String temp = "";
		for(State s: solnStates){
			temp +=s+"\n" ;
		}
		return temp;
	}
	
	public String printSolutionReverse(){
		java.util.Collections.reverse(solnStates);
		String temp = "";
		for(State s: solnStates){
			temp +=s+"\n" ;
		}
		return temp;
	}

    public void run(){

    	solnStates = aStarSearch(p.start, p.goal);

        running = false;
        Debug.println("Exiting A-Star : "+flag);
    }
    /******* Concurrency stuff *******/
    
    public ArrayList<State> aStarSearch(State start, State goal){
    	bag.check(flag);
    
        p.setStart(start);
        p.setGoal(goal);


        closedSet.clear();
        openSet.add(start);
        cameFrom.clear();

        start.gScore = 0;
        start.fScore = start.gScore + p.getHx(start);

        while(openSet.size() > 0){
            //this.notifyAll();

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
                    Debug.println("openSet contains neighbour");
                    if(neighbour.cameFrom.gScore > current.gScore){
                        neighbour.cameFrom = current;
                        neighbour.gScore = current.gScore;
                        Debug.println("[OSet]Parent pointer redirect");
                    }
                }else if( closedSet.contains(neighbour) ){
                    Debug.println("closedSet contains neighbour");
                    if(neighbour.cameFrom.gScore > current.gScore){
                        neighbour.cameFrom = current;
                        neighbour.gScore = current.gScore;
                        Debug.println("[CSet]Parent pointer redirect");
                    }

                    //for all descendents of neighbour, change parent
                    //if reqd
                    for( State des : p.getNeighbours(neighbour) ){
                        if( openSet.contains(des) ){
                            if(des.cameFrom.gScore > neighbour.gScore){
                                des.cameFrom = neighbour;
                                des.gScore = neighbour.gScore;
                            }
                        }								
                    }

                }else{
                    Debug.println("neither contains neighbour");
                    neighbour.cameFrom = current;
                    openSet.add(neighbour);
                }
            }
            
            try {
                Thread.sleep((int)(Math.random() * 100));
                if(forcedExit)
                	return reconstructPath(cameFrom, current);
            } catch (InterruptedException e) { }
            
        }

        
        return null;
    }

    //No of iter
    //Path length
    //bidirectional => common pt
    //parent ptr => ptr - child reln
    /*private ArrayList<State> path;*/

 
}
