import java.util.ArrayList;

class Problem{
	State start, current, goal;
	
	public void setStart(State s){
		start = s;
	}
	
	public void setGoal(State g){
		goal = g;
	}
	
	public void setCurrState(State curr){
		current = curr;
	}
	
	public int getHx(State s){
		return 0;
	}
	
	public int getGx(){
		return 1;
	}
	
	public int getFx(State s){
		return getHx(s) + getGx();
	}
	
	public int getFx(){
		return getHx(current) + getGx();
	}
	
	public State runMove(State s, int move){
		return s;
	}
	
	public boolean isMovePossible(State s, int move){
		return true;
	}
	
	ArrayList<State> getNeighbours(State s){

		int[] moves = new MovesList().getMovesList();
		
		ArrayList<State> newStates = new ArrayList<State>();
	
		for(int i=0; i<moves.length; i++){
			if(isMovePossible(s,moves[i])){
				newStates.add( runMove(s,moves[i]) );
			}
		}
		
		return newStates;
	}
	
	int distBetween(State s1, State s2){
		//the states are not used here doesn't mean they should
		//be dropped. Its because the difference between the states
		//could come from another source like a distance matrix,etc
		return 1;
	}
	
}
