import java.util.LinkedList;

public class Paquete extends Producto {
	private LinkedList<Atraccion> atracciones;
	private int tipoPromo;
	private String descPromo;

	public Paquete(String nombre, int tipoPromo, String valorPromo, String descPromo,
			LinkedList<Atraccion> atracciones) {
		super(nombre, 0, 0);
		this.atracciones = atracciones;
		this.descPromo=descPromo;
		this.tipoPromo=tipoPromo;

		int promoCostoTotal=0;
		double tiempo=0;

		for (Atraccion atraccion : atracciones) {
			promoCostoTotal += atraccion.getCosto();
			tiempo+=atraccion.getTiempo();
		}
		switch (tipoPromo) {// 1=Procentual 2=Absoluto 3=Atraccion gratis
		case 1: {
			promoCostoTotal -= promoCostoTotal * (Integer.parseInt(valorPromo) / 100);
			break;
		}
		case 2: {
			promoCostoTotal = Integer.parseInt(valorPromo);
			break;
		}
		case 3: {
			for (Atraccion atraccion : atracciones) {
				if (atraccion.getNombre().equalsIgnoreCase(valorPromo)) {
					promoCostoTotal -= atraccion.getCosto();
				}
			}
			break;
		}

		}

		super.setCosto(promoCostoTotal);
		super.setTiempo(tiempo);

	}
	
	public boolean tieneCupo() {//True si todas las atracciones tienen cupo
		for(Atraccion a: this.getAtracciones()) {
			if(!a.tieneCupo()) {
				return false;
			}
		}
		return true;
	}
	
	

	public LinkedList<Atraccion> getAtracciones() {
		return atracciones;
	}



	public int getTipoPromo() {
		return tipoPromo;
	}



	public String getDescPromo() {
		return descPromo;
	}



	@Override
	public String toString() {
		String nombresAtracciones = "";
		for (Atraccion atraccion : atracciones) {

			nombresAtracciones += atraccion.getNombre();
			if (atracciones.getLast() != atraccion) {
				nombresAtracciones += ", ";
			}
		}
		return "Paquete: " + super.getNombre() + " costo: " + super.getCosto() + " Monedas" + " " + " incluye: "
				+ nombresAtracciones + " Promocion: " + descPromo + " Tiempo Total: " + super.getTiempo() + " Horas";
	}

}
