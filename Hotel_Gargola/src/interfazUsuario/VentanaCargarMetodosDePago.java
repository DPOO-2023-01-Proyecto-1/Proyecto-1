package interfazUsuario;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import modelo.ClaseDinamica;

import modelo.Hotel;
import modelo.IMetodoDePago;

public class VentanaCargarMetodosDePago extends JFrame implements ActionListener {


	JFrame frame = new JFrame();
	JPanel panelSur;
	
	JButton regresar;
	JButton agregar;
	JButton cargar;
	ClaseDinamica metodoDePago;
	Hotel hotel = new Hotel();
	VentanaCliente vCliente1;
	
	JComboBox<String> comboBox;
	ArrayList<String> selectedMethods;




	VentanaCargarMetodosDePago(VentanaCliente vCliente)
	{	
		
		vCliente1 = vCliente;
		frame.setTitle("Cargar Metodos De Pago");
		frame.setSize(new Dimension(750,750));
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//--------CUADRAR LABEL---------//
		
		
		panelSur = new JPanel();
		panelSur.setLayout(new GridLayout(2,2));
		
		regresar =  new JButton("Regresar");
		agregar = new JButton("Agregar");
		cargar = new JButton("Cargar");
		comboBox = new JComboBox<>();
		
		selectedMethods = new ArrayList<>();
		
		
		loadPaymentMethods();
		
		

		

		
		
		
		//---------ACTIONS LISTENERS-----------//
		regresar.addActionListener(this);
		agregar.addActionListener(this);
		cargar.addActionListener(this);
		
	
		
		//--------------ADDERS----------//
		
		panelSur.add(comboBox);
		panelSur.add(regresar);
		panelSur.add(agregar);
		panelSur.add(cargar);
		
		
		
		
		frame.add(panelSur);
		
		
		
		
		frame.setVisible(true);
	}
	
	
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()== regresar)
		{	
			
			vCliente1.getFrameCliente().setVisible(true);
			frame.dispose();
		}
		else if (e.getSource() == agregar) {
	        String selectedMethod = (String) comboBox.getSelectedItem();
	        selectedMethods.add(selectedMethod);
	        JOptionPane.showMessageDialog(frame, "El método de pago se ha agregado correctamente.");
	    }
		else if (e.getSource() == cargar) {
		    // Perform the payment methods loading process using the selectedMethods ArrayList
			List<Object> objetosMetodosPago = ClaseDinamica.crearObjetos(selectedMethods);
			abrirVentanaPagar(objetosMetodosPago);
			frame.dispose();
			
		}
		
	}
	
	
	private void abrirVentanaPagar(List<Object> objetosMetodosPago) {
        VentanaPagarReserva ventanaPagar = new VentanaPagarReserva(objetosMetodosPago);
        ventanaPagar.setVisible(true);
    }
	
	
	private void loadPaymentMethods() {
	    try (BufferedReader reader = new BufferedReader(new FileReader("data/metodosDePago.txt"))) {
	        String line;
	        while ((line = reader.readLine()) != null) {
	            comboBox.addItem(line);
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	

	
	
	
	

}
