import java.util.Calendar;
import java.util.LinkedList;
import java.util.TimeZone;
import java.sql.*;

public class Gestor {

	private LinkedList<Oferta> rechazados;

	public Gestor() {
		rechazados = new LinkedList<Oferta>();

	}

	public LinkedList<Oferta> getRechazados() {
		return rechazados;
	}

	public void setRechazados(LinkedList<Oferta> rechazados) {
		this.rechazados = rechazados;
	}

	public Oferta generarOferta(Usuario usuario) throws SQLException {
		if (BuscarOferta(usuario, true) == Oferta.vacia()) {
			return BuscarOferta(usuario, false);
		} else {
			return BuscarOferta(usuario, true);
		}

	}

	private Oferta BuscarOferta(Usuario usuario, boolean conPref) throws SQLException {
		Oferta oferta = Oferta.vacia();

		/*
		 * Buscamos promos que contanga al menos una atracción de la preferencia del
		 * usuario. Ninguna de las atracciones en la promo esta en el itinerario del
		 * usuario
		 */
		ResultSet rs = Basedatos.getResults(
				"select promocion.nombre, promocion.descripcion, atraccion.atraccion_id\r\n"
				+ "from promocion join promocion_atraccion on promocion.promocion_id = promocion_atraccion.promocion_id\r\n"
				+ "join atraccion on atraccion.atraccion_id = promocion_atraccion.atraccion_id \r\n"
				+ "join tipo_atraccion on tipo_atraccion.tipo_atraccion_id = atraccion.tipo_id\r\n"
				+ this.conPref(conPref, usuario)+"\r\n"
				+ "group by promocion.nombre\r\n"
				+ "\r\n"
				+ "EXCEPT\r\n"
				+ "\r\n"
				+ "select promocion.nombre, promocion.descripcion, atraccion.atraccion_id\r\n"
				+ "from promocion join promocion_atraccion on promocion.promocion_id = promocion_atraccion.promocion_id\r\n"
				+ "join atraccion on atraccion.atraccion_id = promocion_atraccion.atraccion_id \r\n"
				+ "join tipo_atraccion on tipo_atraccion.tipo_atraccion_id = atraccion.tipo_id\r\n"
				+ "where atraccion.atraccion_id in(select detalle_itinerario.atraccion_id\r\n"
				+ "								from detalle_itinerario join itinerario on itinerario.itinerario_id = detalle_itinerario.itinerario_id\r\n"
				+ "								join usuario on usuario.usuario_id = itinerario.usuario_id\r\n"
				+ "								where usuario.nombre ='"+usuario.getNombre()+"')");
		/* Hay hal menos una oferta de tipo promo que cumple los requisitos */
		if (rs.next()) {
			oferta.setNombre(rs.getString(1));
			oferta.setDescripcion(rs.getString(2));
			ResultSet rs1 = Basedatos
					.getResults("select sum(atraccion.costo) as costo, sum(atraccion.tiempo) as tiempo\r\n"
							+ "from promocion join promocion_atraccion on promocion.promocion_id = promocion_atraccion.promocion_id\r\n"
							+ "join atraccion on atraccion.atraccion_id = promocion_atraccion.atraccion_id \r\n"
							+ "where promocion.nombre = '" + oferta.getNombre()+"'");
			oferta.setPrecio(rs1.getInt(1));
			oferta.setTiempo(rs1.getDouble(2));
			/*
			 * Aplicar la promoción para determinar el verdadero costo
			 */
			switch (rs.getInt(3)) {
			/* Descuento porcentual */
			case 1: {
				oferta.setPrecio(oferta.getPrecio() * Integer.parseInt(rs.getString(4)) / 100);
				break;
			}
			/* Precio absoluto */
			case 2: {
				oferta.setPrecio(Integer.parseInt(rs.getString(4)));
				break;
			}
			case 3: {
				ResultSet rs2 = Basedatos.getResults(
						"select atraccion.costo from atraccion\r\n"
								+ "where atraccion.nombre ='" + rs.getString(4)+"')");
				oferta.setPrecio(oferta.getPrecio() - rs2.getInt(1));
				break;
			}
			}
			if (usuario.getPresupuesto() < oferta.getPrecio() || usuario.getTiempo_disponible() < oferta.getTiempo()) {
				oferta = Oferta.vacia();
			}
			if (rechazados.contains(oferta)) {
				oferta = Oferta.vacia();
			}
			if (oferta != Oferta.vacia()) {
				return oferta;
			}
		}
		/*
		 * No hay ninguna oferta tipo promo que cumpla los requisitos. Buscamos una
		 * atracción de la preferencia del usuario. Debe ser la mas cara y en caso de
		 * haber empate debe ser la que mas tiempo consuma y no debe estar en el
		 * itinerario.
		 */
		rs = Basedatos
				.getResults("select atraccion.nombre, atraccion.descripcion, atraccion.costo, atraccion.tiempo\r\n"
						+ "from atraccion join tipo_atraccion on atraccion.tipo_id = tipo_atraccion.tipo_atraccion_id\r\n"
						+ this.conPref(conPref, usuario) + "\r\n"
						+ "\r\n"
						+ "EXCEPT\r\n"
						+ "\r\n"
						+ "select atraccion.nombre, atraccion.descripcion, atraccion.costo, atraccion.tiempo\r\n"
						+ "from atraccion join tipo_atraccion on atraccion.tipo_id = tipo_atraccion.tipo_atraccion_id\r\n"
						+ "where atraccion.atraccion_id in(select detalle_itinerario.atraccion_id\r\n"
						+ "								from detalle_itinerario join itinerario on itinerario.itinerario_id = detalle_itinerario.itinerario_id\r\n"
						+ "														join usuario on usuario.usuario_id = itinerario.usuario_id\r\n"
						+ "								where usuario.nombre ='" + usuario.getNombre() + "')\r\n"
						+ "\r\n" + "order by costo desc, tiempo desc");

		if (rs.isBeforeFirst()) {
			oferta = new Oferta(rs.getString(1), false, rs.getString(2), rs.getInt(3), rs.getDouble(4));

			if (usuario.getPresupuesto() < oferta.getPrecio() || usuario.getTiempo_disponible() < oferta.getTiempo()) {
				oferta = Oferta.vacia();
			}
			if (!rechazados.contains(oferta)) {
				return oferta;
			}
		}
		return Oferta.vacia();
	}

