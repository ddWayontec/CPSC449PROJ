import task.Tasker;

public class Driver {

	public static void main(String[] args) {
		
	    int costMatrix[][] =
	    {
	        {9, 2, 7, 8},
	        {6, 4, 3, 7},
	        {5, 8, 1, 8},
	        {7, 6, 9, 4}
	    };
	
	 
	    /* int costMatrix[][] =
	    {
	        {82, 83, 69, 92},
	        {77, 37, 49, 92},
	        {11, 69,  5, 86},
	        { 8,  9, 98, 23}
	    };
	    */
	 
	    /* int costMatrix[][] =
	    {
	        {2500, 4000, 3500},
	        {4000, 6000, 3500},
	        {2000, 4000, 2500}
	    };*/
	 
	    /*int costMatrix[][] =
	    {
	        {90, 75, 75, 80},
	        {30, 85, 55, 65},
	        {125, 95, 90, 105},
	        {45, 110, 95, 115}
	    };*/
	    
	 Tasker tasker = new Tasker(costMatrix);
	    
	}

}