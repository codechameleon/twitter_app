package edu.gvsu.cis.twitter.mocking;

/*********************************************
 * Mock Status Object.
 *
 ********************************************/
public interface Status {
	
	/***************************************
	 * Set status.
	 * @param mgs status 
	 ***************************************/
	void setStatus(String mgs);
	
	/***************************************
	 * Get id.
	 * @return long
	 ***************************************/
	long getId();
	
	/***************************************
	 * Set id.
	 * @param id long
	 ***************************************/
	void setId(long id);

}
