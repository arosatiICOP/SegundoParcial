package baseDatos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import logica.Mesa;

public class AccesoDatos {

	Connection con;
	Statement st;
	ResultSet rs;
	
	public void abrirConexion() {	
	try {
		String userName="postgres";
		String password="Agustin1810";
		String url="jdbc:postgresql://localhost/datosRestaurante";
			
		con = DriverManager.getConnection(url, userName, password);
		System.out.println("Conexión a la BD");
		
		}catch (Exception e) {
			System.out.println("Error en conexión ");
			System.out.println(e.getMessage());
		}
	}
	
	public void cerrarConexion() {	
	try {
		con.close();
		System.out.println("Conexión cerrada");
		}catch (SQLException e) {
			System.out.println("Error al cerrar conexión");
		}
	}
	
	public void agregarResto(String nombre, String calle, String localidad) {
        
		 try {
			 String sql = "INSERT INTO Restaurante (nombre, calle, localidad) VALUES"
			 		+ "(?, ?, ?)";
	            PreparedStatement preparedStatement = con.prepareStatement(sql);
	            preparedStatement.setString(1, nombre);
	            preparedStatement.setString(2, calle);
	            preparedStatement.setString(4, localidad);


	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (con != null) {
	                    con.close();
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	
	public void agregarReserva(String fecha, String nombre, String apellido, int cantCom, int idMesa) {
        
		String sql = "INSERT INTO Reserva (fecha, nombreCliente, apellidoCliente, cantComensales, mesa_id) "
				+ "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setDate(1, java.sql.Date.valueOf(fecha));
            preparedStatement.setString(2, nombre);
            preparedStatement.setString(3, apellido);
            preparedStatement.setInt(4, cantCom);
            preparedStatement.setInt(5, idMesa);

            int filas = preparedStatement.executeUpdate();

            if (filas > 0) {
                System.out.println("Reserva agregada correctamente.");
            } else {
                System.out.println("No se pudo agregar la reserva.");
            	}
        	} catch (SQLException e) {
        		e.printStackTrace();
        }
	}
	
	public void agregarMesa(int nroMesa, int capacidad, int consumo, String estado) {

        String sql = "INSERT INTO Mesa (nroMesa, capacidad, consumo, estado) VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setInt(1, nroMesa);
            preparedStatement.setInt(2, capacidad);
            preparedStatement.setDouble(3, consumo);
            preparedStatement.setString(4, estado);

            int filas = preparedStatement.executeUpdate();

            if (filas > 0) {
                System.out.println("Mesa creada correctamente.");
            } else {
                System.out.println("No se pudo crear la mesa.");
            }
        } catch (SQLException e) {
        e.printStackTrace();
        }
	}
	
	public void borrarMesa(int nroMesa) {

	    String deleteSQL = "DELETE FROM Mesa WHERE nroMesa = ?";
	    try (PreparedStatement stDelete = con.prepareStatement(deleteSQL)) {
	        stDelete.setInt(1, nroMesa);
	        int filasAfectadas = stDelete.executeUpdate();
	        if (filasAfectadas > 0) {
	            System.out.println("Mesa eliminada exitosamente.");
	        } else {
	            System.out.println("No se encontró ninguna mesa con el ID proporcionado.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}	
}