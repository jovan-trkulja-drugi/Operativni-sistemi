package os1.restoran;

import org.svetovid.Svetovid;

public class Konobar extends Thread {
	
	private Kuhinja k;
	
	public Konobar(Kuhinja k) {
		this.k = k;
	}
	
	@Override
	public void run() {
		
		while(!Thread.interrupted()) {
			
			try {
				
				Thread.sleep(5000);
				System.err.println("Pravim sendvic");
				k.napraviSendvic();
				
				Thread.sleep(5000);
				System.err.println("Pravim tofu");
				k.napraviTofu();
				
				Thread.sleep(5000);
				System.err.println("Pravim potaz");
				k.napraviPotazOdBundeve();
				
			} catch(InterruptedException e) {
				Svetovid.in.readInt();
			}
		}
	}

}
