import java.util.LinkedList;

public class Paquete extends Producto{
	private LinkedList<Atraccion> atracciones;
	private String promocion;

	public Paquete(String nombre, int costo,String promocion, LinkedList<Atraccion> atracciones) {
		super(nombre, costo);
		this.atracciones=atracciones;		
		this.promocion = promocion;
		
	}

	@Override
	public String toString() {
		String nombresAtracciones = "";
		for (Atraccion atraccion : atracciones) {
			
			nombresAtracciones += atraccion.getNombre();
			if (atracciones.getLast() != atraccion) {
				nombresAtracciones += ", ";
			}
		}
		return "Paquete: " + super.getNombre() + " costo: " + super.getCosto() + " oros" + " " + " incluye: " + nombresAtracciones + 
				" promocion: " + promocion;
	}
	
	

}
