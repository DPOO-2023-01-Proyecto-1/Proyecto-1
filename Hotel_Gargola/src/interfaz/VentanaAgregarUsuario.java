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

public class VentanaAgregarUsuario extends JFrame implements ActionListener{
	
	JFrame frame = new JFrame();
	JButton regresar;
	JTextField login;
	JTextField password;
	JTextField status;
	
	JComboBox <String> users;
	
	VentanaAdministrador vAdmin;
	JButton addNewUser;
	
	VentanaAgregarUsuario (VentanaAdministrador vAdmin1)
	{
		vAdmin = vAdmin1;
		frame.setTitle("Hacer Reserva");
		frame.setSize(new Dimension(750,750));
		
		JPanel Panel = new JPanel(new GridLayout(3, 2));

		Panel.add(new JLabel("New User Login"));
		login = new JTextField();
		Panel.add(login);

		Panel.add(new JLabel("New User Password"));
		password = new JTextField();
		Panel.add(password);

		Panel.add(new JLabel("New User Status"));
		status = new JTextField();
		Panel.add(status);
		
		addNewUser = new JButton("Agregar Usuario Al Catalogo");

		regresar =  new JButton("Regresar");
		
		users = new JComboBox();
		try {
			BufferedReader brUsers;
			String linea = "";
			brUsers = new BufferedReader(new FileReader("./data/usuarios.txt"));
			while ((linea = brUsers.readLine()) != null) 
			{
				String[] partes = linea.split(";"); // Separa la linea por los ;
				String paraAgregar = partes[0] + "; " + partes[1] + "; " + partes[2];
				users.addItem(paraAgregar);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//---------ACTIONS LISTENERS-----------//
		regresar.addActionListener(this);
		addNewUser.addActionListener(this);
		
		//--------------ADDERS----------//
		frame.add(regresar, BorderLayout.SOUTH);
		frame.add(addNewUser,BorderLayout.WEST);
		frame.add(users,BorderLayout.NORTH);
		frame.add(Panel);
		
		
		frame.setVisible(true);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
	    if (e.getSource() == regresar) {
	        vAdmin.getFrameAdmin().setVisible(true);
	        frame.dispose();
	    } else if (e.getSource() == addNewUser) {
	        boolean usuarioAgregado = false;
	        boolean usuarioExistente = false;
	        try {
	            String loginUser = login.getText();
	            String passwordUser = password.getText();
	            String statusUser = status.getText();
	            String archivo = "./data/usuarios.txt";
	            
	            for (Map.Entry<String, Usuario> entry : Hotel.getMapaUsuarios().entrySet()) {
	                if (entry.getKey().equalsIgnoreCase(loginUser)) {
	                    usuarioExistente = true;
	                    break;
	                }
	            }
	            
	            if (!usuarioExistente) {
	                Administrador.addUser(loginUser, passwordUser, statusUser, Hotel.getMapaUsuarios(), archivo);
	                usuarioAgregado = true;
	            }
	        } catch (IOException ex) {
	            JOptionPane.showMessageDialog(null, "Error al agregar usuario: " + ex.getMessage(), "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
	        } catch (NumberFormatException ex) {
	            JOptionPane.showMessageDialog(null, "Formato incorrecto en alguno de los campos", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
	        }
	        
	        if (usuarioAgregado) {
	            JOptionPane.showMessageDialog(null, "Usuario agregado exitosamente", "Mensaje de Aprobación", JOptionPane.INFORMATION_MESSAGE);
	            login.setText("");
	            password.setText("");
	            status.setText("");
	        } else if (usuarioExistente) {
	            JOptionPane.showMessageDialog(null, "El usuario ya existe", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
	            login.setText("");
	            password.setText("");
	            status.setText("");
	        }
	    }
	}

}


