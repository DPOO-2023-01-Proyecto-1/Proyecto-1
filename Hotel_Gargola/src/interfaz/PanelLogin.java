package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import modelo.Hotel;
import modelo.Usuario;



public class PanelLogin extends JPanel implements ActionListener
{	
	private JPanel panel;
	private Hotel hotel = new Hotel();
	private JTextField loginUsuario;
	private JTextField passwordUsuario;
	private JButton bIngresar;
	private VentanaInicio vInicio;
	


	public PanelLogin(VentanaInicio vInicio2)
	{	
		vInicio = vInicio2;
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(150,400));
		panel.setLayout(new GridLayout(3,2,0,0));
		JLabel labelIngrese = new JLabel("Ingrese su login");
		labelIngrese.setBounds(10,100,100,100);
		
		loginUsuario = new JTextField();
		JLabel labelPassword = new JLabel("Ingrese su contraseña");
		
		passwordUsuario = new JTextField();
		passwordUsuario.setBounds(100,100,100,100);
		bIngresar = new JButton("Ingresar");
		bIngresar.addActionListener(this);
		
		panel.add(labelIngrese); 
		panel.add(loginUsuario);
		panel.add(labelPassword);
		panel.add(passwordUsuario);
		panel.add(bIngresar);
		vInicio.add(panel);

		
		
		
	
	}
	@Override //aqui si es necesario tener el action performed o que 
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==bIngresar)
		{
			Map<String, Usuario> mapaUsuarios = hotel.getMapaUsuarios();
			String login = loginUsuario.getText();
			String password = passwordUsuario.getText();
			if(mapaUsuarios.get(login)== null)
			{
				JOptionPane.showMessageDialog(null, "Su usuario no ha sido encontrado", "Mensaje no encontrado", JOptionPane.WARNING_MESSAGE);
			}
			else
			{
				Usuario usuario = mapaUsuarios.get(login);
				if(!usuario.getPassword().equals(password)) //Si la contraseña es incorrecta manda mensaje de error
				{
					JOptionPane.showMessageDialog(null, "Contraseña incorrecta", "Mensaje de incorrecto", JOptionPane.WARNING_MESSAGE);
				}
				else
				{
					String tipoUsuario = usuario.getUserType();
					System.out.println(tipoUsuario);
					if(tipoUsuario.equals("administrador") )
					{
						System.out.println("después sale ventana de admin");
						VentanaAdministrador vAdmin = new VentanaAdministrador();
						vInicio.setVisible(false);;
					}
					else if(tipoUsuario.equals("recepcionista"))
					{
						
					}
					else
					{
						
					}
				}
				
				
			}
		}
	}
	
}










































/*

*/