package interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import modelo.Hotel;
import modelo.Empleado;
import modelo.Habitacion;
import modelo.Producto;

public class VentanaAgregarProductoRoom extends JFrame implements ActionListener {
	JFrame frame = new JFrame();
	JPanel panelSur;
	JPanel panelNorte;
	JButton regresar;
	Hotel hotel = new Hotel();
	VentanaEmpleado vEmpleado1;
	JTextField codigoHabitacion;

	JComboBox <String> productosDisponibles; //será que cambio a prdocutto?
	JLabel indiqueCodigo;
	JButton agregarProducto;
	VentanaAgregarProductoRoom(VentanaEmpleado vEmpleado)
	{	
		
		vEmpleado1 = vEmpleado;
		frame.setTitle("Agregar Producto Habitación");
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
		agregarProducto = new JButton("Agregar producto");
		productosDisponibles = new JComboBox();
		try {
			BufferedReader brProductos;
			String linea = "";
			brProductos = new BufferedReader(new FileReader("./data/productos.txt"));
			while ((linea = brProductos.readLine()) != null) 
			{
				String[] partes = linea.split(";"); // Separa la linea por los ;
				String paraAgregar = partes[0] + "; " + partes[1] + "; " + partes[2] + ", " + partes[3]+ "; ";
				//System.out.println(partes[0]);
				productosDisponibles.addItem(paraAgregar);
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
		agregarProducto.addActionListener(this);
		//--------------ADDERS----------//
		panelNorte.add(indiqueCodigo);
		panelNorte.add(codigoHabitacion);
		panelSur.add(agregarProducto);
		panelSur.add(regresar);
		
		
		frame.add(panelNorte, BorderLayout.NORTH);
		frame.add(productosDisponibles, BorderLayout.CENTER);
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
		if (e.getSource() == agregarProducto)
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
				String linea = productosDisponibles.getSelectedItem().toString();
				String[] partes = linea.split(";");
				String codigoProducto = partes[0];
		        Empleado.addProductRoom(rutaArchivo, intHabitacion, codigoProducto, hotel.getMapaHabitaciones());
		        JOptionPane.showMessageDialog(null, "Producto agregado", "Mensaje de confirmación", JOptionPane.PLAIN_MESSAGE);
				}
			}
			
			
		}
	}

}
