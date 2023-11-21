package interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logica.ExceptionCreacion;
import logica.Mesa;
import logica.Reserva;
import logica.Reservada;
import logica.Restaurante;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.JButton;
import com.toedter.calendar.JCalendar;

import baseDatos.AccesoDatos;

public class RegistrarReserva extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtCantCom;
	private JTextField txtMesa;
	private JButton btnBuscarMesaL;
	private JButton btnAceptar;
	private Restaurante restaurante;
	private JCalendar calendar;
	private AccesoDatos inicioCon;

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
		
		setSize(600, 320);
		setTitle("Registrar reserva");
		setLocationRelativeTo(this);
		procesos();
	}
	
	public void procesos() {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Registrar Reserva");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTitulo.setBounds(89, 23, 185, 42);
		contentPane.add(lblTitulo);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(130, 76, 150, 20);
		contentPane.add(txtNombre);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(130, 110, 150, 20);
		contentPane.add(txtApellido);
		
		txtCantCom = new JTextField();
		txtCantCom.setColumns(10);
		txtCantCom.setBounds(194, 144, 86, 20);
		contentPane.add(txtCantCom);
		
		btnBuscarMesaL = new JButton("Buscar Mesas Libres");
		btnBuscarMesaL.setBounds(194, 195, 135, 23);
		btnBuscarMesaL.addActionListener(this);
		contentPane.add(btnBuscarMesaL);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(427, 55, 37, 14);
		contentPane.add(lblFecha);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(62, 79, 46, 14);
		contentPane.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(62, 113, 46, 14);
		contentPane.add(lblApellido);
		
		JLabel lblCantidadDeComensales = new JLabel("Cantidad \r\nde Comensales");
		lblCantidadDeComensales.setBounds(46, 138, 118, 33);
		contentPane.add(lblCantidadDeComensales);
		
		txtMesa = new JTextField();
		txtMesa.setBounds(129, 196, 55, 20);
		contentPane.add(txtMesa);
		txtMesa.setColumns(10);
		
		JLabel lblNumMesa = new JLabel("Numero Mesa");
		lblNumMesa.setBounds(35, 199, 73, 14);
		contentPane.add(lblNumMesa);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(122, 238, 89, 23);
		btnAceptar.addActionListener(this);
		contentPane.add(btnAceptar);
		
		calendar = new JCalendar();
		calendar.setBounds(360, 90, 184, 153);
		contentPane.add(calendar);
	}

	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==btnBuscarMesaL) {
		consultaMesaL con = new consultaMesaL();
		con.mandarDatos(restaurante);
		con.llenarTabla();
		con.setVisible(true);
		}else if(e.getSource()==btnAceptar) {
		try {
			altaReserva();
		} catch (ExceptionCreacion e1) {
			e1.printStackTrace();
		}
		}
	}

	public void mandarDatos(Restaurante restaurante) {
		this.restaurante=restaurante;	
	}
	
	/*private void altaReserva() throws ExceptionCreacion {
		Reserva nuevaR = new Reserva();
		
		nuevaR.setApellido(txtApellido.getText());
		nuevaR.setNombre(txtNombre.getText());
		nuevaR.setFecha(calendar.getDate());	
		nuevaR.setCantCom(Integer.parseInt(txtCantCom.getText()));
		int datoIng= Integer.parseInt(txtMesa.getText());
		
	    Mesa mesaReservada = buscarMesaPorNumero(datoIng);

	    if (mesaReservada != null) {
	        nuevaR.setMesa(mesaReservada);
	        mesaReservada.setEstado(new Reservada());
	        restaurante.registrarEnBD(nuevaR);
	        inicioCon.abrirConexion();
	    } else {
	        System.out.println("No se encontro la mesa con el numero ingresado.");
	    }
	}*/
	
	private void altaReserva() throws ExceptionCreacion {
	    Reserva nuevaR = new Reserva();

	    nuevaR.setApellido(txtApellido.getText());
	    nuevaR.setNombre(txtNombre.getText());
	    nuevaR.setFecha(new java.sql.Date(calendar.getDate().getTime()));
	    nuevaR.setCantCom(Integer.parseInt(txtCantCom.getText()));
	    int datoIng = Integer.parseInt(txtMesa.getText());

	    Mesa mesaReservada = buscarMesaPorNumero(datoIng);

	    if (mesaReservada != null) {
	    	if (nuevaR.getCantCom() <= mesaReservada.getCapacidad()) {
	        nuevaR.setMesa(mesaReservada);
	        mesaReservada.setEstado(new Reservada());
	        restaurante.registrarEnBD(nuevaR);

	        inicioCon.abrirConexion();
	        inicioCon.agregarReserva(
	            nuevaR.getFecha().toString(),
	            nuevaR.getNombre(),
	            nuevaR.getApellido(),
	            nuevaR.getCantCom(),
	            mesaReservada.getNroMesa()
	        );
	        inicioCon.cerrarConexion();
	    	 } else {
	             System.out.println("La cantidad de comensales supera la capacidad de la mesa.");
	         }
	    } else {
	        System.out.println("No se encontró la mesa con el numero ingresado.");
	    }
	}
	
    public Mesa buscarMesaPorNumero(int numeroMesa) {
    	
    	ArrayList<Mesa> listaMesas = restaurante.getListaMesas();
    	
        for (Mesa m : listaMesas) {
            if (m.getNroMesa() == numeroMesa) {
                return m;
            }
        }
        return null;
    }


	public void abrirConexion(AccesoDatos inicioCon) {
		this.inicioCon= inicioCon;
		
	}
}