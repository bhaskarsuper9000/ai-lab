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

			if(current == goal)
				return reconstructPath(cameFrom, goal);
				
			closedSet.add(current);
			//State tempStates[] = neighbour(current);
			for( State neighbour : p.getNeighbours(current) ){
				System.out.println("Neighbour expanded");
				if(closedSet.contains(neighbour)){
					System.out.println("Closed Set contains neighbour");
					continue;
				}

				int tempScore = current.gScore + p.distBetween(current, neighbour);
				System.out.println("Dist = "+tempScore);
				
				if( openSet.contains(neighbour) || tempScore < neighbour.gScore ){
					neighbour.cameFrom = current;
					neighbour.gScore = tempScore;
					neighbour.fScore = neighbour.gScore + p.getHx(neighbour);
					if( !openSet.contains(neighbour) ){
						openSet.add(neighbour);
					}
				}
			}
		}
		return null;
	}
	
	//No of iter
	//Path length
	//bidirectional => common pt
	//parent ptr => ptr - child reln
	
	private ArrayList<State> reconstructPath( HashMap<State,State> cameFrom, State currentNode ){
		System.out.println("Inside reconstruct path");
	
		if( cameFrom.containsKey(currentNode) ){
			ArrayList<State> p = reconstructPath(cameFrom, cameFrom.get(currentNode));
			p.add(currentNode);
			return p;
		}else{
			ArrayList<State> p = new ArrayList<State>();
			p.add(currentNode);		
			return p;
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
