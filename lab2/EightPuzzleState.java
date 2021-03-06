class EightPuzzleState extends State{

	int a[][];
	//EightPuzzleState


	public EightPuzzleState(){
		int temp[][] = {{5,1,8},{0,2,3},{4,7,6}}; //initial state
		a = temp;
	}
	//copy a state constructor
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
		Debug.print("Equals?"+ this+ "=="+s);

        for(int i=0; i<3; i++)
			for(int j=0; j<3; j++)
				if(a[i][j] != s.a[i][j]){
					Debug.println(": false");
                    return false;
                }

        Debug.println(": true..");
		return true;
	}

	public int compareTo(Object ob){
		Debug.println("compared");
		if( ((EightPuzzleState)ob).fScore > fScore )
			return -1;
		else if ( ((EightPuzzleState)ob).fScore < fScore )
			return 1;
		return 0;
	}

	public void swap(Point p1, Point p2){
		int temp = a[p1.x][p1.y];
		a[p1.x][p1.y] = a[p2.x][p2.y];
		a[p2.x][p2.y] = temp;
	}

	public String toString(){
		String temp = "{";
		for(int i=0; i<3; i++){
			temp += "{";
			for(int j=0; j<3; j++)
				temp += a[i][j] + ",";
			temp += "\b},";
		}
		temp +="\b}";

		return temp;

	}
}
