package logica;

public class Reservada implements Estado{

	public void liberar(Mesa m){
		System.out.println("La mesa ha sido liberada.");
		m.setEstado(new Liberada());
	}

	public void reservar(Mesa m) throws ExceptionEstado{
		throw new ExceptionEstado("La mesa ya se encuentra reservada antes de la solicitud.");	
	}

	public void ocupar(Mesa m) {
		System.out.println("La mesa ha sido ocupada con exito.");
		m.setEstado(new Ocupada());
	}
	
	public void mostrarEstado(Mesa m) {
		System.out.println("Reservada.");	
	}
	
	public void deshabilitar(Mesa m) throws ExceptionEstado {
		throw new ExceptionEstado("La mesa se encuentra reservada, no se puede deshabilitar.");
	}
}