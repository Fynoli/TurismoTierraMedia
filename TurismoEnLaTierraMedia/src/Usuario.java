import java.util.*;


public class Usuario {
	String Nombre;
	int Presupuesto;
	double Tiempo_disponible;
	int Atraccion_fav;
	private LinkedList<String> Itinerario;
	

	// el tema de la atraccion favorita estuve viendo videos sobre enum pero no entiendo como se puede aplicar aca
	// con numeros enteros
	
	
	public Usuario(String nombre, int presupuesto, int atraccion_fav, double tiempo_disponible) {
		this.Nombre = nombre;
		this.Presupuesto = presupuesto;
		this.Tiempo_disponible = tiempo_disponible;
		this.Atraccion_fav = atraccion_fav;
		
	}

	
	
	public void setValores(Atraccion Atraccion) {
		 this.Presupuesto -= Atraccion.getCosto();
		 this.Tiempo_disponible -= Atraccion.getTiempoVisita();
	}
	
	
	
	

	
	public void crearItinerario(String lugar) {
		
		
		LinkedList <String> Itinerario = new LinkedList<String>();
			
		
		Itinerario.add(lugar);
		
		
		// este codigo es innecesario pero lo hice para chequear que se esten agregando
		// a la lista, recorre cada elemento y lo va imprimiendo
		
		for (String ubicacion : Itinerario) {
			System.out.println(ubicacion);
			
		}
		
		
		
		
	}
	
	
	
	// cuando finalice el recorrido sacamos todas las atracciones de su itinerario
	
		public void finalizarRecorrido() {
		
		
		LinkedList <String> Itinerario = new LinkedList<String>();
		
		// recorre cada una de las atracciones y las va eliminando
		
		for (String ubicacion : Itinerario) {
			
			Itinerario.remove(ubicacion);
		}
		
		System.out.print("lo que quedo es: " + Itinerario);
		
	}
	
	
	
	
	
	
	
	
	
}
