package interfaz;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset; 
import org.jfree.data.category.DefaultCategoryDataset; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities; 

public class VEstadisticasServicios extends ApplicationFrame {
	 Map<String,Integer> mapaServicios = new LinkedHashMap<>();
	 
	   public VEstadisticasServicios( String applicationTitle , String chartTitle ) throws NumberFormatException, IOException {
	      super( applicationTitle ); 
	      JFreeChart barChart = ChartFactory.createBarChart(
	         chartTitle,           
	         "Category",            
	         "Score",            
	         createDataset(),          
	         PlotOrientation.VERTICAL,           
	         true, true, false);
	         
	      ChartPanel chartPanel = new ChartPanel( barChart );        
	      chartPanel.setPreferredSize(new java.awt.Dimension( 560 , 367 ) );        
	      setContentPane( chartPanel ); 
	   }
	   private CategoryDataset createDataset( ) throws NumberFormatException, IOException
	   {
		   	BufferedReader brServicios;
		   	final DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
			String linea = "";
			brServicios = new BufferedReader(new FileReader("./data/servicios.txt"));
			while ((linea = brServicios.readLine()) != null) 
			{
				String[] partes = linea.split(";"); // Separa la linea por los ;
				final String nombreProducto = partes[1];
				final int totalProducto = Integer.parseInt(partes[4]);
				final String estadisticasServicios = "Estadisticas Servicios";
				dataset.addValue(totalProducto, nombreProducto,estadisticasServicios);
		
		
			}
			return dataset;
	   }
	   
	  
	   
	   

}


