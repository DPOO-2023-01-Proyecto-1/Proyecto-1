package interfaz;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class VentanaAdministrador {
	JFrame frame = new JFrame();
	JLabel labelBienvenida = new JLabel("Bienvenido administrador");
	VentanaAdministrador()
	{
		frame.setSize(420,420);
		frame.add(labelBienvenida);
		
		
		
		
		
		frame.setVisible(true);
	}

}
