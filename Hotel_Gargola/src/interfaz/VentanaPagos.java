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
    

    VentanaPagos(VentanaCheckOut vCheckOut1) {
        vCheckOut = vCheckOut1;
        frame.setTitle("Pagos");
        frame.setSize(new Dimension(400, 200));
        

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
    
		
		else if (e.getSource() == btnGenerarFactura) {
            String bookingIdStr = txtBookingId.getText();
            if (bookingIdStr.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Por favor, ingrese el ID de reserva.");
                return;
            }

            int bookingId;
            try {
                bookingId = Integer.parseInt(bookingIdStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "El ID de reserva debe ser un número entero.");
                return;
            }

            ArrayList<Reserva> bookingsList = Recepcionista.getBookingsList("reservas.txt");
            Reserva targetBooking = null;
            for (Reserva booking : bookingsList) {
                if (Reserva.getBookingId() == bookingId) {
                    targetBooking = bookingId;
                    break;
                }
            }

            if (targetBooking != null) {
                // Generar factura
                void factura = Recepcionista.generateBill(bookingIdStr, null);

                // Mostrar factura en un cuadro de diálogo
                JOptionPane.showMessageDialog(frame, factura.toString(), "Factura de Reserva",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "No se encontró ninguna reserva con el ID proporcionado.");
            }
        }
    }
    
    
}
