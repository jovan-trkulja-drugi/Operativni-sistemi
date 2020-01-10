package os1.atleta;

public class Atleta extends Thread {
	
	private Staza staza;
	private String akcija;
	private double id;
	
	public Atleta(Staza staza) {
		
		this.id = Math.random() * 500;
		this.staza = staza;
	}
	
	
	@Override
	public void run() {
		
		while(!interrupted()) {
			
			try {

				staza.popniNekog(this);
				
				trci();
				
				staza.spustiNekog(this);
				
				odmoriSe();
				
			} catch(InterruptedException e) {
				
			}
		}
		
	}
	
	private void trci() throws InterruptedException {
		Thread.sleep(5_000);
	}
	
	private void odmoriSe() throws InterruptedException {
		this.setAkcija("ODMARA");
		Thread.sleep(5_000);
//		this.setAkcija("CEKA");
	}

	@Override
	public String toString() {
		
		return this.getName();
		
	}
	
	public void setAkcija(String akcija) {
		this.akcija = akcija;
	}
	
	public String getAkcija() {
		return akcija;
	}
	
	public double getIdAtlete() {
		return this.id;
	}

}
