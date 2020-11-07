
public abstract class Producto {
	private String nombre;
	private int costo;
	private double tiempo;

	
	public Producto(String nombre, int costo,double tiempo) {
		super();
		this.nombre = nombre;
		this.costo = costo;
		this.tiempo=tiempo;
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
	
	

	public double getTiempo() {
		return tiempo;
	}

	public void setTiempo(double tiempo) {
		this.tiempo = tiempo;
	}

	@Override
	public boolean equals(Object obj) {
		return ((Producto) obj).getNombre().equals(this.getNombre());
	}

	
	
	
	

	

}
