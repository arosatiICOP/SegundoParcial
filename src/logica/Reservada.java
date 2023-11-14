package logica;

public class Reservada implements Estado{


	public void liberar(Mesa m) {
		System.out.println("La mesa ha sido liberada con exito.");
		m.setEstado(new Liberada());
	}

	public void reservar(Mesa m) throws ExceptionEstado{
		throw new ExceptionEstado("La mesa ya se encuentra reservada antes de la solicitud.");	
	}

	public void ocupar(Mesa m) throws ExceptionEstado{
		throw new ExceptionEstado("La mesa se encuentra reservada por otros clientes.");
	}
	
	public void mostrarEstado(Mesa m) throws ExceptionEstado {
		System.out.println("Estoy reservada.");	
	}
}
