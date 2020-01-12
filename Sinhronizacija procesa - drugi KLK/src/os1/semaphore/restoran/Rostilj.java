package os1.semaphore.restoran;

public class Rostilj extends Thread {
	
	private Kuhinja k;
	
	public Rostilj(Kuhinja k) {
		this.k = k;
	}
	
	@Override public void run() {
		
		while(!interrupted()) {
			
			try {
				
				sleep(3000);
				
				k.addSir();
			} catch(InterruptedException e) {
				
			}
		}
	}

}
