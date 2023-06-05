package interfazUsuario;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import interfaz.VentanaAgregarHabitacion;
import interfaz.VentanaAgregarProductoCatalogo;
import interfaz.VentanaAgregarServicioCatalogo;
import interfaz.VentanaAgregarUsuario;
import interfaz.VentanaEliminarProductoCatalogo;
import interfaz.VentanaEliminarServicioCatalogo;
import interfaz.VentanaEliminarUsuario;

public class VentanaCliente extends JFrame implements ActionListener {
	JFrame frame = new JFrame();
	JLabel labelBienvenida = new JLabel("Bienvenido cliente");
	JPanel panelBotones = new JPanel();
	JButton bVerReservas;
	JButton bVHacerReserva;
	JButton bVCargarMetodosDePago;
	
	
	
	VentanaCliente()
	{
		frame.setSize(750,750);
		frame.setTitle("Ventana Cliente");
		panelBotones.setLayout(new GridLayout(2,2));
		//panelBotones.setBounds(375, 375, 300, 200); 
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//----- INICIA BOTONES PANEL-------//
		
		bVerReservas = new JButton("Ver reservas disponibles");
		bVHacerReserva = new JButton("Hacer reserva");
		bVCargarMetodosDePago = new JButton("Cargar metodos de pago");
		
		
		//------ADDS ACTION LISTENER BOTONES---------//
		
		bVerReservas.addActionListener(this);
		bVHacerReserva.addActionListener(this);
		bVCargarMetodosDePago.addActionListener(this);
		;
		
		
		//------AGREGA AL PANEL-------//
		panelBotones.add(bVerReservas);
		panelBotones.add(bVHacerReserva);
		panelBotones.add(bVCargarMetodosDePago);
		
		
		
		
		//----AGREGA AL FRAME-----//
		frame.add(labelBienvenida, BorderLayout.NORTH);
		frame.add(panelBotones, BorderLayout.CENTER);
		
		
	
		
		frame.setVisible(true);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==bVerReservas)
		{
			VentanaVerReservas vVerReservas = new VentanaVerReservas(this);
			frame.setVisible(false);
		}
		else if (e.getSource() == bVHacerReserva) 
		{
			VentanaHacerReservas vHacerReservas = new VentanaHacerReservas(this);
			frame.setVisible(false);
		}
		else if (e.getSource() == bVCargarMetodosDePago)
		{
			VentanaCargarMetodosDePago vCargarMetodosDePago = new VentanaCargarMetodosDePago(this);
			frame.setVisible(false);
		}
		
		
		
		
	}
	
	//-----GETTERS----------//
	public JFrame getFrameCliente()
	{
		return frame;
	}

}