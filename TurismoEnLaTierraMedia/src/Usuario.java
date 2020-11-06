import java.util.*;


public class Usuario {
	private String nombre;
	private int presupuesto;
	private double tiempo_disponible;
	private enum atraccion_fav { PAISAJE, AVENTURA, DEGUSTACION };
	private LinkedList<String> itinerario;
	

	// el tema de la atraccion favorita estuve viendo videos sobre enum pero no entiendo como se puede aplicar aca
	// con numeros enteros
	// no necesariamente se usa con enteros, entras al dato y te salen esas tres opciones (flor)
	// no se si lo van a aplicar pero los profes recomentdaron utilizar Lower Camel Case en los nombres de variables (flor)
	
	
	public Usuario(String nombre, int presupuesto, double tiempo_disponible, enum atraccion_fav) {
		this.nombre = nombre;
		this.presupuesto = presupuesto;
		this.tiempo_disponible = tiempo_disponible;
		this.atraccion_fav = atraccion_fav;
		
	}

	
	
	/*public void setValores(Atraccion atraccion) {
		 this.presupuesto -= Atraccion.getCosto();
		 this.tiempo_disponible -= Atraccion.getTiempoVisita();
	}*/
	
	
	
	

	
	public void crearitinerario(String lugar) {
		
		
		LinkedList <String> itinerario = new LinkedList<String>();
			
		
		itinerario.add(lugar);
		
		
		// este codigo es innecesario pero lo hice para chequear que se esten agregando
		// a la lista, recorre cada elemento y lo va imprimiendo
		
		for (String ubicacion : itinerario) {
			System.out.println(ubicacion);
			
		}
		
		
		
		
	}
	
	
	
	// cuando finalice el recorrido sacamos todas las atracciones de su itinerario
	
		public void finalizarRecorrido() {
		
		
		LinkedList <String> itinerario = new LinkedList<String>();
		
		// recorre cada una de las atracciones y las va eliminando
		
		for (String ubicacion : itinerario) {
			
			itinerario.remove(ubicacion);
		}
		
		System.out.print("lo que quedo es: " + itinerario);
		
		
		
	}



		@Override
		public String toString() {
			return "Usuario: "+nombre+"  Presupuesto: "+presupuesto+" monedas"+"  Tiempo disponible: "+tiempo_disponible+" horas"+"  Atraccion favorita: "+atraccion_fav;
		}
	
	
	
	
	
	
	
	
	
}
