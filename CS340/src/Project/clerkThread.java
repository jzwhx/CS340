package Project;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;


public class clerkThread implements Runnable{
	public static long time = System.currentTimeMillis();
	private int id;
	public static List<passengerThread> queue = new ArrayList<passengerThread>();//create a list for passengers get into queue
	private static Object lock = new Object(); // create lock for M.E.
	private ArrayList<Integer> seatArray = new ArrayList<Integer>();
	private int seat;
	private int zone;
	
	public clerkThread(int id) {
		this.id = id;
		getRandomSeat(); //when passenger thread is created, get random seat to each passenger.
	}

	
	public void run() {
		
		while(!main.clerkTerminate) {
			passengerThread passenger = null;
			synchronized (lock) { //if one passenger is check in now, other should wait
				if(!queue.isEmpty()) {
					passenger = removeFromQueue();
					msg(passenger.getName() + "is in the queue");
				}
			}
			if(passenger == null) {
				Thread.yield();
				continue;
			}
			assistPassenger(passenger);
			
		}
	}

	public synchronized static void addToQueue(passengerThread passenger) {
		queue.add(passenger);
	}

	
	public synchronized static passengerThread removeFromQueue() {
		if(queue.isEmpty()) {
			return null;
		}
		passengerThread passenger = queue.get(0); //get the first passenger from queue
		queue.remove(0); // remove the first passenger from queue.
		return passenger;
	}
	
	public void assistPassenger(passengerThread passenger) {
		msg("assigning seat" + getSeat() + "to" + passenger.getName());
	}
	
	
	public void setSeatArrayList(int n) {
		for(int i = 0; i < n; i++) {
			seatArray.add(i);
		}
	}
	
	public void setRandomSeat() {
		if(seatArray.size() > 0) {
			Random rand = new Random();
			int index = rand.nextInt(seatArray.size());
			seat = seatArray.remove(index);
			setSeat(seat);
		}
	}
	
	public void getRandomSeat() {
		setSeatArrayList(main.numPassengers);
		setRandomSeat();
	}
	
	public int getSeat() {
		return seat;
	}
	
	public void setSeat(int seat) {
		this.seat = seat;
	}
	
	
	public int getZone() {
		return zone;
	}
	
	public void setZone(int zone) {
		this.zone = zone;
	}
	
	public String getName() {
		return "Clerk-" + this.id;
	}
	public void msg(String m) {//to print message
		System.out.println("[" + (System.currentTimeMillis() - time) + "]" + getName() + ":" + m);
	}
	
	
}
