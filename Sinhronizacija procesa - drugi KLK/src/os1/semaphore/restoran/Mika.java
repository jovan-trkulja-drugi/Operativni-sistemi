package os1.semaphore.restoran;

public class Mika extends Thread {
	
	private Kuhinja k;
	
	public Mika(Kuhinja k) {
		this.k = k;
	}
	
	@Override public void run() {
		
		while(!Thread.interrupted()) {
			
			try {
				
				sleep(9000);
				
				k.addPovrce();
				
			} catch(InterruptedException e) {
				
			}
		}
	}

}
