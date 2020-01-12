package os1.atomic_semaphore.saMoodlea;

import java.util.concurrent.atomic.AtomicInteger;

public class ControllerBerberiAtomic {

	private AtomicInteger b = new AtomicInteger(0);

	public void budiBerberina() {
		boolean ok;
		do {
			int tmp = b.get();
			ok =  tmp == 0 || tmp == 1;
			
			if(ok && tmp == 0) 
				ok = b.compareAndSet(0, 1);
			else if(ok && tmp == 1)
				ok = b.compareAndSet(1, 2);
			else 
				Thread.yield();
		
		} while(!ok);
	}

	public void budiMusteriju() {
		boolean ok;
		do {
			int tmp = b.get();
			ok = tmp == 1 || tmp == 2;
			if(ok && tmp == 1) 
				ok = b.compareAndSet(1, 0);
			else if(ok && tmp == 2) 
				ok = b.compareAndSet(2, 1);
			else 
				Thread.yield();
		} while(!ok);

	}

}
