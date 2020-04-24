package Project;

import java.util.TreeMap;

public class PassengerThread implements Runnable{

	private int id;
	private int seat;
	private int zone;
	private boolean needAssistance;
	public static TreeMap<Integer, PassengerThread> seats = new TreeMap<>();
	public int count = 0;
	
	public PassengerThread(int id) {
		this.id = id;
	}
	
	public void run() {

		arrive();
		if(this.seat == 0) {
			goToClerk();
		}
		
		//getPass();
		
	}

	public void arrive() {
		msg("Arrving airport and heading for the check-in.");
	}
	
	public void goToClerk() {
		needAssistance = true;
		ClerkThread.addToQueue(this); //add this passenger to queue
		//while(needAssistance) {
			//Thread.yield(); //BW
		//}
		
	}
	
	//public void getPass() {
		//ClerkThread
	//}
	
	public void getRandomSeat() {
		PassengerThread current = new PassengerThread(this.id);
		while(Main.comingList.peek() != null) {
			current = Main.comingList.peek();
			int i = 1 + (int)(Math.random()*30);
			while(seats.containsKey(i)) {
				i = 1 + (int)(Math.random()*30);
			}			
			seats.put(i, current);
			current.setSeat(i);
			current.setZone(i%10 + 1);
			Main.comingList.remove();
		}
	}
	
	public void setNeedAssistance(boolean value) {
		this.needAssistance = value;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getName() {//to get name
		return "Passenger-" + this.id;
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
	public void msg(String m) {//to print message
		System.out.println("[" + (System.currentTimeMillis() - Main.time) + "]" + getName() + ":" + m);
	}
}
