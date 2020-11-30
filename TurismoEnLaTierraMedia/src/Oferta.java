
public class Oferta{
	private String nombre;
	private boolean paquete;
	private String descripcion;
	private double tiempo;
	private int precio;
	//private int id;
	public Oferta(String nombre,boolean paquete, String descripcion, int precio, double tiempo) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.tiempo = tiempo;
		this.precio = precio;
		this.paquete= paquete;
		//this.id=id;
	}
	public Oferta() {
		// TODO Auto-generated constructor stub
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

	public void setPaquete(boolean paquete) {
		this.paquete = paquete;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return ((Oferta) obj).getNombre().equals(this.nombre);
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Producto: "+this.nombre+ " Descripcion: "+this.descripcion+" Precio: "+this.precio+" Monedas Tiempo de recorrido: "+this.tiempo;
	}
//	@Override
//	public boolean equals(Object obj) {
//		return ((Oferta) obj).getNombre().equals(this.nombre);
//	}
	
	public static Oferta vacia() {
		return new Oferta("Vacia",false,"",0,0.0);
	}
	
	public boolean isPaquete() {
		return this.paquete;
	}
	
	
}
