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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import baseDatos.MesaBD;
import logica.Mesa;
import logica.Restaurante;
import javax.swing.JTextField;

public class LiberarMesa extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

		private JButton btnLiberarMesa;
		private Restaurante restaurante;
		private MesaBD mesaDB;
		private JButton btnCancelar;
		private int idResto;
		private DefaultTableModel table;
		private JTable tablaMesasO;
		private JTextField txtConsumo;
	
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

		public LiberarMesa() throws SQLException {
			setSize(310, 310);
			setLocationRelativeTo(this);
			mesaDB = new MesaBD();
			procesos();	
		}
		
		public void procesos() throws SQLException {
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			btnLiberarMesa = new JButton("Liberar");
			btnLiberarMesa.setBounds(44, 235, 90, 25);
			btnLiberarMesa.addActionListener(this);
			contentPane.add(btnLiberarMesa);
			
			btnCancelar = new JButton("Cancelar");
			btnCancelar.setBounds(159, 235, 90, 25);
			btnCancelar.addActionListener(this);
			contentPane.add(btnCancelar);
			
			JLabel lblTitulo = new JLabel("Liberar Mesa");
			lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblTitulo.setBounds(100, 25, 120, 30);
			contentPane.add(lblTitulo);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(24, 102, 245, 110);
			contentPane.add(scrollPane);
			
			tablaMesasO = new JTable();
			
			table = new DefaultTableModel();
			
			tablaMesasO.setModel(table);
			
			table.addColumn("Numero Mesa");table.addColumn("Estado Mesa");table.addColumn("Capacidad");
			
			scrollPane.setViewportView(tablaMesasO);
			
			JLabel lblConsumo = new JLabel("Consumo");
			lblConsumo.setBounds(44, 66, 90, 28);
			contentPane.add(lblConsumo);
			
			txtConsumo = new JTextField();
			txtConsumo.setBounds(121, 71, 86, 20);
			contentPane.add(txtConsumo);
			txtConsumo.setColumns(10);
			
		}

		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == btnLiberarMesa) {
		        int filaSeleccionada = tablaMesasO.getSelectedRow();

		        if (filaSeleccionada != -1) {
		            int nroMesa = (int) table.getValueAt(filaSeleccionada, 0);
		            
		            try {
		            	anadirConsumo(nroMesa);
		                mesaDB.cambiarEstado("Liberada", nroMesa);
		                llenarTablaMesaO();
		            } catch (SQLException e1) {
		                e1.printStackTrace();
		            }
		        } else {
		            JOptionPane.showMessageDialog(this, "Seleccione una mesa para liberar.", "Error", JOptionPane.ERROR_MESSAGE);
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
		
		public void anadirConsumo(int nroMesa) throws SQLException {
			 String checkCon = txtConsumo.getText().trim();
			 
			 if (checkCon.isEmpty()) {
			        System.out.println("Error: El campo 'consumo' no debe estar vacio.");
			        return;
			    }
			 int cantCon = Integer.parseInt(txtConsumo.getText().trim());
			 
			 try {
			        
			        if (cantCon <= 0 ) {
			            System.out.println("Error: El dato ingresado en 'consumo' debe ser un numero positivo.");
			            return;
			        }
			        mesaDB.actualizarConsumo(nroMesa, cantCon);      
			} catch (NumberFormatException e) {
		        System.out.println("Error: Ingrese numero para consumo.");
		    }
			 
			mesaDB.updateCon(cantCon, nroMesa);
		}
		
		public void llenarTablaMesaO() throws SQLException {

		    while (table.getRowCount() > 0) {
		        table.removeRow(0);
		    }

		    ArrayList<Mesa> listaM = mesaDB.buscarMesas(idResto);

		    for (Mesa m : listaM) {
		        if ("Ocupada".equals(m.getEstadoSQL())) {
		            Object[] fila = new Object[3];
		            fila[0] = m.getNroMesa();
		            fila[1] = m.getEstadoSQL();
		            fila[2] = m.getCapacidad();

		            table.addRow(fila);
		        }
		    }
		}
	}