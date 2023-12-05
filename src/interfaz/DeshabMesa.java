package interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import baseDatos.MesaBD;
import logica.ExceptionEstado;
import logica.Mesa;
import logica.Restaurante;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import java.awt.Font;

public class DeshabMesa extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnDeshabMesa;
	private Restaurante restaurante;
	private MesaBD mesaDB;
	private JButton btnCancelar;
	private DefaultTableModel table;
	private JTable tablaMesasL;
	private int idResto;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeshabMesa frame = new DeshabMesa();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public DeshabMesa() {
		setSize(350, 275);
		setLocationRelativeTo(this);
		mesaDB = new MesaBD();
		procesos();	
	}
	
	public void procesos() {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnDeshabMesa = new JButton("Deshabilitar");
		btnDeshabMesa.setBounds(64, 200, 90, 25);
		btnDeshabMesa.addActionListener(this);
		contentPane.add(btnDeshabMesa);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(171, 201, 89, 23);
		btnCancelar.addActionListener(this);
		contentPane.add(btnCancelar);
		
		JLabel lblTitulo = new JLabel("Deshabilitar Mesa");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTitulo.setBounds(100, 10, 140, 30);
		contentPane.add(lblTitulo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(39, 51, 245, 138);
		contentPane.add(scrollPane);
		
		tablaMesasL = new JTable();
		
		table = new DefaultTableModel();
		
		tablaMesasL.setModel(table);
		
		table.addColumn("Numero Mesa");table.addColumn("Estado Mesa");table.addColumn("Capacidad");
		
		scrollPane.setViewportView(tablaMesasL);
		
	}

	public void actionPerformed(ActionEvent e) {

		 if (e.getSource() == btnDeshabMesa) {
		        int filaSeleccionada = tablaMesasL.getSelectedRow();

		        if (filaSeleccionada != -1) {
		            int nroMesa = (int) table.getValueAt(filaSeleccionada, 0);
		            
		            try {
		            	mesaDB.deshabilitarMesa(nroMesa);
		                mesaDB.cambiarEstado("Deshabilitada", nroMesa);
		                
		                llenarTablaMesasL();
		            } catch (SQLException e1) {
		                e1.printStackTrace();
		            } catch (ExceptionEstado e1) {
						e1.printStackTrace();
					}
		        } else {
		            JOptionPane.showMessageDialog(this, "Seleccione una mesa para deshabilitar.", "Error", JOptionPane.ERROR_MESSAGE);
		        }
		        }
		else if(e.getSource()==btnCancelar) {
			dispose();
		}
	}
	
	public void llenarTablaMesasL() throws SQLException {

	    while (table.getRowCount() > 0) {
	        table.removeRow(0);
	    }

	    ArrayList<Mesa> listaM = mesaDB.buscarMesas(idResto);

	    for (Mesa m : listaM) {
	        if ("Liberada".equals(m.getEstadoSQL())) {
	            Object[] fila = new Object[3];
	            fila[0] = m.getNroMesa();
	            fila[1] = m.getEstadoSQL();
	            fila[2] = m.getCapacidad();

	            table.addRow(fila);
	        }
	    }
	}

	public void mandarDatos(Restaurante restaurante) {
		idResto = restaurante.getNroRestaurante();
		this.restaurante=restaurante;
		setTitle(restaurante.getNombre());
		
	}
}