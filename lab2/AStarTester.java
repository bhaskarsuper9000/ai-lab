class AStarTester{
	public static void main(String args[]){
		AStar ast = new AStar();
		
		ast.p = new EightPuzzle();
		
		ast.aStarSearch();
	}
}
