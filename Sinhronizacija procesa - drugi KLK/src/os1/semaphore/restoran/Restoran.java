package os1.semaphore.restoran;

public class Restoran {
	
	public static void main(String[] args) {
		
		Kuhinja k = new Kuhinja();
		
		Mika m = new Mika(k);
		Joki j = new Joki(k);
		Rostilj vule = new Rostilj(k);
		Rostilj gule = new Rostilj(k);
		
		Konobarica dara = new Konobarica(k);
		Konobarica rada = new Konobarica(k);
		
		m.start();
		j.start();
		vule.start();
		gule.start();
		
		dara.start();
		rada.start();
	}

}
