package interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import baseDatos.AccesoDatos;
import logica.Mesa;
import logica.Restaurante;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;

public class BorrarMesa extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNumMesa;
	private JButton btnBorrarMesa;
	private Restaurante restaurante;
	private AccesoDatos inicioCon;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BorrarMesa frame = new BorrarMesa();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public BorrarMesa() {
		setSize(250, 220);
		setLocationRelativeTo(this);
		setTitle("Borrar mesa");
		procesos();	
	}
	
	public void procesos() {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtNumMesa = new JTextField();
		txtNumMesa.setColumns(10);
		txtNumMesa.setBounds(110, 72, 65, 20);
		contentPane.add(txtNumMesa);
		
		JLabel lblNumMesa = new JLabel("Numero Mesa");
		lblNumMesa.setBounds(28, 75, 72, 15);
		contentPane.add(lblNumMesa);
		
		JLabel lblTitulo = new JLabel("Crear Mesa");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTitulo.setBounds(65, 26, 80, 15);
		contentPane.add(lblTitulo);
		
		btnBorrarMesa = new JButton("Borrar");
		btnBorrarMesa.setBounds(65, 120, 90, 25);
		contentPane.add(btnBorrarMesa);
		
	}

	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==btnBorrarMesa) {
			borrarMesa();
		}
	}

	private void borrarMesa() {
	    int numMesa = Integer.parseInt(txtNumMesa.getText());
	    ArrayList<Mesa> listaMesas = restaurante.getListaMesas();
	    inicioCon.abrirConexion();
	    Mesa mesaEncontrada = null;
	    
	    Iterator<Mesa> iterator = listaMesas.iterator();
	    while (iterator.hasNext()) {
	        Mesa mesa = iterator.next();
	        if (mesa.getNroMesa() == numMesa) {
	            mesaEncontrada = mesa;
	            break;
	        }
	        if (mesaEncontrada != null) {
	            listaMesas.remove(mesaEncontrada);
	            inicioCon.borrarMesa(numMesa);
	            JOptionPane.showMessageDialog(this, "Mesa "+ numMesa +" borrada exitosamente.");
	        } else {
	            JOptionPane.showMessageDialog(this, "No se encontró la mesa con el número "+ numMesa);
	        }
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