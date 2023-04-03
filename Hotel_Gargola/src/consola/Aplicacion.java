package consola;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import modelo.Administrador;
import modelo.Empleado;
import modelo.Hotel;
import modelo.Huesped;
import modelo.Recepcionista;
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

		
			System.out.print("Ingrese su login: ");
			String login = scanner.nextLine(); 
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
				
				menu_administrador();
				
				System.out.println("");
				
				System.out.print("Seleccione una opción: ");
				
				System.out.println("");

				// Leer la entrada del usuario como una cadena
				String seleccionStr = scanner.nextLine();

				// Convertir la cadena en un número entero, si es posible
				int seleccionInt = Integer.parseInt(seleccionStr);
				
				// Procesar la opción seleccionada
				switch (seleccionInt) 
				{
				    case 1:
				    	Hotel.mostrarProductos();
				    	
				    	System.out.println("");
				    	
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
				    	
				    	System.out.println("");
				    	
				    	System.out.println("Ingrese el codigo del servicio que desea agregar:");
				    	
				    	String codigoServicio = scanner.nextLine();
				    	
				    	System.out.println("Ingrese el codigo de la habitacion sobre la cual desea agregar el servicio:");
						
						String codigoHabitacionServicio = scanner.nextLine();
						
						int intCodigoHabitacionServicio = Integer.parseInt(codigoHabitacionServicio);
						
						String rutaArchivoServicio = "./data/habitaciones.txt";
						
				        admin.addServiceRoom(rutaArchivoServicio, intCodigoHabitacionServicio, codigoServicio, hotel.getMapaHabitaciones());
				        
				        break;
				        
				        
				    case 3:
				    	
				    	Hotel.mostrarProductos();
				    	
				    	System.out.println("");
				    	
				    	System.out.println("Ingrese el codigo del producto que desea eliminar:");
				    	
				    	String codigoProductoEliminar = scanner.nextLine();
				    	
				    	System.out.println("Ingrese el codigo de la habitacion sobre la cual desea eliminar el producto:");
						
						String codigoHabitacionEliminar = scanner.nextLine();
						
						int intCodigoHabitacionEliminar = Integer.parseInt(codigoHabitacionEliminar);
						
						String rutaArchivoEliminar = "./data/habitaciones.txt";
						
				        admin.removeProductRoom(rutaArchivoEliminar, intCodigoHabitacionEliminar, codigoProductoEliminar, hotel.getMapaHabitaciones());
						
				        break;
				        
				    case 4:
				    	
				    	Hotel.mostrarServicios();
				    	
				    	System.out.println("");
				    	
				    	System.out.println("Ingrese el codigo del servicio que desea eliminar:");
				    	
				    	String codigoServicioEliminar = scanner.nextLine();
				    	
				    	System.out.println("Ingrese el codigo de la habitacion sobre la cual desea eliminar el servicio:");
						
						String codigoHabitacionServicioEliminar = scanner.nextLine();
						
						int intCodigoHabitacionServicioEliminar = Integer.parseInt(codigoHabitacionServicioEliminar);
						
						String rutaArchivoServicioEliminar = "./data/habitaciones.txt";
						
				        admin.removeServiceRoom(rutaArchivoServicioEliminar, intCodigoHabitacionServicioEliminar, codigoServicioEliminar, hotel.getMapaHabitaciones());
				        
				        break;
				        
				    case 5:
				    	
				    	Hotel.mostrarServicios();
				    	
				    	System.out.println("");
				    	
				    	System.out.println("Ingrese el nombre del servicio que desea agregar:");
				    	
				    	String nombreServicioAgregar = scanner.nextLine();
				    	
				    	System.out.println("Ingrese el codigo del servicio que desea agregar:");
				    	
				    	String codigoServicioAgregar = scanner.nextLine();
				    	
				    	int intCodigoServicioAgregar = Integer.parseInt(codigoServicioAgregar);
				    	
				    	System.out.println("Ingrese el valor del servicio que desea agregar:");
				    	
				    	String valorServicioAgregar = scanner.nextLine();
				    	
				    	int intValorServicioAgregar = Integer.parseInt(valorServicioAgregar);
				    	
				    	String rutaServicioAgregar = "./data/servicios.txt";
				    	
				    	System.out.println("Ingrese la descripcion para el servicio:");
				    	
				    	String descripcionServicioAgregar = scanner.nextLine();
				    	
				    	admin.addServiceCatalog(hotel.getMapaServicios(), intCodigoServicioAgregar, nombreServicioAgregar, intValorServicioAgregar, rutaServicioAgregar,descripcionServicioAgregar);
				    	
				    	break;
				    	
				    
				    case 6:
				    	
				    	
				    	Hotel.mostrarProductos();
				    	
				    	System.out.println("");
				    	
				    	System.out.println("Ingrese el nombre del producto que desea agregar:");
				    	
				    	String nombreProductoAgregar = scanner.nextLine();
				    	
				    	System.out.println("Ingrese el codigo del producto que desea agregar:");
				    	
				    	String codigoProductoAgregar = scanner.nextLine();
				    	
				    	int intCodigoProductoAgregar = Integer.parseInt(codigoProductoAgregar);
				    	
				    	System.out.println("Ingrese el valor del producto que desea agregar:");
				    	
				    	String valorProductoAgregar = scanner.nextLine();
				    	
				    	int intValorProductoAgregar = Integer.parseInt(valorProductoAgregar);
				    	
				    	String rutaProductoAgregar = "./data/productos.txt";
				    	
				    	menu_restricciones();
				    	
				    	System.out.println("Ingrese la opcion que desea para las restricciones");
				    	
				    	String respuestaRestricciones = scanner.nextLine();
				    	
				    	if (respuestaRestricciones.equals("1")) {
				    		
				    		String restricciones = "Sin restriccion";
				    		
				    		admin.addProductCatalog(hotel.getMapaProductos(),intCodigoProductoAgregar, nombreProductoAgregar, intValorProductoAgregar, rutaProductoAgregar , restricciones);
				    		
				    	}
				    	
				    	else if (respuestaRestricciones.equals("2")) {
				    		
				    		String restricciones = "Habitacion";
				    		
				    		admin.addProductCatalog(hotel.getMapaProductos(),intCodigoProductoAgregar, nombreProductoAgregar, intValorProductoAgregar, rutaProductoAgregar , restricciones);
				    		
				    		
				    	}
				    	
				    	else if (respuestaRestricciones.equals("3")) {
				    		
				    		String restricciones = "Restaurante";
				    		
				    		admin.addProductCatalog(hotel.getMapaProductos(),intCodigoProductoAgregar, nombreProductoAgregar, intValorProductoAgregar, rutaProductoAgregar , restricciones);
				    		
				    	}
				    	
				    	else {
				    		
				    		System.out.print("Opcion invalida para las restricciones");
				    	}
				    	
				    	break;
				    	
				    	
				    case 7:
				    	
				    	Hotel.mostrarServicios();
				    	
				    	System.out.println("");
				    	
				    	System.out.println("Ingrese e codigo del servicio que desea eliminar del catalogo de servicios:");
				    	
				    	String codigoServicioEliminarCatalogo = scanner.nextLine();
				    	
				    	int intCodigoServicioEliminarCatalogo = Integer.parseInt(codigoServicioEliminarCatalogo);
				    	
				    	String rutaServicioEliminarCatalogo = "./data/servicios.txt";
				    	
				    	admin.deleteServiceCatalog(hotel.getMapaServicios(), intCodigoServicioEliminarCatalogo, rutaServicioEliminarCatalogo);
				    	
				    	break;
				    	
				    case 8:
				    	
				    	Hotel.mostrarProductos();
				    	
				    	System.out.println("");
				    	
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
				
				menu_empleado();
				
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
				
				Recepcionista recep = (Recepcionista) usuario;
				
				System.out.println("Bienvenido recepcionista");
				
				menu_recepcionista();
				
				System.out.println("");
				
				System.out.print("Seleccione una opción: ");
				
				String archivoRutaReservas = "./data/reservas.txt";
				
				String opcionRecepcionista = scanner.nextLine();
				
				int intOpcionRecepcionista = Integer.parseInt(opcionRecepcionista);
				
				switch(intOpcionRecepcionista) 
				{
					case 1:
						
						int bookingId = recep.generateID();
						
						System.out.println("Ingrese el numero de personas que se van a hospedar:");
						
						String numeroPersonasReserva = scanner.nextLine();
						
						int intNumeroPersonasReserva = Integer.parseInt(numeroPersonasReserva);
					    
						int i = 0;
						
						ArrayList<String> huespedes = new ArrayList<>();
						
					    while(i < intNumeroPersonasReserva) {
					    	
					    	
					      System.out.println("Ingrese el nombre del huesped:");
					      
					      String nombreHuesped = scanner.nextLine();
					      
					      System.out.println("Ingrese la edad del huesped:");
					      
					      String edadHuesped = scanner.nextLine();
					      
					      int intEdadHuesped = Integer.parseInt(edadHuesped);
					      
					      System.out.println("Ingrese el correo electronico del huesped");
					      
					      String correoHuesped = scanner.nextLine();
					      
					      int codigoHuesped = recep.generateID();
					      
					      String archivoRutaHuespedes = "./data/huespedes.txt";
					      
					      Huesped huesped = recep.crearHuesped(nombreHuesped, intEdadHuesped, correoHuesped, archivoRutaHuespedes ,codigoHuesped );
					      
					      String stringGuestId = String.valueOf(huesped.getGuestID());
					      
					      huespedes.add(stringGuestId);
					      
					      i++;
					    }
					    
					    System.out.println("Ingrese la fecha de comienzo de la reserva DD-MM-AAAA");
					    
					    String entryDate = scanner.nextLine();
					    
					    System.out.println("Ingrese la fecha de salida de la reserva DD-MM-AAAA");
					    
					    String departureDate = scanner.nextLine();
					    
					    int associatedValue = 0;
					    
					    recep.imprimirHabitacionesDisponiblesPorCapacidad(intNumeroPersonasReserva, hotel.getMapaHabitaciones());
					    
					    System.out.println("Ingrese los codigos de las habitaciones que desea asociar, separandolas por comas en caso de que haya mas de una:");
					    
					    String habitacionesAsociadasDisponibles = scanner.nextLine();
					    
					    String[] listaHabitacionesSeleccionadas = habitacionesAsociadasDisponibles.split(",");
					    
					    ArrayList<Integer> habitacionesAsociadas = recep.convertirListaStringAInt(listaHabitacionesSeleccionadas);
					    
						recep.checkIn(bookingId, entryDate, departureDate, habitacionesAsociadas, huespedes, associatedValue, archivoRutaReservas);
						
						break;
						
						
					case 2:
						
						
						
				}

				
				
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
	
	public void menu_administrador() {
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
	
	public void menu_empleado() {
		System.out.println("Ingrese a partir de las siguientes opciones la accion que desea realizar\n");
		System.out.println("(1)- Agregar un Producto a una habitacion - (Integer) codigo de la habitacion - (Integer) Codigo Producto");
		System.out.println("(2)- Agregar un Servicio a una habitacion - (Integer) codigo de la habitacion - (Integer) Codigo Servicio");
	}
	
	public void menu_restricciones() {
		
		System.out.println("(1)- Sin restricciones");
		System.out.println("(2)- Restriccion Habitacion");
		System.out.println("(3)- Restriccion Restaurante");
		
	}
	
	public void menu_recepcionista() {
		
		System.out.println("Ingrese a partir de las siguientes opciones la accion que desea realizar\n");
		System.out.println("(1)- Crear una reserva (Check-In)");
		System.out.println("(2)- Cerrar una reserva (Check-Out)");
		System.out.println("(3)- Modificar una reserva");
		System.out.println("(4)- Obtener la lista de reservas activas");
		
		
		
	}

}
