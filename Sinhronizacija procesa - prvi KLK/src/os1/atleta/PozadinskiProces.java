package os1.atleta;

public class PozadinskiProces extends Thread {
	
	private Staza staza;
	
	public PozadinskiProces(Staza staza) {
		
		this.staza = staza;
	}
	
	@Override
	public void run() {
		
		try {
			
			while(!Thread.interrupted()) {
				
				Thread.sleep(5_000);
				
				System.err.println(staza.toString());
			}
			
		} catch(InterruptedException e) {
			
		}
		
		
		
	}

}
