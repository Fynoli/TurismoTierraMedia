import java.sql.*;
import java.util.LinkedList;


public abstract class Basedatos {


	public static LinkedList<Usuario> cargarUsuarios() throws SQLException {
		LinkedList<Usuario> usuarios = new LinkedList<Usuario>();
		ResultSet rs = Basedatos.getResults("\n"
				+ "SELECT usuario.nombre, usuario.usuario_id, usuario.presupuesto, tipo_atraccion.nombre, usuario.tiempo_disponible\n"
				+ "FROM usuario\n"
				+ "JOIN tipo_atraccion ON usuario.atraccion_favorita = tipo_atraccion.tipo_atraccion_id");
		while (rs.next()) {
			usuarios.add(new Usuario(rs.getString("nombre"), rs.getInt("presupuesto"),
					rs.getDouble("tiempo_disponible"), rs.getString("tipo_atraccion.nombre")));
		}
		return usuarios;
	}

    public static ResultSet getResults(String query){
        Connection miConexion = null;
        try{
            //1 - Crear la conexion, Obj Connection
            String url = "jdbc:sqlite:Turismo.db";
            miConexion = DriverManager.getConnection(url);
//            System.out.println("conexion establecida");
            // 2 - Crear un objeto Statement
            final PreparedStatement statement = miConexion.prepareStatement(query);
//            Statement miStatement = miConexion.createStatement();
            // 3 - Ejecutar una query SQL
            ResultSet miResultSet = statement.executeQuery();
//            System.out.println("envio query");
            // 4 - Leer el ResultSet, que seria el resultado de la consulta
//            while(miResultSet.next()){
//                System.out.println(miResultSet.getString("nombre") + ' ' + miResultSet.getInt("presupuesto"));
//            }

            return miResultSet;


        }catch (Exception e) {
//            System.out.println("No conecta");
            e.printStackTrace();
            return null;
        }
    }

    public static boolean insert(String query){
        Connection miConexion = null;
        try{
            //1 - Crear la conexion, Obj Connection
            String url = "jdbc:sqlite:Turismo.db";
            miConexion = DriverManager.getConnection(url);
//            System.out.println("conexion establecida");
            // 2 - Crear un objeto Statement
            Statement miStatement = miConexion.createStatement();
            // 3 - Ejecutar una query SQL
            boolean miResultSet = miStatement.execute(query);
//            System.out.println("envio query");
            // 4 - Leer el ResultSet, que seria el resultado de la consulta
//            while(miResultSet.next()){
//                System.out.println(miResultSet.getString("nombre") + ' ' + miResultSet.getInt("presupuesto"));
//            }
            return miResultSet;


        }catch (Exception e) {
//            System.out.println("No conecta");
            e.printStackTrace();
            return false;
        }
    }
}