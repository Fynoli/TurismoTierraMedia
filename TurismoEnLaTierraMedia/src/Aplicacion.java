import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.io.*;
import java.util.Scanner;

public class Aplicacion {

	public static void main(String[] args) throws SQLException{
		
		LinkedList<Usuario> usuarios= new LinkedList<Usuario>();
		usuarios=Basedatos.cargarUsuarios();
		
		Gestor gestor= new Gestor(); // creas un gestor y le pasas la base de datos para que administre
		String decision = "Y"; 
		
		Scanner sc=new Scanner(System.in); // creamos un scanner para leer la entrada de consola 
		
		Oferta  oferta=null;
		
		for (Usuario usuario : usuarios) {
			System.out.println("Bienvenido " + usuario.getNombre() + ". Prepara tu itinerario");
			System.out.println("Presta atencion a las siguientes ofertas: ");
			while(! gestor.generarOferta(usuario).equals(Oferta.vacia())) {
				oferta = gestor.generarOferta(usuario);
				System.out.println("Monedas restantes: " + usuario.getPresupuesto() + " Tiempo restante: " + usuario.getTiempo_disponible());
				System.out.println(oferta);
				System.out.println("Desea comprarla?");
				System.out.println("Y - Si");
				System.out.println("N - No");
				decision=sc.next();
				if(decision.equalsIgnoreCase("Y")) {
					gestor.modifItinerario(usuario, oferta);
					System.out.println("Producto adquirido!");
				}
				else {
					gestor.getRechazados().add(oferta);
				}
			}
			System.out.println("No hay mas ofertas disponibles. Este es tu itinerario para el dia de hoy.");
			System.out.println("---------------------------------------------- \n");
			
			/*TODO: Mostrar el itinerario del usuario*/
			
			System.out.println("---------------------------------------------- \n");
			gestor.getRechazados().clear();
		}
		
		System.out.println("Programa Finalizado con exito, Se generaron los archivos en la carpeta de itinerarios");
		
		
		
		
		
	}
	
}
