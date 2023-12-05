package logica;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JOptionPane;

import baseDatos.MesaBD;
import interfaz.SelectResto;

import java.util.Iterator;

public class Restaurante {
	
	private static int idRestaurante=1;
	private int nroRestaurante;
	private String nombre;
	private String calle;
	private String localidad;
	private ArrayList<Mesa> listaMesas;
	private ArrayList<Reserva> listaReservas;
	private MesaBD mesaDB;
	//private boolean sePulsoNo = false;
	
	public Restaurante() {
		Procesos();
		nroRestaurante=idRestaurante++;
		listaMesas=new ArrayList<Mesa>();
		listaReservas=new ArrayList<Reserva>();
		
	}
	
	public Restaurante(String nombre, String calle, String localidad){
		this.nombre=nombre;
		this.calle=calle;
		this.localidad=localidad;
		nroRestaurante=idRestaurante++;
		listaMesas=new ArrayList<Mesa>();
		listaReservas=new ArrayList<Reserva>();
		
	}
	
	public Restaurante(int nroResto, String nombre, String calle, String localidad){
		this.nombre=nombre;
		this.calle=calle;
		this.localidad=localidad;
		this.nroRestaurante=nroResto;
		listaMesas=new ArrayList<Mesa>();
		listaReservas=new ArrayList<Reserva>();
		
	}

	public void Procesos() {
		Scanner ingresoDatos = new Scanner(System.in);
		
		System.out.println("Ingrese nombre del restaurante.");
		nombre=ingresoDatos.nextLine().toLowerCase().replaceAll("[0-9\\p{Punct}]", "");
		System.out.println("Ingrese calle.");
		calle = ingresoDatos.nextLine().toLowerCase().replaceAll("[^a-zA-Z0-9 ]", "");
		System.out.println("Ingrese localidad.");
		localidad=ingresoDatos.nextLine().toLowerCase().replaceAll("[0-9\\p{Punct}]", "");
	}
	/*  noooooo
	private void crearMesas(){

	    int[] cantidad = {4, 4, 3};
	    int[] capacidad = {2, 4, 6};

	    for (int j = 0; j < cantidad.length; j++) {
	        for (int i = 0; i < cantidad[j]; i++) {
	            Mesa m = new Mesa(capacidad[j]);
	            listaMesas.add(m);
	        }
	    }
	}
	*/
	
	public void crearMesas(int[] cantidades, int[] capacidades) {
	    for (int i = 0; i < cantidades.length; i++) {
	        for (int j = 0; j < cantidades[i]; j++) {
	            Mesa mesa = new Mesa(capacidades[i]);
	            this.listaMesas.add(mesa);
	        }
	    }
	}
	
	public void crearMesas(int cantidades, int capacidades, int idResto) throws SQLException {
	    for (int i = 1; i <= cantidades; i++) {
	            this.listaMesas.add(new Mesa(capacidades));
	        }
	    for (int i = 1; i<= cantidades; i++) {
	    	mesaDB.guardarMesas(capacidades, idResto);
	    }
	    }


	public void mostrarMesas() throws ExceptionEstado {
		for(Mesa m: listaMesas) {
			m.mostrarMesa();
		}
	}
	
	public void mostrarReservas() {
		for(Reserva r: listaReservas) {
			r.mostrarReserva();
		}
	}
	
	public void mostrarMesaEsp() {
		Scanner ingresoDatos = new Scanner(System.in);
		System.out.println("Ingrese estado de mesa que desea buscar. Opciones: Liberada / Ocupada / Reservada");
		String datoIngresado = ingresoDatos.nextLine().toLowerCase().replaceAll("[0-9\\p{Punct}]", "");
		switch (datoIngresado) {
        case "liberada":
            this.mostrarMesasLiberadas();
            break;
        case "ocupada":
        	this.mostrarMesasOcupadas();
        case "reservada":
            this.mostrarMesasReservadas();
            break;
        default:
            System.out.println("Opción no válida.");
            break;
		}
	}
	
	/*public void mostrarMesaEstFecha() {
	        Scanner ingresoDatos = new Scanner(System.in);
	        System.out.println("Ingrese estado de mesa que desea buscar. Opciones: Liberada / Ocupada / Reservada");
	        String estadoIngresado = ingresoDatos.nextLine().toLowerCase().replaceAll("[0-9\\p{Punct}]", "");

	        System.out.print("Ingrese la fecha (Formato: dd MM yyyy): ");
	        String fechaIngresada = ingresoDatos.nextLine();

	        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd MM yyyy");
	        Date fecha;

	        try {
	            fecha = formatoFecha.parse(fechaIngresada);
	        } catch (ParseException e) {
	            System.out.println("Error al analizar la fecha. Ingresala en el formato pedido.");
	            return;
	        }

	        switch (estadoIngresado) {
	            case "liberada":
	                mostrarMesasLibres(listaReservas, fecha);
	                break;
	            case "ocupada":
	                mostrarMesasOcupadas(listaReservas, fecha);
	                break;
	            case "reservada":
	                mostrarMesasReservadas(listaReservas, fecha);
	                break;
	            default:
	                System.out.println("Opción no válida.");
	                break;
	        }
	    }*/
	
