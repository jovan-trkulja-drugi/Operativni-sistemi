package os1.leksin.zadatak;

import os.simulation.Application;
import os.simulation.AutoCreate;
import os.simulation.Color;
import os.simulation.Container;
import os.simulation.Operation;
import os.simulation.Thread;

public class Raskrsnica extends Application {

//	protected Sinhronizacija raskrsnica = new Sinhronizacija();
//	protected class Sinhronizacija {
//
//		private StanjeSemafora stanje;
//
//		public synchronized void cekaj(SmerVozila smer) throws InterruptedException {
//			if (smer == SmerVozila.KA_MOSTU || smer == SmerVozila.KA_STANICI) {
//				while (stanje != StanjeSemafora.ZELENO_BULEVAR) {
//					wait();
//				}
//			} else {
//				while (stanje != StanjeSemafora.ZELENO_NF) {
//					wait();
//				}
//			}
//		}
//
//		public synchronized void obavesti(StanjeSemafora stanje) {
//			this.stanje = stanje;
//			notifyAll();
//		}
//	}

	protected enum StanjeSemafora {
		CRVENO_SVIMA, ZELENO_BULEVAR, ZELENO_NF;
	}

	protected enum SmerVozila {
		KA_MOSTU, KA_STANICI, KA_FAKSU, KA_DOMOVIMA;
	}
	
	protected void promenaSemafora(StanjeSemafora novoStanje) {
		// Sinhronizacija
		// raskrsnica.obavesti(novoStanje);
	}

	@AutoCreate
	protected class Auto extends Thread {

		protected final SmerVozila smer = randomElement(SmerVozila.values());

		public Auto() {
			setColor(BOJA[smer.ordinal()]);
		}

		@Override
		protected void run() {
			dolazi(smer);
			// Sinhronizacija
			// raskrsnica.cekaj(smer);
			prelazi(smer);
			odlazi(smer);
		}
	}

	// ------------------- //
	//    Sistemski deo    //
	// ------------------- //
	// Ne dirati kod ispod //
	// ------------------- //

	protected final Color[] BOJA      = { BLUE, MAGENTA, YELLOW, ORANGE };
	protected final Container nis     = box("");
	protected final Container park    = box("");
	protected final Container sup     = box("");
	protected final Container jd      = box("");
	protected final Container rIstok  = box("Ка факсу").color(ARMY);
	protected final Container rZapad  = box("Ка домовима").color(ARMY);
	protected final Container rSever  = box("Ка станици").color(MAROON);
	protected final Container rJug    = box("Ка мосту").color(MAROON);
	protected final Container rCentar = box("").color(CARBON);
	protected final Container main    = column(row(nis, rSever, jd), row(rZapad, rCentar, rIstok), row(park, rJug, sup));
	protected final Operation auto    = duration("0.8").name("Ауто %d").color(GREEN);

	protected final Operation[] kaMostu = {
			duration("1.0").text("Долази").container(rSever).textAfter("Чека").containerAfter(rSever),
			duration("1.5").text("Прелази").container(rCentar).textAfter("Чека").containerAfter(rCentar),
			duration("1.0").text("Одлази").container(rJug).containerAfter()
	};
	protected final Operation[] kaStanici = {
			duration("1.0").text("Долази").container(rJug).textAfter("Чека").containerAfter(rJug),
			duration("1.5").text("Прелази").container(rCentar).textAfter("Чека").containerAfter(rCentar),
			duration("1.0").text("Одлази").container(rSever).containerAfter()
	};
	protected final Operation[] kaFaksu = {
			duration("1.0").text("Долази").container(rZapad).textAfter("Чека").containerAfter(rZapad),
			duration("1.5").text("Прелази").container(rCentar).textAfter("Чека").containerAfter(rCentar),
			duration("1.0").text("Одлази").container(rIstok).containerAfter()
	};
	protected final Operation[] kaDomovima = {
			duration("1.0").text("Долази").container(rIstok).textAfter("Чека").containerAfter(rIstok),
			duration("1.5").text("Прелази").container(rCentar).textAfter("Чека").containerAfter(rCentar),
			duration("1.0").text("Одлази").container(rZapad).containerAfter()
	};

	protected void dolazi(SmerVozila smer) {
		faza(smer, 0);
	}

	protected void prelazi(SmerVozila smer) {
		faza(smer, 1);
	}

	protected void odlazi(SmerVozila smer) {
		faza(smer, 2);
	}

	protected void faza(SmerVozila smer, int indeks) {
		switch (smer) {
		case KA_MOSTU: kaMostu[indeks].performUninterruptibly(); break;
		case KA_STANICI: kaStanici[indeks].performUninterruptibly(); break;
		case KA_FAKSU: kaFaksu[indeks].performUninterruptibly(); break;
		case KA_DOMOVIMA: kaDomovima[indeks].performUninterruptibly(); break;
		}
	}

	@AutoCreate(1)
	protected class Semafor extends Thread {

		@Override
		protected void step() {
			rIstok.setColor(MAROON); rZapad.setColor(MAROON); rSever.setColor(MAROON); rJug.setColor(MAROON);
			promenaSemafora(StanjeSemafora.CRVENO_SVIMA);
			sleepUninterruptibly(2000);
			rIstok.setColor(MAROON); rZapad.setColor(MAROON); rSever.setColor(ARMY); rJug.setColor(ARMY);
			promenaSemafora(StanjeSemafora.ZELENO_BULEVAR);
			sleepUninterruptibly(5000);
			rIstok.setColor(MAROON); rZapad.setColor(MAROON); rSever.setColor(MAROON); rJug.setColor(MAROON);
			promenaSemafora(StanjeSemafora.CRVENO_SVIMA);
			sleepUninterruptibly(2000);
			rIstok.setColor(ARMY); rZapad.setColor(ARMY); rSever.setColor(MAROON); rJug.setColor(MAROON);
			promenaSemafora(StanjeSemafora.ZELENO_NF);
			sleepUninterruptibly(5000);
		}
	}

	public static void main(String[] a) {
		launch("Раскрсница");
	}
}
