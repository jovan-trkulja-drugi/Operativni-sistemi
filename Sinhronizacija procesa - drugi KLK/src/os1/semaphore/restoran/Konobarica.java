package os1.semaphore.restoran;

import org.svetovid.Svetovid;

public class Konobarica extends Thread {
	
	private Kuhinja k;
	
	public Konobarica(Kuhinja k) {
		this.k = k;
	}
	
	@Override public void run() {
		
		while(!interrupted()) {
			
			try {
				
				Thread.sleep(5000);
				System.err.println("Pravim sendvic");
				k.napraviSendvic();
				
				Thread.sleep(5000);
				System.err.println("Pravim tofu");
				k.napraviTofu();
				
				Thread.sleep(5000);
				System.err.println("Pravim potaz");
				k.napraviPotazBundeva();
				
			} catch(InterruptedException e) {
				Svetovid.in.readInt();
			}
		}
	}

}
