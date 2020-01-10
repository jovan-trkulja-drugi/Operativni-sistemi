package os1.leksin.zadatak;

public class Front extends Thread {

	private Semafor semafor;
	private Put p;

	public Front(Put p) {
		this.semafor = Semafor.NONE;
		this.p = p;
	}

	@Override
	public synchronized void run() {

		try {

			while (!Thread.interrupted()) {
				
				while (getSvetlo() == Semafor.CRVENO) {

					wait();

				}
				
				stanje();
				Thread.sleep(3000);

				zabranjenProlaz();
				p.getBulevar().slobodanProlaz();
				p.getBulevar().notify();
				
			}
		} catch (InterruptedException e) {
			interrupt();
		}

	}
	
	public void stanje() {
		System.out.println("Stanje na bulevaru je: " + ispis());
	}
	
	private String ispis() {
		
		if(getSvetlo() == Semafor.CRVENO)
			return "crveno";
		else if(getSvetlo() == Semafor.ZELENO)
			return "zeleno";
		else
			return "none";
	}

	public void slobodanProlaz() {
		this.semafor = Semafor.ZELENO;
	}

	public void zabranjenProlaz() {
		this.semafor = Semafor.CRVENO;
	}

	public Semafor getSvetlo() {
		return semafor;
	}

}
