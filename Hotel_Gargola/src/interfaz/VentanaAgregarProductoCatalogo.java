package interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class VentanaAgregarProductoCatalogo extends JFrame implements ActionListener{
	
	JFrame frame = new JFrame();
	JButton regresar;
	VentanaAdministrador vAdmin;
	
	VentanaAgregarProductoCatalogo (VentanaAdministrador vAdmin1)
	{
		vAdmin = vAdmin1;
		frame.setTitle("Agregar Producto al Catalogo");
		frame.setSize(new Dimension(750,750));
		
		
		//------------CUADRAR LABEL------------//
		regresar =  new JButton("Regresar");
		
		//---------ACTIONS LISTENERS-----------//
		regresar.addActionListener(this);
		
		
		
		
		
		//--------------ADDERS----------//
		frame.add(regresar, BorderLayout.SOUTH);
		
		
		
		frame.setVisible(true);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==regresar)
		{	
			
			vAdmin.getFrameAdmin().setVisible(true);
			frame.dispose();
		}
		
	}

}