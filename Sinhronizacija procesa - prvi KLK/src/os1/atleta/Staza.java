package os1.atleta;

import java.util.ArrayList;
import java.util.List;

public class Staza {

	private final int max_ljudi = 5;
	private int trenutno_ljudi = 0;
	
	private Atleta[] atlete;
	
	private List<Atleta> lista;

	public Staza(Atleta... atlete) {
		
		this.lista = new ArrayList<>();
		this.atlete = atlete;
	}

	public synchronized void popniNekog(Atleta a) {

		try {

			while (trenutno_ljudi + 1 > max_ljudi) {
				
				a.setAkcija("CEKA");
				wait();
				
			}
			
			lista.add(a);
			trenutno_ljudi += 1;
			azurirajTrcanje(a);
			notifyAll();

		} catch (InterruptedException e) {

		}

	}
	
	private void azurirajTrcanje(Atleta a) {
		
		for(int i = 0; i < atlete.length; i++) {
			
			if(atlete[i].getIdAtlete() == a.getIdAtlete())
				atlete[i].setAkcija("TRCI");
		}
	}
	
	private void azurirajSpustanje(Atleta a) {
		
		for(int i = 0; i < atlete.length; i++) {
			
			if(atlete[i].getIdAtlete() == a.getIdAtlete())
				atlete[i].setAkcija("NE TRCI");
		}
	}

	public synchronized void spustiNekog(Atleta a) {

		try {
			
			if(lista.contains(a)) {
				
				trenutno_ljudi -= 1;
				azurirajSpustanje(a);
				lista.remove(a);
				notifyAll();
			}
			

		} catch (Exception e) {

		}

	}
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		lista.forEach(e -> {
			if("TRCI".equals(e.getAkcija()))
				sb.append(e.getAkcija() + " , " + e.toString() + " | ");
		});
		
		sb.append("\n---------------\n");
		
		for(Atleta a: atlete) {
			
			if("TRCI".equals(a.getAkcija()))
				continue;
			
			sb.append(a.getAkcija() + " , " + a.toString() + " | ");
		}
		
		sb.append("\n-------------------\n");
		sb.append("\n");
		
		return sb.toString();
	}

}
