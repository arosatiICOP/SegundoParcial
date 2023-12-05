package interfaz;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import baseDatos.MesaBD;

import logica.Mesa;
import logica.Restaurante;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class OcuparMesa extends JFrame implements ActionListener{

		private static final long serialVersionUID = 1L;
		private JPanel contentPane;
		private JButton btnOcuparMesa;
		private Restaurante restaurante;
		private MesaBD mesaDB;
		private JButton btnCancelar;
		private int idResto;
		private DefaultTableModel table;
		private JTable tablaMesasO;
	
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						OcuparMesa frame = new OcuparMesa();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}

		public OcuparMesa() throws SQLException {
			setSize(310, 270);
			setLocationRelativeTo(this);
			mesaDB = new MesaBD();
			procesos();	
		}
		
		public void procesos() throws SQLException {
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			btnOcuparMesa = new JButton("Ocupar");
			btnOcuparMesa.setBounds(45, 190, 90, 25);
			btnOcuparMesa.addActionListener(this);
			contentPane.add(btnOcuparMesa);
			
			btnCancelar = new JButton("Cancelar");
			btnCancelar.setBounds(160, 190, 90, 25);
			btnCancelar.addActionListener(this);
			contentPane.add(btnCancelar);
			
			JLabel lblTitulo = new JLabel("Ocupar Mesa");
			lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblTitulo.setBounds(100, 25, 120, 30);
			contentPane.add(lblTitulo);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(25, 60, 245, 110);
			contentPane.add(scrollPane);
			
			tablaMesasO = new JTable();
			
			table = new DefaultTableModel();
			
			tablaMesasO.setModel(table);
			
			table.addColumn("Numero Mesa");table.addColumn("Estado Mesa");table.addColumn("Capacidad");
			
			scrollPane.setViewportView(tablaMesasO);
			
		}

		public void actionPerformed(ActionEvent e) {

			 if (e.getSource() == btnOcuparMesa) {
			        int filaSeleccionada = tablaMesasO.getSelectedRow();

			        if (filaSeleccionada != -1) { // Verificar si se seleccionó una fila
			            int nroMesa = (int) table.getValueAt(filaSeleccionada, 0);
			            
			            try {

			                mesaDB.cambiarEstado("Ocupada", nroMesa);
			                
			                llenarTablaMesaR();
			            } catch (SQLException e1) {
			                e1.printStackTrace();
			            }
			        } else {
			            JOptionPane.showMessageDialog(this, "Seleccione una mesa para ocupar.", "Error", JOptionPane.ERROR_MESSAGE);
			        }
			        }
			else if(e.getSource()==btnCancelar) {
				dispose();
			}
		}
		
		public void mandarDatos(Restaurante restaurante) {
			this.restaurante=restaurante;
			idResto = restaurante.getNroRestaurante();
			setTitle(restaurante.getNombre());
		}
		
		public void llenarTablaMesaR() throws SQLException {

		    while (table.getRowCount() > 0) {
		        table.removeRow(0);
		    }

		    ArrayList<Mesa> listaM = mesaDB.buscarMesas(idResto);

		    for (Mesa m : listaM) {
		        if ("Reservada".equals(m.getEstadoSQL())) {
		            Object[] fila = new Object[3];
		            fila[0] = m.getNroMesa();
		            fila[1] = m.getEstadoSQL();
		            fila[2] = m.getCapacidad();

		            table.addRow(fila);
		        }
		    }
		}
	}