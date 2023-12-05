package interfaz;

import java.awt.EventQueue;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import baseDatos.MesaBD;
import baseDatos.ReservaBD;
import logica.ExceptionEstado;
import logica.Mesa;
import logica.Reserva;
import logica.Restaurante;
import javax.swing.JButton;

public class VistaActualRyM extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tablaReservas;
	private JTable tablaMesas;
	private Restaurante restaurante;
	private DefaultTableModel tabla;
	private DefaultTableModel tabla1;
	private JButton btnVolver;
	private MesaBD mesaDB;
	private ReservaBD reservaDB;
	private int idResto;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaActualRyM frame = new VistaActualRyM();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VistaActualRyM() {
		setBounds(900,400,780, 591);
		mesaDB = new MesaBD();
		reservaDB = new ReservaBD();
		procesos();
	}
	
	public void procesos() {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(70, 295, 635, 200);
		contentPane.add(scrollPane);
		
		tablaReservas = new JTable();
		
		tabla1 = new DefaultTableModel();
		
		tablaReservas.setModel(tabla1);
		
		tabla1.addColumn("Numero Reserva");tabla1.addColumn("Nombre");tabla1.addColumn("Apellido");
		tabla1.addColumn("Cant. Comensales");tabla1.addColumn("Numero Mesa");tabla1.addColumn("Fecha");
		
		scrollPane.setViewportView(tablaReservas);
		
		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(220, 55, 350, 200);
		contentPane.add(scrollPane1);
		
		tablaMesas = new JTable();
		
		tabla = new DefaultTableModel();
		
		tablaMesas.setModel(tabla);
		
		tabla.addColumn("Numero Mesa");tabla.addColumn("Estado Mesa");tabla.addColumn("Capacidad");tabla.addColumn("Consumo");
		
		scrollPane1.setViewportView(tablaMesas);
		
		JLabel lblTituloM = new JLabel("Mesas");
		lblTituloM.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTituloM.setBounds(369, 19, 85, 25);
		contentPane.add(lblTituloM);
		
		JLabel lblTituloR = new JLabel("Reservas");
		lblTituloR.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTituloR.setBounds(355, 265, 85, 25);
		contentPane.add(lblTituloR);
		
		btnVolver = new JButton("Volver a la pantalla de inicio");
		btnVolver.setBounds(290, 510, 220, 25);
		btnVolver.addActionListener(this);
		contentPane.add(btnVolver);
	}
	
	public void llenarTablaM() throws SQLException{
        MesaBD mesaDB = new MesaBD();

        while (tabla.getRowCount() > 0) {
            tabla.removeRow(0);
        }

        ArrayList<Mesa> listaM = mesaDB.buscarMesas(idResto);

        for (Mesa m : listaM) {
            Object[] fila = new Object[4];
            fila[0] = m.getNroMesa();
            fila[1] = m.getEstadoSQL();
            fila[2] = m.getCapacidad();
            fila[3] = m.getConsumo();

            tabla.addRow(fila);
        }
    }
	 
	 public void llenarTablaR() {
	        ReservaBD reservaDB = new ReservaBD();

	        while (tabla1.getRowCount() > 0) {
	            tabla1.removeRow(0);
	        }

	        ArrayList<Reserva> listaR = reservaDB.buscarReservas(idResto);

	        for (Reserva r : listaR) {
	            Object[] fila = new Object[6];
	            fila[0] = r.getNroReserva();
	            fila[1] = r.getNombre();
	            fila[2] = r.getApellido();
	            fila[3] = r.getCantCom();
	            fila[4] = r.getIdMesa();
	            fila[5] = r.getFecha();

	            tabla1.addRow(fila);
	        }
	    }

	public void mandarDatos(Restaurante restaurante) {
		this.restaurante=restaurante;
		setTitle("Info. Reservas y Mesas - " + this.restaurante.getNombre());
		idResto = restaurante.getNroRestaurante();
	} 
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnVolver) {
			dispose();
		}	
	}
}