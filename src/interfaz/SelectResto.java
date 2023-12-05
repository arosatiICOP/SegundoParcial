package interfaz;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import baseDatos.AccesoDatos;
import logica.Restaurante;

import javax.swing.JButton;
import javax.swing.JComboBox;

public class SelectResto extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<Object> comboBox;
	private JButton btnIngresar;
	private JButton btnSalir;
	private Restaurante restoSelec;
	private ArrayList<Restaurante> listaRestaurantes;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectResto frame = new SelectResto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SelectResto() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(this);
		setTitle("Seleccion de Restaurante");
		setSize(400, 300);
		procesos();
	}
	
	 public void procesos() {
	        contentPane = new JPanel();
	        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	        

	        AccesoDatos accesoDatos = new AccesoDatos();
	        listaRestaurantes = accesoDatos.obtenerRestaurantes();

	        setContentPane(contentPane);
	        contentPane.setLayout(null);

	        btnIngresar = new JButton("Ingresar");
	        btnIngresar.setBounds(70, 185, 90, 25);
	        btnIngresar.addActionListener(this);
	        contentPane.add(btnIngresar);

	        comboBox = new JComboBox<>();
	        comboBox.setBounds(100, 95, 180, 25);
	        contentPane.add(comboBox);
	        
	        btnSalir = new JButton("Salir");
	        btnSalir.setBounds(210, 185, 90, 25);
	        btnSalir.addActionListener(this);
	        contentPane.add(btnSalir);

	       for (Restaurante restaurante : listaRestaurantes) {
	        	comboBox.addItem(restaurante.getNombre());
	        }
	    }

	    public Restaurante getRestauranteSeleccionado() {
	        return restoSelec;
	    }
	 
	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==btnIngresar) {
			restoSelec = listaRestaurantes.get(comboBox.getSelectedIndex());

	        Menu nuevoM = new Menu();
	        nuevoM.mandarDatos(restoSelec);
	        nuevoM.setVisible(true);
		}   
		else if(e.getSource()==btnSalir) {
			JOptionPane.showMessageDialog(null,"¡Hasta Luego!","Saliendo del Sistema", JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		}
	}
}