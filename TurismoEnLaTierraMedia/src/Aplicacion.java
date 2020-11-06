import java.io.FileNotFoundException;
import java.util.LinkedList;

public class Aplicacion {

	public static void main(String[] args) throws FileNotFoundException{
		
		
		
		/*// en principio todo el codigo que van a ver ahora solo lo hice para mas o menos ir dandole forma
		// a lo que vamos a hacer y darnos una idea de como podemos hacerlo, tambien para testear que todo vaya funcionando 
		// obviamente esto se puede mejorar y una vez mejorado lo vamos a aplicar a las demas clases 
		
		// lo que van a ver ahora es para testear el metodo crearItinerario();
		
		// lo que hice aca fue una prueba, cree un usuario con sus atributos
		
		
		Usuario Pedro = new Usuario("pedro", 50, 2, 5.0);
		
		
		
		// despues cree un array con atracciones, en el programa real este array deberia ser creado por 
		// el gestor una vez que el usuario haya aceptado o no los distintos paquetes
		
		Atraccion arr[] = {new Atraccion("Moria", 15, 2.5, 0, 2), new Atraccion("Minas Tirith", 20, 1.5, 0, 1),
		new Atraccion("La Comarca", 10, 3, 0, 2), new Atraccion("Mordor", 15, 1, 0, 3)};
		
		
		
		// una vez que tengo el array con las atracciones que va a concurrir el usuario, las agrego a su itinerario
		// hago un for que recorra el array y uso el metodo crearItinerario() para pasarle cada uno de los nombres
		// de los lugares que va a concurrir
		
		
		for(int i=0; i < arr.length; i++) {
			
			System.out.println(arr[i].getName());
			Pedro.crearItinerario(arr[i].getName());
		}
		

		// aca hice dos cosas, primero cree un print para comprobar que efectivamente este recorriendo todos los elementes del array
		// y despues dentro del metodo crearItinerario() agregue un print para comprobar que se esten agregado los lugares
		// a su itinerario
		
		
		
		
		// agregue strings a la lista porque en realidad el ejercicio no pide que sea una lista de objetos y creo que es medio al pedo
		// hacerlo asi 
		
		
		Pedro.finalizarRecorrido();
		
		// saca todas las atracciones de su itinerario y le puse un print para ver que haya sacado todo
		
		
		// voy a dejar todos estos prints que puse asi ustedes tambien pueden ver que todo esto funciona asi no se gastan
		// testeando, despues cuando todos los vean los borramos obvio*/
		
		LinkedList<Atraccion> atracciones=new LinkedList<Atraccion>();
		atracciones=EntradaSalida.CargarAtracciones("atracciones.csv");
		
		for(Atraccion atraccion : atracciones) {
			System.out.println(atraccion);
		}
		
		System.out.println("-------------------------------------------------------");
		
		LinkedList<Usuario> usuarios=new LinkedList<Usuario>();
		usuarios=EntradaSalida.CargarUsuarios("usuarios.csv");
		
		for(Usuario usuario : usuarios) {
			System.out.println(usuario);
		}
		
		
	}

}
