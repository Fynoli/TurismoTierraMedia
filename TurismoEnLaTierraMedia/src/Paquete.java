import java.util.LinkedList;

public class Paquete extends Producto{
	private LinkedList<Atraccion> atracciones;

	public Paquete(String nombre, int costo, LinkedList<Atraccion> atracciones) {
		super(nombre, costo);
		this.atracciones=atracciones;		
		// TODO Auto-generated constructor stub
	}
	
	

}
