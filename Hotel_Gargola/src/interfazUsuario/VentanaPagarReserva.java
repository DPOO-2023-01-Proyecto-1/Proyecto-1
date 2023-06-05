package interfazUsuario;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import modelo.IMetodoDePago;
import modelo.MercadoPago;
import modelo.Nequi;
import modelo.Paypal;
import modelo.Tarjeta;

public class VentanaPagarReserva extends JFrame implements ActionListener{

    private JList<String> listaMetodosPago;
    private DefaultListModel<String> modeloLista;
    
    private JTextField textFieldBusqueda;
    private JButton botonBuscar;
    

    public VentanaPagarReserva(List<Object> metodosPago) {
        setTitle("Pagar");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750,750);
        setResizable( false );

        modeloLista = new DefaultListModel<>();
        listaMetodosPago = new JList<>(modeloLista);
        listaMetodosPago.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
      
        textFieldBusqueda = new JTextField();
        botonBuscar = new JButton("Buscar");
        
        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new GridLayout(2,2));
        panelSuperior.add(textFieldBusqueda);
        panelSuperior.add(botonBuscar);
        
        
        
        botonBuscar.addActionListener(this);

        for (Object metodoPago : metodosPago) {
            modeloLista.addElement(metodoPago.getClass().getSimpleName());
        }

        JScrollPane scrollPane = new JScrollPane(listaMetodosPago);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(panelSuperior, BorderLayout.NORTH);
        add(panel);

        listaMetodosPago.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String metodoSeleccionado = listaMetodosPago.getSelectedValue();
                JOptionPane.showMessageDialog(null, "Has seleccionado el método de pago: " + metodoSeleccionado);
            }
        });
        
        
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == botonBuscar) 
		{
		String idReserva = textFieldBusqueda.getText();
		String archivo = "./data/reservas.txt";
		int intIdReserva = Integer.parseInt(idReserva);
		
		if (buscarEnArchivoCSV(archivo,idReserva) == true) {
			
			int valorTotal = calculateTotalValue(intIdReserva,archivo);
			JOptionPane.showMessageDialog(null, "Pago completado", "Completado", JOptionPane.INFORMATION_MESSAGE);
		}
			
		}
		
		
		
	}
	
	public boolean buscarEnArchivoCSV(String archivoCSV, String idBuscado) {
	    try (BufferedReader br = new BufferedReader(new FileReader(archivoCSV))) {
	        String linea;
	        while ((linea = br.readLine()) != null) {
	            String[] elementos = linea.split(";");
	            if (elementos.length > 0 && elementos[0].equals(idBuscado)) {
	            	JOptionPane.showMessageDialog(null, "Si existe una reserva con el ID: " + idBuscado);
	                return true;
	            }
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    
	    // Mostrar mensaje de error si no se encuentra el ID buscado
	    JOptionPane.showMessageDialog(null, "No existe una reserva con el ID: " + idBuscado, "Error", JOptionPane.ERROR_MESSAGE);
	    
	    return false;
	}
	
	public static int calculateTotalValue(Integer bookingId, String archivoReservas) {
	    int totalValue = 0;

	    try {
	        FileReader frReservas = new FileReader(archivoReservas);
	        BufferedReader brReservas = new BufferedReader(frReservas);
	        String lineaReserva;
	        while ((lineaReserva = brReservas.readLine()) != null) {
	            String[] partesReserva = lineaReserva.split(";");
	            Integer id = Integer.parseInt(partesReserva[0]);
	            if (id.equals(bookingId)) {
	                Integer valorAsociado = Integer.parseInt(partesReserva[5]);
	                totalValue += valorAsociado;
	                break; // Assuming there is only one reservation with the given bookingId
	            }
	        }
	        brReservas.close();
	    } catch (FileNotFoundException e) {
	        System.out.println("No se encontró el archivo " + e.getMessage());
	    } catch (IOException e) {
	        System.out.println("Error al leer el archivo: " + e.getMessage());
	    }

	    return totalValue;
	}
	
	
	
	
	private void seleccionarMetodoPago(Object metodoPagoSeleccionado,int valorTotal ) {
	    if (metodoPagoSeleccionado instanceof Nequi) {
	        Nequi nequi = (Nequi) metodoPagoSeleccionado;
	        double montoTotal = nequi.getMontoTotalCuenta();
	        
	        if (montoTotal >= valorTotal) {
	            // Realizar el pago y mostrar mensaje de completado
	            
	            JOptionPane.showMessageDialog(null, "Pago completado con Nequi", "Completado", JOptionPane.INFORMATION_MESSAGE);
	        } else {
	            // Mostrar mensaje de error si el montoTotal es menor al valorTotal
	            JOptionPane.showMessageDialog(null, "El monto de Nequi es insuficiente", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    } else if (metodoPagoSeleccionado instanceof Tarjeta) {
	        Tarjeta tarjeta = (Tarjeta) metodoPagoSeleccionado;
	        double montoTotal = tarjeta.getMontoTotalCuenta();
	        
	        if (montoTotal >= valorTotal) {
	            // Realizar el pago y mostrar mensaje de completado
	            
	            JOptionPane.showMessageDialog(null, "Pago completado con Tarjeta", "Completado", JOptionPane.INFORMATION_MESSAGE);
	        } else {
	            // Mostrar mensaje de error si el montoTotal es menor al valorTotal
	            JOptionPane.showMessageDialog(null, "El monto de la tarjeta es insuficiente", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    } else if (metodoPagoSeleccionado instanceof MercadoPago) {
	        MercadoPago mercadoPago = (MercadoPago) metodoPagoSeleccionado;
	        double montoTotal = mercadoPago.getMontoTotalCuenta();
	        
	        if (montoTotal >= valorTotal) {
	            // Realizar el pago y mostrar mensaje de completado
	            
	            JOptionPane.showMessageDialog(null, "Pago completado con MercadoPago", "Completado", JOptionPane.INFORMATION_MESSAGE);
	        } else {
	            // Mostrar mensaje de error si el montoTotal es menor al valorTotal
	            JOptionPane.showMessageDialog(null, "El monto de MercadoPago es insuficiente", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    } else if (metodoPagoSeleccionado instanceof Paypal) {
	        Paypal paypal = (Paypal) metodoPagoSeleccionado;
	        double montoTotal = paypal.getMontoTotalCuenta();
	        
	        if (montoTotal >= valorTotal) {
	            // Realizar el pago y mostrar mensaje de completado
	            
	            JOptionPane.showMessageDialog(null, "Pago completado con Paypal", "Completado", JOptionPane.INFORMATION_MESSAGE);
	        } else {
	            // Mostrar mensaje de error si el montoTotal es menor al valorTotal
	            JOptionPane.showMessageDialog(null, "El monto de Paypal es insuficiente", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    } else {
	        // Mostrar mensaje de error si el objeto de método de pago no es de la clase esperada
	        JOptionPane.showMessageDialog(null, "Método de pago inválido", "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	
    
}
    
  