package logica;

import java.util.Date;
import java.util.Scanner;

public class Mesa{
	
	private Estado estado;
	private String estadoSQL;
	private static int idMesa=1;
	private int nroMesa;
	private int capacidad;
	private double consumo;
	
	public Mesa(){
		/* Sacarle el comentario a procesos si quiere probar crear una mesa en el menu de la clase "Principal"
		 , luego volver a comentar procesos para que funcione la opcion
		"alta mesa" del submenu "gestion" de la interfaz "Menu"*/
		this.estado = new Liberada();
		this.estadoSQL="Liberada";
		nroMesa=idMesa++;
		//procesos();
	}
	
	public Mesa(int capacidad) {
		this.estado = new Liberada();
		nroMesa=idMesa++;
		this.estadoSQL="Liberada";
		this.capacidad=capacidad;
		consumo=0;
	}
	
	public Mesa(int capacidad, int nroMesa) {
		this.estado = new Liberada();
		this.nroMesa=nroMesa;
		this.estadoSQL="Liberada";
		this.capacidad=capacidad;
		consumo=0;
	}
	
	public Mesa(int nroMesa, Estado estado, int capacidad) {
		this.estadoSQL="Liberada";
		this.nroMesa=nroMesa;
		this.estado=estado;
		this.capacidad=capacidad;
	}
	
	public Mesa(int nroMesa, String estado, int capacidad,int consumo) {
		this.nroMesa=nroMesa;
		this.estadoSQL=estado;
		this.capacidad=capacidad;
		this.consumo=consumo;
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
	
	public String estadoActual() {
		String est="";
		if(this.estado instanceof Liberada){
			est="Liberada";
		}else if(this.estado instanceof Ocupada) {
			est="Ocupada";
		}else if(this.estado instanceof Reservada) {
			est="Reservada";
		}
		return est;
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
	
	public void setEstado(String string) {
		this.estadoActual();
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

	public String getEstadoSQL() {
		return estadoSQL;
	}

	public void setEstadoSQL(String estadoSQL) {
		this.estadoSQL = estadoSQL;
	}
}