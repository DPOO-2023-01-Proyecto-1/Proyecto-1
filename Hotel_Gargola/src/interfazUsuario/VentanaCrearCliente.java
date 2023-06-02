package interfazUsuario;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import modelo.Hotel;

public class VentanaCrearCliente extends JFrame implements ActionListener {
	JFrame frame = new JFrame();
	JPanel panelSur;
	
	JButton regresar;
	Hotel hotel = new Hotel();
	VInicioUsuario vInicio1;




	VentanaCrearCliente(VInicioUsuario vInicio)
	{	
		
		vInicio1 = vInicio;
		frame.setTitle("Crear cliente");
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
			
			vInicio1.setVisible(true);
			frame.dispose();
		}
		
	}
}