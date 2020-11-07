import java.io.FileNotFoundException;
import java.io.File;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

public class EntradaSalida {
	
	
	private LinkedList<Atraccion> atracciones;
	private LinkedList<Usuario> usuarios;
	private LinkedList<Paquete> paquetes;
	
	
	public EntradaSalida() 
	{
		
	}

	
	public void cargarArchivos() throws FileNotFoundException {
		
		// rellena las listas con los archivos. manejate flor
		this.atracciones = cargarAtracciones("atracciones.csv");
		this.usuarios = cargarUsuarios("usuarios.csv");
		this.paquetes = cargarPaquetes("paquetes.csv");
	}
	

	
	
	private LinkedList<Atraccion> cargarAtracciones (String archivo_atracciones) throws FileNotFoundException{
		LinkedList<Atraccion> atracciones = new LinkedList<Atraccion>();
		Scanner sc = new Scanner( new File(archivo_atracciones));
		sc.nextLine(); //Para omitir las cabeceras
		while (sc.hasNextLine()) {
			atracciones.add(CreaAtraccionDesdeLinea(sc.nextLine()));
		}
		sc.close();
		Collections.sort(atracciones,Collections.reverseOrder());
		return atracciones;
	}
	
	private Atraccion CreaAtraccionDesdeLinea(String linea) {
		Atraccion atraccion=null;
	    Scanner sc = new Scanner(linea);
	    sc.useDelimiter(";");
	    while (sc.hasNext()){
	    	atraccion=new Atraccion(sc.next(),sc.nextInt(),sc.nextDouble(),sc.nextInt(),sc.next());
	    }
	    sc.close();
	    return atraccion;
	}
	
	
	private LinkedList<Usuario> cargarUsuarios (String archivo_usuarios) throws FileNotFoundException{
		LinkedList<Usuario> usuarios = new LinkedList<Usuario>();
		Scanner sc = new Scanner( new File(archivo_usuarios));
		sc.nextLine(); //Para omitir las cabeceras
		while (sc.hasNextLine()) {
			usuarios.add(CreaUsuariosDesdeLinea(sc.nextLine()));
		}
		sc.close();
		return usuarios;
	}
	
	private Usuario CreaUsuariosDesdeLinea(String linea) {
		Usuario usuario=null;
	    Scanner sc = new Scanner(linea);
	    sc.useDelimiter(";");
	    while (sc.hasNext()){
	    	usuario=new Usuario(sc.next(), sc.nextInt(), sc.nextDouble(), sc.next());
	    }
	    sc.close();
	    return usuario;
	}
	
	
	
	
	
	// leer el archivo donde ponemos los paquetes 
	// lee una lista de strings 
	
	private LinkedList<Paquete> cargarPaquetes (String archivo_paquetes) throws FileNotFoundException{
		LinkedList<Paquete> paquetes = new LinkedList<Paquete>();
		
		Scanner sc = new Scanner( new File(archivo_paquetes));
		
		sc.nextLine(); //Para omitir las cabeceras
		
		while (sc.hasNextLine()) {
			paquetes.add(CreaPaqueteDesdeLinea(sc.nextLine()));
		}
		sc.close();
		return paquetes;
	}
	
	
	
	
	
	private Paquete CreaPaqueteDesdeLinea(String linea) {
		Paquete paquete=null;

		
	    Scanner sc = new Scanner(linea);
	    sc.useDelimiter(";");
	    while (sc.hasNext()) { 	
	    	paquete=new Paquete(sc.next(), sc.nextInt(), sc.next(),sc.next(), leerAtraccionesDelPaquete(sc.next()));
	    }
	    sc.close();
	    return paquete;
	}
	
	
	
	
	
	
	// lee las atracciones desde el paquete 
	private LinkedList<Atraccion> leerAtraccionesDelPaquete(String linea) {
		LinkedList<Atraccion> atracciones = new LinkedList<Atraccion>();
		
	    Scanner sc = new Scanner(linea);
	    sc.useDelimiter(",");
	    while (sc.hasNext()){
	    	String nombreDeAtraccion = sc.next();
	    	for (Atraccion atraccion : this.atracciones) {
	    		
	    		// cada vez que encuentre en el archivo una atraccion que coincida 
	    		// con una que tenemos la agrega, de lo contrario no hace nada
	    		// cuando no encuentra mas coma, se termina
	    		if (atraccion.getNombre().equals(nombreDeAtraccion) ) {
	    			atracciones.add(atraccion);
	    		}
	    		
	    	}
	    	
	   
	    }
	    sc.close();
	    return atracciones;
	}


	public LinkedList<Atraccion> getAtracciones() {
		return atracciones;
	}


	public LinkedList<Usuario> getUsuarios() {
		return usuarios;
	}


	public LinkedList<Paquete> getPaquetes() {
		return paquetes;
	}
	
	
	
	
}
