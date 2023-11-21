package logica;

public class ExceptionCreacion extends Exception{

		public ExceptionCreacion(String message) {
		    super(message);
		    revisarEstado();
		}

		public void revisarEstado() {
		    System.out.println("Error en la creacion: " + getMessage());
		}
	}
