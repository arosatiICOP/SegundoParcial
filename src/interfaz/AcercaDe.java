package interfaz;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class AcercaDe extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnVolver;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AcercaDe frame = new AcercaDe();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AcercaDe() {
		setSize (410, 225);
		setTitle("Acerca de");
		procesos();
	}
	
	public void procesos() {
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("Nombre del Alumno:");
		label.setBounds(32, 10, 125, 23);
		contentPane.add(label);
		JLabel label_1 = new JLabel("Agustin");
		label_1.setBounds(42, 37, 81, 23);
		contentPane.add(label_1);

		JLabel label_2 = new JLabel("Apellido del Alumno:");
		label_2.setBounds(232, 10, 125, 23);
		contentPane.add(label_2);
		JLabel label_3 = new JLabel("Rosati");
		label_3.setBounds(253, 42, 69, 23);
		contentPane.add(label_3);

		JLabel label_4 = new JLabel("Fecha del Examen:");
		label_4.setBounds(32, 78, 125, 24);
		contentPane.add(label_4);
		JLabel label_5 = new JLabel("14-11-2023");
		label_5.setBounds(217, 76, 81, 33);
		contentPane.add(label_5);

		JLabel label_6 = new JLabel("Instancia del Examen:");
		label_6.setBounds(56, 113, 106, 33);
		contentPane.add(label_6);
		JLabel label_7 = new JLabel("Segundo parcial");
		label_7.setBounds(218, 118, 104, 23);
		contentPane.add(label_7);

        btnVolver = new JButton("Volver a la pantalla de inicio");
        btnVolver.setBounds(66, 152, 220, 23);
        btnVolver.addActionListener(this);
        contentPane.add(btnVolver);
    }

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnVolver) {
			dispose();
		}	
	}

}
