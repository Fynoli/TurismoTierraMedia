import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.sql.*;

public class Gestor {

	private LinkedList<Producto> rechazados;

	public Gestor() {
		rechazados=new LinkedList<Producto>();
	}

	public Producto GenerarSugerencia(Usuario usuario) {

		Producto sugerencia;

		sugerencia = buscarProducto(usuario,true);
		if(sugerencia == null) {
			sugerencia = buscarProducto(usuario,false);
		}

		return sugerencia;
	}

	private void buscarProductos(Usuario usuario) {
		ResultSet rs = Basedatos.getResults("\n" +
				"SELECT promocion.nombre, promocion.descripcion, SUM(promocion.costo), atraccion.nombre\n" +
				"FROM tipo_atraccion\n" +
				"JOIN atraccion ON tipo_atraccion.nombre = '"+ usuario.getAtraccion_fav()+"' AND tipo_atraccion.tipo_atraccion_id = atraccion.tipo_id\n" +
				"JOIN promocion_atraccion ON promocion_atraccion.atraccion_id = atraccion.atraccion_id\n" +
				"JOIN promocion ON promocion.promocion_id = promocion_atraccion.promocion_id");

	}
//	private Producto buscarProducto( Usuario usuario, boolean conPreferencia) throws java.sql.SQLException{
//		//Primero paquetes(Primero preferencias, luego mas caros, luego mas tiempo) luego atracciones
//		// tomo un usuario -> levanto las promo que coincida con los gustos ->
//		//Buscamos un paquete que tenga una atraccion que el usuario prefiera
//		ResultSet rs = Basedatos.getResults("\n" +
//				"SELECT promocion.nombre, promocion.descripcion, SUM(promocion.costo), atraccion.nombre\n" +
//				"FROM tipo_atraccion\n" +
//				"JOIN atraccion ON tipo_atraccion.nombre = 'Degustacion' AND tipo_atraccion.tipo_atraccion_id = atraccion.tipo_id\n" +
//				"JOIN promocion_atraccion ON promocion_atraccion.atraccion_id = atraccion.atraccion_id\n" +
//				"JOIN promocion ON promocion.promocion_id = promocion_atraccion.promocion_id");
//		if(rs != null) {
//
//		}
//		SELECT DISTINCT promocion.nombre, promocion.descripcion, promocion.costo
//		FROM tipo_atraccion
//		JOIN atraccion ON tipo_atraccion.nombre = 'Degustacion' AND tipo_atraccion.tipo_atraccion_id = atraccion.tipo_id
//		JOIN promocion_atraccion ON promocion_atraccion.atraccion_id = atraccion.atraccion_id
//		JOIN promocion ON promocion.promocion_id = promocion_atraccion.promocion_id

//		"promocion promocion_atraccion atraccion tipo_atraccion"
//		"nombre_promocion costo_promo descripcion atracciones_nombre"
//		"tipo_atraccion.nombre = usuario.atraccion_favorita && promo.tiempo < usuario.tiempo_disponible " +
//				"&& promo.costo < usuario.presupuestp && promo.cupo >=1 && que la atraccion no en el itinerario"



		//BUSCAMOS UN PAQUETE QUE CONTENGA UNA ATRACCION QUE SEA DE PREFERENCIA DEL USUARIO

		//				for(Paquete producto: dataBase.getPaquetes()) {//Recorremos paquetes
//					for(Atraccion atraccion: (producto).getAtracciones()) {
//						if (!usuario.tieneProducto(producto) && !rechazados.contains(producto)) {
//							if (usuario.puedeComprar(producto)) {
//								if (producto.tieneCupo()) {
//									if (conPreferencia) {
//										if (atraccion.getTipo_atraccion().equalsIgnoreCase(usuario.getAtraccion_fav())) {
//											return producto;
//										}
//									}
//									else {
//										return producto;
//									}
//								}
//							}
//						}
//
//
//					}

	//				Buscamos la atraccion mas cara de las que coinciden con su preferencia
	LinkedList<Atraccion> atracciones=new LinkedList<Atraccion>();
				for(Atraccion atraccion: dataBase.getAtracciones()) {
		if(!usuario.tieneProducto(atraccion) && !rechazados.contains(atraccion)) { // si el usuario no tiene la atraccion en su itinerario y tampoco la rechazo
			if(usuario.puedeComprar(atraccion)) {
				if(atraccion.tieneCupo()) {
					if(conPreferencia) {
						if(atraccion.getTipo_atraccion().equalsIgnoreCase(usuario.getAtraccion_fav())) {
							atracciones.add(atraccion);
						}

					}
					else {
						atracciones.add(atraccion);
					}
				}
			}
		}
	}
				if(!atracciones.isEmpty()) {
		return atracciones.getFirst();
	}

				return null;
}



	public void mostrarItinerario(Usuario usuario) {
		System.out.println("\n\nItinerario de: " + usuario.getNombre());
		for(Producto producto:usuario.getItinerario()) {
			if(producto instanceof Paquete) {
				System.out.println("\n" + (((Paquete) producto).toString()));
			}
			else {
				System.out.println("\n" + ((Atraccion) producto).toString());
			}
		}

		System.out.println("\nCosto total: " + usuario.costoItinerario() + " Monedas   Tiempo total: " + usuario.tiempoItinerario() + " Horas\n");
		System.out.println("\n Le restan en su presupuesto: "+ usuario.getPresupuesto() + " Monedas y le quedan "+ usuario.getTiempo_disponible()+" Horas libres");

	}

	public void ImprimirItinerario(Usuario usuario) {
		FileWriter archivo = null;
		PrintWriter pw = null;
		DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		try
		{
			archivo = new FileWriter("TurismoTierraMedia/TurismoEnLaTierraMedia/itinerarios/itinerario de "+usuario.getNombre()+"_"+ df.format(LocalDateTime.now())+".txt");
			pw = new PrintWriter(archivo);

			pw.println("Cliente: "+usuario.getNombre()+"\n");
			pw.println("Itinerario: ");

			for(Producto producto:usuario.getItinerario()) {
				if(producto instanceof Paquete) {
					pw.println(((Paquete) producto).toString());
				}
				else {
					pw.println(((Atraccion) producto).toString());
				}
			}

			pw.println("\nCosto total: "+usuario.costoItinerario()+" Monedas        Tiempo total: "+usuario.tiempoItinerario()+" Horas");
			pw.println("\n Le restan en su presupuesto: "+ usuario.getPresupuesto() + " Monedas y le quedan "+ usuario.getTiempo_disponible()+" Horas libres");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// Nuevamente aprovechamos el finally para
				// asegurarnos que se cierra el fichero.
				if (null != archivo)
					archivo.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

//	public LinkedList<Producto> getRechazados() {
//		return rechazados;
//	}
//
//	public void setRechazados(LinkedList<Producto> rechazados) {
//		this.rechazados = rechazados;
//	}



}
