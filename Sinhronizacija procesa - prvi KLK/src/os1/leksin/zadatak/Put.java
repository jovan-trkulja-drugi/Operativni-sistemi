package os1.leksin.zadatak;

public class Put {
	
	private Bulevar bulevar;
	private Front front;
	
//	private Object lockBulevar;
//	private Object lockFront;
	
	public Put() {
		
//		this.lockBulevar = new Object();
//		this.lockFront = new Object();
//		
		this.bulevar = new Bulevar(this);
		this.front = new Front(this);
		
	}
	
	public void simuliraj() {
		
			bulevar.zabranjenProlaz();
			front.slobodanProlaz();
			
			bulevar.start();
			front.start();
			
			
		
		
		
	}
	
	public Front getFront() {
		return this.front;
	}
	
	public Bulevar getBulevar() {
		return this.bulevar;
	}
	
	
	

}