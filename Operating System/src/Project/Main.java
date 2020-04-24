package Project;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;


public class Main {

	public static long time = System.currentTimeMillis();
	public static int numPassengers = 31;
	public static int numClerk = 2;
	public static int counterNum = 3;
	public static Thread[] passenger_Thread = new Thread[numPassengers];
	public static Thread[] clerk_Thread = new Thread[numClerk];
	public static boolean passenger_terminate = false;
	public static boolean clerk_terminate = false;
	public static Queue<PassengerThread> comingList = new LinkedList<PassengerThread>();
	public static Vector<Integer> randomSet = new Vector<Integer>();
	
	public static void main(String[] args) {
		for(int i = 1; i < numPassengers; i++) {
			randomSet.add(i);
		}
		
		for(int i = 1; i < numPassengers; i++) {
			passenger_Thread[i] = new Thread(new PassengerThread(i));
			passenger_Thread[i].start();
		}
		
		for(int i = 0; i< numClerk; i++) {
			clerk_Thread[i] = new Thread(new ClerkThread(i));
			clerk_Thread[i].start();
		}
			
	}

}
