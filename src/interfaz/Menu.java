package interfaz;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import baseDatos.AccesoDatos;
import logica.Restaurante;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class Menu extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JMenuItem mntmVistaActual;
	private JMenuItem mntmAcercaDe;
	private JMenuItem mntmVistaXFecha;
	private JMenuItem mntmSalir;
	private Restaurante restaurante;
	private JMenuItem mntmBajaMesa;
	private JMenuItem mntmAltaMesa;
	private JMenuItem mntmRegistrar;
	private AccesoDatos inicioCon;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Menu principal");
		setLocationRelativeTo(this);
		restaurante = new Restaurante();
		inicioCon = new AccesoDatos();
		setSize(450, 300);
		procesos();
	}
	
	public void procesos(){
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 210, 25);
		contentPane.add(menuBar);
		
		JMenu mnReserva = new JMenu("Reserva");
		menuBar.add(mnReserva);
		
		mntmRegistrar = new JMenuItem("Registrar");
		mntmRegistrar.addActionListener(this);
		mnReserva.add(mntmRegistrar);
		
		JMenu mnGestion = new JMenu("Gestion");
		menuBar.add(mnGestion);
		
		mntmAltaMesa = new JMenuItem("Alta Mesa");
		mntmAltaMesa.addActionListener(this);
		mnGestion.add(mntmAltaMesa);
		
		mntmBajaMesa = new JMenuItem("Baja Mesa");
		mntmBajaMesa.addActionListener(this);
		mnGestion.add(mntmBajaMesa);
		
		JMenu mnInfo = new JMenu("Info");
		menuBar.add(mnInfo);
		
		mntmVistaActual = new JMenuItem("Vista Actual");
		mntmVistaActual.addActionListener(this);
		mnInfo.add(mntmVistaActual);
		
		mntmVistaXFecha = new JMenuItem("Vista por Fecha");
		mnInfo.add(mntmVistaXFecha);
		
		mntmAcercaDe = new JMenuItem("Acerca De");
		mntmAcercaDe.addActionListener(this);
		mnInfo.add(mntmAcercaDe);
		
		mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(this);
		menuBar.add(mntmSalir);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==mntmRegistrar) {
			RegistrarReserva nuevoR = new RegistrarReserva();
			nuevoR.mandarDatos(restaurante);
			nuevoR.abrirConexion(inicioCon);
			nuevoR.setVisible(true);
		}
		else if(e.getSource()==mntmAltaMesa) {
			RegistrarMesa nuevaM = new RegistrarMesa();
			nuevaM.mandarDatos(restaurante);
			nuevaM.abrirConexion(inicioCon);
			nuevaM.setVisible(true);
		}
		else if(e.getSource()==mntmBajaMesa) {
			BorrarMesa borrarM = new BorrarMesa();
			borrarM.mandarDatos(restaurante);
			borrarM.abrirConexion(inicioCon);
			borrarM.setVisible(true);
		}
		else if(e.getSource()==mntmAcercaDe) {
			AcercaDe a = new AcercaDe();
			a.setVisible(true);
		}
		else if(e.getSource()==mntmVistaActual) {
			VistaActualM vistaA = new VistaActualM();
			VistaActualR vistaR = new VistaActualR();
			vistaA.mandarDatos(restaurante);
			vistaR.mandarDatos(restaurante);
			vistaA.llenarTabla();
			vistaR.llenarTabla();
			vistaA.setVisible(true);
			vistaR.setVisible(true);
		}
		
		else if(e.getSource()==mntmSalir) {
			System.exit(0);
		}
	}
}