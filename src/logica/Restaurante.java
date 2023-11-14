package logica;

import java.util.ArrayList;
import java.util.Scanner;

public class Restaurante {

	private String nombre;
	private String calle;
	private String localidad;
	private ArrayList<Mesa> listaMesas;
	private ArrayList<Reserva> listaReservas;
	
	public Restaurante() {
		Procesos();
		listaMesas=new ArrayList<Mesa>();
		listaReservas=new ArrayList<Reserva>();
		crearMesas();
	}
	
	public Restaurante(String nombre, String calle, String localidad) {
		this.nombre=nombre;
		this.calle=calle;
		this.localidad=localidad;
		listaMesas=new ArrayList<Mesa>();
		listaReservas=new ArrayList<Reserva>();
		crearMesas();
	}

	public void Procesos() {
		Scanner ingresoDatos = new Scanner(System.in);
		
		System.out.println("Ingrese nombre del restaurante.");
		nombre=ingresoDatos.nextLine();
		System.out.println("Ingrese calle.");
		calle=ingresoDatos.nextLine();
		System.out.println("Ingrese localidad.");
		localidad=ingresoDatos.nextLine();	
	}
	
	private void crearMesas() {
	    int[] capacidad = {2, 4, 6};
	    int[] cantidad = {5, 5, 3};

	    for (int j = 0; j<capacidad.length; j++) {
	        for (int i = 0; i<cantidad[j]; i++) {
	            Mesa nuevaMesa = new Mesa(capacidad[j]);
	            nuevaMesa.setEstado(new Liberada());
	            listaMesas.add(nuevaMesa);
	        }
	    }
	}
	
	public void mostrarMesas() throws ExceptionEstado {
		for(Mesa m: listaMesas) {
			m.mostrarMesa();
		}
	}
	
	public void mostrarMesaEsp() {
		Scanner ingresoDatos = new Scanner(System.in);
		System.out.println("Ingrese estado de mesa que desea buscar. Opciones: Liberada / Ocupada / Reservada");
		String datoIngresado = ingresoDatos.nextLine().toLowerCase().replaceAll("[0-9\\p{Punct}]", "");
		switch(datoIngresado) {
		case"liberada":
			for(Mesa m:listaMesas) {
				
			}
		case"ocupada":
		case"reservada":
		}	
	}
	
	public void conteoMesas() {
		int capTotal=0;
		
		for(Mesa m:listaMesas) {
			capTotal= capTotal + m.getCapacidad();
		}
		System.out.println("Capacidad total: "+capTotal);
	}
	
	public void altaMesa() {
		Mesa nuevaM = new Mesa();
		listaMesas.add(nuevaM);
	}
	
	public void bajaMesa() {
		Scanner ingresoDatos = new Scanner(System.in);
		boolean mesaEncontrada=false;
		System.out.println("Ingrese numero de mesa que desea borrar.");
		int numeroIng = ingresoDatos.nextInt();
		for (Mesa m: listaMesas) {
			if(m.getNroMesa()==numeroIng) {
				mesaEncontrada= true;
				listaMesas.remove(numeroIng);
			}else {
			
			}
		}	
	}
	
	public void mostrarMenu(int datoIng) {
		System.out.println("Menu:");
		System.out.println("1-Mostrar reservas.");
		System.out.println("2-Mostrar mesas.");
		System.out.println("3-Mostrar mesas segun filtros");
		System.out.println("3-Crear reserva.");
		System.out.println("4-Crear mesa.");
		System.out.println("5-Modificar estado de una mesa.");
		System.out.println("6-Eliminar mesa.");
		
		
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public ArrayList<Mesa> getListaMesas() {
		return listaMesas;
	}

	public void setListaMesas(ArrayList<Mesa> listaMesas) {
		this.listaMesas = listaMesas;
	}

	public ArrayList<Reserva> getListaReservas() {
		return listaReservas;
	}

	public void setListaReservas(ArrayList<Reserva> listaReservas) {
		this.listaReservas = listaReservas;
	}
}