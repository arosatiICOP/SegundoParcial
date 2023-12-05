package interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import baseDatos.MesaBD;
import logica.Liberada;
import logica.Mesa;

import logica.Restaurante;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import java.awt.Font;

public class RegistrarMesa extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtCantidad;
	private JButton btnCrearMesa;
	private Restaurante restaurante;
	private JButton btnCancelar;
	private MesaBD mesaDB;
	private JTextField txtCapacidad;
	private JLabel lblTitulo;
	private int idResto;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrarMesa frame = new RegistrarMesa();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public RegistrarMesa() {
		setSize(350, 205);
		setLocationRelativeTo(this);
		mesaDB = new MesaBD();
		procesos();
	}
	
	public void procesos() {
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtCantidad = new JTextField();
		txtCantidad.setBounds(145, 50, 85, 20);
		contentPane.add(txtCantidad);
		txtCantidad.setColumns(10);
		
		JLabel lblCap = new JLabel("Capacidad");
		lblCap.setBounds(75, 86, 55, 15);
		contentPane.add(lblCap);
		
		btnCrearMesa = new JButton("Crear");
		btnCrearMesa.setBounds(65, 125, 90, 25);
		btnCrearMesa.addActionListener(this);
		contentPane.add(btnCrearMesa);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(180, 125, 90, 25);
		btnCancelar.addActionListener(this);
		contentPane.add(btnCancelar);
		
		txtCapacidad = new JTextField();
		txtCapacidad.setColumns(10);
		txtCapacidad.setBounds(145, 85, 85, 20);
		contentPane.add(txtCapacidad);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(75, 54, 55, 15);
		contentPane.add(lblCantidad);
		
		lblTitulo = new JLabel("Creacion de Mesa");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTitulo.setBounds(110, 10, 140, 30);
		contentPane.add(lblTitulo);
	}

	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==btnCrearMesa) {
			try {
				crearMesa();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		else if(e.getSource()== btnCancelar) {
			dispose();
		}
	}
/*
	private void crearMesa() throws SQLException {
	    String checkCap = txtCapacidad.getText().trim();
	    String checkCant = txtCantidad.getText().trim();
	    
	    if (checkCap.isEmpty() || checkCant.isEmpty()) {
	        System.out.println("Error: Ambos campos deben tener valores numéricos.");
	        return;
	    }

	    try {
	        int capCom = Integer.parseInt(checkCap);
	        int cantMesas = Integer.parseInt(checkCant);

	        if (capCom <= 0 || cantMesas <= 0) {
	            System.out.println("Error: La capacidad y la cantidad de mesas deben ser valores positivos.");
	            return;
	        }


	        for (int i = 0; i < cantMesas; i++) {
	            Mesa nuevaM = new Mesa();
	            nuevaM.setNroMesa(1);
	            nuevaM.setCapacidad(capCom);
	            nuevaM.setEstado(new Liberada());
	            nuevaM.setEstadoSQL("Liberada");
	            mesaDB.guardarMesas(nuevaM, idResto);
	            this.restaurante.registrarEnBD(nuevaM);
	        }

	    } catch (NumberFormatException e) {
	        System.out.println("Error: Ingrese numeros para la capacidad y la cantidad de mesas.");
	    }
	}*/
	
	private void crearMesa() throws SQLException {
	    String checkCap = txtCapacidad.getText().trim();
	    String checkCant = txtCantidad.getText().trim();

	    if (checkCap.isEmpty() || checkCant.isEmpty()) {
	        System.out.println("Error: Ambos campos deben tener valores numéricos.");
	        return;
	    }

	    try {
	        int capCom = Integer.parseInt(checkCap);
	        int cantMesas = Integer.parseInt(checkCant);

	        if (capCom <= 0 || cantMesas <= 0) {
	            System.out.println("Error: La capacidad y la cantidad de mesas deben ser valores positivos.");
	            return;
	        }

	        int idRestaurante = this.idResto;
	        int ultimoNroMesa = obtenerUltimoNroMesa(idRestaurante);

	        for (int i = 0; i < cantMesas; i++) {
	            Mesa nuevaM = new Mesa(capCom, ultimoNroMesa + i + 1);
	            mesaDB.guardarMesas(nuevaM, idRestaurante);
	            this.restaurante.registrarEnBD(nuevaM);
	        }

	    } catch (NumberFormatException e) {
	        System.out.println("Error: Ingrese números para la capacidad y la cantidad de mesas.");
	    }
	}

	private int obtenerUltimoNroMesa(int idRestaurante) throws SQLException {
	    int ultimoNro = 0;

	    try {
	        ArrayList<Mesa> mesas = mesaDB.buscarMesas(idRestaurante);

	        for (Mesa mesa : mesas) {
	            int idMesa = mesa.getNroMesa();
	            if (idMesa > ultimoNro) {
	                ultimoNro = idMesa;
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw e;
	    }

	    return ultimoNro;
	}

	public void mandarDatos(Restaurante restaurante) {
		this.restaurante=restaurante;
		this.idResto=restaurante.getNroRestaurante();
		setTitle(restaurante.getNombre());
	}
}