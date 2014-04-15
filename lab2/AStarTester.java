import java.util.ArrayList;

class AStarTester{
	public static void main(String args[]){
		AStar ast = new AStar();
		
		ast.p = new EightPuzzle();
		
		ArrayList<State> states = ast.aStarSearch();
		//System.out.println(states == null);
		
		ast.showStatus();
		System.out.println("Printing path");
        for(State s: states){
			System.out.println(s);
		}

	}
}
