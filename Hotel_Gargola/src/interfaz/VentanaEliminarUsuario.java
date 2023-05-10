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
import modelo.Hotel;
import modelo.Usuario;

public class VentanaEliminarUsuario  extends JFrame implements ActionListener {
	
	
	JFrame frame;
	JButton regresar;
	JButton eraseUser;
	JComboBox <String> users;
	JTextField login;
	private VentanaAdministrador vAdmin;
	
	
	
	public VentanaEliminarUsuario(VentanaAdministrador vAdmin1) {
		vAdmin = vAdmin1;
		frame = new JFrame();
		frame.setTitle("Eliminar servicio del catalogo");
        frame.setSize(new Dimension(750, 750));
        
        JPanel Panel = new JPanel(new GridLayout(2, 2));
        
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        Panel.add(new JLabel("User Login"));
        login = new JTextField();
        Panel.add(login);
        
        regresar = new JButton("Regresar");
        eraseUser = new JButton("Erase User");
        
        regresar.addActionListener(this);
        eraseUser.addActionListener(this);
        
        users = new JComboBox<String>();
        try {
            BufferedReader brServicios;
            String linea = "";
            brServicios = new BufferedReader(new FileReader("./data/usuarios.txt"));
            while ((linea = brServicios.readLine()) != null) {
                String[] partes = linea.split(";"); // Separa la linea por los ;
                String paraAgregar = partes[0] + "; " + partes[1] + "; " + partes[2];

                users.addItem(paraAgregar);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        frame.add(users,BorderLayout.NORTH);
        frame.add(regresar,BorderLayout.SOUTH);
        Panel.add(eraseUser);
        frame.add(Panel);
        
        
        frame.setVisible(true);

        
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	    if (e.getSource() == regresar) {
	        vAdmin.getFrameAdmin().setVisible(true);
	        frame.dispose();
	    } else if (e.getSource() == eraseUser) {
	        try {
	            String loginUser = login.getText();
	            String archivo = "./data/usuarios.txt";

	            Map<String, Usuario> usuarios = Hotel.getMapaUsuarios();
	            Usuario usuario = usuarios.get(loginUser);

	            if (usuario != null && !usuario.getUserType().equals("administrador")) {
	                Administrador.deleteUser(loginUser, usuarios, archivo);
	                JOptionPane.showMessageDialog(null, "El usuario " + loginUser + " ha sido eliminado correctamente.");
	            } else {
	                JOptionPane.showMessageDialog(null, "No se pudo eliminar el usuario " + loginUser + "debido a que un administrador no puede eliminarse a si mismo.");
	            }
	            login.setText("");
	        } catch (IOException ex) {
	            JOptionPane.showMessageDialog(null, "Error al eliminar usuario: " + ex.getMessage());
	            login.setText("");
	        }
	    }
	}

	
}

	
		
		
		
	


