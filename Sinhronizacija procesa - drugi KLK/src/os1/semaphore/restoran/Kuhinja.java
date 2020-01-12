package os1.semaphore.restoran;

import java.util.concurrent.Semaphore;

public class Kuhinja {
	
	private Semaphore mutexHleb = new Semaphore(2);
	private Semaphore mutexPotaz = new Semaphore(30);
	private Semaphore mutexSir = new Semaphore(1);
	private Semaphore mutexPovrce = new Semaphore(4);
	
	private Integer cena = 0;
	
	public void addHleb() {
		mutexHleb.release(6);
	}
	
	public void addSir() {
		mutexSir.release();
	}
	
	public void addPotaz() {
		mutexPotaz.release(10);
	}
	
	public void addPovrce() {
		mutexPovrce.release(1000);
	}
	
	public void napraviSendvic() {
		
		try {
			
			mutexHleb.acquire(2);
			mutexSir.acquire(1);
			mutexPotaz.acquire(100);
			
			cena += 230;
			
		} catch(InterruptedException e) {
			
		} finally {
			
			mutexHleb.release(2);
			mutexSir.release();
			mutexPotaz.release(100);
		}
	}
	
	public void napraviPotazBundeva() {
		
		try {
			
			mutexPotaz.acquire(1);
			mutexHleb.acquire();
			
			cena += 340;
			
		} catch(InterruptedException e) {
			
		} finally {
			mutexPotaz.release();
			mutexHleb.release();
		}
	}
	
	public void napraviTofu() {
		
		try {
			
			mutexSir.acquire();
			mutexPovrce.acquire(300);
			
			cena += 340;
			
		} catch(InterruptedException e) {
			
		} finally {
			mutexSir.release();
			mutexPovrce.release(300);
		}
		
	}

}
