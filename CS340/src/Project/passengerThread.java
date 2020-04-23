package Project;

import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;
import java.util.Random;

public class passengerThread implements Runnable{

	public static long time = System.currentTimeMillis();

	private int counter = 2;
	private int counterNum;
	private int id;

	//private int numPassengers = 30;
	
	public passengerThread(int id) {
		this.id = id;
	}
	
	public void run() {
		arrive();
			
	}
	
	public void arrive() {
		msg("Arrving airport and heading for the check-in.");
	}
	
	private void goToClerk()
	

	
	/*public int randomSeat(main main) {
		ArrayList<Integer> list = new ArrayList<Integer>(main.numPassengers);
		for(int i = 0; i < main.numPassengers; i++) {
			list.add(i);
		}
		Random rand = new Random();
		while(list.size() > 0) {
			int index = rand.nextInt(list.size());
			for(int i = 0; i< main.numPassengers; i++) {
				seatArray.add(list.remove(index));
			}
		}
		return seat;
	}*/
	

	
	public String getName() {
		return "Passenger-" + this.id;
	}

	
	public void msg(String m) {//to print message
		System.out.println("[" + (System.currentTimeMillis() - time) + "]" + getName() + ":" + m);
	}
	
}
