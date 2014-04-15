class MCMovesList extends MovesList{
	//possible moves
	//<1 cnbl, 1 msnry>, <2 cnbl,0>, <0,2>, <1,0>, <0,1>

	public final static int SEND_11 = 0;
	public final static int SEND_20 = 1;
	public final static int SEND_02 = 2;
	public final static int SEND_10 = 3;
	public final static int SEND_01 = 4;

	MCMovesList(){
		super(5);
		moves[0] = SEND_11;
		moves[1] = SEND_20;
		moves[2] = SEND_02;
		moves[3] = SEND_10;
		moves[3] = SEND_01;
	}
	
	int[] getMovesList(){
		return moves;
	}
}