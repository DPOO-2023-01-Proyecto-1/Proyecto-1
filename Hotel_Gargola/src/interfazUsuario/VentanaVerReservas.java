package interfazUsuario;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import modelo.Hotel;
import modelo.Empleado;
import modelo.Habitacion;
import modelo.Producto;

public class VentanaVerReservas extends JFrame implements ActionListener {
	JFrame frame = new JFrame();
	JPanel panelSur;
	
	JButton regresar;
	Hotel hotel = new Hotel();
	VentanaCliente vCliente1;




	VentanaVerReservas(VentanaCliente vCliente)
	{	
		
		vCliente1 = vCliente;
		frame.setTitle("Ver Reservas Disponibles");
		frame.setSize(new Dimension(750,750));
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//--------CUADRAR LABEL---------//
		panelSur = new JPanel();
		panelSur.setLayout(new GridLayout());
		
		
		
		
		
		regresar =  new JButton("Regresar");
		
		
		//---------ACTIONS LISTENERS-----------//
		regresar.addActionListener(this);
	
		
		//--------------ADDERS----------//
		
		
		panelSur.add(regresar);
		
		
		
		frame.add(panelSur, BorderLayout.SOUTH);
		
		
		
		
		frame.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==regresar)
		{	
			
			vCliente1.getFrameCliente().setVisible(true);
			frame.dispose();
		}
		
	}
}