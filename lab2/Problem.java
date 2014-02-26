class Problem{
	State start, goal;
	
	public int getHx(State s){
		return 0;
	}
	
	public int getGx(){
		return 0;
	}
	
	public int getFx(State s){
		return getHx(s) + getGx();
	}
	
	public State runMove(State s, int move){
		return s;
	}
	
	public bool isMovePossible(State s, int move){
		return true;
	}
	
	ArrayList<State> getNeighbours(State s){
		int[] moves = MovesList.getMovesList();
		
		ArrayList<State> newStates = new ArrayList<State>();
	
		for(int i=0; i<moves.length; i++){
			if(isMovePossible(s,moves[i])){
				newStates.add( runMove(s,moves[i]) );
			}
		}
		
		return newStates;
	}
	
}
