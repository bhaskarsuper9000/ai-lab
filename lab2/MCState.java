class MCState extends State{

	//<missionaries in left (0-3)> <cannibals in left (0-3)> <boat presence on left (0/1)>
	int[] a = new int[3];

	//int a[5]; <mL,cL,boat presence on left, mR, cR>

	public MCState(){
		int temp[] = {3, 3, 1};	//initial state
		a = temp;
	}
	//copy a state constructor
	public MCState(MCState s){
		for(int i=0; i<3; i++)
			a[i] = s.a[i];
	}
	//goal state constructor
	public MCState(int goal){
		int[] temp = new int[3];
		if(goal==1)
		{
			int[] t = {0,0,0};
			temp = t;
		}
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
			MCState s = (MCState) ob;
			for(int i=0; i<3; i++)
					if(a[i] != s.a[i])
						return false;

			return true;
	}

	public int compareTo(Object ob){
		Debug.println("compared");
		if( ((MCState)ob).fScore > fScore )
			return -1;
		else if ( ((MCState)ob).fScore < fScore )
			return 1;
		return 0;
	}
	
	public String toString(){
		String temp = "{";
		for(int i=0; i<3; i++){
				temp += a[i]+"," ;
		}
		temp = temp.substring(0, temp.length()-1);
		temp +="}";
		
		return temp;
	}

}
