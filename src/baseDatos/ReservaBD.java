package baseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import logica.Reserva;

public class ReservaBD {

	AccesoDatos manejoDB = new AccesoDatos();
	MesaBD mesaDB = new MesaBD();
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	Statement st;
	
	public boolean guardarReserva(Reserva r, int idResto) throws SQLException {
        String sqlReserva = "INSERT INTO reserva(nroreserva, fecha, nombrecliente, apellidocliente, cantcomensales, mesa_id, restaurante_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        con = manejoDB.abrirConexion();
        
        try {
                    pst = con.prepareStatement(sqlReserva);
                    java.sql.Date fechaSQL = new java.sql.Date(r.getFecha().getTime());
                    pst.setInt(1, r.getNroReserva());
                    pst.setDate(2, fechaSQL);
                    pst.setString(3, r.getNombre());
                    pst.setString(4, r.getApellido());
                    pst.setInt(5, r.getCantCom());
                    pst.setInt(6, r.getIdMesa());
                    pst.setInt(7, r.getIdResto());

                    pst.execute();
                    mesaDB.cambiarEstado("Reservada", r.getIdMesa());
                    return true;
        } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e.toString());
        return false;
	    } finally {
	        manejoDB.cerrarConexion();
	    }
	}
	
	public ArrayList<Reserva> buscarReservas(int idRestaurante) {
	    ArrayList<Reserva> r = new ArrayList<Reserva>();
	    String sql = "SELECT Reserva.* FROM Reserva JOIN Restaurante_Mesa ON Reserva.mesa_id = Restaurante_Mesa.mesa_id WHERE Restaurante_Mesa.restaurante_id = ?";
	    try {
	        con = manejoDB.abrirConexion();
	        pst = con.prepareStatement(sql);
	        pst.setInt(1, idRestaurante); 
	        rs = pst.executeQuery();
	        while (rs.next()) {
	            Reserva reserva = new Reserva();
	            reserva.setFecha(rs.getDate("fecha"));
	            reserva.setNombre(rs.getString("nombreCliente"));
	            reserva.setApellido(rs.getString("apellidoCliente"));
	            reserva.setCantCom(rs.getInt("cantComensales"));
	            reserva.setIdMesa(rs.getInt("mesa_id"));
	            reserva.setIdResto(rs.getInt("restaurante_id"));
	            r.add(reserva);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return null;
	    } finally {
	        manejoDB.cerrarConexion();
	    }
	    return r;
	}
}