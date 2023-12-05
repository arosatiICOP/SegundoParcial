package logica;

public class Liberada implements Estado{

	public void liberar(Mesa m) throws ExceptionEstado{
		throw new ExceptionEstado("La mesa ya estaba libre antes de la solicitud.");
	}

	public void reservar(Mesa m) {
		System.out.println("La mesa ha sido reservada con exito.");
		m.setEstado(new Reservada());
	}

	public void ocupar(Mesa m) throws ExceptionEstado {
		throw new ExceptionEstado("Primero debes reservar la mesa.");
	}

	public void mostrarEstado(Mesa m) {
		System.out.println("Liberada.");	
	}

	public void deshabilitar(Mesa m) {
		System.out.println("Se deshabilito con exito la mesa.");
		m.setEstado(new Deshabilitada());
	}	
}