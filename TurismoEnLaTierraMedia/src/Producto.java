
public abstract class Producto {
	private String nombre;
	private int costo;

	
	public Producto(String nombre, int costo) {
		super();
		this.nombre = nombre;
		this.costo = costo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCosto() {
		return costo;
	}

	public void setCosto(int costo) {
		this.costo = costo;
	}
	
	
	

}
