import java.util.*;

public class Usuario {
	private String nombre;
	private int presupuesto;
	private double tiempo_disponible;
	private String atraccion_fav;
	private LinkedList<Producto> itinerario;

	// el tema de la atraccion favorita estuve viendo videos sobre enum pero no
	// entiendo como se puede aplicar aca
	// con numeros enteros
	// no necesariamente se usa con enteros, entras al dato y te salen esas tres
	// opciones (flor)
	// no se si lo van a aplicar pero los profes recomentdaron utilizar Lower Camel
	// Case en los nombres de variables (flor)

	public Usuario(String nombre, int presupuesto, double tiempo_disponible, String atraccion_fav) {
		this.nombre = nombre;
		this.presupuesto = presupuesto;
		this.tiempo_disponible = tiempo_disponible;
		this.atraccion_fav = atraccion_fav;
		itinerario = new LinkedList<Producto>();

	}

	/*
	 * public void setValores(Atraccion atraccion) { this.presupuesto -=
	 * Atraccion.getCosto(); this.tiempo_disponible -= Atraccion.getTiempoVisita();
	 * }
	 */
	public boolean puedeComprar(Producto p) {
		return this.getTiempo_disponible()>=p.getTiempo() && this.getPresupuesto()>=p.getCosto();
	}
	
	public void addProducto(Producto producto) {
		if(producto instanceof Paquete) {//Si es paquete le quito un cupo a cada atraccion
			for(Atraccion a: ((Paquete) producto).getAtracciones()) {
				a.ocuparCupo();
			}
		}
		else if(producto instanceof Atraccion) {//si es atraccion le quito un cupo
			((Atraccion) producto).ocuparCupo();
		}
		itinerario.add(producto);
		presupuesto-=producto.getCosto();
		tiempo_disponible-=producto.getTiempo();
	}
	
	
	public int costoItinerario() {
		int costo=0;
		for(Producto producto: itinerario) {
			costo+=producto.getCosto();
		}
		return costo;
	}
	
	public double tiempoItinerario() {
		double tiempo=0;
		for(Producto producto: itinerario) {
			tiempo+=producto.getTiempo();
		}
		return tiempo;
	}

	public String getNombre() {
		return nombre;
	}

	public int getPresupuesto() {
		return presupuesto;
	}

	public double getTiempo_disponible() {
		return tiempo_disponible;
	}

	public String getAtraccion_fav() {
		return atraccion_fav;
	}

	public LinkedList<Producto> getItinerario() {
		return itinerario;
	}

	@Override
	public String toString() {
		return "Usuario: " + nombre + "  Presupuesto: " + presupuesto + " monedas" + "  Tiempo disponible: "
				+ tiempo_disponible + " horas" + "  Atraccion favorita: " + atraccion_fav;
	}

}
