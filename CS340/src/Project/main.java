package Project;

public class main {

	public static long time = System.currentTimeMillis();
	public static int numPassengers = 31;
	public static Thread[] passenger_Thread = new Thread[numPassengers];
	public static int numClerk = 2;
	public static boolean clerkTerminate = false;
	public static Thread[] clerk_Thread = new Thread[numClerk];
	
	public static void main(String[] args) {
		//main main = new main();
		
		for(int i = 1; i < numPassengers; i++) {
			passenger_Thread[i] = new Thread(new passengerThread(i));
			passenger_Thread[i].start();
		}
		
		/*for(int i = 0; i < numClerk; i++) {
			clerk_Thread[i] = new Thread(new clerkThread(i));
			clerk_Thread[i].start();
		}*/
		
	}
	
	public String getName() {
		return "MainThread";
	}
	
	public void msg(String m) {//print method
		System.out.println("[" + (System.currentTimeMillis() - time) + ")" + getName() + ": " + m);

	}

}