	public void mostrarMesasLiberadas() {
		//this.listaMesas=listaMesas;
	    System.out.println("Mesas Liberadas:");
	    for (Mesa mesa : this.listaMesas) {
	        try {
	            if (mesa.getEstado() instanceof Liberada) {
	                mesa.mostrarMesa();
	            }
	        } catch (ExceptionEstado e) {
	            System.out.println("No hay mesas liberadas. Error: " + e.getMessage());
	        }
	    }
	}
	
	private void mostrarMesasReservadas() {
		//this.listaMesas=listaMesas;
	    System.out.println("Mesas Reservadas:");
	    for (Mesa mesa : this.listaMesas) {
	        try {
	            if (mesa.getEstado() instanceof Reservada) {
	                mesa.mostrarMesa();
	            }
	        } catch (ExceptionEstado e) {
	            System.out.println("No hay mesas reservadas. Error: " + e.getMessage());
	        }
	    }
	}
	
	private void mostrarMesasOcupadas() {
		//this.listaMesas=listaMesas;
	    System.out.println("Mesas Ocupadas:");
	    for (Mesa mesa : listaMesas) {
	        try {
	            if (mesa.getEstado() instanceof Ocupada) {
	                mesa.mostrarMesa();
	            }
	        } catch (ExceptionEstado e) {
	            System.out.println("No hay mesas ocupadas. Error: " + e.getMessage());
	        }
	    }
	}
	
	public void conteoMesas() {
	    int capTotal = 0;
	    int cantMesasL = 0;
	    int cantMesasO = 0;
	    int cantMesasR = 0;

	    for (Mesa m : listaMesas) {
	        capTotal += m.getCapacidad();

	        if (m.getEstado() instanceof Liberada) {
	            cantMesasL++;
	        } else if (m.getEstado() instanceof Ocupada) {
	            cantMesasO++;
	        } else if (m.getEstado() instanceof Reservada) {
	        	cantMesasR++;
	        }
	    }
	    System.out.println("Capacidad total: " + capTotal);
	    System.out.println("Cantidad de mesas liberadas: " + cantMesasL);
	    System.out.println("Cantidad de mesas ocupadas: " + cantMesasO);
	    System.out.println("Cantidad de mesas reservadas: " + cantMesasR);
	}
	
	public void altaMesa() {

		Mesa nuevaM = new Mesa();
		listaMesas.add(nuevaM);
	}
	
	public void altaReserva() {
	    Scanner ingresoDatos = new Scanner(System.in);
	    Reserva nuevaR = new Reserva();
	    listaReservas.add(nuevaR);
	    this.mostrarMesasLiberadas(); 
	    System.out.println("Ingrese numero de la mesa deseada.");
	    int datoIng = ingresoDatos.nextInt();

	    Mesa mesaReservada = buscarMesaPorNumero(datoIng);

	    if (mesaReservada != null) {
	        nuevaR.setMesa(mesaReservada);
	        mesaReservada.setEstado(new Reservada());
	    } else {
	        System.out.println("No se encontró la mesa con el número ingresado.");
	    }
	}
	
    public Mesa buscarMesaPorNumero(int numeroMesa) {
        for (Mesa m : listaMesas) {
            if (m.getNroMesa() == numeroMesa) {
                return m;
            }
        }
        return null;
    }

