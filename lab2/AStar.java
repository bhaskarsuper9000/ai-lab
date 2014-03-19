import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

class AStar{
	private HashSet<State> closedSet;
	private PriorityQueue<State> openSet;
	private HashMap<State, State> cameFrom;
	
	Problem p;
	State current;
	
	AStar(){
		p = new Problem();
		closedSet = new HashSet<State> ();
		openSet   = new PriorityQueue<State> ();
		cameFrom  = new HashMap<State, State> ();
	}
	
	public ArrayList<State> aStarSearch(){
        System.out.println("start = " + p.start + "goal = "+ p. goal);
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
				
				/*
				if( openSet.contains(neighbour) || tempScore <= neighbour.gScore ){
					neighbour.cameFrom = current;
					neighbour.gScore = tempScore;
					neighbour.fScore = neighbour.gScore + p.getHx(neighbour);
					if( !openSet.contains(neighbour) ){
						openSet.add(neighbour);
					}
				}
				*/
				
				if( openSet.contains(neighbour) ){
					System.out.println("openSet contains neighbour");
					if(neighbour.cameFrom.gScore > current.gScore){
						neighbour.cameFrom = current;
						neighbour.gScore = current.gScore;
					}
				}else if( closedSet.contains(neighbour) ){
					System.out.println("closedSet contains neighbour");
					if(neighbour.cameFrom.gScore > current.gScore){
						neighbour.cameFrom = current;
						neighbour.gScore = current.gScore;
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
		}
		return null;
	}
	
	//No of iter
	//Path length
	//bidirectional => common pt
	//parent ptr => ptr - child reln
	/*private ArrayList<State> path;*/

	private ArrayList<State> reconstructPath( HashMap<State,State> cameFrom, State currentNode ){
		System.out.println("Inside reconstruct path");
	
		if( cameFrom.containsKey(currentNode) ){
			System.out.println("Inside if :cameFrom contains currNode");
            ArrayList<State> path = reconstructPath(cameFrom, cameFrom.get(currentNode));
			path.add(currentNode);
			return path;
		}else{
			System.out.println("Inside else :cameFrom doesn't contain currNode");
            ArrayList<State> path = new ArrayList<State>();
			path.add(currentNode);		
	        System.out.println("path = "+path);	
            return path;
		}
	}
	
	/*
	
	function reconstruct_path(came_from, current_node)
    if current_node in came_from
        p := reconstruct_path(came_from, came_from[current_node])
        return (p + current_node)
    else
        return current_node
	
	function A*(start,goal)
		closedset := the empty set    // The set of nodes already evaluated.
		openset := {start}    // The set of tentative nodes to be evaluated, initially containing the start node
		came_from := the empty map    // The map of navigated nodes.
	 
		g_score[start] := 0    // Cost from start along best known path.
		// Estimated total cost from start to goal through y.
		f_score[start] := g_score[start] + heuristic_cost_estimate(start, goal)
	 
		while openset is not empty
		    current := the node in openset having the lowest f_score[] value
		    if current = goal
		        return reconstruct_path(came_from, goal)
	 
		    remove current from openset
		    add current to closedset
		    for each neighbor in neighbor_nodes(current)
		        if neighbor in closedset
		            continue
		        tentative_g_score := g_score[current] + dist_between(current,neighbor)
	 
		        if neighbor not in openset or tentative_g_score < g_score[neighbor] 
		            came_from[neighbor] := current
		            g_score[neighbor] := tentative_g_score
		            f_score[neighbor] := g_score[neighbor] + heuristic_cost_estimate(neighbor, goal)
		            if neighbor not in openset
		                add neighbor to openset
	 
		return failure
	
	*/
}
