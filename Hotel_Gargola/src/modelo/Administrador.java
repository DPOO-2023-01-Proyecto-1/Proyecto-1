package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Administrador extends Usuario {

	public Administrador(String login, String password, String userType) {
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

	public void removeProductRoom(String archivo, int idBuscado, String productoEliminar,
			Map<Integer, Habitacion> habitaciones) {
		try {
			// Abre el archivo para lectura y escritura
			BufferedReader reader = new BufferedReader(new FileReader(archivo));
			FileWriter writer = new FileWriter(archivo + ".tmp");

			String linea;
			boolean encontrado = false;

			// Lee cada línea del archivo
			while ((linea = reader.readLine()) != null) {
				// Divide la línea por el carácter ";" para obtener los elementos
				String[] elementos = linea.split(";");

				// Si el segundo elemento es el id buscado
				if (Integer.parseInt(elementos[0]) == idBuscado) {
					encontrado = true;

					// Verifica si la habitación tiene el producto a eliminar
					String[] productos = elementos[3].split(",");
					List<String> productosList = new ArrayList<>(Arrays.asList(productos));

					if (!productosList.remove(productoEliminar)) {
						throw new RuntimeException("La habitación no tiene el producto a eliminar.");
					}

					// Reemplaza la línea original por la línea actualizada
					elementos[3] = String.join(",", productosList);
					linea = String.join(";", elementos);

					// Actualiza la habitación correspondiente en el HashMap
					Habitacion habitacion = habitaciones.get(idBuscado);
					habitacion.getConsumptionRecord().remove(productoEliminar);
					actualizarHabitacion(habitacion);
				}

				// Escribe la línea en el archivo temporal
				writer.write(linea + "\n");
			}

			// Si no se encontró el id buscado, lanza una excepción
			if (!encontrado) {
				throw new RuntimeException("No se encontró el id buscado.");
			}

			// Cierra el lector y escritor de archivos
			reader.close();
			writer.close();

			// Reemplaza el archivo original con el archivo temporal
			File originalFile = new File(archivo);
			File tempFile = new File(archivo + ".tmp");

			if (!tempFile.renameTo(originalFile)) {
				throw new RuntimeException("No se pudo actualizar el archivo.");
			}

			System.out.println("Producto eliminado exitosamente.");
		} catch (IOException e) {
			System.out.println("Error al eliminar el producto: " + e.getMessage());
		}
	}

	public void removeServiceRoom(String archivo, int idBuscado, String servicioEliminar,
	Map<Integer, Habitacion> habitaciones) {
		try {
			// Abre el archivo para lectura y escritura
			BufferedReader reader = new BufferedReader(new FileReader(archivo));
			FileWriter writer = new FileWriter(archivo + ".tmp");

			String linea;
			boolean encontrado = false;

			// Lee cada línea del archivo
			while ((linea = reader.readLine()) != null) {
				// Divide la línea por el carácter ";" para obtener los elementos
				String[] elementos = linea.split(";");

				// Si el segundo elemento es el id buscado
				if (Integer.parseInt(elementos[0]) == idBuscado) {
					encontrado = true;

					// Verifica si la habitación tiene el producto a eliminar
					String[] productos = elementos[3].split(",");
					List<String> productosList = new ArrayList<>(Arrays.asList(productos));

					if (!productosList.remove(servicioEliminar)) {
						throw new RuntimeException("La habitación no tiene el producto a eliminar.");
					}

					// Reemplaza la línea original por la línea actualizada
					elementos[3] = String.join(",", productosList);
					linea = String.join(";", elementos);

					// Actualiza la habitación correspondiente en el HashMap
					Habitacion habitacion = habitaciones.get(idBuscado);
					habitacion.getConsumptionRecord().remove(servicioEliminar);
					actualizarHabitacion(habitacion);
				}

				// Escribe la línea en el archivo temporal
				writer.write(linea + "\n");
			}

			// Si no se encontró el id buscado, lanza una excepción
			if (!encontrado) {
				throw new RuntimeException("No se encontró el id buscado.");
			}

			// Cierra el lector y escritor de archivos
			reader.close();
			writer.close();

			// Reemplaza el archivo original con el archivo temporal
			File originalFile = new File(archivo);
			File tempFile = new File(archivo + ".tmp");

			if (!tempFile.renameTo(originalFile)) {
				throw new RuntimeException("No se pudo actualizar el archivo.");
			}

			System.out.println("Producto eliminado exitosamente.");
		} catch (IOException e) {
			System.out.println("Error al eliminar el producto: " + e.getMessage());
		}
	}

	public static void addProductCatalog(Map<Integer, Producto> products, Integer id, String name, Integer value,
			String textFile, String locationRestrictions) {
		// Crear objeto Product con los datos recibidos
		Producto newProduct = new Producto(id, value, name, locationRestrictions);

		// Agregar producto al Map
		products.put(id, newProduct);

		// Escribir datos en archivo de texto
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(textFile, true))) {
			writer.newLine();
			writer.write(id + ";" + name + ";" + value + ";" + locationRestrictions);
			System.out.println("Producto agregado existosamente");
			
		} catch (IOException e) {
			System.err.println("Error al escribir en archivo: " + e.getMessage());
		}

	}

	public static void addServiceCatalog(Map<Integer, Servicio> services, Integer id, String name, Integer value,
			String textFile, String description) {
		// Crear objeto Product con los datos recibidos
		Servicio newService = new Servicio(id, name, value, description);

		// Agregar producto al Map
		services.put(id, newService);

		// Escribir datos en archivo de texto
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(textFile, true))) {
			writer.newLine();
			writer.write(id + ";" + name + ";" + value + ";" + description);
			System.out.println("Servicio agregado existosamente");
			
		} catch (IOException e) {
			System.err.println("Error al escribir en archivo: " + e.getMessage());
		}

	}


	public void deleteProductCatalog(Map<Integer, Producto> products, Integer id, String textFile) {
		// Obtener el producto correspondiente al ID

		Producto deletedProduct = products.get(id);

		// Si el producto existe en el Map

		if (deletedProduct != null) {
			// Eliminar producto del Map

			products.remove(id);

			// Eliminar línea correspondiente al producto en el archivo de texto

			try (BufferedReader reader = new BufferedReader(new FileReader(textFile));
					BufferedWriter writer = new BufferedWriter(new FileWriter(textFile + ".temp"))) {
				String currentLine;
				while ((currentLine = reader.readLine()) != null) {
					String[] fields = currentLine.split(";");
					if (!fields[0].equals(String.valueOf(id))) {
						writer.write(currentLine);
						writer.newLine();
					}
				}
			} catch (IOException e) {
				System.err.println("Error al eliminar producto: " + e.getMessage());
			}

			// Renombrar archivo temporal y borrar el original

			try {
				java.nio.file.Files.move(java.nio.file.Paths.get(textFile + ".temp"), java.nio.file.Paths.get(textFile),
						java.nio.file.StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				System.err.println("Error al renombrar archivo temporal: " + e.getMessage());
			}
		} else {
			System.out.println("El producto con ID " + id + " no existe en el Map.");
		}
	}

	public void deleteServiceCatalog(Map<Integer, Servicio> services, Integer id, String textFile) {
		// Obtener el Servicio correspondiente al ID

		Servicio deletedService = services.get(id);

		// Si el Servicio existe en el Map

		if (deletedService != null) {
			// Eliminar Servicio del Map

			services.remove(id);

			// Eliminar línea correspondiente al Servicio en el archivo de texto

			try (BufferedReader reader = new BufferedReader(new FileReader(textFile));
					BufferedWriter writer = new BufferedWriter(new FileWriter(textFile + ".temp"))) {
				String currentLine;
				while ((currentLine = reader.readLine()) != null) {
					String[] fields = currentLine.split(";");
					if (!fields[0].equals(String.valueOf(id))) {
						writer.write(currentLine);
						writer.newLine();
					}
				}
			} catch (IOException e) {
				System.err.println("Error al eliminar producto: " + e.getMessage());
			}

			// Renombrar archivo temporal y borrar el original

			try {
				java.nio.file.Files.move(java.nio.file.Paths.get(textFile + ".temp"), java.nio.file.Paths.get(textFile),
						java.nio.file.StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				System.err.println("Error al renombrar archivo temporal: " + e.getMessage());
			}
		} else {
			System.out.println("El producto con ID " + id + " no existe en el Map.");
		}
	}

	public void addRoom(Map<Integer, Habitacion> rooms, int roomId, int valueByNight, String roomType,
			int guestCapacity, String availableServices, String filePath) throws IOException {
		// Convierte el String de servicios en una lista de Strings

		ArrayList<String> servicesList = new ArrayList<String>();
		String[] servicesCodes = availableServices.split(",");
		for (String code : servicesCodes) {
			servicesList.add(code.trim());
		}

		Habitacion newRoom = new Habitacion(roomId, false, servicesList, new ArrayList<String>(),
				new ArrayList<String>(), valueByNight, guestCapacity, roomType, 0);
		rooms.put(roomId, newRoom);

		// Escribe la información de la habitación en el archivo de texto
		BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
		writer.write(newRoom.getId() + ";" + newRoom.getOccupancyStatus() + ";"
				+ String.join(",", newRoom.getServices()) + ";" + String.join(",", newRoom.getConsumptionRecord()) + ";"
				+ String.join(",", newRoom.getGuestList()) + ";" + newRoom.getValueByNight() + ";"
				+ newRoom.getGuestCapacity() + ";" + newRoom.getRoomType() + ";" + newRoom.getTotalValue());
		writer.newLine();
		writer.close();
	}

	public void deleteRoom(Integer roomId, Map<Integer, Habitacion> habitaciones, String rutaArchivo)
			throws IOException {
		habitaciones.remove(roomId);

		File archivo = new File(rutaArchivo);
		File archivoTemporal = new File("temp.txt");

		BufferedReader lector = new BufferedReader(new FileReader(archivo));
		BufferedWriter escritor = new BufferedWriter(new FileWriter(archivoTemporal));

		String linea;
		while ((linea = lector.readLine()) != null) {
			String[] atributos = linea.split(";");
			if (Integer.parseInt(atributos[0]) != roomId) {
				escritor.write(linea + "\n");
			}
		}

		lector.close();
		escritor.close();

		archivo.delete();
		archivoTemporal.renameTo(new File(rutaArchivo));
	}

	public void addUser(String login, String password, String userType, Map<String, Usuario> usuarios,
			String rutaArchivo) throws IOException {

		Usuario usuario;

		if (userType.equals("administrador")) {
			usuario = new Administrador(login, password, userType);
		} else if (userType.equals("recepcionista")) {
			usuario = new Recepcionista(login, password, userType);
		} else {
			usuario = new Empleado(login, password, userType);
		}

		usuarios.put(login, usuario);

		FileWriter escritor = new FileWriter(rutaArchivo, true);
		escritor.write(login + ";" + password + ";" + userType + "\n");
		escritor.close();

	}

	public void deleteUser(String login, Map<String, Usuario> usuarios, String rutaArchivo) throws IOException {

		usuarios.remove(login);

		FileWriter escritor = new FileWriter(rutaArchivo);

		for (Usuario usuario : usuarios.values()) {
			escritor.write(usuario.getLogin() + ";" + usuario.getPassword() + ";" + usuario.getUserType() + "\n");
		}
		escritor.close();
	}

	public void getBookings(Map<Integer, Reserva> reservas) {
		for (Map.Entry<Integer, Reserva> entry : reservas.entrySet()) {
			Integer idReserva = entry.getKey();
			Reserva reserva = entry.getValue();
			System.out.println("Reserva #" + idReserva);
			System.out.println("Date: " + reserva.getDate());
			System.out.println("Associated rooms: " + reserva.getRoomsList());
			System.out.println("Guest list: " + reserva.getGuestList());
			System.out.println("Associated value: " + reserva.getAssociatedValue());
			System.out.println("------------------------------------");
		}
	}

	public void getUsers(Map<String, Usuario> usuarios) {
		for (Usuario usuario : usuarios.values()) {
			System.out.println("Login: " + usuario.getLogin());
			System.out.println("Password: " + usuario.getPassword());
			System.out.println("UserType: " + usuario.getUserType());
			System.out.println("--------------------");
		}
	}

}