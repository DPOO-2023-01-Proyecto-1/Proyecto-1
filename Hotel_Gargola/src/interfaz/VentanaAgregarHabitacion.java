package interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import modelo.Administrador;
import modelo.Habitacion;
import modelo.Hotel;

public class VentanaAgregarHabitacion extends JFrame implements ActionListener{
	
	JFrame frame = new JFrame();
	JButton regresar;
	JButton AddNewRoom;
	VentanaAdministrador vAdmin;
	JComboBox <String> habitacionesActuales;
	
	JTextField roomID;
	JTextField roomValueByNight;
	JTextField roomType;
	JTextField roomGuestCapacity;
	JTextField roomAvailableServices;
	
	
	VentanaAgregarHabitacion (VentanaAdministrador vAdmin1)
	{
		vAdmin = vAdmin1;
		frame.setTitle("Agregar Habitacion");
		frame.setSize(new Dimension(750,750));
		
		JPanel Panel = new JPanel(new GridLayout(5,2));
		
		Panel.add(new JLabel("Room ID"));
		roomID = new JTextField();
		Panel.add(roomID);

		Panel.add(new JLabel("Room Value By Night"));
		roomValueByNight = new JTextField();
		Panel.add(roomValueByNight);

		Panel.add(new JLabel("Room Type"));
		roomType = new JTextField();
		Panel.add(roomType);

		Panel.add(new JLabel("Room Guest Capacity"));
		roomGuestCapacity = new JTextField();
		Panel.add(roomGuestCapacity);
		
		Panel.add(new JLabel("Room Available Services"));
		roomAvailableServices = new JTextField();
		Panel.add(roomAvailableServices);
		

		AddNewRoom = new JButton("Add New Room to Catalog");
		
		habitacionesActuales = new JComboBox();
		try {
			BufferedReader brServicios;
			String linea = "";
			brServicios = new BufferedReader(new FileReader("./data/habitaciones.txt"));
			while ((linea = brServicios.readLine()) != null) 
			{
				String[] partes = linea.split(";"); // Separa la linea por los ;
				String paraAgregar = "IdRoom: " + partes[0] + ", Available Services Codes: " + partes[2] + ", Room Type:" + partes[7];
				habitacionesActuales.addItem(paraAgregar);
			}
			
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		//------------CUADRAR LABEL------------//
		regresar =  new JButton("Regresar");
		
		//---------ACTIONS LISTENERS-----------//
		regresar.addActionListener(this);
		AddNewRoom.addActionListener(this);
		
		
		
		
		
		//--------------ADDERS----------//
		frame.add(regresar, BorderLayout.SOUTH);
		frame.add(AddNewRoom,BorderLayout.WEST);
		frame.add(habitacionesActuales, BorderLayout.NORTH);
		frame.add(Panel);
		
		
		
		frame.setVisible(true);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
	    if (e.getSource() == regresar) {
	        vAdmin.getFrameAdmin().setVisible(true);
	        frame.dispose();
	    } else if (e.getSource() == AddNewRoom) {
	        boolean HabitacionAgregada = false;
	        try {
	            String IdRoom = roomID.getText();
	            Integer intIdRoom = Integer.parseInt(IdRoom);

	            String priceByNightRoom = roomValueByNight.getText();
	            Integer intPriceByNightRoom = Integer.parseInt(priceByNightRoom);

	            String guestCapacityRoom = roomGuestCapacity.getText();
	            Integer intGuestCapacityRoom = Integer.parseInt(guestCapacityRoom);

	            String avaibleServicesRoom = roomAvailableServices.getText();

	            String typeRoom = roomType.getText();

	            String archivo = "./data/habitaciones.txt";

	            Map<Integer, Habitacion> mapaHabitaciones = Hotel.getMapaHabitaciones();
	            if(mapaHabitaciones.containsKey(intIdRoom)) {
	                JOptionPane.showMessageDialog(null, "La habitación ya existe", "Error al agregar habitación", JOptionPane.ERROR_MESSAGE);
	                roomID.setText("");
	                roomValueByNight.setText("");
	                roomGuestCapacity.setText("");
	                roomAvailableServices.setText("");
	                roomType.setText("");
	            } else {
	            	/*
	                Administrador.addRoom(mapaHabitaciones, intIdRoom, intPriceByNightRoom, typeRoom, intGuestCapacityRoom, avaibleServicesRoom, archivo);
	                */
	                HabitacionAgregada = true;
	                roomID.setText("");
	                roomValueByNight.setText("");
	                roomGuestCapacity.setText("");
	                roomAvailableServices.setText("");
	                roomType.setText("");
	            }
	        } catch (NumberFormatException ex) {
	            JOptionPane.showMessageDialog(null, "Los valores de capacidad de huéspedes y precio deben ser números enteros", "Error de formato", JOptionPane.ERROR_MESSAGE);
	        } catch (Exception ex) {
	            JOptionPane.showMessageDialog(null, "Error al agregar habitación: " + ex.getMessage(), "Error de aplicación", JOptionPane.ERROR_MESSAGE);
	        }

	        if (HabitacionAgregada) {
	            JOptionPane.showMessageDialog(null, "Habitación agregada correctamente", "Mensaje de Aprobación", JOptionPane.INFORMATION_MESSAGE);
	        }
	    }
	}


}
