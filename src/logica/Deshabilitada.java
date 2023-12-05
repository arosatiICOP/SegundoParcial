package logica;

public class Deshabilitada implements Estado{

		public void liberar(Mesa m) {
			System.out.println("La mesa ha sido habilitada con exito.");
			m.setEstado(new Liberada());
		}

		public void reservar(Mesa m) throws ExceptionEstado{
			throw new ExceptionEstado("La mesa se encuentra deshabilitada, liberala primero.");	
		}

		public void ocupar(Mesa m) throws ExceptionEstado{
			throw new ExceptionEstado("La mesa se encuentra deshabilitada, liberala primero y luego debe reservarse.");
		}
		
		public void mostrarEstado(Mesa m){
			System.out.println("Deshabilitada.");	
		}
		
		public void deshabilitar(Mesa m) throws ExceptionEstado{
			throw new ExceptionEstado("La mesa ya estaba deshabilitada.");
		}
}