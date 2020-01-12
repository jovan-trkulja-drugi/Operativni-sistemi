package os1.atomic_semaphore.saMoodlea;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import os.simulation.Application;
import os.simulation.AutoCreate;
import os.simulation.Color;
import os.simulation.Container;
import os.simulation.Item;
import os.simulation.Operation;
import os.simulation.Thread;

/*
 * Dat je bafer fiksne velicine. Vise procesa zeli da istovremeno dodaje i
 * uklanja elemente sa ovog bafera.
 * 
 * Realizovati operaciju dodavanja tako da, ako je bafer pun, blokira proces
 * dok se ne oslobodi mesto za novi element. Takodje, realizovati operaciju
 * uklanjanja tako da, ako je bafer prazam, blokira proces dok se ne doda novi
 * element. 
 */
public class ProizvodjaciIPotrosacSemaphore extends Application {

	protected Bafer bafer = new Bafer(12);
	
	private Semaphore mutex_sto = new Semaphore(1);
	private Semaphore sto = new Semaphore(12);
	private Semaphore zaUzimanje = new Semaphore(0);
	
	protected class Bafer {

		private final List<Element> lista = new ArrayList<>();
		private final int velicina;

		public Bafer(int velicina) {
			this.velicina = velicina;
		}

		// Sinhronizacija
		public void stavi(Element o) {
			
			sto.acquireUninterruptibly();
			
			try {
				mutex_sto.acquireUninterruptibly();
				lista.add(o);
				elementi.addItem(o);
				mutex_sto.release();
			} finally {
				zaUzimanje.release();
			}
			
		}
		
		

		// Sinhronizacija
		public Element uzmi() {
			
			Element result;
			
			zaUzimanje.acquireUninterruptibly();
			
			try {
				mutex_sto.acquireUninterruptibly();
				result = lista.remove(0);
				elementi.removeItem(result);
				mutex_sto.release();
			} finally {
				sto.release();
			}
			
			return result;
			
		}
		
	}

	// ------------------- //
	//    Sistemski deo    //
	// ------------------- //
	// Ne dirati kod ispod //
	// ------------------- //

	@AutoCreate(4)
	protected class Proizvodjac extends Thread {

		private final int id = getID();
		private int br = 0;

		@Override
		protected void step() {
			Element element = proizvedi(id + "x" + (br++));
			bafer.stavi(element);
		}

	}

	@AutoCreate(4)
	protected class Potrosac extends Thread {

		@Override
		protected void step() {
			Element element = bafer.uzmi();
			potrosi(element);
		}
	}

	protected final Container proizvodjaci = box("Произвођачи").color(NAVY);
	protected final Container potrosaci    = box("Потрошачи").color(MAROON);
	protected final Container elementi     = box("Елементи").color(NEUTRAL_GRAY);
	protected final Container main         = row(proizvodjaci, elementi, potrosaci);
	protected final Operation proizvodjac  = init().name("Произв. %d").color(AZURE).text("Чека").container(proizvodjaci);
	protected final Operation potrosac     = init().name("Потр. %d").color(ROSE).text("Чека").container(potrosaci);
	protected final Operation element      = init();
	protected final Operation proizvodnja  = duration("3±1").text("Производи").textAfter("Чека");
	protected final Operation potrosnja    = duration("7±2").text("Троши %s").textAfter("Чека");

	protected Element proizvedi(String vrednost) {
		proizvodnja.performUninterruptibly();
		return new Element(vrednost);
	}

	protected void potrosi(Element element) {
		potrosnja.performUninterruptibly(element.getName());
	}

	protected class Element extends Item {

		public Element(String vrednost) {
			setName(vrednost);
		}

		private int getIndex() {
			return bafer.lista.indexOf(this);
		}

		@Override
		public Color getColor() {
			int index = getIndex();
			if ((index >= 0) && (index < bafer.velicina)) {
				return CHARTREUSE;
			} else {
				return ORANGE;
			}
		}

		@Override
		public String getText() {
			return String.format("Bafer[%d]", getIndex());
		}
	}

	public static void main(String[] arguments) {
		launch("Произвођачи и потрошачи");
	}
}
