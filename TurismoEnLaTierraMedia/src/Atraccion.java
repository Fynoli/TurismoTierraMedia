
public class Atraccion extends Producto implements Comparable<Atraccion>{
	
	private String tipo_atraccion;
	private int cupo_diario;
	
	
	
	public Atraccion(String nombre, int costo, double tiempo_de_recorrido,int cupo_diario,String tipo_atraccion) {
		super(nombre, costo,tiempo_de_recorrido);
		this.tipo_atraccion=tipo_atraccion;
		this.cupo_diario=cupo_diario;
		// TODO Auto-generated constructor stub
	}


	// supongo que habria que ir restando los cupos disponibles del dia 
	// a medida que la gente vaya usando la atraccion
	
	public void ocuparCupo(){
		this.cupo_diario --;
	}
	
	public boolean tieneCupo() {
		return this.cupo_diario>0;
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


	@Override
	public String toString() {
		return "Araccion: "+getNombre()+"  Costo: "+getCosto()+" Monedas"+"  Tiempo: "+this.getTiempo()+" horas"+"  Cupo: "+cupo_diario+"  Tipo: "+tipo_atraccion;
	}


	@Override
	public int compareTo(Atraccion o) {
		if(this.getCosto()>o.getCosto()) {
			return 1;
		}
		if(o.getCosto()>this.getCosto()) {
			return -1;
		}
		if(this.getTiempo()>o.getTiempo()) {
			return 1;
		}
		if(this.getTiempo()<o.getTiempo()) {
			return -1;
		}
		return 0;
	}
	
	
	
	

}
