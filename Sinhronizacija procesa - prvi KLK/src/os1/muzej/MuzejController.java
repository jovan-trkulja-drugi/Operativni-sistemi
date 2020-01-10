package os1.muzej;

public class MuzejController {

//	private E covek;
//	private String tmp = "";
//	private int brojac;
//
//	public MuzejController() {
//		this.brojac = 0;
//	}
//
//	public synchronized void udji(E obj) {
//		while (!Thread.interrupted()) {
//			if(covek != null) {
//				if(covek instanceof Englez) {
//					if(obj instanceof Englez) {
//						brojac++;
//					} else {
//						try {
//							while(brojac > 0) {
//								tmp.wait();
//							}
//						} catch (InterruptedException e) {
//						}
//					}
//				}
//				else if(covek instanceof Italijan) {
//					if(obj instanceof Italijan) {
//						brojac++;
//					} else {
//						try {
//							while(brojac > 0) {
//								tmp.wait();
//							}
//						} catch (InterruptedException e) {
//						}
//					}
//				}
//				else if(covek instanceof Nemac) {
//					if(obj instanceof Nemac) {
//						brojac++;
//					} else {
//						try {
//							while(brojac > 0) {
//								tmp.wait();
//							}
//						} catch (InterruptedException e) {
//						}
//					}
//				}
//			}
//		}
//	}
//
//	public synchronized void izadji(E obj) {
//		while (!Thread.interrupted()) {
//			if(brojac == 1) {
//				brojac--;
//				tmp.notifyAll();
//			}
//			else if (brojac > 1){
//				brojac--;
//			}
//			else {
//				return;
//			}
//		}
//	}
}
