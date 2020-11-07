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
		itinerario.add(producto);
		presupuesto-=producto.getCosto();
		tiempo_disponible-=producto.getTiempo();
	}
	
	

	public void crearitinerario(String lugar) {

		LinkedList<String> itinerario = new LinkedList<String>();

		itinerario.add(lugar);

		// este codigo es innecesario pero lo hice para chequear que se esten agregando
		// a la lista, recorre cada elemento y lo va imprimiendo

		for (String ubicacion : itinerario) {
			System.out.println(ubicacion);

		}

	}

	// cuando finalice el recorrido sacamos todas las atracciones de su itinerario

	public void finalizarRecorrido() {

		LinkedList<String> itinerario = new LinkedList<String>();

		// recorre cada una de las atracciones y las va eliminando

		for (String ubicacion : itinerario) {

			itinerario.remove(ubicacion);
		}

		System.out.print("lo que quedo es: " + itinerario);

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
