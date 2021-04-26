/**
 * A class representing a patient.
 * 
 * @author TODO
 */
public class Patient {
	// instance variables
	// TODO ATTENTION: CODE NEEDED HERE
	private String name;
	public int arrival_time;
	private int urgency;
	// declare instance variables
	// -----
	
	// constructor
	public Patient(String name, int arrival_time, int urgency) {
		this.name = name;
		this.arrival_time = arrival_time;
		this.urgency = urgency;
		// TODO ATTENTION: CODE NEEDED HERE
		// initialize instance variables
		// -----
	}
	
	// functions
	/**
	 * @return this patient's arrival time
	 */
	public int arrival_time() {
		// TODO ATTENTION: CODE NEEDED HERE
		// return this patient's arrival time
		return arrival_time;
		// -----
	}

	
	/**
	 * @return this patient's urgency
	 */
	public int urgency() {
		// TODO ATTENTION: CODE NEEDED HERE
		// return this patient's urgency
		return urgency;
		//-----
	}
	
	/**
	 * @param time - current simulation time
	 * @return wait time of this patient
	 */
	public int wait_time(int time){
		int finalTime = time - arrival_time;
		// TODO ATTENTION: CODE NEEDED HERE
		// return this patient's wait time
		return finalTime;
		// -----
	}
}