	private String conPref(boolean conpref, Usuario usuario) {
		if (conpref) {
			return "where tipo_atraccion.nombre ='"+usuario.getAtraccion_fav()+"' AND atraccion.cupo > 1";
		}
		return "where atraccion.cupo > 1";
	}

	public void modifItinerario(Usuario usuario, Oferta oferta) throws SQLException {
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT-3:00"));
		String fecha = cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.MONTH) + "/" + cal.get(Calendar.YEAR);

		/*
		 * Le quitamos al usuario el tiempo y el dinero que cuesta la oferta que compro
		 */
		usuario.Comprar(oferta);
		/*
		 * TODO: Comprobar en la tabla itinerario si existe un itinerario con la ID de
		 * el usuario, en caso contrario debe ser creado
		 */

		// Guardamos en db un itinerario asociado al usuario
		Basedatos.insert("INSERT INTO itinerario (fecha, usuario_id)\r\n" + "VALUES (" + fecha + ","
				+ usuario.getUsuario_id() + ")");

		// Conseguimos el id del itinerario del usuario
		ResultSet rs;
		rs = Basedatos.getResults("SELECT itinerario.itinerario_id \r\n" + "FROM itinerario \r\n"
				+ "JOIN usuario ON itinerario.usuario_id =" + usuario.getUsuario_id()+")");
		int itinerario_id = rs.getInt(1);

		if (oferta.isPaquete()) {
			rs = Basedatos.getResults("select atraccion.atraccion_id\r\n"
					+ "from promocion join promocion_atraccion \r\n"
					+ "on promocion.promocion_id = promocion_atraccion.promocion_id\r\n"
					+ "join atraccion on atraccion.atraccion_id = promocion_atraccion.atraccion_id \r\n"
					+ "where promocion.nombre ='"+oferta.getNombre()+"')");
			
			while(rs.next()) {
				Basedatos.insert("INSERT INTO detalle_itinerario (itinerario_id, atraccion_id)\r\n"
						+ "VALUES ("+ itinerario_id + "," + rs.getInt(1) + ")");
			}
			

		} else {
			// Obtenemos la id de la atraccion
			rs = Basedatos.getResults("SELECT atraccion.atraccion_id\r\n" + "FROM atraccion\r\n"
					+ "WHERE atraccion.nombre ='" + oferta.getNombre()+"')");
			int atraccion_id = rs.getInt(1);

			Basedatos.insert("INSERT INTO detalle_itinerario (itinerario_id, atraccion_id)\r\n"
					+ "VALUES ("+ itinerario_id + "," + atraccion_id + ")");
		}

	}
}
