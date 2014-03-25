class MCState extends State{

	//<missionaries in left (0-3)> <cannibals in left (0-3)> <boat presence on left (0/1)>
	int a[3];

	public MCState(){
		int temp[] = {3, 3, 1};
		a = temp;
	}

	public MCState(MCState s){
		for(int i=0; i<3; i++)
			a[i] = s.a[i];
	}

	public MCState(int goal){
		if(goal==1)
			int temp[] = {0,0,0}

		a=temp;
	}

	public int[] getGrid(){
			return a;
	}

	public long hashCode(Object ob){
			long temp = 0;
			//Sum (position * value)

			for(int i=0; i<3; i++)
				temp+=hashCode(Integer.toString(a[i]));

			return temp;
		}

	public boolean equals(Object ob){
			EightPuzzleState s = (EightPuzzleState) ob;
			for(int i=0; i<3; i++)
					if(a[i] != s.a[i])
						return false;

			return true;
	}

	public int compareTo(Object ob){
			return (equals(ob)?0:-1);
	}