    public void bajaMesa() {
        Scanner ingresoDatos = new Scanner(System.in);
        System.out.println("Ingrese número de mesa que desea borrar.");
        int numeroIng = ingresoDatos.nextInt();
        
        // Usar un iterador para recorrer la lista y eliminar la mesa
        Iterator<Mesa> iterator = listaMesas.iterator();
        boolean mesaEncontrada = false;

        while (iterator.hasNext()) {
            Mesa mesa = iterator.next();
            if (mesa.getNroMesa() == numeroIng) {
                mesaEncontrada = true;
                iterator.remove(); // Eliminar la mesa de la lista de manera segura
                System.out.println("Mesa eliminada exitosamente.");
                break; // Salir del bucle una vez que se haya eliminado la mesa
            }
        }

        if (!mesaEncontrada) {
            System.out.println("No se encontró la mesa con el número ingresado.");
        }
    }

	
	public void cambiarEstadoMesa() {
	    Scanner scanner = new Scanner(System.in);

	    System.out.print("Ingrese el número de la mesa: ");
	    int numeroMesa = scanner.nextInt();

	    System.out.print("Ingrese el nuevo estado (Liberada/Ocupada/Reservada): ");
	    String nuevoEstadoStr = scanner.next().toLowerCase();

	    System.out.println("Ingrese consumo de la mesa.");
	    int ganancia = scanner.nextInt();
	    
	    Estado nuevoEstado = null;

	    switch (nuevoEstadoStr) {
	        case "liberada":
	            nuevoEstado = new Liberada();
	            break;
	        case "ocupada":
	            nuevoEstado = new Ocupada();
	            break;
	        case "reservada":
	            nuevoEstado = new Reservada();
	            break;
	        default:
	            System.out.println("Estado no válido. No se ha cambiado el estado de la mesa.");
	            return;
	    }

	    for (Mesa mesa : listaMesas) {
	        if (mesa.getNroMesa() == numeroMesa) {
	            // Verificar si el nuevo estado es igual al estado actual
	            if (mesa.getEstado().getClass().equals(nuevoEstado.getClass())) {
	                System.out.println("La mesa ya está en ese estado. No se ha cambiado el estado de la mesa.");
	                return;
	            }
	            
	            if (mesa.getEstado() instanceof Ocupada && nuevoEstado instanceof Reservada) {
	                System.out.println("No se puede cambiar una mesa ocupada a reservada.");
	                return;
	            } else if (mesa.getEstado() instanceof Reservada && nuevoEstado instanceof Ocupada) {
	                System.out.println("No se puede cambiar una mesa reservada a ocupada.");
	                return;
	            }

	            try {
	                nuevoEstado.mostrarEstado(mesa);
	                mesa.setEstado(nuevoEstado);
	                System.out.println("Estado de la mesa cambiado con éxito.");
	                mesa.actualizarConsumo(ganancia);
	            } catch (ExceptionEstado e) {
	                System.out.println("Error al cambiar el estado de la mesa: " + e.getMessage());
	            }
	            return;
	        }
	    }
	    System.out.println("No se encontró la mesa con el número especificado.");
	}
	
	public void mostrarRestoFecha() {
	    Scanner ingresoDatos = new Scanner(System.in);
	    System.out.print("Ingrese la fecha (Formato: dd MM yyyy): ");
	    String fechaIngresada = ingresoDatos.nextLine();

	    SimpleDateFormat formatoFecha = new SimpleDateFormat("dd MM yyyy");
	    Date fecha;

	    try {
	        fecha = formatoFecha.parse(fechaIngresada);
	    } catch (ParseException e) {
	        System.out.println("Error al analizar la fecha. Ingresala en el formato pedido.");
	        return;
	    }

	    System.out.println("Información del Restaurante:");

	    for (Reserva reserva : listaReservas) {
	        if (reserva.getFecha().equals(fecha)) {
	            reserva.mostrarReserva();

	            Mesa mesaReserva = reserva.getMesa();

	            if (mesaReserva != null) {
	                // Verificar si la reserva tiene una mesa asignada
	                try {
	                    mesaReserva.mostrarMesa();
	                } catch (ExceptionEstado e) {
	                    System.out.println("Error al mostrar el estado de la mesa: " + e.getMessage());
	                }
	            } else {
	                System.out.println("La reserva no tiene una mesa asignada.");
	            }
	            break;
	        }
	    }
	}
	
	public void mostrarRestaurante() {
	        System.out.println("Información del Restaurante:");
	        System.out.println("Nombre: " + nombre);
	        System.out.println("Calle: " + calle);
	        System.out.println("Localidad: " + localidad+"\n");
	}
	
	public void mostrarMenu() {
		System.out.println("Menu:");
		System.out.println("1-Mostrar reservas.");
		System.out.println("2-Mostrar mesas.");
		System.out.println("3-Mostrar mesas segun estado.");
		System.out.println("4-Mostrar mesas segun estado y fecha");
		System.out.println("5-Mostrar restaurante segun fecha.");
		System.out.println("6-Crear reserva.");
		System.out.println("7-Crear mesa.");
		System.out.println("8-Modificar estado de una mesa.");
		System.out.println("9-Eliminar mesa.");
		System.out.println("10-Conteo de mesas total.");
		System.out.println("0-Salir"+"\n");
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
	
	public void registrarEnBD(Reserva r) {

		listaReservas.add(r);
	}
	
	public void registrarEnBD(Mesa m) {

		listaMesas.add(m);
	}

	public void borrarMesa(int nroMesa) {
		listaMesas.remove(nroMesa);	
	}

	public int getIdRestaurante() {
		return idRestaurante;
	}

	public void setIdRestaurante(int idRestaurante) {
		this.idRestaurante = idRestaurante;
	}

	public int getNroRestaurante() {
		return nroRestaurante;
	}

	public void setNroRestaurante(int nroRestaurante) {
		this.nroRestaurante = nroRestaurante;
	}
}