package interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import modelo.Recepcionista;

public class VentanaCheckOut extends JFrame implements ActionListener{
	
	JFrame frame = new JFrame();
	JTextField bookingIdField ;
	JButton regresar;
	JButton hacerCheckOut;
	JButton pagar;
	JPanel panelSur = new JPanel();;
	VentanaRecepcionista vRecepcionista;
	
	VentanaCheckOut(VentanaRecepcionista vRecepcionista1)
	{
		vRecepcionista = vRecepcionista1;
		frame.setTitle("Hacer Check out");
		frame.setSize(new Dimension(750,750));
		panelSur.setLayout(new GridLayout(2,1));
		
		// Campos de texto para ingresar los datos de la reserva
	    JPanel Panel = new JPanel(new GridLayout(1, 2));

	    

	    Panel.add(new JLabel("Ingrese su numero de reserva: "));
	    bookingIdField = new JTextField();
	    Panel.add(bookingIdField);
		
		
		//------------CUADRAR LABEL------------//
	    
		regresar =  new JButton("Regresar");
		hacerCheckOut = new JButton("Hacer CheckOut");
		pagar = new JButton("Pagar");
		
		//---------ACTIONS LISTENERS-----------//
		pagar.addActionListener(this);
		regresar.addActionListener(this);
		hacerCheckOut.addActionListener(this);
		
		panelSur.add(pagar);
		panelSur.add(regresar);

		
		
		
		//--------------ADDERS----------//
		frame.add(panelSur, BorderLayout.SOUTH);

		frame.add(hacerCheckOut, BorderLayout.WEST);
		frame.add(Panel);
		
		
		
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
		if (e.getSource()==pagar)
		{
			VentanaPagos vPagos = new VentanaPagos(this);
			frame.setVisible(false);
		}
		
		else if (e.getSource()==hacerCheckOut) {
			boolean checkout = false;
			try {
	        
	        String entryDate = bookingIdField.getText();
	        Integer bookingID = Integer.parseInt(entryDate);
	        String archivo = "./data/huespedes.txt";
	        String archivo2 = "./data/reservas.txt";
	        Recepcionista.checkOut(bookingID, archivo2, archivo);
	        checkout = true;
			bookingIdField.setText("");

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(null, "error "+ex.getMessage(),
					"Error de formato", JOptionPane.ERROR_MESSAGE);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error al hacer Check-out: " + ex.getMessage(),
					"Error de aplicación", JOptionPane.ERROR_MESSAGE);
		}

		if (checkout) {
			JOptionPane.showMessageDialog(null, "Ha completado el Check-out exitosamente", "Mensaje de Aprobación",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
	public JFrame getFrameCheckOut()
	{
		return frame;
	}

}
