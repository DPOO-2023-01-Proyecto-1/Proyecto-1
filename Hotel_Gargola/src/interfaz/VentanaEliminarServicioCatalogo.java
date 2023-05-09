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
import java.util.Set;

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
import modelo.Servicio;

public class VentanaEliminarServicioCatalogo extends JFrame implements ActionListener {

   
    private JFrame frame;
    private JTextField codigoServicio;
    private JButton regresar;
    private JButton eraseServiceCatalog;
    private JComboBox<String> serviciosDisponibles;
    private VentanaAdministrador vAdmin;

    public VentanaEliminarServicioCatalogo(VentanaAdministrador vAdmin1) {
        vAdmin = vAdmin1;
        frame = new JFrame();
        frame.setTitle("Eliminar servicio del catalogo");
        frame.setSize(new Dimension(750, 750));

        JPanel Panel = new JPanel(new GridLayout(2, 2));

        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        Panel.add(new JLabel("Servicio ID"));
        codigoServicio = new JTextField();
        Panel.add(codigoServicio);

        regresar = new JButton("Regresar");
        eraseServiceCatalog = new JButton("Eliminar servicio del catalogo");

        serviciosDisponibles = new JComboBox<String>();
        try {
            BufferedReader brServicios;
            String linea = "";
            brServicios = new BufferedReader(new FileReader("./data/servicios.txt"));
            while ((linea = brServicios.readLine()) != null) {
                String[] partes = linea.split(";"); // Separa la linea por los ;
                String paraAgregar = partes[0] + "; " + partes[1] + "; " + partes[2] + ", " + partes[3];

                serviciosDisponibles.addItem(paraAgregar);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        regresar.addActionListener(this);
        eraseServiceCatalog.addActionListener(this);

        frame.add(serviciosDisponibles, BorderLayout.NORTH);
        frame.add(regresar, BorderLayout.SOUTH);
        Panel.add(eraseServiceCatalog);
        frame.add(Panel);

        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == regresar) {
            vAdmin.getFrameAdmin().setVisible(true);
            frame.dispose();
        } else if (e.getSource() == eraseServiceCatalog) {
            try {
                String CodigoServicio = this.codigoServicio.getText();
                Integer intCodigoServicio = Integer.parseInt(CodigoServicio);
                String archivo = "./data/servicios.txt";

                Map<Integer, Servicio> mapaServicios = Hotel.getMapaServicios();
                Map<Integer, Habitacion> mapaHabitaciones = Hotel.getMapaHabitaciones();
                
                Administrador.deleteServiceCatalog(mapaServicios, mapaHabitaciones, intCodigoServicio, archivo);

          
                

	            Set<Integer> keys = mapaServicios.keySet();
	            
	            boolean servicioEncontro = (keys.contains(intCodigoServicio));
	            
	            if (servicioEncontro) {
	            	JOptionPane.showMessageDialog(null, "El producto con ID " + intCodigoServicio + " no pudo ser eliminado correctamente del catálogo debido a que se encontro en el registro de consumo de una habitacion.");
	            	codigoServicio.setText("");
	            }else {
	            	JOptionPane.showMessageDialog(null, "El producto con ID " + intCodigoServicio + " ha sido eliminado correctamente del catálogo.");
	            	codigoServicio.setText("");
	            }


         

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido para el código del servicio.");
                this.codigoServicio.setText("");
            }
        }
    }
}
