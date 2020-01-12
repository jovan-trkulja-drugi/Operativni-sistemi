package os1.semaphore.trambolina;

import java.util.concurrent.Semaphore;;

public class Trambolina {
	
	private Semaphore brojDece;
	private Semaphore maxTezina;
	
	private Semaphore decak;
	private Semaphore devojcica;
	
	private Semaphore tram;
	
	private Integer brDev;
	private Integer brDec;
	
	public Trambolina() {
		brojDece = new Semaphore(5);
		maxTezina = new Semaphore(300);
		
		decak = new Semaphore(1);
		devojcica = new Semaphore(1);
		
		tram = new Semaphore(1);
		
		brDev = 0;
		brDec = 0;
	}
	
	public void popniNekoDete(int tezina) {
		
		brojDece.acquireUninterruptibly(1);
		maxTezina.acquireUninterruptibly(tezina);
		
	}
	
	public void spustiNekoDete(int tezina) {
		
		brojDece.release(1);
		maxTezina.release(tezina);
	}
	
	public void popniDecaka(int tezina) {
		
		decak.acquireUninterruptibly();
		
		try {
			brDec++;
			if(brDec == 1)
				tram.acquireUninterruptibly();
		} finally {
			decak.release();
			brojDece.acquireUninterruptibly();
			maxTezina.acquireUninterruptibly(tezina);
		}
	}
	
	public void spustiDecaka(int tezina) {
		
		decak.acquireUninterruptibly();
		
		try {
			brDec--;
			if(brDec == 0)
				tram.release();
		} finally {
			decak.release();
			brojDece.release();
			maxTezina.release(tezina);
		}
	}
	
	public void popniDevojcicu(int tezina) {
		
		devojcica.acquireUninterruptibly();
		
		try {
			
			brDev++;
			if(brDev == 1)
				tram.acquireUninterruptibly();
			
		} finally {
			
			devojcica.release();
			
			brojDece.acquireUninterruptibly(1);
			maxTezina.acquireUninterruptibly(tezina);
		}
	}
	
	public void spustiDevojcicu(int tezina) {
		
		devojcica.acquireUninterruptibly();
		
		try {
			
			brDev--;
			if(brDev == 0)
				tram.release();
		} finally {
			devojcica.release();
			brojDece.release();
			maxTezina.release(tezina);
		}
	}
	
	
	

}
