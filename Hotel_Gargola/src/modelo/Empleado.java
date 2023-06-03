package modelo;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class Empleado extends Usuario
{

	public Empleado(String login, String password, String userType) {
		super(login, password, userType);
		// TODO Auto-generated constructor stub
	}
	
	
	public static void addProductRoom(String archivo, int idBuscado, String nuevoProducto,
			Map<Integer, Habitacion> habitaciones) {
		try {
			// Lee todas las lineas del archivo en una lista
			List<String> lineas = Files.readAllLines(Paths.get(archivo), Charset.defaultCharset());
			boolean encontrado = false;

			// Itera sobre las lineas y actualiza la línea correspondiente
			for (int i = 0; i < lineas.size(); i++) {
				String linea = lineas.get(i);
				String[] elementos = linea.split(";");

				// Si se encuentra la linea correspondiente al ID buscado
				if (Integer.parseInt(elementos[0]) == idBuscado) {
					encontrado = true;

					// Agrega el nuevo producto a la tercera columna
					if (elementos.length > 2 && !elementos[3].isEmpty()) {
						elementos[3] += "," + nuevoProducto;
					} else {
						elementos[3] = nuevoProducto;
					}

					// Actualiza la línea en la lista
					linea = String.join(";", elementos);
					lineas.set(i, linea);

					// Actualiza la habitación correspondiente en el HashMap
					Habitacion habitacion = habitaciones.get(idBuscado);
					habitacion.getConsumptionRecord().add(nuevoProducto);
					

					break; // Termina el bucle porque ya se actualizó la línea correspondiente
				}
			}

			// Si no se encontró el ID buscado, lanza una excepción
			if (!encontrado) {
				throw new RuntimeException("No se encontró el id buscado.");
			}

			// Escribe todas las lineas actualizadas al archivo
			Files.write(Paths.get(archivo), lineas, Charset.defaultCharset());

			//System.out.println("Producto agregado exitosamente.");
			
			//------------PARA GRAFICAS DE CANTIDAD DE PRODUCTOS----------//
		

			String archivoProductos = "./data/productos.txt";
			List<String> lineasProducto = Files.readAllLines(Paths.get(archivoProductos), Charset.defaultCharset());
			boolean encontradoProducto = false;
			for (int i = 0; i < lineasProducto.size(); i++) {
				
				String linea = lineasProducto.get(i);
				String[] elementosProductos = linea.split(";");
				System.out.println(elementosProductos[0]);

				// Si se encuentra la linea correspondiente al ID buscado
				if (Integer.parseInt(elementosProductos[0])==Integer.parseInt(nuevoProducto)) {
					encontradoProducto = true;

					int numeroProductoVendidos = Integer.parseInt(elementosProductos[4])+1;
					elementosProductos[4]= Integer.toString(numeroProductoVendidos);

					// Actualiza la línea en la lista
					linea = String.join(";", elementosProductos);
					lineasProducto.set(i, linea);
					System.out.println("aqui pasó algo");
					break; // Termina el bucle porque ya se actualizó la línea correspondiente
				}
			}
			Files.write(Paths.get(archivoProductos), lineasProducto, Charset.defaultCharset());
			
			
		} catch (IOException e) {
			//System.out.println("Error al agregar el producto: " + e.getMessage());
		}
	}

	public static void addServiceRoom(String archivo, int idBuscado, String nuevoServicio,
			Map<Integer, Habitacion> habitaciones) {
		try {
			// Lee todas las lineas del archivo en una lista
			List<String> lineas = Files.readAllLines(Paths.get(archivo), Charset.defaultCharset());
			boolean encontrado = false;

			// Itera sobre las lineas y actualiza la línea correspondiente
			for (int i = 0; i < lineas.size(); i++) {
				String linea = lineas.get(i);
				String[] elementos = linea.split(";");

				// Si se encuentra la linea correspondiente al ID buscado
				if (Integer.parseInt(elementos[0]) == idBuscado) {
					encontrado = true;

					// Agrega el nuevo producto a la tercera columna
					if (elementos.length > 2 && !elementos[3].isEmpty()) {
						elementos[3] += "," + nuevoServicio;
					} else {
						elementos[3] = nuevoServicio;
					}

					// Actualiza la línea en la lista
					linea = String.join(";", elementos);
					lineas.set(i, linea);

					// Actualiza la habitación correspondiente en el HashMap
					Habitacion habitacion = habitaciones.get(idBuscado);
					habitacion.getConsumptionRecord().add(nuevoServicio);
					actualizarHabitacion(habitacion);

					break; // Termina el bucle porque ya se actualizó la línea correspondiente
				}
			}

			// Si no se encontró el ID buscado, lanza una excepción
			if (!encontrado) {
				throw new RuntimeException("No se encontró el id buscado.");
			}

			// Escribe todas las lineas actualizadas al archivo
			Files.write(Paths.get(archivo), lineas, Charset.defaultCharset());
			
			//----------FUNCION GRAFICAS DE SERVICIOS ---------//
			String archivoServicios = "./data/servicios.txt";
			List<String> lineasServicio = Files.readAllLines(Paths.get(archivoServicios), Charset.defaultCharset());
			boolean encontradoProducto = false;
			for (int i = 0; i < lineasServicio.size(); i++) {
				
				String linea = lineasServicio.get(i);
				String[] elementosServicios = linea.split(";");
				System.out.println(elementosServicios[0]);

				// Si se encuentra la linea correspondiente al ID buscado
				if (Integer.parseInt(elementosServicios[0])==Integer.parseInt(nuevoServicio)) {
					encontradoProducto = true;

					int numeroProductoVendidos = Integer.parseInt(elementosServicios[4])+1;
					elementosServicios[4]= Integer.toString(numeroProductoVendidos);

					// Actualiza la línea en la lista
					linea = String.join(";", elementosServicios);
					lineasServicio.set(i, linea);

					break; // Termina el bucle porque ya se actualizó la línea correspondiente
				}
			}
			Files.write(Paths.get(archivoServicios), lineasServicio, Charset.defaultCharset());
			//System.out.println("Servicio agregado exitosamente.");
		} catch (IOException e) {
			//System.out.println("Error al agregar el servicio: " + e.getMessage());
		}
	}

	private static void actualizarHabitacion(Habitacion habitacion) {
		//System.out.println("Habitación actualizada:");
		//System.out.println("ID: " + habitacion.getId());
		//System.out.println("Consumption record: " + habitacion.getConsumptionRecord());
	}

}
