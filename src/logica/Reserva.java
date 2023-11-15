package logica;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Reserva {

	private Date fecha;
	private String nombre;
	private String apellido;
	private int cantCom;
	private Mesa mesa;
	
	public Reserva() {
		procesos();
	}
	
	public  void procesos() {
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
}