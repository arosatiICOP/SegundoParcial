package interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import logica.Liberada;
import logica.Mesa;
import logica.Restaurante;

import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;

public class consultaMesaL extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tablaMesasLibres;
	private Restaurante restaurante;
	private DefaultTableModel tabla;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					consultaMesaL frame = new consultaMesaL();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public consultaMesaL() {
		setSize(385, 300);
		setLocationRelativeTo(this);
		setTitle("Informacion mesas libres");
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
		
		tablaMesasLibres = new JTable();
		
		tabla = new DefaultTableModel();
		
		tablaMesasLibres.setModel(tabla);
		
		tabla.addColumn("Numero Mesa");tabla.addColumn("Capacidad");
		
		scrollPane.setViewportView(tablaMesasLibres);
		
		JLabel lblTitulo = new JLabel("Mesas Libres");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTitulo.setBounds(140, 15, 100, 25);
		contentPane.add(lblTitulo);
	}

	public void mandarDatos(Restaurante restaurante) {
		this.restaurante=restaurante;	
	}

	public void llenarTabla() {
		
		ArrayList<Mesa> listaMesas =  restaurante.getListaMesas();
		
		for (Mesa mesa: listaMesas) {
			if (mesa.getEstado() instanceof Liberada) {
			Object[] fila = new Object[2];
			fila[0]=mesa.getNroMesa();
			fila[1]=mesa.getCapacidad();
			
			tabla.addRow(fila);
			}
		}
	}
}