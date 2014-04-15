import java.util.ArrayList;

class MCProblem extends Problem{

	//heuristic number of people on the left side divided by boat capacity
	private final int PEOPLE_BY_CAPACITY = 1;

	private int heuristic=PEOPLE_BY_CAPACITY;
	
	MCProblem(){
	
		start = new MCState();
		
		current = new MCState();
		Debug.println("current");
		goal = new MCState(1);
		Debug.println("REached problem.");

		setHeuristic(PEOPLE_BY_CAPACITY);
	}

	public void setHeuristic(int n){
			heuristic = n;
	}

	public int getHx(State st){
		MCState s = (MCState)st;
			int total = 0;
			switch(heuristic){
			case PEOPLE_BY_CAPACITY:
				MCState vect = s;
				total = (vect.a[0] + vect.a[1])/2;
				break;
			}
			return total;
		}

	public State runMove(State st, int move){
		MCState s = (MCState)st;
		MCState tempState = new MCState(s);
		Debug.println("Running moves from <M="+s.a[0]+", C="+s.a[1]+", Boat="+s.a[2]);
		if(s.a[2]==1)
		{
			switch(move){
			case MCMovesList.SEND_11:
				Debug.println("Sending 1 M and 1 C"); 
				tempState.a[0]-= 1; 
				tempState.a[1]-= 1;
			break;
			case MCMovesList.SEND_20:
				Debug.println("Sending 2 M and 0 C"); 
				tempState.a[0]-= 2;
			break;
			case MCMovesList.SEND_02:
				Debug.println("Sending 0 M and 2 C"); 
				tempState.a[1]-= 2;
			break;
			case MCMovesList.SEND_10:
				Debug.println("Sending 1 M and 0 C"); 
				tempState.a[0]-= 1;
			break;
			case MCMovesList.SEND_01:
				Debug.println("Sending 0 M and 1 C"); 
				tempState.a[1]-= 1;
			break;
			}
		}
		else
		{
			switch(move){
			case MCMovesList.SEND_10:
				tempState.a[0] += 1;
			break;
			case MCMovesList.SEND_01:
				tempState.a[1] += 1;
			break;
			}
		}
		return tempState;
	}

	public boolean isMovePossible(State st, int move){
		MCState s = (MCState)st;
		if(s.a[2]==1)
			{
			int mLeftAfterMove=-1;
			int cLeftAfterMove=-1;
				switch(move){
				case MCMovesList.SEND_11:
					mLeftAfterMove = s.a[0]-1;
					cLeftAfterMove = s.a[1]-1;
				break;
				case MCMovesList.SEND_20:
					mLeftAfterMove = s.a[0]-2;
					cLeftAfterMove = s.a[1]-0;
				break;
				case MCMovesList.SEND_02:
					mLeftAfterMove = s.a[0]-0;
					cLeftAfterMove = s.a[1]-2;
				break;
				case MCMovesList.SEND_10:
					mLeftAfterMove = s.a[0]-1;
					cLeftAfterMove = s.a[1]-0;
				break;
				case MCMovesList.SEND_01:
					mLeftAfterMove = s.a[0]-0;
					cLeftAfterMove = s.a[1]-1;
				break;
				}
				if(mLeftAfterMove>=0 && cLeftAfterMove>=0 && mLeftAfterMove>=cLeftAfterMove)
					return true;
				else return false;
			}
			else
			{
				int mLeftAfterMove=-1;
				int cLeftAfterMove=-1;
					switch(move){
					case MCMovesList.SEND_11:
						return false;
					case MCMovesList.SEND_20:
						return false;
					case MCMovesList.SEND_02:
						return false;
					case MCMovesList.SEND_10:
						mLeftAfterMove = 3-s.a[0]-1;
					break;
					case MCMovesList.SEND_01:
						cLeftAfterMove = 3-s.a[1]-1;
					break;
					}
					if(mLeftAfterMove>=0 && cLeftAfterMove>=0 && mLeftAfterMove>=cLeftAfterMove)
						return true;
					else return false;
			}
	}
	
	ArrayList<State> getNeighbours(State st){
		MCState s = (MCState)st; 
	
		int[] moves = new MCMovesList().getMovesList();
		
		ArrayList<State> newStates = new ArrayList<State>();
	
		for(int i=0; i<moves.length; i++){
			if(isMovePossible(s,moves[i])){
				newStates.add( runMove(s,moves[i]) );
			}
		}
		
		return newStates;
	}
}















