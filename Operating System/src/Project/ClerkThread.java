package Project;

import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList; 
import java.util.List;
import java.util.Vector;

public class ClerkThread implements Runnable{

	private static List<PassengerThread> queue = new ArrayList<PassengerThread>();//create a list for passenger to get in the queue
	private int id;
	private static Object lock = new Object();//create lock for mutual exclusion
	public ArrayList<Integer> seatArray = new ArrayList<Integer>();
	int count = 0;
	
	public ClerkThread(int id) {
		this.id = id;
	}
	
	public void run() {
		/*(while(!Main.clerk_terminate) {
			while(count != Main.numPassengers/2) {
				PassengerThread passenger = callAndRemoveFromQueue();
				if(queue.isEmpty()) {
					Thread.yield();
				}else {
					assistPassenger(passenger);
					count++;
				}
				
			}
		}*/

		PassengerThread passenger = callAndRemoveFromQueue();
		assistPassenger(passenger);
				/*PassengerThread passenger = null;
				synchronized(lock) {
					if(!queue.isEmpty()) {
						passenger = callAndRemoveFromQueue();
					}
				}
				
					if(passenger == null) {
						Thread.yield(); //BW
						//continue;
					}
					assistPassenger(passenger);
					count++;*/

		
		
	}

	public void assistPassenger(PassengerThread passenger) {
		
		
			msg(passenger.getName() + " is in the queue");
			
			int rand = ThreadLocalRandom.current().nextInt(0, Main.randomSet.size());
			passenger.setSeat(Main.randomSet.remove(rand));
			msg("assigning seat" + passenger.getSeat()  + " to " + passenger.getName());
			//passenger.setNeedAssistance(false);
	
	}
	
	/*public void setSeatArrayList() {
		for(int i = 1; i < 31; i++) {
			seatArray.add(i);
		}
	}*/
	
	public synchronized static void addToQueue(PassengerThread passenger) {
		queue.add(passenger);
		
	}
	
	public synchronized static PassengerThread callAndRemoveFromQueue() {
		if(queue.isEmpty()) {
			return null;
		}
		PassengerThread passenger = queue.get(0); //get the first passenger from queue
		queue.remove(0);
		return passenger;
	}
	
	public String getName() { //to get name
		return "Clerk-" + this.id;
	}

	public void msg(String m) {//to print message
		System.out.println("[" + (System.currentTimeMillis() - Main.time) + "]" + getName() + ":" + m);
	}
	
}
