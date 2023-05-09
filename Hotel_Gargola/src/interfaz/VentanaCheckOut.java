package interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
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
	VentanaRecepcionista vRecepcionista;
	
	VentanaCheckOut(VentanaRecepcionista vRecepcionista1)
	{
		vRecepcionista = vRecepcionista1;
		frame.setTitle("Hacer Check out");
		frame.setSize(new Dimension(750,750));
		
		// Campos de texto para ingresar los datos de la reserva
	    JPanel Panel = new JPanel(new GridLayout(1, 2));

	    

	    Panel.add(new JLabel("Ingrese su numero de reserva: "));
	    bookingIdField = new JTextField();
	    Panel.add(bookingIdField);
		
		
		//------------CUADRAR LABEL------------//
		regresar =  new JButton("Regresar");
		hacerCheckOut = new JButton("Hacer CheckOut");
		
		//---------ACTIONS LISTENERS-----------//
		regresar.addActionListener(this);
		hacerCheckOut.addActionListener(this);
		
		
		
		
		
		//--------------ADDERS----------//
		frame.add(regresar, BorderLayout.SOUTH);
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
		
		else if (e.getSource()==hacerCheckOut) {
			boolean checkout = false;
			try {
	        
	        String entryDate = bookingIdField.getText();
	        Integer bookingID = Integer.parseInt(entryDate);
	        String archivo = "./data/huespedes.txt";
	        String archivo2 = "./data/reservas.txt";
	        Recepcionista.checkOut(bookingID, archivo, archivo2);
	        checkout = true;
			bookingIdField.setText("");

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(null, "Los valores de precio e ID deben ser números enteros",
					"Error de formato", JOptionPane.ERROR_MESSAGE);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error al hacer Check-In: " + ex.getMessage(),
					"Error de aplicación", JOptionPane.ERROR_MESSAGE);
		}

		if (checkout) {
			JOptionPane.showMessageDialog(null, "Ha completado el Check-In exitosamente", "Mensaje de Aprobación",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}
}

}
