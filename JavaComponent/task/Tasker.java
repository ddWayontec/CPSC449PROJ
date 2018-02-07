package task;

import java.util.Arrays;
import java.util.PriorityQueue;

// Using Branch and Bound algorithm to solve Machine assignment problem
public class Tasker {

	// Calculates the least promising cost of node
	// after Machine m is assigned to task t
	private static int calculatePromisingCost(int[][] costMatrix, Node child) {
		
		int promisingCost = 0;
		
		// Creates an array of available assignments set to TRUE
		boolean[] availableNodesArray = new boolean[costMatrix.length];
		Arrays.fill(availableNodesArray, Boolean.TRUE);
		
		// For each machine find the task that has the minimal penalty cost
		for (int i = child.mach + 1; i < costMatrix.length; i++) {
			int minTaskCost = Integer.MAX_VALUE, minTaskIndex = -1;
			
			// For each task
			for (int j = 0; j < costMatrix.length; j++) {
				// If task is unassigned
				if (!child.assignedNodesArray[j] && availableNodesArray[j] && costMatrix[i][j] < minTaskCost) {
					// Store task ID and cost
					minTaskIndex = j;
					minTaskCost = costMatrix[i][j];
				}
			}
			
			// Add cost of next machine
			promisingCost += minTaskCost;
			
			// Make optimal task unavailable
			if (minTaskIndex != -1)
				availableNodesArray[minTaskIndex] = false;
		}
		
		return promisingCost;
	}
	
	// Finds minimum cost using Branch and Bound algorithm
	public static int optimize(int[][] costMatrix) {
		
		PriorityQueue<Node> activeNodesArray = new PriorityQueue<Node>();
		
		// Initialize heap with a dummy node with dummy values
		Node root = new Node(costMatrix);
		
		// Adds a dummy node to list of live nodes;
		activeNodesArray.add(root);
		
		while (!activeNodesArray.isEmpty()) {
			// Find a live node with least estimated cost 
			// and delete from list of live nodes
			Node activeNode = activeNodesArray.poll();
			
			int currMach = activeNode.mach + 1;
			
			// If all machine are assigned to a task 
			if (currMach == costMatrix.length)
				return activeNode.promisingCost;
			
			for (int currTask = 0; currTask < costMatrix.length; currTask++)
		      {
		        // If task is unassigned
		        if (!activeNode.assignedNodesArray[currTask]) {
		          // Create a new tree node
		          Node child = new Node(currMach, currTask, activeNode.assignedNodesArray, activeNode);
		 
		          // Cost for ancestor nodes plus current node
		          child.pathCost = activeNode.pathCost + costMatrix[currMach][currTask];
		 
		          // Calculate node least promising cost
		          child.promisingCost = child.pathCost +
		            calculatePromisingCost(costMatrix, child);
		 
		          // Add child to list of live nodes;
		          activeNodesArray.add(child);
		          
		        }
		  
		      }
		}
		return -1;
	}
	
	// Node class representing space tree
	private static class Node implements Comparable<Node> {
		
		// Stores parent node of current node 
		private Node parent;
		// Contains cost for ancestors node
		private int pathCost;
		// Contains least promising cost
		private int promisingCost;
		// Contains worker ID
		private int mach;
		// Contains task ID
		private int task;
		// Contains information about available jobs
		private boolean[] assignedNodesArray;
		
		// Dummy node constructor
		public Node(int[][] costMatrix) {
			
			this.assignedNodesArray = new boolean[costMatrix.length];
			this.mach = -1;
			this.task = -1;
			this.parent = null;
		}
		
		// Node creation constructor
		public Node(int machine, int task, boolean[] assigned, Node parent) {
			
			this.assignedNodesArray = new boolean[assigned.length];
			// Copies input assigned array to the node's assigned array
			for (int i = 0; i < assigned.length; i++)
				this.assignedNodesArray[i] = assigned[i];
			
			this.assignedNodesArray[task] = true;
			this.mach = machine;
			this.task = task;
			this.parent = parent;
		}
		
		// Comparison method used by pQueue to compare and order nodes in queue
		public int compareTo(Node n) {	
			if (this.promisingCost < n.promisingCost)
	            return -1;
	        else if(this.promisingCost > n.promisingCost)
	            return 1;
	        else
	            return 0;
		}
		
	}
	
}
