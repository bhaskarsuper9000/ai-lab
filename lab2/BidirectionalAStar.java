import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

class BidirectionalAStar extends Thread{ 

    public void run(){
        Thread t1 = new Thread(this);
        Thread t2 = new Thread(this);

        t1.start();
        t2.start();

        while(t1.p.getCurrent() == t2.p.getCurrent()){
                t1.
        }

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
            notify();

            current = openSet.poll();
            p.setCurrState(current);
            System.out.println("Next move = "+current+" f(x)="+current.fScore);

            System.out.println("Checking for goal...");
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
                System.out.println("neighbour: "+neighbour+ "f(x)="+neighbour.fScore);
                if(closedSet.contains(neighbour)){
                    System.out.println("Closed Set contains neighbour");
                    continue;
                }

                int tempScore = current.gScore + p.distBetween(current, neighbour);
                System.out.println("Dist = "+tempScore);


                if( openSet.contains(neighbour) ){
                    System.out.println("openSet contains neighbour");
                    if(neighbour.cameFrom.gScore > current.gScore){
                        neighbour.cameFrom = current;
                        neighbour.gScore = current.gScore;
                        System.out.println("[OSet]Parent pointer redirect");
                    }
                }else if( closedSet.contains(neighbour) ){
                    System.out.println("closedSet contains neighbour");
                    if(neighbour.cameFrom.gScore > current.gScore){
                        neighbour.cameFrom = current;
                        neighbour.gScore = current.gScore;
                        System.out.println("[CSet]Parent pointer redirect");
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
                    System.out.println("neither contains neighbour");
                    neighbour.cameFrom = current;
                    openSet.add(neighbour);
                }
            }
            
            wait();
        }

        
        return null;
    }

    //No of iter
    //Path length
    //bidirectional => common pt
    //parent ptr => ptr - child reln
    /*private ArrayList<State> path;*/

 
}
