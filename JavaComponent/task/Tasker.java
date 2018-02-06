package task;

import java.util.Arrays;
import java.util.PriorityQueue;

// Using Branch and Bound algorithm to solve Machine assignment problem
public class Tasker {

	
	public  Tasker(int[][] costMatrix) {
		
		Node node = new Node();
		
		
	}
	
	// Calculates the least promising cost of node
	// after Machine m is assigned to task t
	private int calculateCost(int[][] costMatrix, int size, boolean[] assigned, int m, int t) {
		
		int cost = 0;
		boolean[] available = new boolean[m];
		Arrays.fill(available, Boolean.TRUE);
		
		// For each machine, starting from next machine
		for (int i = m + 1; i < size; i++) {
			int min = size + 1, minIndex = -1;
			
			// For each machine, starting from next machine
			for (int j = 0; j < size; j++) {
				// If task is unassigned
				if (!assigned[j] && available[j] && costMatrix[i][j] < min) {
					// Store task ID and cost
					minIndex = j;
					min = costMatrix[i][j];
				}
			}
			
			// Add cost of next machine
			cost += min;
			
			// Make optimal task unavailable
			available[minIndex] = false;
		}
		
		return cost;
	}
	
	// Finds minimum cost using Branch and Bound algorithm
	private void findMinCost(int[][] costMatrix) {
		
		int size = costMatrix.length;
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		
		// Initialize heap with a dummy node with cost 0
		Node root = newNode(-1, -1, new boolean[size], null);
		
		// Adds a dummy node to list of live nodes;
		pq.add(root);
		
		while (!pq.empty()) {
			// Find a live node with least estimated cost 
			// and delete from list of live nodes
			Node minNode = pq.top();
			
			int m = minNode.machine + 1;
			
			// If all machine are assigned to a task 
			if (m == size) {
				// Print Assignments
				//return minNode.cost
			}
			
			for (int t = 0; t < size; t++)
		      {
		        // If task is unassigned
		        if (!minNode.assigned[t]) {
		          // Create a new tree node
		          Node child = newNode(m, t, minNode.assigned, minNode);
		 
		          // Cost for ancestor nodes plus current node
		          child.pathCost = minNode.pathCost + costMatrix[m][t];
		 
		          // Calculate node least promising cost
		          child.cost = child.pathCost +
		            calculateCost(costMatrix, size, child.assigned, m, t);
		 
		          // Add child to list of live nodes;
		          pq.add(child);
		        }
		      }
			
			
			
		}
	}
	
	// Node class representing space tree
	public class Node implements Comparable<Node> {
		
		// Stores parent node of current node 
		private Node parent;
		// Contains cost for ancestors node
		private int pathCost;
		// Contains least promising cost
		private int cost;
		// Contains worker ID
		private int machine;
		// Contains task ID
		private int task;
		// Contains information about available jobs
		private boolean[] assigned;
		
		// Node creation constructor
		public Node(int machine, int task, boolean[] assigned, Node parent) {
			
			// Copies input assigned array to the node's assigned array
			for (int i = 0; i < 8; i++)
				this.assigned[i] = assigned[i];
			this.assigned[task] = true;
			
			this.machine = machine;
			this.task = task;
			this.parent = parent;
		}
		
		// Comparison method used by pQueue to compare and order nodes in queue
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
