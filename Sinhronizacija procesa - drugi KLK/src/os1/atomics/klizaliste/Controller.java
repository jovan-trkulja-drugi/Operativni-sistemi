package os1.atomics.klizaliste;

import java.util.concurrent.atomic.AtomicInteger;

public class Controller {
	
	private AtomicInteger devojkeObuva = new AtomicInteger(0);
	private AtomicInteger devojkeIzuva = new AtomicInteger(0);
	
	private AtomicInteger tezina;
	
	private AtomicInteger momakObuva = new AtomicInteger(0);
	private AtomicInteger momakIzuva = new AtomicInteger(0);
	
	public Controller(Integer MAX_TEZINA) {
		tezina = new AtomicInteger(MAX_TEZINA);
	}
	
	public void obuvaDevojka(Integer t) {
		
		devojkeObuva.incrementAndGet();
		
		boolean ok;
		
		do {
			
			int staraVr = momakObuva.get();
			int novaVr = staraVr - 1;
			
			ok = novaVr >= 0;
			
			if(ok) {
				ok = momakObuva.compareAndSet(staraVr, novaVr);
			} else 
				Thread.yield();
			
		} while(!ok);
		
		boolean okO;
		
		do {
			
			int staraVr = tezina.get();
			int novaVr = staraVr - t;
			
			okO = novaVr >= 0;
			
			if(okO) {
				okO = tezina.compareAndSet(staraVr, novaVr);
			} else
				Thread.yield();
		} while(!okO);
	}
	
	public void obuvaMomak(Integer t) {
		
		momakObuva.incrementAndGet();
		
		boolean ok;
		
		do {
			
			int staraVr = devojkeObuva.get();
			int novaVr = staraVr - 1;
			
			ok = novaVr >= 0;
			
			if(ok)
				ok = devojkeObuva.compareAndSet(staraVr, novaVr);
			else
				Thread.yield();
		} while(!ok);
		boolean okO;
		do {
			
			int staraVr = tezina.get();
			int novaVr = staraVr - t;
			
			okO = novaVr >= 0;
			
			if(okO) {
				okO = tezina.compareAndSet(staraVr, novaVr);
			} else
				Thread.yield();
		} while(!okO);
	}
	
	public void izuvaDevojka(Integer t) {
		
		devojkeIzuva.incrementAndGet();
		
		boolean ok;
		
		do {
			
			int staraVr = momakIzuva.get();
			int novaVr = staraVr - 1;
			
			ok = novaVr >= 0;
			
			if(ok) {
				ok = momakIzuva.compareAndSet(staraVr, novaVr);
			} else
				Thread.yield();
		} while(!ok);
		boolean okO;
		do {
			
			int staraVr = tezina.get();
			int novaVr = staraVr + t;

			okO = tezina.compareAndSet(staraVr, novaVr);
			
		} while(!okO);
	}
	
	public void izuvaMomak(Integer t) {
		momakIzuva.incrementAndGet();
		
		boolean ok;
		
		do {
			
			int staraVr = devojkeIzuva.get();
			int novaVr = staraVr - 1;
			
			ok = novaVr >= 0;
			
			if(ok) {
				ok = devojkeIzuva.compareAndSet(staraVr, novaVr);
			} else
				Thread.yield();
		} while(!ok);
		boolean okO;
		do {
			
			int staraVr = tezina.get();
			int novaVr = staraVr + t;

			okO = tezina.compareAndSet(staraVr, novaVr);
			
		} while(!okO);
	}
	
	// DORADA
	
	public void obuvaDevojka2(Integer t) {
		
		devojkeObuva.incrementAndGet();
		devojkeIzuva.decrementAndGet();
		
		boolean ok;
		
		do {
			
			int staraVr = momakObuva.get();
			
			ok = staraVr == 0;

			if(!ok)
				Thread.yield();
			
		} while(!ok);
		
		boolean okO;
		
		do {
			
			int staraVr = tezina.get();
			int novaVr = staraVr - t;
			
			okO = novaVr >= 0;
			
			if(okO) {
				okO = tezina.compareAndSet(staraVr, novaVr);
			} else
				Thread.yield();
		} while(!okO);
	}
	
	public void obuvaMomak2(Integer t) {
		
		momakObuva.incrementAndGet();
		momakIzuva.decrementAndGet();
		
		boolean ok;
		
		do {
			
			int staraVr = devojkeObuva.get();
			
			ok = staraVr == 0;
			
			if(!ok)
				Thread.yield();

		} while(!ok);
		
		boolean okO;
		do {
			
			int staraVr = tezina.get();
			int novaVr = staraVr - t;
			
			okO = novaVr >= 0;
			
			if(okO) {
				okO = tezina.compareAndSet(staraVr, novaVr);
			} else
				Thread.yield();
		} while(!okO);
	}
	
	public void izuvaDevojka2(Integer t) {
		
		devojkeIzuva.incrementAndGet();
		devojkeObuva.decrementAndGet();
		
		boolean okO;
		do {
			
			int staraVr = tezina.get();
			int novaVr = staraVr + t;

			okO = tezina.compareAndSet(staraVr, novaVr);
			
		} while(!okO);
	}
	
	public void izuvaMomak2(Integer t) {
		
		momakIzuva.incrementAndGet();
		momakObuva.decrementAndGet();
		
		boolean okO;
		do {
			
			int staraVr = tezina.get();
			int novaVr = staraVr + t;

			okO = tezina.compareAndSet(staraVr, novaVr);
			
		} while(!okO);
	}

}
