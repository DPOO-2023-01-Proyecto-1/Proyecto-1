package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Administrador extends Usuario 
{

	public Administrador(String login, String password, String userType) 
	{
		super(login, password, userType);
		// TODO Auto-generated constructor stub
	}
	
	
	public void agregarProducto(String archivo, int idBuscado, String nuevoProducto, Map<String, Habitacion> habitaciones)
	{
	 try {
		      // Abre el archivo para lectura y escritura
		      BufferedReader reader = new BufferedReader(new FileReader(archivo));
		      FileWriter writer = new FileWriter(archivo, true);
		      String linea;
		      boolean encontrado = false;
		      
		      // Lee cada línea del archivo
		      while ((linea = reader.readLine()) != null) {
		        // Divide la línea por el carácter ";" para obtener los elementos
		        String[] elementos = linea.split(";");
		        // Si el segundo elemento es el id buscado
		        if (Integer.parseInt(elementos[1]) == idBuscado) {
		          encontrado = true;
		          // Agrega el nuevo producto al tercer elemento
		          if (elementos.length > 2 && !elementos[2].isEmpty()) {
		            elementos[2] += ", " + nuevoProducto;
		          } else {
		            elementos[2] = nuevoProducto;
		          }
		          // Reemplaza la línea original por la línea actualizada
		          linea = String.join(";", elementos);
		          
		          // Actualiza la habitación correspondiente en el HashMap
		          Habitacion habitacion = habitaciones.get(Integer.toString(idBuscado));
		          habitacion.getConsumptionRecord().add(nuevoProducto);
		          actualizarHabitacion(habitacion);
		        }
		        // Escribe la línea en el archivo
		        writer.write(linea + "\n");
		      }
		      
		      // Si no se encontró el id buscado, lanza una excepción
		      if (!encontrado) {
		        throw new RuntimeException("No se encontró el id buscado.");
		      }
		      
		      // Cierra el lector y escritor de archivos
		      reader.close();
		      writer.close();
		      
		      System.out.println("Producto agregado exitosamente.");
		    }
	 		catch (IOException e)
	 		{
		      System.out.println("Error al agregar el producto: " + e.getMessage());
		    }
		  
		  

     }
	
	private void actualizarHabitacion(Habitacion habitacion) 
	{
	    System.out.println("Habitación actualizada:");
	    System.out.println("ID: " + habitacion.getId());
	    System.out.println("Consumption record: " + habitacion.getConsumptionRecord());
	}
	 
	 
	

    public void eliminarProducto(String id, String producto, String archivo, HashMap<String, Habitacion> habitaciones) throws IOException 
    {
    // Leer el archivo y guardar cada línea en un ArrayList
    	
    ArrayList<String> lineas = new ArrayList<>();
    BufferedReader lector = new BufferedReader(new FileReader(archivo));
    String linea;
    
    while ((linea = lector.readLine()) != null) 
    {
        lineas.add(linea);
    }
    
    lector.close();
    
    // Buscar la línea correspondiente a la habitación con el ID dado
    
    int indice = -1;
    for (int i = 0; i < lineas.size(); i++) 
    {
        String[] partes = lineas.get(i).split(";");
        if (partes[0].equals(id)) 
        {
            indice = i;
            break;
        }
    }
    
    // Si no se encontró la habitación, lanzar una excepción
    if (indice == -1) 
    {
        throw new IllegalArgumentException("No se encontró una habitación con ID " + id);
    }
    
    // Obtener la habitación correspondiente y eliminar el producto de su lista
    
    Habitacion habitacion = habitaciones.get(id);
    if (!habitacion.getConsumptionRecord().contains(producto)) 
    {
    	
        throw new IllegalArgumentException("La habitación con ID " + id + " no contiene el producto " + producto);
    }
    habitacion.getConsumptionRecord().remove(producto);
    
    // Actualizar la línea correspondiente en el ArrayList
    String[] partes = lineas.get(indice).split(";");
    partes[2] = String.join(",", habitacion.getConsumptionRecord());
    lineas.set(indice, String.join(";", partes));
    
    // Escribir las líneas actualizadas en el archivo
    BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo));
    for (String lineaActualizada : lineas) 
    {
        escritor.write(lineaActualizada + "\n");
    }
    escritor.close();
    }
    
}
	
	
	

