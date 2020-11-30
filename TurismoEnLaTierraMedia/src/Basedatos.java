import java.sql.*;
import java.util.LinkedList;


public class Basedatos {


	public static LinkedList<Usuario> cargarUsuarios() throws SQLException {
		LinkedList<Usuario> usuarios = new LinkedList<Usuario>();
		ResultSet rs = Basedatos.getResults("\n"
				+ "SELECT usuario.nombre, usuario.usuario_id, usuario.presupuesto, tipo_atraccion.nombre AS tipo_atra, usuario.tiempo_disponible\n"
				+ "FROM usuario\n"
				+ "JOIN tipo_atraccion ON usuario.atraccion_favorita = tipo_atraccion.tipo_atraccion_id");
		while (rs.next()) {
			usuarios.add(new Usuario(rs.getInt("usuario_id"), rs.getString("nombre"), rs.getInt("presupuesto"),
					rs.getDouble("tiempo_disponible"), rs.getString("tipo_atra")));
		}
		return usuarios;
	}

    public static ResultSet getResults(String query) throws SQLException{
        Connection miConexion = null;
        try{
            String url = "jdbc:sqlite:Turismo.db";
            miConexion = DriverManager.getConnection(url);
            
            final PreparedStatement statement = miConexion.prepareStatement(query);
            ResultSet miResultSet = statement.executeQuery();
            return miResultSet;

        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error y listo");
            return null;
        }finally {
        	miConexion.close();
        }
    }

    public static boolean insert(String query) throws SQLException{
    	Connection miConexion = null;
        try{

            String url = "jdbc:sqlite:Turismo.db";
            miConexion = DriverManager.getConnection(url);
            final PreparedStatement statement = miConexion.prepareStatement(query);
            statement.execute();
            return true;


        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally {
        	miConexion.close();
        }
    }
}