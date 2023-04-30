package interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class VentanaAgregarServicioRoom extends JFrame implements ActionListener {
	JFrame frame = new JFrame();
	JButton regresar;
	VentanaEmpleado vEmpleado1;
	JTextField codigoHabitacion;
	JLabel indiqueCodigo;
	VentanaAgregarServicioRoom(VentanaEmpleado vEmpleado)
	{	
		vEmpleado1 = vEmpleado;
		frame.setTitle("Agregar Producto Habitación");
		frame.setSize(new Dimension(750,750));
		//--------CUADRAR LABEL---------//
		indiqueCodigo = new JLabel();
		indiqueCodigo.setText("Ingrese la habitación");
		
		indiqueCodigo.setBounds(0,0,200,100);
		regresar =  new JButton("Regresar");
	
		codigoHabitacion = new JTextField();
		
		
		frame.add(indiqueCodigo);
		frame.add(codigoHabitacion);
		frame.add(regresar, BorderLayout.SOUTH);
		
		
		//---------ACTIONS LISTENERS-----------//
		regresar.addActionListener(this);
		
		frame.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==regresar)
		{	
			
			vEmpleado1.getFrameEmpleado().setVisible(true);
			frame.dispose();
		}
		
	}

}
