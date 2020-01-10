package os1.druge.vezbe;

public class Test {
	
	private static NitKojaUzima[] niti = new NitKojaUzima[12];
	
	public static void main(String[] args) {
		
		for(int i = 0; i<12; i++) {
			
			niti[i] = new NitKojaUzima();
		}
		
		NitKojaDeli nkd = new NitKojaDeli();
		nkd.start();
		
		for(int i = 0; i<12; i++) {
			niti[i].start();
		}
	}
	
	public NitKojaUzima[] getNiti() {
		
		return niti;
	}

}
