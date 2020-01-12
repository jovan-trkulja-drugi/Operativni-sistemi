package os1.semaphore.mravinjak;

import java.util.concurrent.Semaphore;

public class Mravinjak {
	
	private Semaphore mravRadnik = new Semaphore(0);
	private Semaphore mravZidar = new Semaphore(0);
	
	private Semaphore hrana = new Semaphore(0);
	private Semaphore velicina = new Semaphore(0);
	
	private Semaphore mutexHrana = new Semaphore(0);
	private Semaphore mutexVelicina = new Semaphore(0);
	
	protected class MravZidar extends Thread {
		
		@Override
		public void run() {
			
			while(true) {
				mravZidar.acquireUninterruptibly();
			}
			
		}
	}
	
	protected class MravRadnik extends Thread {
		
		@Override
		public void run() {
			
			mutexHrana.acquireUninterruptibly();
			
			int vel = velicina.availablePermits();
			int trenH = hrana.availablePermits();
			
			if(vel == 1 && trenH < 50) {
				
			}
			
		}
	}
	
	public void doneoHranu(int n) {
		hrana.release(n);
	}

}

class Priroda {

	public static int nadjiHranu() {
		int n = (int) (2 + 8 * Math.random());
		try {
			Thread.sleep((long) (1000 + 1000 * Math.random()));
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		System.out.println(Thread.currentThread().getName() + " je pronasao " + n + " grama hrane.");
		return n;
	}

	public static void prosiriMravinjak() {
		try {
			Thread.sleep((long) (1000 + 3000 * Math.random()));
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		System.out.println(Thread.currentThread().getName() + " je prosirio mravinjak.");
	}
}