package interfaz;

import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logica.Mesa;
import logica.Reserva;
import logica.Reservada;
import logica.Restaurante;

public class VistaActualR extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tablaReservas;
	private Restaurante restaurante;
	private DefaultTableModel tabla;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaActualR frame = new VistaActualR();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VistaActualR() {
		setBounds(900,400,400, 300);
		setTitle("Reservas");
		procesos();
	}
	
	public void procesos() {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 50, 350, 200);
		contentPane.add(scrollPane);
		
		tablaReservas = new JTable();
		
		tabla = new DefaultTableModel();
		
		tablaReservas.setModel(tabla);
		
		tabla.addColumn("Nombre");tabla.addColumn("Apellido");tabla.addColumn("Fecha");tabla.addColumn("Numero Mesa");
		
		scrollPane.setViewportView(tablaReservas);
		
		JLabel lblTitulo = new JLabel("Reservas");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTitulo.setBounds(140, 15, 100, 25);
		contentPane.add(lblTitulo);
	}

	public void mandarDatos(Restaurante restaurante) {
		this.restaurante=restaurante;	
	}

	public void llenarTabla() {
		
		ArrayList<Reserva> listaR =  restaurante.getListaReservas();
		
		for (Reserva r: listaR) {
			Object[] fila = new Object[4];
			fila[0]=r.getNombre();
			fila[1]=r.getApellido();
			fila[2]=r.getFecha();
			fila[3]=r.getMesa().getNroMesa();
			
			tabla.addRow(fila);
			}
	}
}