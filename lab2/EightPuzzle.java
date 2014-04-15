import java.util.ArrayList;

class Point{ int x,y; Point(int x, int y){this.x=x; this.y=y;}}
class EightPuzzle extends Problem{

	public static final int NO_HEURISTIC = 0;
	public static final int DISPLACED_TILE = 1;
	public static final int MANHATTEN_DIST = 2;
	public static final int MANHATTEN_PENALTY = 3;	//Over-estimated
	
	private int heuristic = NO_HEURISTIC;
	
	EightPuzzle(){
		//set the states to a default problem
		start   = new EightPuzzleState();
		current = new EightPuzzleState();
		EightPuzzleState tempSt = new EightPuzzleState();
		
		int t[][] = {{1,2,3},{4,5,6},{7,8,0}};
		tempSt.setGrid(t);
		goal = tempSt;
		start.gScore = 0;
		
		setHeuristic(NO_HEURISTIC);
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
		
		throw new RuntimeException();
		//return new Point(0,0);
	}

	public int getHx(State s){
		int total = 0;
		switch(heuristic){
			case NO_HEURISTIC:
			break;

			case DISPLACED_TILE:
				for(int i=1; i<=8; i++){
					Point p = getPos(i,s);
					total += (  (getPos(i,goal).x==getPos(i,s).x) 
							&& (getPos(i,goal).y==getPos(i,s).y) )?0:1;
				}			 
			break;
			
			case MANHATTEN_DIST:
				for(int i=1; i<=8; i++){
					Point p = getPos(i,s);
					total += (  Math.abs(getPos(i,goal).x - getPos(i,s).x) 
							+  Math.abs(getPos(i,goal).y - getPos(i,s).y) );
				}
			break;
		}
		return total;
	}
	
	public State runMove(State st, int move){
		EightPuzzleState s = (EightPuzzleState)st;
		Point p = getPos(0,s);
		Debug.println("0 found at pos: "+p.x + ","+p.y);
		
		EightPuzzleState tempState = new EightPuzzleState(s);
	
		switch(move){
			case EightPuzzleMovesList.MOVE_LEFT:
				Debug.println("Move Left");
				tempState.swap(p,new Point(p.x,p.y-1));
			break;
			
			case EightPuzzleMovesList.MOVE_RIGHT:
				Debug.println("Move Right");
				tempState.swap(p,new Point(p.x,p.y+1));
			break;
			
			case EightPuzzleMovesList.MOVE_UP:
				Debug.println("Move Up");
				tempState.swap(p,new Point(p.x-1,p.y));
			break;
			
			case EightPuzzleMovesList.MOVE_DOWN:
				Debug.println("Move Down");
				tempState.swap(p,new Point(p.x+1,p.y));
			break;
		}
		
		tempState.gScore = s.gScore + 1;	//hardcoded or loaded from matrix
		tempState.fScore = tempState.gScore + getHx(tempState);

		return tempState;
	}
	
	public boolean isMovePossible(State s, int move){		
		Point p = getPos(0,s);
	
		switch(move){
			case EightPuzzleMovesList.MOVE_LEFT:
				if(p.y == 0)
					return false;
			break;
			
			case EightPuzzleMovesList.MOVE_RIGHT:
				if(p.y == 2)
					return false;
			break;
			
			case EightPuzzleMovesList.MOVE_UP:
				if(p.x == 0)
					return false;
			break;
			
			case EightPuzzleMovesList.MOVE_DOWN:
				if(p.x == 2)
					return false;
			break;
		}
		return true;
	}
	
	ArrayList<State> getNeighbours(State st){
		EightPuzzleState s = (EightPuzzleState)st; 
	
		int[] moves = new EightPuzzleMovesList().getMovesList();
		
		ArrayList<State> newStates = new ArrayList<State>();
	
		for(int i=0; i<moves.length; i++){
			if(isMovePossible(s,moves[i])){
				newStates.add( runMove(s,moves[i]) );
			}
		}
		
		return newStates;
	}

}
