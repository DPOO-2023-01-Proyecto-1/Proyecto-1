package interfaz;



import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class VentanaHacerReserva extends JFrame implements ActionListener{
	
	JFrame frame = new JFrame();
	JButton regresar;
	VentanaRecepcionista vRecepcionista;
	
	VentanaHacerReserva(VentanaRecepcionista vRecepcionista1)
	{
		vRecepcionista = vRecepcionista1;
		frame.setTitle("Hacer Reserva");
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
			
			vRecepcionista.getFrameRecepcionista().setVisible(true);
			frame.dispose();
		}
		
	}

}
