class MCState extends State{

	//<missionaries in left (0-3)> <cannibals in left (0-3)> <boat presence on left (0/1)>
	int a[3];
	//int a[5]; <mL,cL,boat presence on left, mR, cR>

	public MCState(){
		int temp[] = {3, 3, 1, 0, 0};	//initial state
		a = temp;
	}
	//copy a state constructor
	public MCState(MCState s){
		for(int i=0; i<5; i++)
			a[i] = s.a[i];
	}
	//goal state constructor
	public MCState(int goal){
		if(goal==1)
			int temp[] = {0,0,0,3,3}

		a=temp;
	}

	public int[] getGrid(){
			return a;
	}

	public long hashCode(Object ob){
			long temp = 0;
			//Sum (position * value)

			for(int i=0; i<5; i++)
				temp+=hashCode(Integer.toString(a[i]));

			return temp;
		}

	public boolean equals(Object ob){
			EightPuzzleState s = (EightPuzzleState) ob;
			for(int i=0; i<5; i++)
					if(a[i] != s.a[i])
						return false;

			return true;
	}

	public int compareTo(Object ob){
			return (equals(ob)?0:-1);
	}