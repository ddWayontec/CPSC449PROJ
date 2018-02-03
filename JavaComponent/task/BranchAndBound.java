package task;

public class BranchAndBound {

	public  BranchAndBound() {
		
	}
	
	
	
	
	
	
	
	
	
	
	
	public class Node implements Comparable<Node> {
		
		Node parent;
		int pathCost;
		int cost;
		int machine;
		int task;
		boolean assigned[];
		
		public Node(int machine, int task, boolean assigned[], Node parent) {
			
			for (int i = 0; i < 8; i++)
				this.assigned[i] = assigned[i];
			
			this.machine = machine;
			this.task = task;
			this.parent = parent;
			this.assigned[task] = true;
	
		}
		
		public int compareTo(Node n) {
	     
			if (this.cost < n.cost)
	            return -1;
	        else if(this.cost > n.cost)
	            return 1;
	        else
	            return 0;
		}
		
	}
	
}
