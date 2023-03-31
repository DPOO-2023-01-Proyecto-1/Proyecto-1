package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Administrador extends Usuario {

	public Administrador(String login, String password, String userType) {
		super(login, password, userType);
		// TODO Auto-generated constructor stub
	}

	public void addProductRoom(String archivo, int idBuscado, String nuevoProducto, Map<String, Habitacion> habitaciones) {
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

					}

					else {
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
		} catch (IOException e) {
			System.out.println("Error al agregar el producto: " + e.getMessage());
		}

	}
	
	public void addServiceRoom(String archivo, int idBuscado, String nuevoServicio, Map<String, Habitacion> habitaciones) {
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

					// Agrega el nuevo servicio al tercer elemento

					if (elementos.length > 2 && !elementos[2].isEmpty()) {
						elementos[2] += ", " + nuevoServicio;

					}

					else {
						elementos[2] = nuevoServicio;
					}
					// Reemplaza la línea original por la línea actualizada
					linea = String.join(";", elementos);

					// Actualiza la habitación correspondiente en el HashMap
					Habitacion habitacion = habitaciones.get(Integer.toString(idBuscado));
					habitacion.getConsumptionRecord().add(nuevoServicio);
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

	public void deleteProductRoom(String id, String producto, String archivo, Map<String, Habitacion> habitaciones)
			throws IOException {
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
		for (int i = 0; i < lineas.size(); i++) {
			String[] partes = lineas.get(i).split(";");
			if (partes[0].equals(id)) {
				indice = i;
				break;
			}
		}

		// Si no se encontró la habitación, lanzar una excepción
		if (indice == -1) {
			throw new IllegalArgumentException("No se encontró una habitación con ID " + id);
		}

		// Obtener la habitación correspondiente y eliminar el producto de su lista

		Habitacion habitacion = habitaciones.get(id);
		if (!habitacion.getConsumptionRecord().contains(producto)) {

			throw new IllegalArgumentException("La habitación con ID " + id + " no contiene el producto " + producto);
		}

		habitacion.getConsumptionRecord().remove(producto);
		actualizarHabitacion(habitacion);

		// Actualizar la línea correspondiente en el ArrayList

		String[] partes = lineas.get(indice).split(";");
		partes[2] = String.join(",", habitacion.getConsumptionRecord());
		lineas.set(indice, String.join(";", partes));

		// Escribir las líneas actualizadas en el archivo

		BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo));
		for (String lineaActualizada : lineas) {
			escritor.write(lineaActualizada + "\n");
		}
		escritor.close();
	}
	
	public void deleteServiceRoom(String id, String servicio, String archivo, Map<String, Habitacion> habitaciones)
			throws IOException {
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
		for (int i = 0; i < lineas.size(); i++) {
			String[] partes = lineas.get(i).split(";");
			if (partes[0].equals(id)) {
				indice = i;
				break;
			}
		}

		// Si no se encontró la habitación, lanzar una excepción
		if (indice == -1) {
			throw new IllegalArgumentException("No se encontró una habitación con ID " + id);
		}

		// Obtener la habitación correspondiente y eliminar el servicio de su lista

		Habitacion habitacion = habitaciones.get(id);
		if (!habitacion.getConsumptionRecord().contains(servicio)) {

			throw new IllegalArgumentException("La habitación con ID " + id + " no contiene el producto " + servicio);
		}

		habitacion.getConsumptionRecord().remove(servicio);
		actualizarHabitacion(habitacion);

		// Actualizar la línea correspondiente en el ArrayList

		String[] partes = lineas.get(indice).split(";");
		partes[2] = String.join(",", habitacion.getConsumptionRecord());
		lineas.set(indice, String.join(";", partes));

		// Escribir las líneas actualizadas en el archivo

		BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo));
		for (String lineaActualizada : lineas) {
			escritor.write(lineaActualizada + "\n");
		}
		escritor.close();
	}

	public void addProductCatalog(Map<Integer, Producto> products, Integer id, String name, Integer value, String textFile,
			String locationRestrictions) {
		// Crear objeto Product con los datos recibidos
		Producto newProduct = new Producto(id, value, name, locationRestrictions);

		// Agregar producto al Map
		products.put(id, newProduct);

		// Escribir datos en archivo de texto
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(textFile, true))) {
			writer.write(id + ";" + value + ";" + name + ";" + locationRestrictions);
			writer.newLine();
		} catch (IOException e) {
			System.err.println("Error al escribir en archivo: " + e.getMessage());
		}

	}
	
	public void addServiceCatalog(Map<Integer, Producto> services, Integer id, String name, Integer value, String textFile,
			String description) {
		// Crear objeto Product con los datos recibidos
		Producto newProduct = new Producto(id, value, name, description);

		// Agregar producto al Map
		services.put(id, newProduct);

		// Escribir datos en archivo de texto
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(textFile, true))) {
			writer.write(id + ";" + value + ";" + name + ";" + description);
			writer.newLine();
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
	
	public void deleteServiceCatalog(Map<Integer, Producto> services, Integer id, String textFile) {
		// Obtener el Servicio correspondiente al ID

		Producto deletedService = services.get(id);

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
	
	public void deleteRoom(Integer roomId, Map<Integer, Habitacion> habitaciones, String rutaArchivo) throws IOException {
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
	
	public void addUser(String login, String password, String userType, Map<String, Usuario> usuarios, String rutaArchivo) throws IOException
	{
		
	Usuario usuario;
	
	if (userType.equals("administrador"))
	{
	usuario = new Administrador(login, password, userType);
	}
	else if (userType.equals("recepcionista"))
	{
	usuario = new Recepcionista(login, password, userType);
	}
	else
	{
	usuario = new Empleado(login, password, userType);
	}
	
	usuarios.put(login, usuario);

	FileWriter escritor = new FileWriter(rutaArchivo, true);
	escritor.write(login + ";" + password + ";" + userType + "\n");
	escritor.close();
	
	}
	
	
	public void deleteUser(String login, Map<String, Usuario> usuarios, String rutaArchivo) throws IOException
	{
		
	usuarios.remove(login);

	FileWriter escritor = new FileWriter(rutaArchivo);
	
	for (Usuario usuario : usuarios.values()) 
	{
		escritor.write(usuario.getLogin() + ";" + usuario.getPassword() + ";" + usuario.getUserType() + "\n");
	}
	escritor.close();
	}
	
	
	public void getBookings(Map<Integer, Reserva> reservas) 
	{
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