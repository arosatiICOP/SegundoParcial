package logica;

public class ExceptionEstado extends Exception{

	public ExceptionEstado(String message) {
	    super(message);
	    revisarEstado();
	}

	public void revisarEstado() {
	    System.out.println("Error en el cambio de estado: " + getMessage());
	}
}