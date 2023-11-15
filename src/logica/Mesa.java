package logica;

import java.util.Date;
import java.util.Scanner;

public class Mesa{
	
	private Estado estado;
	private static int idMesa;
	private int nroMesa;
	private int capacidad;
	private double consumo;
	
	public Mesa(){
		this.estado = new Liberada();
		nroMesa=++idMesa;
		procesos();
	}
	
	public Mesa(int capacidad) {
		this.estado = new Liberada();
		nroMesa=++idMesa;
		this.capacidad=capacidad;
		consumo=0;
	}
	
	public void procesos() {
		Scanner ingresoDatos = new Scanner(System.in);
		System.out.println("Ingrese capacidad de la mesa.");
		capacidad=ingresoDatos.nextInt();
		consumo=0;
	}
	
	public void mostrarMesa() throws ExceptionEstado {
		System.out.println("Datos mesa numero: "+nroMesa);
		System.out.println("Estado: ");
		mostrarEstado();
		System.out.println("Capacidad: "+capacidad);
		System.out.println("Consumo total del dia: "+consumo+"\n");
	}
	
	public void actualizarConsumo(int ganancia) {
		consumo = consumo + ganancia;
	}
	
	public void mostrarEstado() throws ExceptionEstado {
		estado.mostrarEstado(this);
	}
	
	public void liberarMesa(Mesa m) throws ExceptionEstado {
		estado.liberar(this);
	}
	
	public void reservarMesa(Mesa m) throws ExceptionEstado {
		estado.reservar(this);
	}
	
	public void ocuparMesa(Mesa m) throws ExceptionEstado {
		estado.ocupar(this);
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public int getNroMesa() {
		return nroMesa;
	}

	public void setNroMesa(int nroMesa) {
		this.nroMesa = nroMesa;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public double getConsumo() {
		return consumo;
	}

	public void setConsumo(double consumo) {
		this.consumo = consumo;
	}

	public static int getIdMesa() {
		return idMesa;
	}

	public static void setIdMesa(int idMesa) {
		Mesa.idMesa = idMesa;
	}

	public boolean tieneReservaEnFecha(Date fecha) {
		return false;
	}
}