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

import logica.Liberada;
import logica.Mesa;
import logica.Ocupada;
import logica.Reserva;
import logica.Reservada;
import logica.Restaurante;

public class VistaActualM extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Restaurante restaurante;
	private JTable tablaMesas;
	private DefaultTableModel tabla;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaActualM frame = new VistaActualM();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VistaActualM() {
		setTitle("Mesas");
		setBounds(400,400,400, 300);
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
		
		tablaMesas = new JTable();
		
		tabla = new DefaultTableModel();
		
		tablaMesas.setModel(tabla);
		
		tabla.addColumn("Numero Mesa");tabla.addColumn("Estado Mesa");tabla.addColumn("Capacidad");
		
		scrollPane.setViewportView(tablaMesas);
		
		JLabel lblTitulo = new JLabel("Mesas");
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
				if (mesa.getEstado() instanceof Reservada) {
					Object[] fila = new Object[3];
					fila[0]=mesa.getNroMesa();
					fila[1]="Reservada";
					fila[2]=mesa.getCapacidad();
					tabla.addRow(fila);
					}
				else if (mesa.getEstado() instanceof Ocupada) {
					Object[] fila1 = new Object[3];
					fila1[0]=mesa.getNroMesa();
					fila1[1]="Ocupada";
					fila1[2]=mesa.getCapacidad();
					tabla.addRow(fila1);
					}
				else {
					Object[] fila2 = new Object[3];
					fila2[0]=mesa.getNroMesa();
					fila2[1]="Libre";
					fila2[2]=mesa.getCapacidad();
					tabla.addRow(fila2);
					}
			}	
		}
}