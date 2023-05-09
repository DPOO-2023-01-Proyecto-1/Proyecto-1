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
import modelo.Producto;

public class VentanaEliminarProductoCatalogo extends JFrame implements ActionListener{
	
	JFrame frame = new JFrame();
	JTextField codigoProducto;
	JButton regresar;
	JButton EraseProductCatalog;
	JComboBox <String> productosDisponibles;
	VentanaAdministrador vAdmin;
	

	public VentanaEliminarProductoCatalogo(VentanaAdministrador vAdmin1) {
		vAdmin = vAdmin1;
		frame.setTitle("Eliminar producto del catalogo");
		frame.setSize(new Dimension(750, 750));
		
		JPanel Panel = new JPanel(new GridLayout(2,2));

		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Panel.add(new JLabel("Product ID"));
		codigoProducto = new JTextField();
		Panel.add(codigoProducto);
		
		regresar = new JButton("Regresar");
		
		EraseProductCatalog = new JButton("Erase product from catalog");
		
		productosDisponibles = new JComboBox();
		try {
			BufferedReader brProductos;
			String linea = "";
			brProductos = new BufferedReader(new FileReader("./data/productos.txt"));
			while ((linea = brProductos.readLine()) != null) 
			{
				String[] partes = linea.split(";"); // Separa la linea por los ;
				String paraAgregar = partes[0] + "; " + partes[1] + "; " + partes[2] + ", " + partes[3];
				
				productosDisponibles.addItem(paraAgregar);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		regresar.addActionListener(this);
		EraseProductCatalog.addActionListener(this);
		
		
		frame.add(productosDisponibles,BorderLayout.NORTH);
		frame.add(regresar,BorderLayout.SOUTH);
		Panel.add(EraseProductCatalog);
		frame.add(Panel);
		
		frame.setVisible(true);
	}
	

	
	

	
	public void actionPerformed(ActionEvent e) {
	    if (e.getSource() == regresar) {
	        vAdmin.getFrameAdmin().setVisible(true);
	        frame.dispose();
	    } else if (e.getSource() == EraseProductCatalog) {
	        try {

	            String CodigoProducto = codigoProducto.getText();
	            Integer intCodigoProducto = Integer.parseInt(CodigoProducto);
	            String archivo = "./data/productos.txt";


	            Map<Integer, Producto> mapaProductos = Hotel.getMapaProductos();
	            Map<Integer, Habitacion> mapaHabitaciones = Hotel.getMapaHabitaciones();

	            boolean productoEncontro = false;

	            Administrador.deleteProductCatalog(mapaProductos, mapaHabitaciones, intCodigoProducto, archivo);
	            JOptionPane.showMessageDialog(null, "El producto con ID " + intCodigoProducto + " ha sido eliminado correctamente del catálogo.");
	            codigoProducto.setText("");
	            
	        } catch (NumberFormatException ex) {
	            JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido para el código del producto.");
	            codigoProducto.setText("");}
	    }
	}


	
}


