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
	
	public void mostrarEstado(Mesa m) throws ExceptionEstado {
		System.out.println("Estoy ocupada.");	
	}
}
