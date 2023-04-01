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
import modelo.Empleado;
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

	public void iniciar(Hotel hotel) throws FileNotFoundException, IOException {
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("Bienvenido a la consola de usuarios");

			// Pedir login al usuario
			System.out.print("Ingrese su login: ");
			String login = scanner.nextLine(); //se comentó esto para ensayar
			Usuario usuario = hotel.getMapaUsuarios().get(login);
			
			Boolean continuacion = true;
			while(continuacion ==true)
			{
				usuario = hotel.getMapaUsuarios().get(login);
				if (usuario == null)
				{
					System.out.println("Login incorrecto");
					System.out.println("Ingrese su login: ");
					login = scanner.nextLine();
				}
				else {
					System.out.println("Login correcto");
					continuacion = false;
				}
			}
			
			// Pedir password al usuario
			System.out.print("Ingrese su password: ");
			String password = scanner.nextLine();
			Boolean continuacion2 = true;
			while(continuacion2 == true)
			{
				// Verificar si el password es correcto
				if (!usuario.getPassword().equals(password)) {
					System.out.println("Password incorrecto");
					System.out.println("Digite su password");
					password = scanner.nextLine();
					
				}
				else {
					System.out.println("Password correcto");
					continuacion2 = false;
				}
			}
				
						// Determinar el tipo de usuario
			String tipo = usuario.getUserType();
			
			switch (tipo) {
			
			case "administrador":
				
				Administrador admin = (Administrador) usuario;
				
				System.out.println("Bienvenido administrador");
				
				menu_administrador(scanner);
				
				System.out.println("");
				
				System.out.print("Seleccione una opción: ");

				// Leer la entrada del usuario como una cadena
				String seleccionStr = scanner.nextLine();

				// Convertir la cadena en un número entero, si es posible
				int seleccionInt = Integer.parseInt(seleccionStr);
				
				// Procesar la opción seleccionada
				switch (seleccionInt) 
				{
				    case 1:
				    	Hotel.mostrarProductos();
				    	System.out.println("Ingrese el codigo del producto que desea agregar:");
				    	
				    	String codigoProducto = scanner.nextLine();
				    	
				    	System.out.println("Ingrese el codigo de la habitacion sobre la cual desea agregar el producto:");
						
						String codigoHabitacion = scanner.nextLine();
						
						int intCodigoHabitacion = Integer.parseInt(codigoHabitacion);
						
						String rutaArchivo = "./data/habitaciones.txt";
						
				        admin.addProductRoom(rutaArchivo, intCodigoHabitacion, codigoProducto, hotel.getMapaHabitaciones());
				        
				        break;
				           
				    case 2:
				    	
				    	Hotel.mostrarServicios();
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
				    	
				    	System.out.println("Ingrese el codigo del servicio que desea eliminar:");
				    	
				    	String codigoServicioEliminar = scanner.nextLine();
				    	
				    	System.out.println("Ingrese el codigo de la habitacion sobre la cual desea eliminar el servicio:");
						
						String codigoHabitacionServicioEliminar = scanner.nextLine();
						
						int intCodigoHabitacionServicioEliminar = Integer.parseInt(codigoHabitacionServicioEliminar);
						
						String rutaArchivoServicioEliminar = "./data/habitaciones.txt";
						
				        admin.removeServiceRoom(rutaArchivoServicioEliminar, intCodigoHabitacionServicioEliminar, codigoServicioEliminar, hotel.getMapaHabitaciones());
				        
				        break;
				        
				    case 5:
				    	
				    	System.out.println("Ingrese el nombre del servicio que desea agregar:");
				    	
				    case 6:
				    	
				    	
				    	
				    case 7:
				    	
				    	System.out.println("Ingrese e codigo del servicio que desea eliminar del catalogo de servicios:");
				    	
				    	String codigoServicioEliminarCatalogo = scanner.nextLine();
				    	
				    	int intCodigoServicioEliminarCatalogo = Integer.parseInt(codigoServicioEliminarCatalogo);
				    	
				    	String rutaServicioEliminarCatalogo = "./data/servicios.txt";
				    	
				    	admin.deleteServiceCatalog(hotel.getMapaServicios(), intCodigoServicioEliminarCatalogo, rutaServicioEliminarCatalogo);
				    	
				    	break;
				    	
				    case 8:
				    	
				    	System.out.println("Ingrese el codigo del producto que desea eliminar del catalogo de productos:");
				    	
				    	String codigoProductoEliminarCatalogo = scanner.nextLine();
				    	
				    	int intCodigoProductoEliminarCatalogo = Integer.parseInt(codigoProductoEliminarCatalogo);
				    	
				    	String rutaProductoEliminarCatalogo = "./data/servicios.txt";
				    	
				    	admin.deleteProductCatalog(hotel.getMapaProductos(),intCodigoProductoEliminarCatalogo, rutaProductoEliminarCatalogo);
				    	
				    	break;
				    
				    case 9:
				    	
				    	System.out.println("A continuacion se observa la lista de las reservas actuales:");
				    	
				    	System.out.println("");
				    	
				    	admin.getBookings(hotel.getMapaReservas());
				    	
				    	break;
				    	
				    case 10:
				    	
				    	System.out.println("A continuacion se observa la lista de los usuarios actuales:");
				    	
				    	System.out.println("");
				    	
				    	admin.getUsers(hotel.getMapaUsuarios());
				    	
				    	break;
				 
				        
				    default:
				        System.out.println("Opción inválida");
				        break;
				}
					
				break;
			case "empleado":
				Empleado empleado = (Empleado)usuario;
				System.out.println("Bienvenido empleado");
				
				menu_empleado(scanner);
				
				System.out.println("");
				
				System.out.print("Seleccione una opción: ");

				// Leer la entrada del usuario como una cadena
				String seleccionStrEmpleado = scanner.nextLine();

				// Convertir la cadena en un número entero, si es posible
				int seleccionIntEmpleado = Integer.parseInt(seleccionStrEmpleado);
				
				// Procesar la opción seleccionada
				switch (seleccionIntEmpleado) 
				{
				    case 1:
				    	Hotel.mostrarProductos();
				    	System.out.println("Ingrese el codigo del producto que desea agregar:");
				    	
				    	String codigoProducto = scanner.nextLine();
				    	
				    	System.out.println("Ingrese el codigo de la habitacion sobre la cual desea agregar el producto:");
						
						String codigoHabitacion = scanner.nextLine();
						
						int intCodigoHabitacion = Integer.parseInt(codigoHabitacion);
						
						String rutaArchivo = "./data/habitaciones.txt";
						
				        empleado.addProductRoom(rutaArchivo, intCodigoHabitacion, codigoProducto, hotel.getMapaHabitaciones());
				        
				        break;
				           
				    case 2:
				    	
				    	Hotel.mostrarServicios();
				    	System.out.println("Ingrese el codigo del servicio que desea agregar:");
				    	
				    	String codigoServicio = scanner.nextLine();
				    	
				    	System.out.println("Ingrese el codigo de la habitacion sobre la cual desea agregar el servicio:");
						
						String codigoHabitacionServicio = scanner.nextLine();
						
						int intCodigoHabitacionServicio = Integer.parseInt(codigoHabitacionServicio);
						
						String rutaArchivoServicio = "./data/habitaciones.txt";
						
				        empleado.addServiceRoom(rutaArchivoServicio, intCodigoHabitacionServicio, codigoServicio, hotel.getMapaHabitaciones());
				        
				        break;
				}
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
	public void menu_empleado(Scanner scanner) {
		System.out.println("Ingrese a partir de las siguientes opciones la accion que desea realizar\n");
		System.out.println("(1)- Agregar un Producto a una habitacion - (Integer) codigo de la habitacion - (Integer) Codigo Producto");
		System.out.println("(2)- Agregar un Servicio a una habitacion - (Integer) codigo de la habitacion - (Integer) Codigo Servicio");
	}
}
