package os1.semaphore.restoran;

public class Mica extends Thread {
	
	private Kuhinja k;
	
	public Mica(Kuhinja k) {
		this.k = k;
	}
	
	@Override public void run() {
		
		while(!interrupted()) {
			
			try {
				
				sleep(4000);
				
				k.addPotaz();
			} catch(InterruptedException e) {
				
			}
		}
	}

}
