package logica;

public class Ocupada implements Estado{

	public void liberar(Mesa m) {
		System.out.println("La mesa ha sido liberada con exito.");
		m.setEstado(new Liberada());
	}

	public void reservar(Mesa m) throws ExceptionEstado{
		throw new ExceptionEstado("La mesa no se puede reservar, se encuentra ocupada.");
	}

	public void ocupar(Mesa m) throws ExceptionEstado{
		throw new ExceptionEstado("La mesa ya se encuentra ocupada.");
	}
	
	public void mostrarEstado(Mesa m) {
		System.out.println("Ocupada.");	
	}
	
	public void deshabilitar(Mesa m) throws ExceptionEstado {
		throw new ExceptionEstado("La mesa se encuentra ocupada, no se puede deshabilitar.");
	}
}