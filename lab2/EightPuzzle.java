class Point{ int x,y; Point(int x, int y){this.x=x; this.y=y;}}
class EightPuzzle extends Problem{

	public static final int DISPLACED_TILE = 1;
	public static final int MANHATTEN_DIST = 2;
	
	private int heuristic = DISPLACED_TILE;
	
	EightPuzzle(){
		//set the states to a default problem
		start   = new EightPuzzleState();
		current = new EightPuzzleState();
		EightPuzzleState tempSt  = new EightPuzzleState();
		
		tempSt.swap(new Point(2,1), new Point(2,2));
		goal = tempSt;
		
		setHeuristic(DISPLACED_TILE);
	}
	
	public void setHeuristic(int n){
		heuristic = n;
	}
	
	public Point getPos(int n, State st){
		EightPuzzleState s = (EightPuzzleState)st;
	
		for(int i=0; i<3; i++){
			for(int j=0; j<3; j++){
				if( s.a[i][j] == n ){
					return new Point(i,j);
				}
			}
		}
		
		return new Point(0,0);
	}

	public int getHx(State s){
		int total = 0;
		switch(heuristic){
			case DISPLACED_TILE:
				for(int i=1; i<8; i++){
					Point p = getPos(i,s);
					total += (  (getPos(i,goal).x==getPos(i,current).x) 
							&& (getPos(i,goal).y==getPos(i,current).y) )?0:1;
				}			 
			break;
			
			case MANHATTEN_DIST:
				for(int i=1; i<8; i++){
					Point p = getPos(i,s);
					total += (  Math.abs(getPos(i,goal).x - getPos(i,current).x) 
							+  Math.abs(getPos(i,goal).y - getPos(i,current).y) );
				}
			break;
		}
		return total;
	}
	
	public State runMove(State st, int move){
		EightPuzzleState s = (EightPuzzleState)st;
		Point p = getPos(0,s);
		EightPuzzleState tempState = new EightPuzzleState(s);
	
		switch(move){
			case EightPuzzleMovesList.MOVE_LEFT:
				tempState.swap(p,new Point(p.x-1,p.y));
			break;
			
			case EightPuzzleMovesList.MOVE_RIGHT:
				tempState.swap(p,new Point(p.x+1,p.y));
			break;
			
			case EightPuzzleMovesList.MOVE_UP:
				tempState.swap(p,new Point(p.x,p.y-1));
			break;
			
			case EightPuzzleMovesList.MOVE_DOWN:
				tempState.swap(p,new Point(p.x,p.y+1));
			break;
		}

		return tempState;
	}
	
	public boolean isMovePossible(State s, int move){		
		Point p = getPos(0,s);
	
		switch(move){
			case EightPuzzleMovesList.MOVE_LEFT:
				if(p.x == 0)
					return false;
			break;
			
			case EightPuzzleMovesList.MOVE_RIGHT:
				if(p.x == 2)
					return false;
			break;
			
			case EightPuzzleMovesList.MOVE_UP:
				if(p.y == 0)
					return false;
			break;
			
			case EightPuzzleMovesList.MOVE_DOWN:
				if(p.y == 2)
					return false;
			break;
		}
		return true;
	}

}