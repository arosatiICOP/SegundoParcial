package interfaz;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import baseDatos.MesaBD;
import logica.Restaurante;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;

public class Menu extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JMenuItem mntmVistaActual;
	private JMenuItem mntmAcercaDe;
	private JMenuItem mntmLambdaM;
	private JMenuItem mntmSalir;
	private Restaurante restaurante;
	private JMenuItem mntmBajaMesa;
	private JMenuItem mntmAltaMesa;
	private JMenuItem mntmRegistrar;
	private JMenuItem mntmOcuparM;
	private JMenuItem mntmLiberarM;
	private MesaBD mesaDB;
	private int idResto;
	private JMenuItem mntmMesasRent;
	private JMenuItem mntmLibres;
	private JMenuItem mntmOcupadas;
	private JMenuItem mntmReservadas;

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
		setLocationRelativeTo(this);
		setSize(450, 300);
		mesaDB = new MesaBD();
		procesos();
	}
	
	public void procesos(){
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 434, 25);
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
		
		mntmOcuparM = new JMenuItem("Ocupar Mesa");
		mntmOcuparM.addActionListener(this);
		mnGestion.add(mntmOcuparM);
		
		mntmBajaMesa = new JMenuItem("Baja Mesa");
		mntmBajaMesa.addActionListener(this);
		
		mntmLiberarM = new JMenuItem("Liberar Mesa");
		mnGestion.add(mntmLiberarM);
		mntmLiberarM.addActionListener(this);
		mnGestion.add(mntmBajaMesa);
		
		
		JMenu mnInfo = new JMenu("Info");
		menuBar.add(mnInfo);
		
		mntmVistaActual = new JMenuItem("Vista Actual");
		mntmVistaActual.addActionListener(this);
		mnInfo.add(mntmVistaActual);
		
		mntmLambdaM = new JMenuItem("Mesas Lambda");
		mntmLambdaM.addActionListener(this);
		mnInfo.add(mntmLambdaM);
		
		mntmMesasRent = new JMenuItem("Mesas Rentables");
		mntmMesasRent.addActionListener(this);
		
		JMenu mnInfoMesas = new JMenu("InfoMesas Lambda");
		mnInfo.add(mnInfoMesas);
		
		mntmLibres = new JMenuItem("Libres");
		mntmLibres.addActionListener(this);
		mnInfoMesas.add(mntmLibres);
		
		mntmOcupadas = new JMenuItem("Ocupadas");
		mntmOcupadas.addActionListener(this);
		mnInfoMesas.add(mntmOcupadas);
		
		mntmReservadas = new JMenuItem("Reservadas");
		mntmReservadas.addActionListener(this);
		mnInfoMesas.add(mntmReservadas);
		mnInfo.add(mntmMesasRent);
		
		mntmAcercaDe = new JMenuItem("Acerca De");
		mntmAcercaDe.addActionListener(this);
		mnInfo.add(mntmAcercaDe);
		
		mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(this);
		menuBar.add(mntmSalir);
		
		JLabel lblTitulo = new JLabel("Menu Principal");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblTitulo.setBounds(139, 110, 210, 35);
		contentPane.add(lblTitulo);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==mntmRegistrar) {
			RegistrarReserva nuevoR = new RegistrarReserva();
			nuevoR.mandarDatos(this.restaurante);
			nuevoR.setVisible(true);
		}
		else if(e.getSource()==mntmAltaMesa) {
			RegistrarMesa nuevaM = new RegistrarMesa();
			nuevaM.mandarDatos(this.restaurante);
			nuevaM.setVisible(true);
		}
		else if(e.getSource()==mntmBajaMesa) {
			DeshabMesa borrarM = new DeshabMesa();
			borrarM.mandarDatos(this.restaurante);
			try {
				borrarM.llenarTablaMesasL();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			borrarM.setVisible(true);
		}
		else if(e.getSource()==mntmAcercaDe) {
			AcercaDe a = new AcercaDe();
			a.setVisible(true);
		}
		else if(e.getSource()==mntmVistaActual) {
			VistaActualRyM vistaR = new VistaActualRyM();
			vistaR.mandarDatos(this.restaurante);
			try {
				vistaR.llenarTablaM();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			vistaR.llenarTablaR();
			vistaR.setVisible(true);
		}
		
		else if (e.getSource() == mntmOcuparM) {
		    try {
		        OcuparMesa ocupar = new OcuparMesa();
		        ocupar.mandarDatos(this.restaurante);
		        ocupar.llenarTablaMesaR();
		        ocupar.setVisible(true);
		    } catch (SQLException e1) {
		        e1.printStackTrace();
		    }
		}
		else if (e.getSource() == mntmLiberarM) {
		    try {
		        LiberarMesa l = new LiberarMesa();
		        l.mandarDatos(this.restaurante);
		        l.llenarTablaMesaO();
		        l.setVisible(true);
		    } catch (SQLException e1) {
		        e1.printStackTrace();
		    }
		}
		
		else if (e.getSource() == mntmLambdaM) {
		    mesaDB.mostrarRes(idResto);
		}
		else if (e.getSource() == mntmMesasRent) {
		    mesaDB.top3Mesas(idResto);
		}
		else if (e.getSource() == mntmLibres) {
		    mesaDB.mostrarMesasL(idResto);
		}
		else if (e.getSource() == mntmOcupadas) {
		    mesaDB.mostrarMesasO(idResto);
		}
		else if (e.getSource() == mntmReservadas) {
		    mesaDB.mostrarMesasR(idResto);
		}
		else if(e.getSource()==mntmSalir) {
			JOptionPane.showMessageDialog(null,"Volviendo a seleccion de restaurante...","Salir de la sesion", JOptionPane.INFORMATION_MESSAGE);
			dispose();
		}
	}

	public void mandarDatos(Restaurante restoSelec) {
		this.restaurante=restoSelec;
		this.idResto=restaurante.getNroRestaurante();
		setTitle("Restaurante '"+restaurante.getNombre()+"'");
	}
}