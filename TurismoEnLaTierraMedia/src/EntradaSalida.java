import java.io.FileNotFoundException;
import java.io.File;
import java.util.LinkedList;
import java.util.Scanner;

public abstract class EntradaSalida {

	public static LinkedList<Atraccion> CargarAtracciones (String archivo_atracciones) throws FileNotFoundException{
		LinkedList<Atraccion> atracciones = new LinkedList<Atraccion>();
		Scanner sc = new Scanner( new File(archivo_atracciones));
		sc.nextLine(); //Para omitir las cabeceras
		while (sc.hasNextLine()) {
			atracciones.add(CreaAtraccionDesdeLinea(sc.nextLine()));
		}
		sc.close();
		return atracciones;
	}
	
	private static Atraccion CreaAtraccionDesdeLinea(String linea) {
		Atraccion atraccion=null;
	    Scanner sc = new Scanner(linea);
	    sc.useDelimiter(";");
	    while (sc.hasNext()){
	    	atraccion=new Atraccion(sc.next(),sc.nextInt(),sc.nextDouble(),sc.nextInt(),sc.next());
	    }
	    sc.close();
	    return atraccion;
	}
	
	
	public static LinkedList<Usuario> CargarUsuarios (String archivo_usuarios) throws FileNotFoundException{
		LinkedList<Usuario> usuarios = new LinkedList<Usuario>();
		Scanner sc = new Scanner( new File(archivo_usuarios));
		sc.nextLine(); //Para omitir las cabeceras
		while (sc.hasNextLine()) {
			usuarios.add(CreaUsuariosDesdeLinea(sc.nextLine()));
		}
		sc.close();
		return usuarios;
	}
	
	private static Usuario CreaUsuariosDesdeLinea(String linea) {
		Usuario usuario=null;
	    Scanner sc = new Scanner(linea);
	    sc.useDelimiter(";");
	    while (sc.hasNext()){
	    	usuario=new Usuario(sc.next(), sc.nextInt(), sc.nextDouble(), sc.next());
	    }
	    sc.close();
	    return usuario;
	}
}
