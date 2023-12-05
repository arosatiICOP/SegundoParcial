package baseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.function.Consumer;

import javax.swing.JOptionPane;

import logica.ExceptionEstado;
import logica.Mesa;
import logica.Restaurante;

public class MesaBD {
		AccesoDatos manejoDB = new AccesoDatos();
		Connection con;
		PreparedStatement pst;
		ResultSet rs;
		Statement st;
		
		public boolean guardarMesas(Mesa mesa, int idResto) throws SQLException {
		    String sql = "INSERT INTO Mesa (nroMesa, capacidad, consumo, estado) VALUES (?, ?, ?, ?)";
		    String sqlRestoMesa = "INSERT INTO Restaurante_Mesa (restaurante_id, mesa_id) VALUES (?, ?)";

		    con = manejoDB.abrirConexion();

		    try {
		        pst = con.prepareStatement(sql);
		        pst.setInt(1, mesa.getNroMesa());
		        pst.setInt(2, mesa.getCapacidad());
		        pst.setDouble(3, mesa.getConsumo());
		        pst.setString(4, mesa.estadoActual());
		        pst.execute();

		        pst = con.prepareStatement(sqlRestoMesa);
		        pst.setInt(1, idResto);
		        pst.setInt(2, mesa.getNroMesa());
		        pst.execute();

		        return true;
		    } catch (SQLException e) {
		        JOptionPane.showMessageDialog(null, e.toString());
		        return false;
		    } finally {
		        manejoDB.cerrarConexion();
		    }
		}
		
		public boolean guardarMesas(int cap, int idResto) throws SQLException { // probar
		    String sql = "INSERT INTO Mesa (nroMesa, capacidad, consumo, estado) VALUES (?, ?, ?, ?)";
		    String sqlRestoMesa = "INSERT INTO Restaurante_Mesa (restaurante_id, mesa_id) VALUES (?, ?)";

		    Mesa m = new Mesa();
		    con = manejoDB.abrirConexion();

		    try {
		        pst = con.prepareStatement(sql);
		        pst.setInt(1, m.getNroMesa());
		        pst.setInt(2, m.getCapacidad());
		        pst.setDouble(3, m.getConsumo());
		        pst.setString(4, m.estadoActual());
		        pst.execute();

		        pst = con.prepareStatement(sqlRestoMesa);
		        pst.setInt(1, idResto);
		        pst.setInt(2, m.getNroMesa());
		        pst.execute();

		        return true;
		    } catch (SQLException e) {
		        JOptionPane.showMessageDialog(null, e.toString());
		        return false;
		    } finally {
		        manejoDB.cerrarConexion();
		    }
		}
		
		/*public boolean deshabilitarMesa(int nroMesa) throws SQLException{

		    //String deleteSQL = "DELETE FROM Mesa WHERE nroMesa = ?";
		    String updateEstado = "UPDATE Mesa SET esActiva = false WHERE nroMesa = ?";
		    // aca debo hacer un update de la columna esActiva y ponerla en false o 0 
		    try {
		    		con = manejoDB.abrirConexion();
		    		pst = con.prepareStatement(updateEstado);
		    		pst.setInt(1, nroMesa);
		    		pst.execute();
		    		return true;
		    } catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e.toString());
				return false;
			}
			finally {
				manejoDB.cerrarConexion();
			}  
		}*/
		
		public boolean deshabilitarMesa(int nroMesa) throws SQLException, ExceptionEstado {
		    String updateEstado = "UPDATE Mesa SET esActiva = false WHERE nroMesa = ?";
		    String updateEst= "UPDATE Mesa SET estado = ? WHERE nroMesa = ?";
		    try {
		        con = manejoDB.abrirConexion();

		        String obtenerEstadoActual = "SELECT estado FROM Mesa WHERE nroMesa = ?";
		        String estadoActual = null;

		        try (PreparedStatement pstObtenerEstado = con.prepareStatement(obtenerEstadoActual)) {
		            pstObtenerEstado.setInt(1, nroMesa);
		            try (ResultSet rs = pstObtenerEstado.executeQuery()) {
		                if (rs.next()) {
		                    estadoActual = rs.getString("estado");
		                }
		            }
		        }

		        if (estadoActual != null && estadoActual.contains("Reservada")) {
		            throw new ExceptionEstado("No se puede deshabilitar la mesa porque está reservada.");
		        }
		        
		        if (estadoActual != null && estadoActual.contains("Deshabilitada")) {
		            throw new ExceptionEstado("No se puede deshabilitar la mesa porque ya está deshabilitada.");
		        }

		        pst = con.prepareStatement(updateEstado);
		        pst.setInt(1, nroMesa);
		        pst.execute();
		        pst = con.prepareStatement(updateEst);
		        pst.setString(1,"Deshabilitada");
		        pst.setInt(2, nroMesa);
		        return true;
		    } catch (SQLException e) {
		        JOptionPane.showMessageDialog(null, e.toString());
		        return false;
		    } finally {
		        manejoDB.cerrarConexion();
		    }
		}
		
