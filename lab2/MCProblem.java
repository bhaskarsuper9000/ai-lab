class MCProblem extends Problem{

	//heuristic number of people on the left side divided by boat capacity
	private final int PEOPLE_BY_CAPACITY = 1;

	private int heuristic=PEOPLE_BY_CAPACITY;

	MCProblem(){
		start = new MCState();
		current = new MCState();
		goal = new MCState(1);

		setHeuristic(PEOPLE_BY_CAPACITY);
	}

	public void setHeuristic(int n){
			heuristic = n;
	}

	public int getHx(State s){
			int total = 0;
			switch(heuristic){
			case PEOPLE_BY_CAPACITY:
				int vect = current.getGrid();
				total = (vect.a[0] + vect.a[1])/2;
				break;
			}
			return total;
		}

	public State runMove(State st, int move){
		MCState tempState = new MCState(current);
		switch(move){
			case MCMovesList.SEND_11:
				tempState.afterMove(MCMovesList.SEND_11);
			break;
			case MCMovesList.SEND_20:
				tempState.afterMove(MCMovesList.SEND_20);
			break;
			case MCMovesList.SEND_02:
				tempState.afterMove(MCMovesList.SEND_02);
			break;
			case MCMovesList.SEND_10:
				tempState.afterMove(MCMovesList.SEND_10);
			break;
			case MCMovesList.SEND_01:
				tempState.afterMove(MCMovesList.SEND_01);
			break;
		}
		return tempState;
	}

	public boolean isMovePossible(State s, int move){

			switch(move){
				case MCMovesList.SEND_11:
								tempState.afterMove(MCMovesList.SEND_11);
							break;
							case MCMovesList.SEND_20:
								tempState.afterMove(MCMovesList.SEND_20);
							break;
							case MCMovesList.SEND_02:
								tempState.afterMove(MCMovesList.SEND_02);
							break;
							case MCMovesList.SEND_10:
								tempState.afterMove(MCMovesList.SEND_10);
							break;
							case MCMovesList.SEND_01:
								tempState.afterMove(MCMovesList.SEND_01);
			break;
			}
			return true;
	}















