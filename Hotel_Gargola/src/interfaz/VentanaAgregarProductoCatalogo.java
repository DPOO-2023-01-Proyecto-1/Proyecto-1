package interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import modelo.Administrador;
import modelo.Hotel;

public class VentanaAgregarProductoCatalogo extends JFrame implements ActionListener{
	
	JFrame frame = new JFrame();
	JButton regresar;
	JTextField IdNewProduct;
	JTextField NameNewProduct;
	JTextField PriceNewProduct;
	JTextField RestrictionNewProduct;
	JButton AddNewProductCatalog;
	
	VentanaAdministrador vAdmin;
	
	VentanaAgregarProductoCatalogo (VentanaAdministrador vAdmin1)
	{
		vAdmin = vAdmin1;
		frame.setTitle("Agregar Producto al Catalogo");
		frame.setSize(new Dimension(750,750));
		
		JPanel Panel = new JPanel(new GridLayout(4, 2));
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
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
		
		
		
		//------------CUADRAR LABEL------------//
		regresar =  new JButton("Regresar");
		
		//---------ACTIONS LISTENERS-----------//
		regresar.addActionListener(this);
		AddNewProductCatalog.addActionListener(this);
		
		
		
		
		
		//--------------ADDERS----------//
		frame.add(regresar, BorderLayout.SOUTH);
		frame.add(AddNewProductCatalog,BorderLayout.WEST);
		frame.add(Panel);
		
		
		
		frame.setVisible(true);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==regresar)
		{	
			
			vAdmin.getFrameAdmin().setVisible(true);
			frame.dispose();
		}
		else if (e.getSource() == AddNewProductCatalog);
		{

			String NProductCatalog = NameNewProduct.getText();
		    String PProductCatalog = PriceNewProduct.getText();
		    Integer intPProductCatalog = Integer.parseInt(PProductCatalog);
		    String IProductCatalog = IdNewProduct.getText();
		    Integer intIProductCatalog = Integer.parseInt(IProductCatalog);
		    String RProductCatalog = RestrictionNewProduct.getText();
		    String archivo = "./data/productos.txt";
		    
		    Administrador.addProductCatalog(Hotel.getMapaProductos(), intIProductCatalog, NProductCatalog, intPProductCatalog, archivo,RProductCatalog);
	    	
		}
	}

}