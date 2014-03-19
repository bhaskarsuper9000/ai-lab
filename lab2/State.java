class State implements Comparable{

	int gScore;
	int fScore;
	State cameFrom;
	
	public int compareTo(Object o){
		return -1;
	}
	
	public String toString(){
		return "generic toString() in state: override as per problem";
	}
}
