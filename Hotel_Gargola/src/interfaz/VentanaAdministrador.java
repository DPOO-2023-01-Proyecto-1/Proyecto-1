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
	JButton bEliminarUsuario;
	JButton bEliminarProductoCatalogo;
	JButton bEliminarServicioCatalogo;
	JButton bDeleteRoom;
	
	VentanaAdministrador()
	{
		frame.setSize(750,750);
		frame.setTitle("Ventana Administrador");
		panelBotones.setLayout(new GridLayout(3,2));
		panelBotones.setBounds(375, 375, 300, 200); //creo que esto no está haciendo ni mierda
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//----- INICIA BOTONES PANEL-------//
		bAgregarHabitacion = new JButton("Nueva Habitacion");
		bAddProductoCat= new JButton("Nuevo Producto");
		bAddServicioCat= new JButton("Nuevo Servicio");
		bAgregarUsuario= new JButton("Nuevo Usuario");
		bEliminarUsuario= new JButton("Eliminar Usuario");
		bEliminarProductoCatalogo = new JButton("Eliminar Producto Catalogo");
		bEliminarServicioCatalogo = new JButton("Eliminar Servicio Catalogo");
		bDeleteRoom = new JButton("Eliminar Habitacion");
		//------ADDS ACTION LISTENER BOTONES---------//
		bAgregarHabitacion.addActionListener(this);
		bAddServicioCat.addActionListener(this);
		bAddProductoCat.addActionListener(this);
		bAgregarUsuario.addActionListener(this);
		bEliminarUsuario.addActionListener(this);
		bEliminarProductoCatalogo.addActionListener(this);
		bEliminarServicioCatalogo.addActionListener(this);
		bDeleteRoom.addActionListener(this);
		
		//------AGREGA AL PANEL-------//
		panelBotones.add(bAgregarHabitacion);
		panelBotones.add(bAddProductoCat);
		panelBotones.add(bAddServicioCat);
		panelBotones.add(bAgregarUsuario);
		panelBotones.add(bEliminarUsuario);
		panelBotones.add(bEliminarProductoCatalogo);
		panelBotones.add(bEliminarServicioCatalogo);
		panelBotones.add(bDeleteRoom);
		
		//----AGREGA AL FRAME-----//
		frame.add(labelBienvenida, BorderLayout.NORTH);
		frame.add(panelBotones, BorderLayout.CENTER);
		
		
		
		
		
		frame.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==bAgregarHabitacion)
		{
			VentanaAgregarHabitacion vAgregarHabitacion = new VentanaAgregarHabitacion(this);
			frame.setVisible(false);
		}
		else if (e.getSource() == bAddProductoCat) 
		{
			VentanaAgregarProductoCatalogo vAgregarProductoCatalogo = new VentanaAgregarProductoCatalogo(this);
			frame.setVisible(false);
		}
		else if (e.getSource() == bAddServicioCat) 
		{
			VentanaAgregarServicioCatalogo vAgregarServicioCatalogo = new VentanaAgregarServicioCatalogo(this);
			frame.setVisible(false);
		}
		else if (e.getSource() == bAgregarUsuario) 
		{
			VentanaAgregarUsuario vAgregarUsuario = new VentanaAgregarUsuario(this);
			frame.setVisible(false);
		}
		else if (e.getSource() == bEliminarUsuario) {
			
			VentanaEliminarUsuario vEliminarUsuario = new VentanaEliminarUsuario(this);
			frame.setVisible(false);
		}
		else if (e.getSource() == bEliminarProductoCatalogo) {
			
			VentanaEliminarProductoCatalogo vEliminarProductoCatalogo = new VentanaEliminarProductoCatalogo(this);
			frame.setVisible(false);
		}
		else if (e.getSource() == bEliminarServicioCatalogo) {
			
			VentanaEliminarServicioCatalogo vEliminarServicioCatalogo = new VentanaEliminarServicioCatalogo(this);
			frame.setVisible(false);
		}
		else if (e.getSource() == bDeleteRoom) {
			
			VentanaDeleteRoom vDeleteRoom = new VentanaDeleteRoom(this);
			frame.setVisible(false);
		}
		
		
	}
	
	//-----GETTERS----------//
	public JFrame getFrameAdmin()
	{
		return frame;
	}

}
