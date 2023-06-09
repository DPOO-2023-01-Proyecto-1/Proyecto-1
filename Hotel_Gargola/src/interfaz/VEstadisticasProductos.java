package interfaz;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

public class VEstadisticasProductos extends ApplicationFrame {
	public VEstadisticasProductos( String applicationTitle , String chartTitle ) throws NumberFormatException, IOException {
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
		   	final DefaultCategoryDataset datasetPrecios = new DefaultCategoryDataset( );
			String linea = "";
			brServicios = new BufferedReader(new FileReader("./data/productos.txt"));
			while ((linea = brServicios.readLine()) != null) 
			{
				String[] partes = linea.split(";"); // Separa la linea por los ;
				final String nombreProducto = partes[1];
				final String estadisticasServicios = "Estadisticas Productos";
				final int totalProducto = Integer.parseInt(partes[4]);
				
				
				dataset.addValue(totalProducto, nombreProducto,estadisticasServicios);
				final int totalProductoPrecios = Integer.parseInt(partes[4])*Integer.parseInt(partes[2]);
				datasetPrecios.addValue(totalProductoPrecios, nombreProducto, estadisticasServicios);
		
		
			}
			return dataset;
	   }
	   
	   

}
