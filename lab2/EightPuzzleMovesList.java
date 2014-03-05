class EightPuzzleMovesList extends MovesList{
	
	public final static int MOVE_LEFT = 0;
	public final static int MOVE_RIGHT = 1;
	public final static int MOVE_UP = 2;
	public final static int MOVE_DOWN = 3;
	
	EightPuzzleMovesList(){
		super(4);
		moves[0] = MOVE_LEFT; 
		moves[1] = MOVE_RIGHT;
		moves[2] = MOVE_UP;
		moves[3] = MOVE_DOWN;
	}

	
}
