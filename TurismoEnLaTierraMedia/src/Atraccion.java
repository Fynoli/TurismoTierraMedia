
public class Atraccion {
	
	String Nombre;
	int Costo;
	double Tiempo_visita;
	int Tipo_Atraccion;
	int Cupo_diario;
	
	
	
	
	public Atraccion(String Nombre, int Coste, double tiempo_de_visita, int tipo_de_atraccion, int cupo_de_visitante_diario) {
		
		this.Nombre= Nombre;
		this.Costo = Coste;
		this.Tiempo_visita = tiempo_de_visita;
		this.Tipo_Atraccion = tipo_de_atraccion;
		this.Cupo_diario = cupo_de_visitante_diario;
		
	}
	
	public String getName() {
		return this.Nombre;
		
	}	
	
	
	public int getCosto() {
		return this.Costo;
	
	}
	
	public double getTiempoVisita() {
		return this.Tiempo_visita;
		
	}
	
	
	// supongo que habria que ir restando los cupos disponibles del dia 
	// a medida que la gente vaya usando la atraccion
	
	public void ocuparCupo(){
		this.Cupo_diario -= 1;
	}
	
	
	
	
	
	

}
