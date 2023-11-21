package interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import baseDatos.AccesoDatos;
import logica.Liberada;
import logica.Mesa;
import logica.Reserva;
import logica.Reservada;
import logica.Restaurante;

import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;

public class RegistrarMesa extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtCap;
	private final ButtonGroup estadoMesa = new ButtonGroup();
	private JButton btnCrearMesa;
	private Restaurante restaurante;
	private JRadioButton rdbtnReservada;
	private JRadioButton rdbtnLibre;
	private AccesoDatos inicioCon;

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
		setSize(350, 235);
		setTitle("Registrar mesa");
		setLocationRelativeTo(this);
		procesos();
	}
	
	public void procesos() {
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		rdbtnReservada = new JRadioButton("Reservada");
		estadoMesa.add(rdbtnReservada);
		rdbtnReservada.setBounds(110, 125, 110, 25);
		contentPane.add(rdbtnReservada);
		
		rdbtnLibre = new JRadioButton("Libre");
		estadoMesa.add(rdbtnLibre);
		rdbtnLibre.setBounds(110, 90, 110, 25);
		contentPane.add(rdbtnLibre);
		
		txtCap = new JTextField();
		txtCap.setBounds(130, 50, 85, 20);
		contentPane.add(txtCap);
		txtCap.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Crear Mesa");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(125, 15, 80, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblCap = new JLabel("Capacidad");
		lblCap.setBounds(63, 55, 55, 15);
		contentPane.add(lblCap);
		
		btnCrearMesa = new JButton("Crear");
		btnCrearMesa.setBounds(110, 160, 90, 25);
		btnCrearMesa.addActionListener(this);
		contentPane.add(btnCrearMesa);
	}


	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==btnCrearMesa) {
			crearMesa();
		}
	}

	private void crearMesa() {
	    String checkNumero = txtCap.getText();
	    inicioCon.abrirConexion();

	    if (checkNumero.matches("\\d+")) { // Verifica que la entrada sea un número
	        try {
	            Mesa nuevaM = new Mesa();
	            int capCom = Integer.parseInt(checkNumero);
	            nuevaM.setCapacidad(capCom);

	            if (rdbtnReservada.isSelected()) {
	                nuevaM.setEstado(new Reservada());
	                inicioCon.agregarMesa(nuevaM.getNroMesa(), capCom, 0, "Reservada");
	            } else {
	                nuevaM.setEstado(new Liberada());
	                inicioCon.agregarMesa(nuevaM.getNroMesa(), capCom, 0, "Libre");
	            }

	            restaurante.registrarEnBD(nuevaM);
	        } catch (NumberFormatException e) {
	            System.out.println("Error: Ingrese una capacidad valida.");
	        }
	    } else {
	        System.out.println("Error: Ingrese un numero valido para la capacidad de la mesa.");
	    }
	    inicioCon.cerrarConexion();
	}

	public void mandarDatos(Restaurante restaurante) {
		this.restaurante=restaurante;
	}

	public void abrirConexion(AccesoDatos inicioCon) {
		this.inicioCon = inicioCon;
		
	}
}
