package os1.semaphore.restoran;

public class Joki extends Thread {
	
	private Kuhinja k;
	
	public Joki(Kuhinja k) {
		this.k = k;
	}
	
	@Override public void run() {
		
		while(!interrupted()) {
			
			try {
				
				sleep(3000);
				
				k.addHleb();
			} catch(InterruptedException e) {
				
			}
		}
	}

}
