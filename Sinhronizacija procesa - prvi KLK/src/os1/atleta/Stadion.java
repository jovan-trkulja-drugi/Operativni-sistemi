package os1.atleta;

public class Stadion {
	
	public static void main(String[] args) {
		
		Atleta[] atlete = new Atleta[11];
		
		Staza s = new Staza(atlete);
		
		for (int i = 0; i < atlete.length; i++) {
			
			Atleta a = new Atleta(s);
			atlete[i] = a;
		}

		Thread[] nitiA = new Thread[11];
		
		for (int i = 0; i < nitiA.length; i++) {
			
			Thread atleta = new Thread(atlete[i]);
			nitiA[i] = atleta;
			
		}
		
		for (int i = 0; i < nitiA.length; i++) {
			
			nitiA[i].start();
		}
		
		PozadinskiProces pp = new PozadinskiProces(s);
		
		pp.start();
	}

}
