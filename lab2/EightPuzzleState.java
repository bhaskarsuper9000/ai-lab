class EightPuzzleState extends State{

	int a[][];
	//EightPuzzleState
	
	
	public EightPuzzleState(){
		int temp[][] = {{0,2,3},{1,5,6},{4,7,8}};
		a = temp;
	}
	
	public EightPuzzleState(EightPuzzleState s){
		a = new int[3][3];
		for(int i=0; i<3; i++)
			for(int j=0; j<3; j++)
				a[i][j] = s.a[i][j];
	}
	
	public int[][] getGrid(){
		return a;
	}
	
	public void setGrid( int g[][] ){
		a = g;
	}

	public long hashCode(Object ob){
		long temp = 0;
		//Sum (position * value)
		for(int i=0; i<3; i++)
			for(int j=0; j<3; j++)
				temp += hashCode((3*i + j) * a[i][j]);
				
		return temp;
	}

	public boolean equals(Object ob){
		EightPuzzleState s = (EightPuzzleState) ob;
		for(int i=0; i<3; i++)
			for(int j=0; j<3; j++)
				if(a[i][j] != s.a[i][j])
					return false;

		return true;
	}
	
	public int compareTo(Object ob){
		System.out.println("compared");
		return (equals(ob)?0:-1);
	}
	
	public void swap(Point p1, Point p2){
		int temp = a[p1.x][p1.y];
		a[p1.x][p1.y] = a[p2.x][p2.y];
		a[p2.x][p2.y] = temp;
	}
}
