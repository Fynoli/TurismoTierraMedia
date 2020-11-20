import java.sql.*;


public class Basedatos {

    public static void main(String [] args){
    	
        try{

            //1 - Crear la conexion, Obj Connection
            String url = "jdbc:sqlite:Turismo.db";
            Connection miConexion = DriverManager.getConnection(url);
            System.out.println("conexion establecida");
            // 2 - Crear un objeto Statement
            Statement miStatement = miConexion.createStatement();
            // 3 - Ejecutar una query SQL
            ResultSet miResultSet = miStatement.executeQuery("SELECT * FROM usuario");
            System.out.println("envio query");
            // 4 - Leer el ResultSet, que seria el resultado de la consulta
            while(miResultSet.next()){
                System.out.println(miResultSet.getString("nombre") + ' ' + miResultSet.getInt("presupuesto"));
            }


        }catch (Exception e){
            System.out.println("No conecta");
            e.printStackTrace();
        }
    }

}


