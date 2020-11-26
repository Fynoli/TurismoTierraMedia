
public class Oferta {
	private String nombre;
	private String descripcion;
	private double tiempo;
	private int precio;
	public Oferta(String nombre, String descripcion, double tiempo, int precio) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.tiempo = tiempo;
		this.precio = precio;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public double getTiempo() {
		return tiempo;
	}
	public void setTiempo(double tiempo) {
		this.tiempo = tiempo;
	}
	public int getPrecio() {
		return precio;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Producto: "+this.nombre+ " Descripcion: "+this.descripcion+" Precio: "+this.precio+" Monedas Tiempo de recorrido: "+this.tiempo;
	}
	
	
}
