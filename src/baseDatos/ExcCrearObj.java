package baseDatos;

import java.sql.SQLException;

public class ExcCrearObj extends SQLException{

		public ExcCrearObj(String message) {
		    super(message);
		    revisarEstado();
		}

		public void revisarEstado() {
		    System.out.println("Error en la creacion: " + getMessage());
		}
}
