import java.util.ArrayList;

class BiAStarTester{
	public static void main(String args[]){
		Bag b = new Bag();
		BidirectionalAStar bast1 = new BidirectionalAStar(b,true);
		BidirectionalAStar bast2 = new BidirectionalAStar(b,false);
		
		bast1.p = new EightPuzzle();
		bast2.p = new EightPuzzle();
		
		bast2.swapStartGoal();
		
		//ArrayList<State> states = ast.aStarSearch();
		
		
		Thread t1 = new Thread(bast1);
		Thread t2 = new Thread(bast2);
		
		t1.start();
		t2.start();
		
		while( bast1.running | bast2.running ){
			if( bast1.current == bast2.current)
				break;
			System.out.print("");
		}
		
		try{
			t1.join();
			t2.join();
        }catch(InterruptedException e){}	
		
		//System.out.println(states == null);
		
		
		/*
		System.out.println("Printing path");
        for(State s: states){
			System.out.println(s);
		}
		*/

	}
}
