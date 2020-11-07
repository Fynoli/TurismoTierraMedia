import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

public class Gestor {
	EntradaSalida dataBase;
	private LinkedList<Producto> rechazados;
	
	
	public Gestor(EntradaSalida dataBase) {
		super();
		this.dataBase = dataBase;
		rechazados=new LinkedList<Producto>();
	}

	public Producto GenerarSugerencia(Usuario usuario) {
		
		Producto sugerencia;
		
		sugerencia=buscarProducto(usuario,true);
		if(sugerencia==null) {
			sugerencia=buscarProducto(usuario,false);
		}
		
		return sugerencia;
	}
	
	private Producto buscarProducto( Usuario usuario, boolean conPreferencia) {
		//Primero paquetes(Primero preferencias, luego mas caros, luego mas tiempo) luego atracciones
		
				//Buscamos un paquete que tenga una atraccion que el usuario prefiera
				for(Paquete producto: dataBase.getPaquetes()) {//Recorremos paquetes
					for(Atraccion atraccion: (producto).getAtracciones()) {
						if(conPreferencia) {
							if(atraccion.getTipo_atraccion().equalsIgnoreCase(usuario.getAtraccion_fav())) {//hay una que le gusta en el paquete
								if(!usuario.getItinerario().contains(producto) && !rechazados.contains(producto)) {//no lo compro ni lo rechazo
									if(usuario.puedeComprar(producto)) {
										return producto;
									}
								}
							}
						}
						else {
							if(!usuario.getItinerario().contains(producto) && !rechazados.contains(producto)) {//no lo compro ni lo rechazo
								if(usuario.puedeComprar(producto)) {
									return producto;
								}
							}
						}
						
					}
				}
				//Buscamos la atraccion mas cara de las que coinciden con su preferencia
				LinkedList<Atraccion> atracciones=new LinkedList<Atraccion>();
				for(Atraccion atraccion: dataBase.getAtracciones()) {
					if(conPreferencia) {
						if(atraccion.getTipo_atraccion().equalsIgnoreCase(usuario.getAtraccion_fav())) {//la atraccion le gusta
							if(!usuario.getItinerario().contains(atraccion) && !rechazados.contains(atraccion)) {//no la tiene en su itinerario ni tampoco la rechazo
								if(usuario.puedeComprar(atraccion)) {//tiene tiempo y dinero
									atracciones.add(atraccion);
								}
							}
						}
					}
					else {
						if(!usuario.getItinerario().contains(atraccion) && !rechazados.contains(atraccion)) {//no la tiene en su itinerario ni tampoco la rechazo
							if(usuario.puedeComprar(atraccion)) {//tiene tiempo y dinero
								atracciones.add(atraccion);
							}
						}
					}
				}
				if(!atracciones.isEmpty()) {
					return atracciones.getFirst();
				}
				
				return null;
	}
	
	public void ImprimirItinerario(Usuario usuario) {
		FileWriter archivo = null;
        PrintWriter pw = null;
        try
        {
            archivo = new FileWriter("itinerario de "+usuario.getNombre()+".txt");
            pw = new PrintWriter(archivo);
            
            pw.println("Cliente: "+usuario.getNombre()+"\n");
            pw.println("Itinerario: ");

            for(Producto producto:usuario.getItinerario()) {
            	if(producto instanceof Paquete) {
            		pw.println(((Paquete) producto).toString());
            	}
            	else {
            		pw.println(((Atraccion) producto).toString());
            	}
            }
            
            pw.println("\nCosto total: "+usuario.costoItinerario()+" Monedas        Tiempo total: "+usuario.tiempoItinerario()+" Horas");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != archivo)
        	   archivo.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
	}

	public LinkedList<Producto> getRechazados() {
		return rechazados;
	}

	public void setRechazados(LinkedList<Producto> rechazados) {
		this.rechazados = rechazados;
	}
	
	
	
}
