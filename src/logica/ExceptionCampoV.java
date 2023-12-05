package logica;

public class ExceptionCampoV extends Exception{

		public ExceptionCampoV(String message) {
		    super(message);
		    revisarCampos();
		}

		public void revisarCampos() {
		    System.out.println("Error al recolectar datos (Campo vacio): " + getMessage());
		}
	}