class MovesList{

	public final static int DEFAULT_MOVE = -1;
	
	public int moves[];
	
	MovesList(){
		moves = new int[1];
		moves[0] = DEFAULT_MOVE;
	}
	
	MovesList(int n){
		moves = new int[n];
		moves[0] = DEFAULT_MOVE;
	}
	
	int[] getMovesList(){
		return moves;
	}
	
}
