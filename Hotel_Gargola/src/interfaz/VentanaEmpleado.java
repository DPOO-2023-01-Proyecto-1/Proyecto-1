package interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VentanaEmpleado extends JFrame implements ActionListener {
	;
	JFrame frame = new JFrame();
	JLabel labelBienvenida = new JLabel("Bienvenido empleado");
	JPanel panelBotones = new JPanel();
	JButton bAddProductoRoom;
	JButton bAddServicioRoom;
	
	
	VentanaEmpleado()
	{
		frame.setSize(750,750);
		frame.setTitle("Ventana Empleado");
		panelBotones.setLayout(new GridLayout(1,2));
		panelBotones.setBounds(375, 375, 300, 200);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//----- INICIA BOTONES PANEL-------//
		bAddProductoRoom= new JButton("Agregar Producto");
		bAddServicioRoom= new JButton("Agregar Servicio");
		//------ADDS ACTION LISTENER BOTONES---------//
		bAddProductoRoom.addActionListener(this);
		bAddServicioRoom.addActionListener(this);
		//------AGREGA AL PANEL-------//
		panelBotones.add(bAddProductoRoom);
		panelBotones.add(bAddServicioRoom);
		
		
		//----AGREGA AL FRAME-----//
		frame.add(labelBienvenida, BorderLayout.NORTH);
		frame.add(panelBotones, BorderLayout.CENTER);
		
		
		
		frame.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == bAddProductoRoom) 
		{
			VentanaAgregarProductoRoom vAddProductRoom = new VentanaAgregarProductoRoom(this);
			frame.setVisible(false);
		}
		if (e.getSource() == bAddServicioRoom) 
		{
			VentanaAgregarServicioRoom vAddServicioRoom = new VentanaAgregarServicioRoom(this);
			frame.setVisible(false);
		}
		
		
		
		
	}
	//-----GETTERS----------//
			public JFrame getFrameEmpleado()
			{
				return frame;
			}

}
