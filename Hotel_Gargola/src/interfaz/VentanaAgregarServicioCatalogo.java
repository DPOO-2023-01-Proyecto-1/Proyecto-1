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

public class VentanaAgregarServicioCatalogo extends JFrame implements ActionListener {

	JFrame frame = new JFrame();
	JButton regresar;
	JTextField IdServiceCatalog;
	JTextField PriceServiceCatalog;
	JTextField DescriptionServiceCatalog;
	JTextField NameServiceCatalog;

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

		// ------------CUADRAR LABEL------------//
		regresar = new JButton("Regresar");

		// ---------ACTIONS LISTENERS-----------//
		regresar.addActionListener(this);

		AddNewServiceCatalog.addActionListener(this);

		// --------------ADDERS----------//
		frame.add(regresar, BorderLayout.SOUTH);
		frame.add(AddNewServiceCatalog, BorderLayout.WEST);
		frame.add(Panel);

		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource()==regresar)
		{				
			vAdmin.getFrameAdmin().setVisible(true);
			frame.dispose();
		}
		else if (e.getSource()==AddNewServiceCatalog)
		{	
			String NServiceCatalog = NameServiceCatalog.getText();
		    String PServiceCatalog = PriceServiceCatalog.getText();
		    int intPServiceCatalog = Integer.parseInt(PServiceCatalog);
		    String IServiceCatalog = PriceServiceCatalog.getText();
		    int intIServiceCatalog = Integer.parseInt(IServiceCatalog);
		    String DServiceCatalog = DescriptionServiceCatalog.getText();
		    String archivo = "./data/servicios.txt";
		    
		
			Administrador.addServiceCatalog(Hotel.getMapaServicios(), intIServiceCatalog, NServiceCatalog, intPServiceCatalog, archivo,DServiceCatalog);
			
		    
			
		}
		
	}

}
