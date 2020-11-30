import java.sql.SQLException;
import java.util.*;

public class Usuario {
	private String nombre;
	private int presupuesto;
	private double tiempo_disponible;
	private String atraccion_fav;
	private int usuario_id;
//	private LinkedList<Producto> itinerario;

	public Usuario(int usuario_id, String nombre, int presupuesto, double tiempo_disponible, String atraccion_fav) {
		this.nombre = nombre;
		this.presupuesto = presupuesto;
		this.tiempo_disponible = tiempo_disponible;
		this.atraccion_fav = atraccion_fav;
		this.usuario_id = usuario_id;

	}

	/*
	 * public void setValores(Atraccion atraccion) { this.presupuesto -=
	 * Atraccion.getCosto(); this.tiempo_disponible -= Atraccion.getTiempoVisita();
	 * }
	 */

	public void Comprar(Oferta oferta) throws SQLException {
		presupuesto -= oferta.getPrecio();
		tiempo_disponible -= oferta.getTiempo();
		Basedatos.insert("UPDATE usuario SET presupuesto=" + this.presupuesto + ", tiempo_disponible="
				+ this.tiempo_disponible + " Where usuario_id=" + this.usuario_id);

	}

	public int costoItinerario() {
		int costo = 0;
//		for (Producto producto : itinerario) {
//			costo += producto.getCosto();
//		}
		return costo;
	}

	public double tiempoItinerario() {
		double tiempo = 0;
//		for (Producto producto : itinerario) {
//			tiempo += producto.getTiempo();
//		}
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

	public int getUsuario_id() {
		return usuario_id;
	}

	@Override
	public String toString() {
		return "Usuario: " + nombre + "  Presupuesto: " + presupuesto + " monedas" + "  Tiempo disponible: "
				+ tiempo_disponible + " horas" + "  Atraccion favorita: " + atraccion_fav;
	}

}
