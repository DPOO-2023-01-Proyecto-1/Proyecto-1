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

import interfaz.VentanaListaDeReservas;

public class DiagramaBarras extends ApplicationFrame {
   Map<Integer,Integer> mapaMeses = new LinkedHashMap<>();
   public DiagramaBarras( String applicationTitle , String chartTitle ) {
      super( applicationTitle ); 
      cargarDatos();
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
   private void cargarDatos()
   {
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
   }
   
   private CategoryDataset createDataset( ) {
	   
	   
	   
	   	   int eneroN = 0;        
	       int febreroN = 0;        
	       int marzoN = 0;        
	       int abrilN = 0;        
	       int mayoN = 0;        
	       int junioN = 0;        
	       int julioN = 0;   
	       int agostoN = 0; 
	       int septiembreN = 0; 
	       int octubreN = 0;
	       int noviembreN = 0; 
	       int diciembreN = 0;
	      if (mapaMeses.get(01)==null) {
	    	  ;
	      }
	      else {
	    	  eneroN = mapaMeses.get(01);
	      }
	      
	      if (mapaMeses.get(02)==null) {;
	    	  ;
	      }
	      else {
	    	  febreroN = mapaMeses.get(02);
	      }
	      if (mapaMeses.get(03)==null) {
	    	  ;  
	      }
	      else {
	    	  marzoN = mapaMeses.get(03);
	      }
	      
	      if (mapaMeses.get(04)==null) {
	    	  ;
	      }
	      else {
	    	  abrilN = mapaMeses.get(04);
	      }
	      if (mapaMeses.get(05)==null) {
	    	  ;
	      }
	      else {
	    	  mayoN = mapaMeses.get(05);
	      }
	      if (mapaMeses.get(06)==null) {
	    	  ;
	      }
	      else {
	    	  junioN = mapaMeses.get(6);
	      }
	      if (mapaMeses.get(7)==null) {
	    	  ;
	      }
	      else {
	    	  julioN = mapaMeses.get(7);
	      }
	      if (mapaMeses.get(8)==null) {
	    	  ;
	      }
	      else {
	    	  agostoN = mapaMeses.get(01);
	      }
	      if (mapaMeses.get(9)==null) {
	    	  ;
	      }
	      else {
	    	  septiembreN = mapaMeses.get(9);
	      }
	      if (mapaMeses.get(10)==null) {
	    	  ;
	      }
	      else {
	    	  octubreN = mapaMeses.get(10);
	      }
	      if (mapaMeses.get(11)==null) {
	    	  ;
	      }
	      else {
	    	  noviembreN = mapaMeses.get(11);
	      }
	      if (mapaMeses.get(12)==null) {
	    	  ;
	      }
	      else {
	    	  diciembreN = mapaMeses.get(12);
	      }
	   		
	   	  final String ocupacion = "Ocupacion";
	   	  final String enero = "Enero";        
	      final String febrero = "Febrero";        
	      final String marzo = "Marzo";        
	      final String abril = "Abril";        
	      final String mayo = "Mayo";        
	      final String junio = "Junio";        
	      final String julio = "Julio";   
	      final String agosto = "Agosto"; 
	      final String septiembre = "Septiembre"; 
	      final String octubre = "Octubre";
	      final String noviembre = "Noviembre"; 
	      final String diciembre = "Diciembre";
	      final DefaultCategoryDataset dataset = new DefaultCategoryDataset( );  

      dataset.addValue( eneroN , enero , ocupacion );        
      dataset.addValue( febreroN , febrero , ocupacion );        
      dataset.addValue( marzoN , marzo , ocupacion ); 
      dataset.addValue( abrilN, abril , ocupacion );           

      dataset.addValue( mayoN , mayo , ocupacion );        
      dataset.addValue( junioN , junio , ocupacion );       
      dataset.addValue( julioN , julio , ocupacion );        
      dataset.addValue( agostoN , agosto , ocupacion );

      dataset.addValue( septiembreN , septiembre , ocupacion );        
      dataset.addValue( octubreN , octubre , ocupacion );        
      dataset.addValue( noviembreN , noviembre , ocupacion );        
      dataset.addValue( diciembreN , diciembre , ocupacion );               

      return dataset; 
   }
   

   
}
