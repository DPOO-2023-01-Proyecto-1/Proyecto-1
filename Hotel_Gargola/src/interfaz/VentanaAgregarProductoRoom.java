package interfaz;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class VentanaAgregarProductoRoom extends JFrame implements ActionListener {
	JFrame frame = new JFrame();
	JButton regresar;
	VentanaAdministrador vAdmin1;
	VentanaAgregarProductoRoom(VentanaAdministrador vAdmin)
	{	
		vAdmin1 = vAdmin;
		frame.setTitle("Agregar Producto Habitación");
		frame.setSize(new Dimension(750,750));
		
		regresar =  new JButton("Regresar");
		regresar.addActionListener(this);
		
		
		frame.add(regresar);
		
		
		frame.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==regresar)
		{	
			
			vAdmin1.getFrameAdmin().setVisible(true);
			frame.dispose();
		}
		
	}

}
