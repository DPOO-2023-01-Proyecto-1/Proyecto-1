package interfazUsuario;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import consola.Aplicacion;
import interfaz.PanelLogin;


public class VInicioUsuario  extends JFrame implements ActionListener //Esto es donde va a ir el login (es lo primero qeu va a aparecer)
{	
	
	VInicioUsuario vIUsuario;
	private PanelLoginUsuario panelLoginUsuario; //aqui es donde va el usuario y el password
	JFrame frameInicio = new JFrame();
	
	public static void main(String[] args)
	{	
		Aplicacion consola = new Aplicacion();
		consola.ejecutarCargarDatos();
		VInicioUsuario ventanaInicio = new VInicioUsuario(); //aqui es donde se inicia el JFRAME
		ventanaInicio.setVisible(true);
	}
	/** Esta es la clase con la que se va a inicializar todo. El proposito
	 * principal de esta clase es manejar el login.
	 * Dependiendo del tipo de usuario lo va a mandar a una ventana distinta.
	 */
	
	
	
	public VInicioUsuario()
	{
		
		setTitle( "Ventana Usuario");
		setSize(750,750 ); 
		setResizable( false );
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		
		panelLoginUsuario = new PanelLoginUsuario(this); //agrega el panel desde la calse PanelLogin

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