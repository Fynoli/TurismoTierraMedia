
public class Atraccion extends Producto{
	
	private String tipo_atraccion;
	private int cupo_diario;
	private double tiempo_de_recorrido;
	
	
	
	public Atraccion(String nombre, int costo, double tiempo_de_recorrido,int cupo_diario,String tipo_atraccion) {
		super(nombre, costo);
		this.tipo_atraccion=tipo_atraccion;
		this.cupo_diario=cupo_diario;
		this.tiempo_de_recorrido=tiempo_de_recorrido;
		// TODO Auto-generated constructor stub
	}


	// supongo que habria que ir restando los cupos disponibles del dia 
	// a medida que la gente vaya usando la atraccion
	
	public void ocuparCupo(){
		this.cupo_diario -= 1;
	}
	
	//Getters and setters
	public String getTipo_atraccion() {
		return tipo_atraccion;
	}



	public void setTipo_atraccion(String tipo_atraccion) {
		this.tipo_atraccion = tipo_atraccion;
	}



	public int getCupo_diario() {
		return cupo_diario;
	}



	public void setCupo_diario(int cupo_diario) {
		this.cupo_diario = cupo_diario;
	}


	public double getTiempo_de_recorrido() {
		return tiempo_de_recorrido;
	}


	public void setTiempo_de_recorrido(double tiempo_de_recorrido) {
		this.tiempo_de_recorrido = tiempo_de_recorrido;
	}


	@Override
	public String toString() {
		return "Araccion: "+getNombre()+"  Costo: "+getCosto()+" Monedas"+"  Tiempo: "+tiempo_de_recorrido+" horas"+"  Cupo: "+cupo_diario+"  Tipo: "+tipo_atraccion;
	}
	
	
	
	

}
