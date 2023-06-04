package interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.ui.RefineryUtilities;

public class VentanaRecepcionista extends JFrame implements ActionListener {
	JFrame frame = new JFrame();
	JLabel labelBienvenida = new JLabel("Bienvenido recepcionista");
	JPanel panelBotones = new JPanel();
	JButton hacerReserva;
	JButton eProductos;
	JButton eServicios;
	JButton listaDeReservas;
	JButton hacerCheckIn;
	JButton hacerCheckOut;
	
	
	
	VentanaRecepcionista()
	{
		
		
		frame.setSize(750,750);
		frame.setTitle("Ventana Recepcionista");
		panelBotones.setLayout(new GridLayout(2,3));
		panelBotones.setBounds(375, 375, 300, 200); //creo que esto no está haciendo ni mierda
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//----- INICIA BOTONES PANEL-------//
		hacerCheckIn = new JButton("Hacer Check-in");
		hacerCheckOut= new JButton("Hacer Check-out");
		hacerReserva = new JButton("Registrarse");
		listaDeReservas = new JButton("Ocupación en un año");
		eServicios = new JButton("Estadisticas de servicios");
		eProductos = new JButton ("Estadisticas de productos");
		//------------ACTIONS LISTENERS------------//
		hacerCheckIn.addActionListener(this);
		hacerCheckOut.addActionListener(this);
		hacerReserva.addActionListener(this);
		listaDeReservas.addActionListener(this);
		eProductos.addActionListener(this);
		eServicios.addActionListener(this);
		
		//---------------AGREGA AL PANEL-------//
		panelBotones.add(hacerCheckIn);
		panelBotones.add(hacerCheckOut);
		panelBotones.add(hacerReserva);
		panelBotones.add(listaDeReservas);
		panelBotones.add(eProductos);
		panelBotones.add(eServicios);
		
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
			DiagramaBarras diagrama = new DiagramaBarras("Ocupación en un año", "Ocupación en un año");
			
			diagrama.pack();
			RefineryUtilities.centerFrameOnScreen( diagrama );
			
			diagrama.setVisible(true);
		}
		if(e.getSource()==eServicios)
		{
			VEstadisticasServicios vServicios = null;
			VServiciosPrecios vServiciosPrecios = null;
			try {
				vServicios = new VEstadisticasServicios("Estadisticas de servicios", "Estadisticas de servicios");
				vServiciosPrecios = new VServiciosPrecios("Estadisticas de precios de productos", "Estadisticas de precios de productos");
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			vServicios.pack();
			vServicios.setVisible(true);
			vServiciosPrecios.pack();
			vServiciosPrecios.setVisible(true);
		}

		if(e.getSource()==eProductos)
		{
			VEstadisticasProductos vProductos = null;
			VEstadisticasProductosPrecios vProductosPrecios = null;
			try {
				vProductos = new VEstadisticasProductos("Estadisticas de productos", "Estadisticas de productos");
				vProductosPrecios = new VEstadisticasProductosPrecios("Estadisticas de precios de productos", "Estadisticas de precios de productos");
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			vProductos.pack();
			vProductos.setVisible(true);
			vProductosPrecios.pack();
			vProductosPrecios.setVisible(true);
		}

		
		
		
	}
	
	//--------RETURNERS-------------//
	
	public JFrame getFrameRecepcionista()
	{
		return frame;
	}
	

}
