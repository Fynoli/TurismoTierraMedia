import java.util.*;

public class Usuario {
	private String nombre;
	private int presupuesto;
	private double tiempo_disponible;
	private String atraccion_fav;
	private LinkedList<Producto> itinerario;

	

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
		return this.getTiempo_disponible() >= p.getTiempo() && this.getPresupuesto() >= p.getCosto();
	}

	public void addProducto(Producto producto) {
		if (producto instanceof Paquete) {// Si es paquete le quito un cupo a cada atraccion
			for (Atraccion a : ((Paquete) producto).getAtracciones()) {
				a.ocuparCupo();
			}
		} else if (producto instanceof Atraccion) {// si es atraccion le quito un cupo
			((Atraccion) producto).ocuparCupo();
		}
		itinerario.add(producto);
		presupuesto -= producto.getCosto();
		tiempo_disponible -= producto.getTiempo();
	}

	public int costoItinerario() {
		int costo = 0;
		for (Producto producto : itinerario) {
			costo += producto.getCosto();
		}
		return costo;
	}

	public double tiempoItinerario() {
		double tiempo = 0;
		for (Producto producto : itinerario) {
			tiempo += producto.getTiempo();
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

	public boolean tieneProducto(Producto producto) {

		if (producto instanceof Paquete) { // si el producto es un paquete entonces
			return this.getItinerario().contains(producto); // retorna true si el paquete esta en el itinerario
		} else {
			if (producto instanceof Atraccion) { // si el producto es una atraccion
				for (Producto p : itinerario) { // recorres cada producto del itinerario
					if (p instanceof Paquete) { // si el producto en el itinerario es un paquete
						return ((Paquete) p).getAtracciones().contains(producto); // retorna true si la atraccion esta
																					// en el paquete

					} else if (p instanceof Atraccion) { // si el producto en el itinerario es un atraccion
						return p.equals(producto); // retorna true si la atraccion en el itinerario es igual al producto
					}
				}
			}
		}
		return false;
	}

}
