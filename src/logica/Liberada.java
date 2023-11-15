package logica;

public class Liberada implements Estado{

	public void liberar(Mesa m) throws ExceptionEstado{
		throw new ExceptionEstado("La mesa ya esta libre antes de la solicitud.");
	}

	public void reservar(Mesa m) {
		System.out.println("La mesa ha sido reservada con exito.");
		m.setEstado(new Reservada());
	}

	public void ocupar(Mesa m) {
		System.out.println("La mesa se ocupo con exito.");
		m.setEstado(new Ocupada());	
	}

	public void mostrarEstado(Mesa m) throws ExceptionEstado {
		System.out.println("Estoy libre.");	
	}
}