package baseDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import logica.Restaurante;

public class AccesoDatos {
		Connection con;

		 public Connection abrirConexion() {
		        try {
		            String userName = "postgres";
		            String password = "Agustin1810";
		            String url = "jdbc:postgresql://localhost/datosRestaurante";
		            con = DriverManager.getConnection(url, userName, password);
		            System.out.println("Conexión a la Base de Datos correcta.");
		            return con;
		        } catch (SQLException e) {
		            System.out.println("Error en conexión ");
		            e.printStackTrace();
		            return null;
		        }
		    }

		    public void cerrarConexion() {
		        try {
		            if (con != null) {
		                con.close();
		            }
		        } catch (SQLException e) {
		            System.out.println("Error al cerrar conexión");
		            e.printStackTrace();
		        }
		    }
		    
		    public void agregarResto(Restaurante resto) {
		        abrirConexion();
		        try {
		            String sql = "INSERT INTO Restaurante (nombre, calle, localidad) VALUES (?, ?, ?)";
		            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		            preparedStatement.setString(1, resto.getNombre());
		            preparedStatement.setString(2, resto.getCalle());
		            preparedStatement.setString(3, resto.getLocalidad());

		            int filasAfect = preparedStatement.executeUpdate();

		            if (filasAfect > 0) {
		                try (ResultSet rsResto = preparedStatement.getGeneratedKeys()) {
		                    if (rsResto.next()) {
		                        resto.setNroRestaurante(rsResto.getInt(1));
		                    } else {
		                        throw new ExcCrearObj("Error al obtener el ID del restaurante.");
		                    }
		                }
		            } else {
		                throw new ExcCrearObj("Error al crear el restaurante.");
		            }

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
		            cerrarConexion();
		        }
		    }

		    public ArrayList<Restaurante> obtenerRestaurantes() {
		        ArrayList<Restaurante> listaRestaurantes = new ArrayList<>();
		        abrirConexion();
		        try {
		            String sql = "SELECT * FROM Restaurante";
		            Statement st = con.createStatement();
		            ResultSet rs = st.executeQuery(sql);

		            while (rs.next()) {
		                int nroRestaurante = rs.getInt("idRestaurante");
		                String nombre = rs.getString("nombre");
		                String calle = rs.getString("calle");
		                String localidad = rs.getString("localidad");

		                Restaurante restaurante = new Restaurante(nroRestaurante, nombre, calle, localidad);
		                listaRestaurantes.add(restaurante);
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        } finally {
		            cerrarConexion();
		        }

		        return listaRestaurantes;
		    }
}		    