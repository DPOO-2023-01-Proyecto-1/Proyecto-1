package interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import modelo.Recepcionista;
import modelo.Reserva;

public class VentanaPagos extends JFrame implements ActionListener {

    JFrame frame = new JFrame();

    VentanaCheckOut vCheckOut;
    JComboBox<String> productosDisponibles;
    JTextField txtBookingId;
    JButton btnGenerarFactura;
    JButton btnRegresar;
    JTextArea textArea = new JTextArea();
    

    VentanaPagos(VentanaCheckOut vCheckOut1) {
        vCheckOut = vCheckOut1;
        frame.setTitle("Pagos");
        frame.setSize(new Dimension(800, 800));
        

        // Crear panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout());

        // Crear panel de formulario
        JPanel panelFormulario = new JPanel(new GridLayout(2, 2));

        // Agregar etiquetas y campos de texto al panel de formulario
        JLabel lblBookingId = new JLabel("ID de Reserva:");
        txtBookingId = new JTextField();
        panelFormulario.add(lblBookingId);
        panelFormulario.add(txtBookingId);

        // Agregar panel de formulario al panel principal
        panelPrincipal.add(panelFormulario, BorderLayout.CENTER);
        panelPrincipal.add(new JScrollPane(textArea), BorderLayout.EAST);

        // Crear y agregar botón generar factura al panel principal
        btnGenerarFactura = new JButton("Generar Factura");
        btnGenerarFactura.addActionListener(this);
        panelPrincipal.add(btnGenerarFactura, BorderLayout.SOUTH);
        btnRegresar = new JButton("regresar");
        btnRegresar.addActionListener(this);
        panelPrincipal.add(btnRegresar, BorderLayout.WEST);
        

        // Agregar panel principal al marco
        frame.getContentPane().add(panelPrincipal);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==btnRegresar)
		{	
			
			vCheckOut.getFrameCheckOut().setVisible(true);
			frame.dispose();
		}
    
		
		if (e.getSource() == btnGenerarFactura) {
           boolean pago = false;
           
           try {
        	   String bookingId = txtBookingId.getText();
        	   String archivo2 = "./data/reservas.txt";
        	   Recepcionista.generateBill(archivo2, bookingId, textArea);
        	   
        	   pago = true;
        	   txtBookingId.setText("");
        	   
           }catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(null, "error "+ex.getMessage(),
					"Error de formato", JOptionPane.ERROR_MESSAGE);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error al hacer Check-out: " + ex.getMessage(),
					"Error de aplicación", JOptionPane.ERROR_MESSAGE);
		}
               
            } else {
                JOptionPane.showMessageDialog(frame, "No se encontró ninguna reserva con el ID proporcionado.");
            }
        }
    }
    
    

