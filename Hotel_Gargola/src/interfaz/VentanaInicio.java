package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.Border;

import modelo.Hotel;
import consola.Aplicacion;



public class VentanaInicio  extends JFrame implements ActionListener //Esto es donde va a ir el login (es lo primero qeu va a aparecer)
{	
	
	VentanaInicio ventanaInicio;
	private PanelLogin panelLogin; //aqui es donde va el usuario y el password
	JFrame frameInicio = new JFrame();
	
	public static void main(String[] args)
	{	
		Aplicacion consola = new Aplicacion();
		consola.ejecutarCargarDatos();
		VentanaInicio ventanaInicio = new VentanaInicio(); //aqui es donde se inicia el JFRAME
		ventanaInicio.setVisible(true);
	}
	/** Esta es la clase con la que se va a inicializar todo. El proposito
	 * principal de esta clase es manejar el login.
	 * Dependiendo del tipo de usuario lo va a mandar a una ventana distinta.
	 */
	
	
	
	public VentanaInicio()
	{
		
		setTitle( "Hotel Gárgola");
		setSize(750,750 ); 
		setResizable( false );
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		
		panelLogin = new PanelLogin(this); //agrega el panel desde la calse PanelLogin

		//add(panelLogin, BorderLatyout.WEST);
		
	}
	
	public void dispose()
    {
        JOptionPane.showMessageDialog( this, "¡Hasta la próxima!" );
        
        System.exit( 0 );
    }
	//-----------RETURNERS-------------------//
	public JFrame getFrameInicio()
	{
		return frameInicio;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}
	
}
