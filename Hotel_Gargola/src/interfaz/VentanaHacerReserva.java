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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VentanaHacerReserva extends JFrame implements ActionListener{
	
	JFrame frame = new JFrame();
	JButton registrarse;
	JButton regresar;
	JTextField nombre;
	JTextField edad;
	JTextField correo;
	VentanaRecepcionista vRecepcionista;
	
	VentanaHacerReserva(VentanaRecepcionista vRecepcionista1)
	{
		vRecepcionista = vRecepcionista1;
		frame.setTitle("Hacer Reserva");
		frame.setSize(new Dimension(750,750));
		
		// Campos de texto para ingresar los datos de la reserva
	    JPanel Panel = new JPanel(new GridLayout(5, 2));

	    

	    Panel.add(new JLabel("Ingrese su nombre:"));
	    nombre = new JTextField();
	    Panel.add(nombre);

	    Panel.add(new JLabel("ingrese su edad:"));
	    edad= new JTextField();
	    Panel.add(edad);

	    Panel.add(new JLabel("Ingrese su correo:"));
	    correo = new JTextField();
	    Panel.add(correo);

		regresar =  new JButton("Regresar");
		registrarse = new JButton("Registrarse");
		
		
		//------------CUADRAR LABEL------------//
		regresar =  new JButton("Regresar");
		registrarse = new JButton("Registarse");
		
		//---------ACTIONS LISTENERS-----------//
		regresar.addActionListener(this);
		registrarse.addActionListener(this);
		
		
		
		
		
		//--------------ADDERS----------//
		frame.add(regresar, BorderLayout.SOUTH);
		frame.add(registrarse,BorderLayout.WEST);
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
		else if (e.getSource()==registrarse){
			boolean usuario = false;
			try {
	        String name = nombre.getText();
	        String age = edad.getText();
	        Integer age2 = Integer.parseInt(age);
	        String email = correo.getText();
	        String archivo = "./data/huespedes.txt";
	        Integer guestID = Recepcionista.generateID();
            Recepcionista.crearHuesped(name, age2, email, archivo,guestID);    
            
            usuario = true;
			nombre.setText("");
			edad.setText("");
			correo.setText("");
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(null, "Los valores de precio e ID deben ser números enteros",
					"Error de formato", JOptionPane.ERROR_MESSAGE);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error al regustrar huesped: " + ex.getMessage(),
					"Error de aplicación", JOptionPane.ERROR_MESSAGE);
		}

		if (usuario) {
			JOptionPane.showMessageDialog(null, "Huesped registrado correctamente", "Mensaje de Aprobación",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}
}

}
