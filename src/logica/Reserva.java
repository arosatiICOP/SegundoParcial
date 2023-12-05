package logica;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Reserva {

	private Date fecha;
	private static int idReserva=1;
	private int nroReserva;
	private String nombre;
	private String apellido;
	private int cantCom;
	private int idResto;
	private int idMesa;
	private Mesa mesa;
	
	
	public Reserva() {
		/* Sacarle el comentario a procesos si quiere probar crear una reserva en el menu de la clase "Principal"
		 , luego volver a comentar procesos para que funcione la opcion
		"registrar" del submenu "reserva" de la interfaz "Menu"*/
		nroReserva=idReserva++;
		//procesos();
	}
	
	public  void procesos() {
		nroReserva=idReserva++;
		Scanner ingresoDatos = new Scanner(System.in);
		
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd MM yyyy");
        System.out.print("Ingrese la fecha (Formato: dd MM yyyy)");
        String fechaIngresada = ingresoDatos.nextLine();
        try {
            Date fecha = formatoFecha.parse(fechaIngresada);
            System.out.println("Fecha ingresada: " + formatoFecha.format(fecha));
            this.fecha=fecha;
        } catch (ParseException e) {
        	System.out.println("Error al analizar la fecha. Ingresala en el formato pedido.");
        	e.printStackTrace();
        }	
		System.out.println("Ingrese nombre del cliente.");
		nombre=ingresoDatos.nextLine();
		System.out.println("Ingrese apellido.");
		apellido=ingresoDatos.nextLine();
		System.out.println("Ingrese cantidad de comensales.");
		cantCom=ingresoDatos.nextInt();
		//mesa= new Mesa();
	}
	
	public Reserva(Date fecha, String nombre, String apellido, int cantCom, Mesa mesa) {
		nroReserva=idReserva++;
	    this.fecha = fecha;
	    this.nombre = nombre;
	    this.apellido = apellido;
	    this.cantCom = cantCom;
	    this.mesa = mesa;
	}
	
	public void mostrarReserva() {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

        System.out.println("Información de la Reserva:");
        System.out.println("Fecha: " + formatoFecha.format(fecha));
        System.out.println("Nombre: " + nombre);
        System.out.println("Apellido: " + apellido);
        System.out.println("Cantidad de comensales: " + cantCom);

        if (mesa != null) {
            System.out.println("Número de Mesa: " + mesa.getNroMesa()+"\n");
        } else {
            System.out.println("Mesa: No asignada"+"\n");
        }
    }

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getCantCom() {
		return cantCom;
	}

	public void setCantCom(int cantCom) {
		this.cantCom = cantCom;
	}

	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}

	public int getIdResto() {
		return idResto;
	}

	public void setIdResto(int idResto) {
		this.idResto = idResto;
	}

	public int getIdMesa() {
		return idMesa;
	}

	public void setIdMesa(int idMesa) {
		this.idMesa = idMesa;
	}

	public static int getIdReserva() {
		return idReserva;
	}

	public static void setIdReserva(int idReserva) {
		Reserva.idReserva = idReserva;
	}

	public int getNroReserva() {
		return nroReserva;
	}

	public void setNroReserva(int nroReserva) {
		this.nroReserva = nroReserva;
	}	
}