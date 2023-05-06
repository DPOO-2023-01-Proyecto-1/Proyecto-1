package interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import modelo.Recepcionista;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VentanaCheckIn extends JFrame implements ActionListener{
	
	
	JTextField bookingIdField ;
	JTextField entryDateField;
	JTextField departureDateField;
	JTextField associatedRoomsField;
	JTextField guestListField;
	JTextField associatedValueField;
	JTextField archivoField;
	
	
	JFrame frame = new JFrame();
	JButton regresar;
	JButton hacerCheckIn;
	VentanaRecepcionista vRecepcionista;
	
public	VentanaCheckIn(VentanaRecepcionista vRecepcionista1)
	
	{
		vRecepcionista = vRecepcionista1;
		frame.setTitle("Hacer Check In");
		frame.setSize(new Dimension(750,750));
		
		
		// Campos de texto para ingresar los datos de la reserva
	    JPanel Panel = new JPanel(new GridLayout(7, 2));

	    Panel.add(new JLabel("Booking ID"));
	    bookingIdField = new JTextField();
	    Panel.add(bookingIdField);

	    Panel.add(new JLabel("Entry date (yyyy-mm-dd)"));
	    entryDateField = new JTextField();
	    Panel.add(entryDateField);

	    Panel.add(new JLabel("Departure date (yyyy-mm-dd)"));
	    departureDateField = new JTextField();
	    Panel.add(departureDateField);

	    Panel.add(new JLabel("Associated rooms (comma-separated)"));
	    associatedRoomsField = new JTextField();
	    Panel.add(associatedRoomsField);

	    Panel.add(new JLabel("Guest list (comma-separated)"));
	    guestListField = new JTextField();
	    Panel.add(guestListField);

	    Panel.add(new JLabel("Associated value"));
	    associatedValueField = new JTextField();
	    Panel.add(associatedValueField);

	    Panel.add(new JLabel("Archivo"));
		regresar =  new JButton("Regresar");
		hacerCheckIn = new JButton("Hacer CheckIn");
		
		//---------ACTIONS LISTENERS-----------//
		regresar.addActionListener(this);
		hacerCheckIn.addActionListener(this);
		
		
		
		
		
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
		else if (e.getSource()==hacerCheckIn) {
	        Integer bookingId = Integer.parseInt(bookingIdField.getText());
	        String entryDate = entryDateField.getText();
	        String departureDate = departureDateField.getText();
	        ArrayList<Integer> associatedRooms = new ArrayList<Integer>();
	        for (String roomId : associatedRoomsField.getText().split(",")) {
	            associatedRooms.add(Integer.parseInt(roomId.trim()));
	        }
	        ArrayList<String> guestList = new ArrayList<String>();
	        for (String guest : guestListField.getText().split(",")) {
	            guestList.add(guest.trim());
	        }
	        Integer associatedValue = Integer.parseInt(associatedValueField.getText());
	        String archivo = archivoField.getText();
	        Recepcionista.checkIn(bookingId, entryDate, departureDate, associatedRooms, guestList, associatedValue, archivo);
	    
	}
		
	}

}
