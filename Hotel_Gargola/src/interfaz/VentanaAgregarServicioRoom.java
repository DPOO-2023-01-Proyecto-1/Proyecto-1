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

import modelo.Empleado;
import modelo.Habitacion;
import modelo.Hotel;

public class VentanaAgregarServicioRoom extends JFrame implements ActionListener {
	JFrame frame = new JFrame();
	JPanel panelSur;
	JPanel panelNorte;
	JButton regresar;
	Hotel hotel = new Hotel();
	VentanaEmpleado vEmpleado1;
	JTextField codigoHabitacion;

	JComboBox <String> serviciosDisponibles; //será que cambio a prdocutto?
	JLabel indiqueCodigo;
	JButton agregarServicio;
	Empleado empleado = new Empleado("pepito","asdf","empleado");
	VentanaAgregarServicioRoom(VentanaEmpleado vEmpleado)
	{	
		
		vEmpleado1 = vEmpleado;
		frame.setTitle("Agregar Servicio Habitación");
		frame.setSize(new Dimension(750,750));
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//--------CUADRAR LABEL---------//
		panelSur = new JPanel();
		panelSur.setLayout(new GridLayout(2,1));
		panelNorte = new JPanel();
		panelNorte.setLayout(new GridLayout(1,2));
		
		indiqueCodigo = new JLabel();
		indiqueCodigo.setText("Ingrese la habitación");
		codigoHabitacion = new JTextField();
		codigoHabitacion.setText("ptp");
		agregarServicio = new JButton("Agregar servicio");
		serviciosDisponibles = new JComboBox();
		try {

			String linea = "";
			BufferedReader brServicios = new BufferedReader(new FileReader("./data/servicios.txt"));
			
			while ((linea = brServicios.readLine()) != null) {
				String[] partes = linea.split(";"); // Separa la linea por los ;
				String paraAgregar = partes[0] + "; " + partes[1] + "; " + partes[2] + "; " + partes[3];
				serviciosDisponibles.addItem(paraAgregar);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		indiqueCodigo.setBounds(0,0,200,100);
		regresar =  new JButton("Regresar");
		
		
		//---------ACTIONS LISTENERS-----------//
		regresar.addActionListener(this);
	
		codigoHabitacion = new JTextField();
		agregarServicio.addActionListener(this);
		//--------------ADDERS----------//
		panelNorte.add(indiqueCodigo);
		panelNorte.add(codigoHabitacion);
		panelSur.add(agregarServicio);
		panelSur.add(regresar);
		
		
		frame.add(panelNorte, BorderLayout.NORTH);
		frame.add(serviciosDisponibles, BorderLayout.CENTER);
		frame.add(panelSur, BorderLayout.SOUTH);
		
		
		
		
		frame.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==regresar)
		{	
			
			vEmpleado1.getFrameEmpleado().setVisible(true);
			frame.dispose();
		}
		if (e.getSource() == agregarServicio)
		{
			if(codigoHabitacion.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(null, "Indique la habitación", "Mensaje de error", JOptionPane.WARNING_MESSAGE);
			}
			else 
			{
				int intHabitacion = Integer.parseInt(codigoHabitacion.getText());
				Map <Integer, Habitacion> mapaHabitaciones = hotel.getMapaHabitaciones();
				if (mapaHabitaciones.get(intHabitacion)== null)
				{
					JOptionPane.showMessageDialog(null, "Habitacion no encontrada", "Mensaje de error", JOptionPane.WARNING_MESSAGE);
				}
				else 
				{
					
				String rutaArchivo = "./data/habitaciones.txt";
				String linea = serviciosDisponibles.getSelectedItem().toString();
				String[] partes = linea.split(";");
				String codigoProducto = partes[0];
		        empleado.addServiceRoom(rutaArchivo, intHabitacion, codigoProducto, hotel.getMapaHabitaciones());
				}
			}
			
			
		}
	}

}