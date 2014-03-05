class MovesList{

	public final static int DEFAULT_MOVE = -1;
	
	public static int moves[];
	
	MovesList(int n){
		moves = new int[n];
		moves[0] = DEFAULT_MOVE;
	}
	
	static int[] getMovesList(){
		return moves;
	}
	
}
