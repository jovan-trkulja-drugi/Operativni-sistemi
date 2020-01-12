package os1.atomic_semaphore.saMoodlea;

import java.util.concurrent.Semaphore;

public class ControllerBerberin {

	private Semaphore berberi = new Semaphore(0);
	private Semaphore musterije = new Semaphore(0);
	
	public void spavaj() {
		berberi.release(1);
		musterije.acquireUninterruptibly(1);
		//mutex.acquireUninterruptibly();
		//musterije.acquireUninterruptibly(1);
		//mutex.release();
	}
	
	public void cekaj() {
//		mutex.acquireUninterruptibly();
		musterije.release(1);
//		mutex.release();
		berberi.acquireUninterruptibly(1);
	}

}