		public ArrayList<Mesa> buscarMesas(int idRestaurante) throws SQLException {
		    ArrayList<Mesa> m = new ArrayList<>();

		    String sql = "SELECT Mesa.* FROM Mesa " +
		                 "JOIN Restaurante_Mesa ON Mesa.idMesa = Restaurante_Mesa.mesa_id " +
		                 "WHERE Restaurante_Mesa.restaurante_id = ? AND Mesa.esActiva = true";

		    try (Connection con = manejoDB.abrirConexion();
		         PreparedStatement pst = con.prepareStatement(sql)) {

		        pst.setInt(1, idRestaurante);

		        try (ResultSet rs = pst.executeQuery()) {
		            while (rs.next()) {
		                Mesa mesa = new Mesa();
		                mesa.setNroMesa(rs.getInt("nroMesa"));
		                mesa.setEstadoSQL(rs.getString("estado"));
		                mesa.setCapacidad(rs.getInt("capacidad"));
		                mesa.setConsumo(rs.getDouble("consumo"));
		                m.add(mesa);
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		        throw e;
		    }
		    return m;
		}
		public void cambiarEstado(String nuevoEstado, int idMesa) {
		    String sqlUpM = "UPDATE mesa SET estado = ? WHERE nromesa = ?";

		    try {
		        con = manejoDB.abrirConexion();
		        pst = con.prepareStatement(sqlUpM);
		        pst.setString(1, nuevoEstado);
		        pst.setInt(2, idMesa);
		        pst.executeUpdate();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        manejoDB.cerrarConexion();
		    }
		}
		
		public void updateCon(int consumo, int idMesa) {
		    String sqlUpM = "UPDATE mesa SET consumo = ? WHERE nromesa = ?";

		    try {
		        con = manejoDB.abrirConexion();
		        pst = con.prepareStatement(sqlUpM);
		        pst.setInt(1, consumo);
		        pst.setInt(2, idMesa);
		        pst.executeUpdate();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        manejoDB.cerrarConexion();
		    }
		}

		public void primerasM(int idResto) throws SQLException {
		    Scanner ingresoDatos = new Scanner(System.in);
		    int capacidad = 0;

		    int eleccion = -1;

		    while (eleccion != 0) {
		        System.out.println("Desea crear mesa/s para el restaurante? -- 1 ->(si) / 0 -> (no) ");
		        eleccion = Integer.parseInt(ingresoDatos.nextLine());


		        while (eleccion != 0 && eleccion != 1) {
		            System.out.println("Por favor, ingrese 1 para 'si' o 0 para 'no'.");
		            eleccion = Integer.parseInt(ingresoDatos.nextLine());
		        }

		        if (eleccion == 1) {
		            System.out.println("Desea crear un grupo de mesas o una sola? Ingrese 1 o 2 (1-'grupo' / 2- 'una').");
		            eleccion = Integer.parseInt(ingresoDatos.nextLine());

		            while (eleccion != 1 && eleccion != 2) {
		                System.out.println("Por favor, ingrese 1 para 'grupo' o 2 para 'una'.");
		                eleccion = Integer.parseInt(ingresoDatos.nextLine());
		            }

		            switch (eleccion) {
		                case 1:
		                    System.out.println("Ingrese cantidad de mesas a crear.");
		                    int cantidad = Integer.parseInt(ingresoDatos.nextLine());
		                    System.out.println("Ingrese capacidad de las mesas.");
		                    capacidad = Integer.parseInt(ingresoDatos.nextLine());
		                    for (int i = 0; i < cantidad; i++) {
		                        Mesa m = new Mesa(capacidad);
		                        guardarMesas(m, idResto);
		                    }
		                    break;
		                case 2:
		                    System.out.println("Ingrese capacidad de la mesa.");
		                    capacidad = Integer.parseInt(ingresoDatos.nextLine());
		                    Mesa m = new Mesa(capacidad);
		                    guardarMesas(m, idResto);
		                    break;
		            }
		        }
		    }
		}
		
		public void mostrarRes(int idRestaurante) {
	        try {
	            ArrayList<Mesa> mesas = buscarMesas(idRestaurante);

	            Consumer<Mesa> mostrarMesa = mesa ->
	                    System.out.println("Mesa: " + mesa.getNroMesa() +
	                            ", Capacidad: " + mesa.getCapacidad() +
	                            ", Consumo: " + mesa.getConsumo() +
	                            ", Estado: " + mesa.getEstadoSQL());

	            mesas.forEach(mostrarMesa);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
		
		public void actualizarConsumo(int idMesa, double consumoAdicional) throws SQLException {
		    String sqlUpdateConsumo = "UPDATE Mesa SET consumo = consumo + ? WHERE idMesa = ?";

		    try {
		        con = manejoDB.abrirConexion();
		        pst = con.prepareStatement(sqlUpdateConsumo);
		        pst.setDouble(1, consumoAdicional);
		        pst.setInt(2, idMesa);
		        pst.executeUpdate();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        manejoDB.cerrarConexion();
		    }
		}
		
		public void mostrarMesasL(int idRestaurante) {
		    try {
		        ArrayList<Mesa> mesas = buscarMesas(idRestaurante);

		        Consumer<Mesa> mostrarMesa = mesa -> {
		            if ("Liberada".equals(mesa.getEstadoSQL())) {
		                System.out.println("Mesa: " + mesa.getNroMesa() +
		                        ", Capacidad: " + mesa.getCapacidad() +
		                        ", Consumo: " + mesa.getConsumo() +
		                        ", Estado: " + mesa.getEstadoSQL());
		            }
		        };

		        mesas.forEach(mostrarMesa);
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}
		
		public void mostrarMesasO(int idRestaurante) {
		    try {
		        ArrayList<Mesa> mesas = buscarMesas(idRestaurante);

		        Consumer<Mesa> mostrarMesa = mesa -> {
		            if ("Ocupada".equals(mesa.getEstadoSQL())) {
		                System.out.println("Mesa: " + mesa.getNroMesa() +
		                        ", Capacidad: " + mesa.getCapacidad() +
		                        ", Consumo: " + mesa.getConsumo() +
		                        ", Estado: " + mesa.getEstadoSQL());
		            }
		        };

		        mesas.forEach(mostrarMesa);
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}
		public void mostrarMesasR(int idRestaurante) {
		    try {
		        ArrayList<Mesa> mesas = buscarMesas(idRestaurante);

		        Consumer<Mesa> mostrarMesa = mesa -> {
		            if ("Reservada".equals(mesa.getEstadoSQL())) {
		                System.out.println("Mesa: " + mesa.getNroMesa() +
		                        ", Capacidad: " + mesa.getCapacidad() +
		                        ", Consumo: " + mesa.getConsumo() +
		                        ", Estado: " + mesa.getEstadoSQL());
		            }
		        };

		        mesas.forEach(mostrarMesa);
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}
		
		
		public void top3Mesas(int idRestaurante) {
	        try {
	            ArrayList<Mesa> mesas = buscarMesas(idRestaurante);

	            mesas.sort(Comparator.comparingDouble(Mesa::getConsumo).reversed());

	            for (int i = 0; i < Math.min(3, mesas.size()); i++) {
	                Mesa mesa = mesas.get(i);
	                System.out.println("Mesa: " + mesa.getNroMesa() +
	                        ", Capacidad: " + mesa.getCapacidad() +
	                        ", Consumo: " + mesa.getConsumo() +
	                        ", Estado: " + mesa.getEstadoSQL());
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
}