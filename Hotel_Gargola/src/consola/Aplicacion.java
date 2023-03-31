package consola;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import modelo.Administrador;
import modelo.Hotel;
import modelo.Usuario;

public class Aplicacion {
	Hotel hotel = new Hotel();
	public void ejecutarAplicacion() throws FileNotFoundException, UnsupportedEncodingException {
		ejecutarCargarDatos();
	}

	public void ejecutarCargarDatos() {

		try {
			Hotel.cargarInfoHotel();
			iniciar(hotel);
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: el archivo no se encontró.");
		} catch (IOException e) {
			System.out.println("Aqui pasó algo malo");
			System.out.println(e.getMessage());
		}
	}

	public void iniciar(Hotel hotel) {
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("Bienvenido a la consola de usuarios");

			// Pedir login al usuario
			System.out.print("Ingrese su login: ");
			String login = scanner.nextLine();

			// Buscar el usuario en el mapa de usuarios del hotel
			Usuario usuario = hotel.getMapaUsuarios().get(login);

			if (usuario == null) {
				System.out.println("Login incorrecto");
				return;
			}

			// Pedir password al usuario
			System.out.print("Ingrese su password: ");
			String password = scanner.nextLine();

			// Verificar si el password es correcto
			if (!usuario.getPassword().equals(password)) {
				System.out.println("Password incorrecto");
				return;
			}

			// Determinar el tipo de usuario
			String tipo = usuario.getUserType();
			
			switch (tipo) {
			
			case "administrador":
				
				Administrador admin = (Administrador) usuario;
				
				System.out.println("Bienvenido administrador");
				
				menu_administrador(scanner);
				
				System.out.print("Seleccione una opción: ");

				// Leer la entrada del usuario como una cadena
				String seleccionStr = scanner.nextLine();

				// Convertir la cadena en un número entero, si es posible
				int seleccionInt = Integer.parseInt(seleccionStr);
				
				// Procesar la opción seleccionada
				switch (seleccionInt) 
				{
				    case 1:
				    	
				    	System.out.println("Ingrese el codigo del producto que desea agregar:");
				    	
				    	String codigoProducto = scanner.nextLine();
				    	
				    	System.out.println("Ingrese el codigo de la habitacion sobre la cual desea agregar el producto:");
						
						String codigoHabitacion = scanner.nextLine();
						
						int intCodigoHabitacion = Integer.parseInt(codigoHabitacion);
						
						String rutaArchivo = "./data/habitaciones.txt";
						
				        admin.addProductRoom(rutaArchivo, intCodigoHabitacion, codigoProducto, hotel.getMapaHabitaciones());
				        
				        break;
				           
				    case 2:
				    	
				    	System.out.println("Ingrese el codigo del servicio que desea agregar:");
				    	
				    	String codigoServicio = scanner.nextLine();
				    	
				    	System.out.println("Ingrese el codigo de la habitacion sobre la cual desea agregar el servicio:");
						
						String codigoHabitacionServicio = scanner.nextLine();
						
						int intCodigoHabitacionServicio = Integer.parseInt(codigoHabitacionServicio);
						
						String rutaArchivoServicio = "./data/habitaciones.txt";
						
				        admin.addServiceRoom(rutaArchivoServicio, intCodigoHabitacionServicio, codigoServicio, hotel.getMapaHabitaciones());
				        
				        break;
				        
				        
				    case 3:
				    	
				    	System.out.println("Ingrese el codigo del producto que desea eliminar:");
				    	
				    	String codigoProductoEliminar = scanner.nextLine();
				    	
				    	System.out.println("Ingrese el codigo de la habitacion sobre la cual desea eliminar el producto:");
						
						String codigoHabitacionEliminar = scanner.nextLine();
						
						int intCodigoHabitacionEliminar = Integer.parseInt(codigoHabitacionEliminar);
						
						String rutaArchivoEliminar = "./data/habitaciones.txt";
						
				        admin.removeProductRoom(rutaArchivoEliminar, intCodigoHabitacionEliminar, codigoProductoEliminar, hotel.getMapaHabitaciones());
						
				        break;
				        
				    case 4:
				    	
				    	System.out.println("Ingrese el codigo del servicio que desea agregar:");
				    	
				    	String codigoServicioEliminar = scanner.nextLine();
				    	
				    	System.out.println("Ingrese el codigo de la habitacion sobre la cual desea agregar el servicio:");
						
						String codigoHabitacionServicioEliminar = scanner.nextLine();
						
						int intCodigoHabitacionServicioEliminar = Integer.parseInt(codigoHabitacionServicioEliminar);
						
						String rutaArchivoServicioEliminar = "./data/habitaciones.txt";
						
				        admin.removeServiceRoom(rutaArchivoServicioEliminar, intCodigoHabitacionServicioEliminar, codigoServicioEliminar, hotel.getMapaHabitaciones());
				        
				        break;
				    	
				    	
				        
				    default:
				        System.out.println("Opción inválida");
				        break;
				}
					
				break;
			case "empleado":
				System.out.println("Bienvenido empleado");
				break;
			case "recepcionista":
				System.out.println("Bienvenido recepcionista");
				break;
			default:
				System.out.println("Tipo de usuario desconocido");
				break;
			}
		}
	}

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		Aplicacion consola = new Aplicacion();
		consola.ejecutarAplicacion();
	}
	
	public void menu_administrador(Scanner scanner) {
		System.out.println("Ingrese a partir de las siguientes opciones la accion que desea realizar\n");
		System.out.println("(1)- Agregar un Producto a una habitacion - (Integer) codigo de la habitacion - (Integer) Codigo Producto");
		System.out.println("(2)- Agregar un Servicio a una habitacion - (Integer) codigo de la habitacion - (Integer) Codigo Servicio");
		System.out.println("(3)- Eliminar un Producto a una habitacion - (Integer) codigo de la habitacion - (Integer) Codigo Producto");
		System.out.println("(4)- Eliminar un Servicio a una habitacion - (Integer) codigo de la habitacion - (Integer) Codigo Servicio");
		System.out.println("(5)- Agregar un Servicio al catalogo de servicios");
		System.out.println("(6)- Agregar un Producto al catalogo de productos");
		System.out.println("(7)- Eliminar un Servicio al catalogo de servicios - (Integer) codigo del servicio");
		System.out.println("(8)- Eliminar un Producto al catalogo de productos - (Integer) codigo del producto");
		System.out.println("(9)- Obtener la lista de reservas actuales");
		System.out.println("(10)- Obtener la lista de usuarios actuales");
		
		
		
	}
}
