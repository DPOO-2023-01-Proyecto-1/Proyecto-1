package interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VentanaAdministrador extends JFrame implements ActionListener {
	JFrame frame = new JFrame();
	JLabel labelBienvenida = new JLabel("Bienvenido administrador");
	JPanel panelBotones = new JPanel();
	JButton bAgregarHabitacion;
	JButton bAddProductoCat;
	JButton bAddServicioCat;
	JButton bAddProductoRoom;
	JButton bAddServicioRoom;
	JButton bAgregarUsuario;
	
	VentanaAdministrador()
	{
		frame.setSize(750,750);
		frame.setTitle("Ventana Administrador");
		panelBotones.setLayout(new GridLayout(2,3));
		panelBotones.setBounds(375, 375, 300, 200); //creo que esto no está haciendo ni mierda
		
		//----- INICIA BOTONES PANEL-------//
		bAgregarHabitacion = new JButton("Nueva Habitacion");
		bAddProductoCat= new JButton("Nuevo Producto");
		bAddServicioCat= new JButton("Nuevo Servicio");
		bAddProductoRoom= new JButton("Agregar Producto");
		bAddServicioRoom= new JButton("Agregar Servicio");
		bAgregarUsuario= new JButton("Nuevo Usuario");
		//------ADDS ACTION LISTENER BOTONES---------//
		bAgregarHabitacion.addActionListener(this);
		bAddProductoCat.addActionListener(this);
		bAddServicioCat.addActionListener(this);
		bAddProductoRoom.addActionListener(this);
		bAddServicioRoom.addActionListener(this);
		bAgregarUsuario.addActionListener(this);
		//------AGREGA AL PANEL-------//
		panelBotones.add(bAgregarHabitacion);
		panelBotones.add(bAddProductoCat);
		panelBotones.add(bAddServicioCat);
		panelBotones.add(bAddProductoRoom);
		panelBotones.add(bAddServicioRoom);
		panelBotones.add(bAgregarUsuario);
		
		//----AGREGA AL FRAME-----//
		frame.add(labelBienvenida, BorderLayout.NORTH);
		frame.add(panelBotones, BorderLayout.CENTER);
		frame.add(new JButton("Pruebita aña"), BorderLayout.SOUTH);
		
		
		
		
		frame.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==bAgregarHabitacion)
		{
			
		}
		if (e.getSource() == bAddProductoRoom) 
		{
			VentanaAgregarProductoRoom vAddProductRoom = new VentanaAgregarProductoRoom(this);
			frame.setVisible(false);
		}
		if (e.getSource() == bAddServicioRoom) 
		{
			
		}
		
		
	}
	
	//-----GETTERS----------//
	public JFrame getFrameAdmin()
	{
		return frame;
	}

}
