package interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VentanaRecepcionista extends JFrame implements ActionListener {
	JFrame frame = new JFrame();
	JLabel labelBienvenida = new JLabel("Bienvenido recepcionista");
	JPanel panelBotones = new JPanel();
	JButton hacerReserva;
	JButton listaDeReservas;
	JButton hacerCheckIn;
	JButton hacerCheckOut;
	
	
	
	VentanaRecepcionista()
	{
		
		
		frame.setSize(750,750);
		frame.setTitle("Ventana Administrador");
		panelBotones.setLayout(new GridLayout(2,3));
		panelBotones.setBounds(375, 375, 300, 200); //creo que esto no está haciendo ni mierda
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//----- INICIA BOTONES PANEL-------//
		hacerCheckIn = new JButton("Hacer Check-in");
		hacerCheckOut= new JButton("Hacer Check-out");
		hacerReserva = new JButton("Registrarse");
		listaDeReservas = new JButton("Lista De Reservas");
		//------------ACTIONS LISTENERS------------//
		hacerCheckIn.addActionListener(this);
		hacerCheckOut.addActionListener(this);
		hacerReserva.addActionListener(this);
		listaDeReservas.addActionListener(this);
		
		//---------------AGREGA AL PANEL-------//
		panelBotones.add(hacerCheckIn);
		panelBotones.add(hacerCheckOut);
		panelBotones.add(hacerReserva);
		panelBotones.add(listaDeReservas);
		
		//----AGREGA AL FRAME------//
		frame.add(labelBienvenida, BorderLayout.NORTH);
		frame.add(panelBotones, BorderLayout.CENTER);
		
		frame.setVisible(true);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getSource()==hacerCheckIn) 
		{
			VentanaCheckIn vCheckIn = new VentanaCheckIn(this);
			frame.setVisible(false);
		}
		if (e.getSource()==hacerCheckOut) 
		{
			VentanaCheckOut vCheckOut = new VentanaCheckOut(this);
			frame.setVisible(false);
		}
		if (e.getSource()==hacerReserva) 
		{
			VentanaHacerReserva vHacerReserva = new VentanaHacerReserva(this);
			frame.setVisible(false);
		}
		if (e.getSource()==listaDeReservas) 
		{
			VentanaListaDeReservas vListaDeReservas = new VentanaListaDeReservas(this);
			frame.setVisible(false);
		}
		
		
	}
	
	//--------RETURNERS-------------//
	
	public JFrame getFrameRecepcionista()
	{
		return frame;
	}
	

}
