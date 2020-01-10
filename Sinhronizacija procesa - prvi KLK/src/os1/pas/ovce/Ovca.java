package os1.pas.ovce;

import java.util.Random;

public class Ovca extends Zivotinja implements Runnable {
	
	private Pas p;
	
	public Ovca(Pas p) {
		this.p = p;
	}
	
	@Override
	public void run() {
		
		Random r = new Random();
		
		while(!Thread.interrupted()) {
			
			int broj = r.nextInt(1000) + 2000;
			try {
				
				Thread.sleep(broj);
				
				int pomeri = r.nextInt(4);
				
				switch (pomeri) {
				
				case 0:
					super.x -= 1;
					break;
				case 1:
					super.x += 1;
					break;
				case 2:
					super.y += 1;
					break;
				case 3:
					super.y -= 1;

				}
				
				if(super.getUdaljenost() > 100) {
					
					p.pomoc(this);
				}
				
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
			
			
		}
		
		
	}
	

}
