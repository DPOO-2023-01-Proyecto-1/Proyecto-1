package interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import modelo.Administrador;
import modelo.Hotel;

public class VentanaAgregarServicioCatalogo extends JFrame implements ActionListener {

	JFrame frame = new JFrame();
	JButton regresar;
	JTextField IdServiceCatalog;
	JTextField PriceServiceCatalog;
	JTextField DescriptionServiceCatalog;
	JTextField NameServiceCatalog;
	JComboBox <String> serviciosDisponibles;

	VentanaAdministrador vAdmin;
	JButton AddNewServiceCatalog;

	VentanaAgregarServicioCatalogo(VentanaAdministrador vAdmin1) {
		vAdmin = vAdmin1;
		frame.setTitle("Agregar Servicio A Catalogo");
		frame.setSize(new Dimension(750, 750));
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

		JPanel Panel = new JPanel(new GridLayout(4, 2));

		Panel.add(new JLabel("Service Name"));
		NameServiceCatalog = new JTextField();
		Panel.add(NameServiceCatalog);

		Panel.add(new JLabel("Service New ID"));
		IdServiceCatalog = new JTextField();
		Panel.add(IdServiceCatalog);

		Panel.add(new JLabel("Service Price"));
		PriceServiceCatalog = new JTextField();
		Panel.add(PriceServiceCatalog);

		Panel.add(new JLabel("Service Description"));
		DescriptionServiceCatalog = new JTextField();
		Panel.add(DescriptionServiceCatalog);

		AddNewServiceCatalog = new JButton("Add New Service to Catalog");
		
		
		serviciosDisponibles = new JComboBox();
		try {
			BufferedReader brServicios;
			String linea = "";
			brServicios = new BufferedReader(new FileReader("./data/servicios.txt"));
			while ((linea = brServicios.readLine()) != null) 
			{
				String[] partes = linea.split(";"); // Separa la linea por los ;
				String paraAgregar = partes[0] + "; " + partes[1] + "; " + partes[2] + ", " + partes[3];
				
				serviciosDisponibles.addItem(paraAgregar);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// ------------CUADRAR LABEL------------//
		regresar = new JButton("Regresar");

		// ---------ACTIONS LISTENERS-----------//
		regresar.addActionListener(this);

		AddNewServiceCatalog.addActionListener(this);

		// --------------ADDERS----------//
		frame.add(regresar, BorderLayout.SOUTH);
		frame.add(AddNewServiceCatalog, BorderLayout.WEST);
		frame.add(serviciosDisponibles, BorderLayout.NORTH);
		frame.add(Panel);

		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == regresar) {
			vAdmin.getFrameAdmin().setVisible(true);
			frame.dispose();
		} else if (e.getSource() == AddNewServiceCatalog) {
			boolean productoAgregado = false;
			try {
				String NameService = NameServiceCatalog.getText();
				String PriceService = PriceServiceCatalog.getText();
				Integer intPriceService = Integer.parseInt(PriceService);
				String IdService = IdServiceCatalog.getText();
				Integer intIdService = Integer.parseInt(IdService);
				String DescriptionProduct = DescriptionServiceCatalog.getText();
				String archivo = "./data/productos.txt";

				Administrador.addProductCatalog(Hotel.getMapaProductos(), intIdService, NameService,
						intPriceService, archivo, DescriptionProduct);

				productoAgregado = true;
				
				NameServiceCatalog.setText("");
				PriceServiceCatalog.setText("");
				IdServiceCatalog.setText("");
				DescriptionServiceCatalog.setText("");
				
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Los valores de precio e ID deben ser números enteros",
						"Error de formato", JOptionPane.ERROR_MESSAGE);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Error al agregar servicio: " + ex.getMessage(),
						"Error de aplicación", JOptionPane.ERROR_MESSAGE);
			}

			if (productoAgregado) {
				JOptionPane.showMessageDialog(null, "Servicio agregado correctamente", "Mensaje de Aprobación",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
}
