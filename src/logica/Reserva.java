package logica;

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
		Procesos();
	}
	
	public  void Procesos() {
		Scanner ingresoDatos = new Scanner(System.in);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.print("Ingrese la fecha: ");
        String fechaIngresada = ingresoDatos.nextLine();
        try {
            fecha = sdf.parse(fechaIngresada);
            System.out.println("Fecha ingresada y guardada: " + fecha);
        } catch (Exception e) {
            System.out.println("Error al analizar la fecha. Ingresala en el formato pedido.");
        }
		System.out.println("Ingrese nombre del cliente.");
		nombre=ingresoDatos.nextLine();
		System.out.println("Ingrese apellido.");
		apellido=ingresoDatos.nextLine();
		System.out.println("Ingrese cantidad de comenzales.");
		cantCom=ingresoDatos.nextInt();
	}
	
	public Reserva(Date fecha, String nombre, String apellido, int cantCom, Mesa mesa) {
		this.fecha=fecha;
		this.nombre=nombre;
		this.apellido=apellido;
		this.cantCom=cantCom;
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