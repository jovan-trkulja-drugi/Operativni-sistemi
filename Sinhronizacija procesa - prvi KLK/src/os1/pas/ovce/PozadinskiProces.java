package os1.pas.ovce;

public class PozadinskiProces extends Thread {
	
	private Imanje imanje;
	
	public PozadinskiProces(Imanje imanje) {
		
		this.imanje = imanje;
	}
	
	@Override
	public void run() {
		setDaemon(true);
		while(!Thread.interrupted()) {
			
			try {
				
				Thread.sleep(250);
				System.err.println(imanje.toString());
				
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			
		}
	}
}
