package modelo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import modelo.Habitacion;
import modelo.Producto;
import modelo.Servicio;

public class Hotel 
{
	private static Map <Integer, Habitacion> mapaHabitaciones = new LinkedHashMap<>();//toca ensayar si si funciona el id en integer como key del hashmap
	private static Map <Integer, Producto> mapaProductos = new LinkedHashMap<>();//toca ensayar si si funciona el id en integer como key del hashmap
	private static Map <Integer, Servicio> mapaServicios = new LinkedHashMap<>();//toca ensayar si si funciona el id en integer como key del hashmap
	
	public static void cargarInfoHotel() throws FileNotFoundException, IOException
    {	
    	cargarHabitaciones();
    	cargarProductos();
    	cargarServicios();
    }
	
	public static void cargarHabitaciones()  throws FileNotFoundException, IOException 
	{// Función que va a crear un hash map a partir de un .txt con las habitaciones
		BufferedReader brHabitaciones = new BufferedReader(new FileReader("./data/habitaciones.txt")); // archivo de habitaciones
		String linea = "";
		System.out.println("Se empezaron a cargar las habitaciones");// Noticia de que se empezó a cargar la habitación. Es necesario quitarlo después
		while((linea = brHabitaciones.readLine()) != null)
		{
			String[] partes = linea.split(";"); //Separa la linea por los ;
			int roomId = Integer.parseInt(partes[0]); //EL id de la habitación está en la posición 0
			String occupancyStatusString = partes[1];
			Boolean occupancyStatus = true; //Este es el que se debe de poner, el anterior solo era para acceder al String. 
			if (occupancyStatusString == "false")
			{
				occupancyStatus = false;
			}
			String availableServicesString = partes[2]; //Aqui está los availables services en string. NO están con lista todavía. 
			String[] partesAvailableServices = availableServicesString.split(","); //aqui separa los available services con comas y sale
			ArrayList <String> listaAvailableServices = new ArrayList<String>();
			for (int i = 0;  i < partesAvailableServices.length; i++) // Este for lo que hace es agregar las partes del availables services en una lista. 
			{	
				//System.out.println(partesAvailableServices[i]);//se  habia agregado esto para ensayar
				listaAvailableServices.add(partesAvailableServices[i]);
			}
			
			String consumptionRecordPreLista = partes[3]; //consumption record es una lista que está separada por comas. Aqui se conierte en string, lo que sigue lo convierte en lista lista.
			String[] partesConsumption = consumptionRecordPreLista.split(","); //Aquí separa los items y los convierte en una items para agregar a una lista. 
			ArrayList <String> listaConsumptionRecord = new ArrayList<String>();
			for (int j = 0;  j < partesConsumption.length; j++) // Este for lo que hace es agregar las partes del consumption record a una lista. 
			{
				//System.out.println(partesConsumption[j]);//se  habia agregado esto para ensayar
				listaConsumptionRecord.add(partesConsumption[j]);
			}
			
			String huespedesPrelista = partes[4];
			String[] partesHuespedes = huespedesPrelista.split(",");
			ArrayList <String> listaHuespedes = new ArrayList<String>();
			for (int d = 0; d < partesHuespedes.length; d++) //Este for lo que va a hacer es separar por las , la lista de huespedes y lo va a agregar en un arraylist que la array va a tener puros strings 
			{
				//System.out.println(partesHuespedes[d]); //se  habia agregado esto para ensayar
				listaHuespedes.add(partesHuespedes[d]);
			}
			
			int valueByNight = Integer.parseInt(partes[5]);
			int guestCapacity = Integer.parseInt(partes[6]);
			String roomType = partes[7];
			int totalValue = Integer.parseInt(partes[8]);
			Habitacion habitacion = mapaHabitaciones.get(roomId);//Aqui lo que s e queire hacer es checkear si la habitación si existe y si no existe pues se crea una nueva y sale mi farafafá
			if (habitacion == null)
			{
				habitacion = new Habitacion(roomId, occupancyStatus, listaAvailableServices, listaConsumptionRecord, listaHuespedes, valueByNight, guestCapacity, roomType, totalValue);//toca ensayar si si funciona el id en integer como key del hashmap
				mapaHabitaciones.put(roomId, habitacion);
			}
		}
    System.out.println("Se cargaron las habitaciones");
	}
	public static void cargarProductos() throws FileNotFoundException, IOException 
	{// Función que va a crear un hash map de los productos que se van a ofrecer en el hotel. Se carga a partir de un archivo .txt que está en la carpeta de data
		BufferedReader brProductos = new BufferedReader(new FileReader("./data/productos.txt")); // archivo de productos. cada linea es así: id;name;value;locationRestrictions
		String linea = "";
		System.out.println("Se empezaron a cargar las los productos");
		while((linea = brProductos.readLine()) != null)
		{
			String[] partes = linea.split(";"); //Separa la linea por los ;
			int productId = Integer.parseInt(partes[0]);
			String productName = partes[1];
			int productValue = Integer.parseInt(partes[2]);
			String productRestrictions = partes[3];
			Producto producto = mapaProductos.get(productId);
			if (producto==null)
			{
				producto = new Producto(productId,  productName, productValue, productRestrictions);
				mapaProductos.put(productId, producto);
			}
		}
	System.out.println("Se cargaron los servicios");
	}
	public static void cargarServicios() throws FileNotFoundException, IOException 
	{// Función que va a crear un hash map de los Servicios que se van a ofrecer en el hotel. Se carga a partir de un archivo .txt que está en la carpeta de data
		BufferedReader brServicios = new BufferedReader(new FileReader("./data/servicios.txt")); // archivo de servicios. cada linea es así: id;name;value;description
		String linea = "";
		System.out.println("Se empezaron a cargar las los servicios");
		while((linea = brServicios.readLine()) != null)
		{
			String[] partes = linea.split(";"); //Separa la linea por los ;
			int serviceId = Integer.parseInt(partes[0]);
			String serviceName = partes[1];
			int serviceValue = Integer.parseInt(partes[2]);
			String serviceDescription = partes[3];
			Servicio servicio = mapaServicios.get(serviceId);
			if (servicio==null)
			{
				servicio = new Servicio(serviceId,  serviceName, serviceValue, serviceDescription);
				mapaServicios.put(serviceId, servicio);
			}
		}
	System.out.println("Se cargaron los servicios");
	}
}
	
	
