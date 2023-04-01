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
	
	
	public void addProductRoom(String archivo, int idBuscado, String nuevoProducto,
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

			System.out.println("Producto agregado exitosamente.");
		} catch (IOException e) {
			System.out.println("Error al agregar el producto: " + e.getMessage());
		}
	}

	public void addServiceRoom(String archivo, int idBuscado, String nuevoServicio,
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

			System.out.println("Servicio agregado exitosamente.");
		} catch (IOException e) {
			System.out.println("Error al agregar el servicio: " + e.getMessage());
		}
	}

	private void actualizarHabitacion(Habitacion habitacion) {
		System.out.println("Habitación actualizada:");
		System.out.println("ID: " + habitacion.getId());
		System.out.println("Consumption record: " + habitacion.getConsumptionRecord());
	}

}
