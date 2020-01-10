package os1.pete.vezbe;

import java.util.Random;

public class Ovca extends Zivotinja implements Runnable {
	
	private Pas p;
	
	public Ovca(Pas p) {
		this.p = p;
	}
	@Override
	public void run() {

		try {
			
			Random rnd = new Random();
			
			while(!Thread.interrupted()) {
				
				synchronized (this) {
					
					int broj = rnd.nextInt(1000) + 2000;

					Thread.sleep(broj);

					int pos = rnd.nextInt(4);

					switch (pos) {
					
					case 0:
						super.x += 1;
						break;
						
					case 1:
						super.x -= 1;
						break;
						
					case 2:
						super.y += 1;
						break;
						
					case 3:
						super.y -= 1;
						
					}
					
					if(super.getUdaljenost() > 100) {
						
						p.pomoc++;
						
						while(p.getLaje() == false) {
							p.probudi();
							wait();
						}
						
						
						super.x = 0;
						super.y = 0;
						
						p.pomoc--;
						
					}
				}
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
