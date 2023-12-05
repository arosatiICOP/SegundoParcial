package interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logica.ExceptionCampoV;

import logica.Liberada;
import logica.Mesa;
import logica.Reserva;

import logica.Restaurante;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JButton;
import com.toedter.calendar.JCalendar;

import baseDatos.MesaBD;
import baseDatos.ReservaBD;

public class RegistrarReserva extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtCantCom;
	private JTextField txtMesa;
	private JButton btnBuscarMesaL;
	private JButton btnGuardar;
	private Restaurante restaurante;
	private JCalendar calendar;
	private JTable tablaMesasLibres;
	private DefaultTableModel tabla;
	private JButton btnCancelar;
	private MesaBD mesaBD;
	private ReservaBD reservaBD;
	private int idResto;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrarReserva frame = new RegistrarReserva();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public RegistrarReserva() {
		
		setSize(600, 465);
		setLocationRelativeTo(this);
		reservaBD = new ReservaBD();
		mesaBD = new MesaBD();
		procesos();
	}
	
	public void procesos() {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(335, 215, 235, 202);
		contentPane.add(scrollPane);
		
		tablaMesasLibres = new JTable();
		
		tabla = new DefaultTableModel();
		
		tablaMesasLibres.setModel(tabla);
		
		tabla.addColumn("Numero Mesa");tabla.addColumn("Capacidad");
		
		scrollPane.setViewportView(tablaMesasLibres);
		
		JLabel lblTitulo = new JLabel("Registrar Reserva");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTitulo.setBounds(78, 50, 185, 42);
		contentPane.add(lblTitulo);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(118, 145, 150, 20);
		contentPane.add(txtNombre);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(118, 179, 150, 20);
		contentPane.add(txtApellido);
		
		txtCantCom = new JTextField();
		txtCantCom.setColumns(10);
		txtCantCom.setBounds(182, 213, 86, 20);
		contentPane.add(txtCantCom);
		
		btnBuscarMesaL = new JButton("Buscar Mesas Libres");
		btnBuscarMesaL.setBounds(182, 264, 135, 23);
		btnBuscarMesaL.addActionListener(this);
		contentPane.add(btnBuscarMesaL);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFecha.setBounds(433, 23, 68, 20);
		contentPane.add(lblFecha);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(50, 148, 46, 14);
		contentPane.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(50, 182, 46, 14);
		contentPane.add(lblApellido);
		
		JLabel lblCantidadDeComensales = new JLabel("Cantidad de Comensales");
		lblCantidadDeComensales.setBounds(34, 207, 118, 33);
		contentPane.add(lblCantidadDeComensales);
		
		txtMesa = new JTextField();
		txtMesa.setBounds(117, 265, 55, 20);
		contentPane.add(txtMesa);
		txtMesa.setColumns(10);
		
		JLabel lblNumMesa = new JLabel("Numero Mesa");
		lblNumMesa.setBounds(23, 268, 73, 14);
		contentPane.add(lblNumMesa);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(46, 330, 89, 23);
		btnGuardar.addActionListener(this);
		contentPane.add(btnGuardar);
		
		calendar = new JCalendar();
		calendar.setBounds(360, 50, 185, 155);
		contentPane.add(calendar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(174, 330, 89, 23);
		btnCancelar.addActionListener(this);
		contentPane.add(btnCancelar);
	}

	public void actionPerformed(ActionEvent e){

		if(e.getSource()==btnBuscarMesaL) {
			try {
				llenarTabla();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		else if(e.getSource()==btnGuardar) {
			try {
				crearReserva();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		}
		else if(e.getSource()==btnCancelar) {
			dispose();
		}
	}

	public void mandarDatos(Restaurante restaurante) {
		this.restaurante=restaurante;
		setTitle(restaurante.getNombre());
		idResto = restaurante.getNroRestaurante();
	}
	
	private void crearReserva() throws SQLException {
	    String checkNom = txtNombre.getText().trim();
	    String checkApe = txtApellido.getText().trim();
	    String checkCantC = txtCantCom.getText().trim();
	    String checkNumM = txtMesa.getText().trim();
	    int numResto = restaurante.getNroRestaurante();
	    java.util.Date fecha = calendar.getDate();

	    if (fecha == null) {
	        System.out.println("Error: Seleccione una fecha.");
	        return;
	    }

	    if (checkNom.isEmpty() || checkApe.isEmpty() || checkCantC.isEmpty() || checkNumM.isEmpty()) {
	        System.out.println("Error: Los campos no deben estar vacios.");
	        return;
	    }

	    int cantCom = Integer.parseInt(txtCantCom.getText().trim());
	    int nroMesa = Integer.parseInt(txtMesa.getText().trim());
	    try {
	        
	        if (cantCom <= 0 || nroMesa <= 0) {
	            System.out.println("Error: Todos los datos ingresados deben ser numeros positivos.");
	            return;
	        }
	        
	} catch (NumberFormatException e) {
        System.out.println("Error: Ingrese numeros para la capacidad y la cantidad de mesas.");
    }

    Reserva nuevaR = new Reserva();
    nuevaR.setFecha(fecha);
	nuevaR.setNombre(checkNom);
	nuevaR.setApellido(checkApe);
	nuevaR.setCantCom(cantCom);
	nuevaR.setIdMesa(nroMesa);
	nuevaR.setIdResto(numResto);
	reservaBD.guardarReserva(nuevaR, numResto);
	mesaBD.cambiarEstado("Reservada", nroMesa);
	llenarTabla();

	this.restaurante.registrarEnBD(nuevaR);

	}	
    
	/*public void llenarTabla() throws SQLException {
	    try {
	        if (txtCantCom.getText().isEmpty()) {
	            throw new ExceptionCampoV("El campo 'Cantidad de Comensales' no debe estar vacio.");
	        }

	        int cantCom = Integer.parseInt(txtCantCom.getText());

	        ArrayList<Mesa> listaMesas = mesaBD.buscarMesas(idResto);

	        DefaultTableModel model = (DefaultTableModel) tablaMesasLibres.getModel();
	        model.setRowCount(0);

	        for (Mesa mesa : listaMesas) {
	        	if (mesa.getCapacidad() >= cantCom && mesa.getEstadoSQL().equals("Liberada")) {
	                Object[] fila = new Object[2];
	                fila[0] = mesa.getNroMesa();
	                fila[1] = mesa.getCapacidad();

	                tabla.addRow(fila);
	            }
	        }
	    } catch (NumberFormatException e) {
	        System.out.println("La cantidad de comensales debe ser un numero.");
	    } catch (ExceptionCampoV e) {
	        e.revisarCampos();
	    }
	}*/
	
	public void llenarTabla() throws SQLException {
	    try {
	        if (txtCantCom.getText().isEmpty()) {
	            throw new ExceptionCampoV("El campo 'Cantidad de Comensales' no debe estar vacío.");
	        }

	        int cantCom = Integer.parseInt(txtCantCom.getText());

	        ArrayList<Mesa> listaMesas = mesaBD.buscarMesas(idResto);

	        DefaultTableModel model = (DefaultTableModel) tablaMesasLibres.getModel();
	        model.setRowCount(0);

	        for (Mesa mesa : listaMesas) {
	            if (mesa.getCapacidad() >= cantCom && mesa.getEstadoSQL().equals("Liberada")) {
	                Object[] fila = new Object[2];
	                fila[0] = mesa.getNroMesa();
	                fila[1] = mesa.getCapacidad();

	                model.addRow(fila);
	            }
	        }
	    } catch (NumberFormatException e) {
	        System.out.println("La cantidad de comensales debe ser un número.");
	    } catch (ExceptionCampoV e) {
	        e.revisarCampos();
	    }
	}
}