package interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import modelo.Recepcionista;

public class VentanaPagos extends JFrame implements ActionListener{
	
	JFrame frame = new JFrame();
	
	VentanaCheckOut vCheckOut;
	JComboBox <String> productosDisponibles;
	VentanaPagos(VentanaCheckOut vCheckOut1)
	{
		vCheckOut = vCheckOut1;
		frame.setTitle("Pagos");
		frame.setSize(new Dimension(750,750));
		
		
		// Campos de texto para ingresar los datos de la reserva


	    


		
		
		//------------CUADRAR LABEL------------//
		
		
		//---------ACTIONS LISTENERS-----------//

		
		
		
		
		//--------------ADDERS----------//


		
		
		
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
