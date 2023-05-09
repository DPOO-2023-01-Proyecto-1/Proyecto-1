package interfaz;




import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.ChartUtilities;

import interfaz.DiagramaBarras;


public class VentanaListaDeReservas extends JFrame implements ActionListener{
	
	JFrame frame = new JFrame();
	JButton regresar;
	JPanel panelCentro;
	String[] meses = {"Enero", "Febrero", "Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"};
	int[] valores = {1000, 2500, 400, 7000, 3300, 2000, 9000, 2500, 3000, 1600, 7000, 1200};
	int maxValue = 10000;
	Map<Integer,Integer> mapaMeses = new LinkedHashMap<>();
	VentanaRecepcionista vRecepcionista;
	
	
	VentanaListaDeReservas(VentanaRecepcionista vRecepcionista1)
	{	
		//----------CUADRA EL HASH MAP-----------//
		try {
			BufferedReader brReservas;
			String linea = "";
			brReservas = new BufferedReader(new FileReader("./data/reservas.txt"));
			while ((linea = brReservas.readLine()) != null) 
			{
				String[] partes = linea.split(";"); // Separa la linea por los ;
				String[] partes2 = partes[1].split("-");
				int mesesito = Integer.parseInt(partes2[1]);
				if (mapaMeses.get(mesesito)==null)
				{
					int valorInicial = 1;
					mapaMeses.put(mesesito, valorInicial);
				}
				else
				{
					int valorsito = mapaMeses.get(mesesito) +1;
					mapaMeses.put(mesesito, valorsito);
				}

				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
		
		
		//--------------------------------------//
		vRecepcionista = vRecepcionista1;
		frame.setTitle("Ver Lista De Reservas");
		frame.setSize(new Dimension(750,750));
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//------------CUADRAR LABEL------------//
		regresar =  new JButton("Regresar");
		
		//---------ACTIONS LISTENERS-----------//
		regresar.addActionListener(this);
		
		
		
		
		
		//--------------ADDERS----------//
		frame.add(regresar, BorderLayout.SOUTH);
		
		//--------CUADRAR GRAFICA---------//
		DiagramaBarras diagrama = new DiagramaBarras("ensayo", "ensayis");
		
		//-------------------------------//
		
		
		diagrama.pack();
		RefineryUtilities.centerFrameOnScreen( diagrama );
	
		diagrama.setVisible(true);
		frame.setVisible(true);
	}
	
	 
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==regresar)
		{
			vRecepcionista.getFrameRecepcionista().setVisible(true);
			frame.dispose();
		}
		
	}
	
	//-------RETURNERS-------//
	public Map<Integer,Integer> getMapaGrafica()
	{
		return mapaMeses;
	}
	
	
}
