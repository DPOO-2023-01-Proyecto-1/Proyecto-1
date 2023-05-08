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

public class VentanaAgregarProductoCatalogo extends JFrame implements ActionListener {

	JFrame frame = new JFrame();
	JButton regresar;
	JTextField IdNewProduct;
	JTextField NameNewProduct;
	JTextField PriceNewProduct;
	JTextField RestrictionNewProduct;
	JButton AddNewProductCatalog;
	JComboBox <String> productosDisponibles;
	VentanaAdministrador vAdmin;

	VentanaAgregarProductoCatalogo(VentanaAdministrador vAdmin1) {
		vAdmin = vAdmin1;
		frame.setTitle("Agregar Producto al Catalogo");
		frame.setSize(new Dimension(750, 750));

		JPanel Panel = new JPanel(new GridLayout(4, 2));

		Panel.add(new JLabel("Product Name"));
		NameNewProduct = new JTextField();
		Panel.add(NameNewProduct);

		Panel.add(new JLabel("Product New ID"));
		IdNewProduct = new JTextField();
		Panel.add(IdNewProduct);

		Panel.add(new JLabel("Product Price"));
		PriceNewProduct = new JTextField();
		Panel.add(PriceNewProduct);

		Panel.add(new JLabel("Product Restrictions"));
		RestrictionNewProduct = new JTextField();
		Panel.add(RestrictionNewProduct);

		AddNewProductCatalog = new JButton("Add New Product to Catalog");
		
		productosDisponibles = new JComboBox();
		try {
			BufferedReader brProductos;
			String linea = "";
			brProductos = new BufferedReader(new FileReader("./data/productos.txt"));
			while ((linea = brProductos.readLine()) != null) 
			{
				String[] partes = linea.split(";"); // Separa la linea por los ;
				String paraAgregar = partes[0] + "; " + partes[1] + "; " + partes[2] + ", " + partes[3]+ "; ";
				System.out.println(partes[0]);
				productosDisponibles.addItem(paraAgregar);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// ------------CUADRAR LABEL------------//
		regresar = new JButton("Regresar");

		// ---------ACTIONS LISTENERS-----------//
		regresar.addActionListener(this);
		AddNewProductCatalog.addActionListener(this);

		// --------------ADDERS----------//
		frame.add(regresar, BorderLayout.SOUTH);
		frame.add(productosDisponibles, BorderLayout.NORTH);
		frame.add(AddNewProductCatalog, BorderLayout.WEST);
		frame.add(Panel);

		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == regresar) {
			vAdmin.getFrameAdmin().setVisible(true);
			frame.dispose();
		} else if (e.getSource() == AddNewProductCatalog) {
			boolean productoAgregado = false;
			try {
				String NameProductCatalog = NameNewProduct.getText();
				String PriceProductCatalog = PriceNewProduct.getText();
				Integer intPriceProductCatalog = Integer.parseInt(PriceProductCatalog);
				String IdProductCatalog = IdNewProduct.getText();
				Integer intIdProductCatalog = Integer.parseInt(IdProductCatalog);
				String RestrictionsProductCatalog = RestrictionNewProduct.getText();
				String archivo = "./data/productos.txt";

				Administrador.addProductCatalog(Hotel.getMapaProductos(), intIdProductCatalog, NameProductCatalog,
						intPriceProductCatalog, archivo, RestrictionsProductCatalog);

				productoAgregado = true;
				NameNewProduct.setText("");
				PriceNewProduct.setText("");
				IdNewProduct.setText("");
				RestrictionNewProduct.setText("");
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Los valores de precio e ID deben ser números enteros",
						"Error de formato", JOptionPane.ERROR_MESSAGE);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Error al agregar producto: " + ex.getMessage(),
						"Error de aplicación", JOptionPane.ERROR_MESSAGE);
			}

			if (productoAgregado) {
				JOptionPane.showMessageDialog(null, "Producto agregado correctamente", "Mensaje de Aprobación",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

}
