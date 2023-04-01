package consola;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import modelo.Administrador;
import modelo.Recepcionista;
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

				menu_administrador();

				System.out.println("");

				System.out.print("Seleccione una opción: ");

				// Leer la entrada del usuario como una cadena
				String seleccionStr = scanner.nextLine();

				// Convertir la cadena en un número entero, si es posible
				int seleccionInt = Integer.parseInt(seleccionStr);

				// Procesar la opción seleccionada
				switch (seleccionInt) {
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

					admin.addServiceRoom(rutaArchivoServicio, intCodigoHabitacionServicio, codigoServicio,
							hotel.getMapaHabitaciones());

					break;

				case 3:

					System.out.println("Ingrese el codigo del producto que desea eliminar:");

					String codigoProductoEliminar = scanner.nextLine();

					System.out.println("Ingrese el codigo de la habitacion sobre la cual desea eliminar el producto:");

					String codigoHabitacionEliminar = scanner.nextLine();

					int intCodigoHabitacionEliminar = Integer.parseInt(codigoHabitacionEliminar);

					String rutaArchivoEliminar = "./data/habitaciones.txt";

					admin.removeProductRoom(rutaArchivoEliminar, intCodigoHabitacionEliminar, codigoProductoEliminar,
							hotel.getMapaHabitaciones());

					break;

				case 4:

					System.out.println("Ingrese el codigo del servicio que desea eliminar:");

					String codigoServicioEliminar = scanner.nextLine();

					System.out.println("Ingrese el codigo de la habitacion sobre la cual desea eliminar el servicio:");

					String codigoHabitacionServicioEliminar = scanner.nextLine();

					int intCodigoHabitacionServicioEliminar = Integer.parseInt(codigoHabitacionServicioEliminar);

					String rutaArchivoServicioEliminar = "./data/habitaciones.txt";

					admin.removeServiceRoom(rutaArchivoServicioEliminar, intCodigoHabitacionServicioEliminar,
							codigoServicioEliminar, hotel.getMapaHabitaciones());

					break;

				case 5:

					System.out.println("Ingrese el nombre del servicio que desea agregar:");

				case 6:

				case 7:

					System.out.println("Ingrese e codigo del servicio que desea eliminar del catalogo de servicios:");

					String codigoServicioEliminarCatalogo = scanner.nextLine();

					int intCodigoServicioEliminarCatalogo = Integer.parseInt(codigoServicioEliminarCatalogo);

					String rutaServicioEliminarCatalogo = "./data/servicios.txt";

					admin.deleteServiceCatalog(hotel.getMapaServicios(), intCodigoServicioEliminarCatalogo,
							rutaServicioEliminarCatalogo);

					break;

				case 8:

					System.out.println("Ingrese el codigo del producto que desea eliminar del catalogo de productos:");

					String codigoProductoEliminarCatalogo = scanner.nextLine();

					int intCodigoProductoEliminarCatalogo = Integer.parseInt(codigoProductoEliminarCatalogo);

					String rutaProductoEliminarCatalogo = "./data/servicios.txt";

					admin.deleteProductCatalog(hotel.getMapaProductos(), intCodigoProductoEliminarCatalogo,
							rutaProductoEliminarCatalogo);

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
				System.out.println("Bienvenido empleado");
				
				break;
				
				
				
			case "recepcionista":
				
				System.out.println("Bienvenido recepcionista");
				
				Recepcionista recep = (Recepcionista) usuario;
				
				menu_recepcionista();

				System.out.println("");

				System.out.print("Seleccione una opción: ");
				
				String seleccionStrRecep = scanner.nextLine();

				int seleccionIntRecep = Integer.parseInt(seleccionStrRecep);
				
				switch(seleccionIntRecep) {
				
				case 1:
					
					System.out.println("Ingrese la fecha de llegada sobre la cual desea hacer el check-in");
					
					String fechaLlegada = scanner.nextLine();
					
					System.out.println("Ingrese la fecha de salida sobre la cual desea hacer el check-in");
					
					String fechaSalida = scanner.nextLine();
					
					System.out.println("Ingrese el numero de huespuedes que desea alojar");
					
					String numeroPersonas = scanner.nextLine();
					
					int intNumeroPersonas = Integer.parseInt(numeroPersonas);
					
					menu_recepcionista_habitaciones();
					
					System.out.println("Ingrese el tipo de habitacion que desea escoger");
					
					String ingresoTipoHabitacion = scanner.nextLine();
					
					int intIngresoTipoHabitacion = Integer.parseInt(ingresoTipoHabitacion);
					
					if (intIngresoTipoHabitacion == 1) {
						
						
						
					}
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
		System.out.println(
				"(1)- Agregar un Producto a una habitacion - (Integer) codigo de la habitacion - (String) Codigo Producto");
		System.out.println(
				"(2)- Agregar un Servicio a una habitacion - (Integer) codigo de la habitacion - (String) Codigo Servicio");
		System.out.println(
				"(3)- Eliminar un Producto a una habitacion - (Integer) codigo de la habitacion - (String) Codigo Producto");
		System.out.println(
				"(4)- Eliminar un Servicio a una habitacion - (Integer) codigo de la habitacion - (String) Codigo Servicio");
		System.out.println("(5)- Agregar un Servicio al catalogo de servicios");
		System.out.println("(6)- Agregar un Producto al catalogo de productos");
		System.out.println("(7)- Eliminar un Servicio al catalogo de servicios - (Integer) codigo del servicio");
		System.out.println("(8)- Eliminar un Producto al catalogo de productos - (Integer) codigo del producto");
		System.out.println("(9)- Obtener la lista de reservas actuales");
		System.out.println("(10)- Obtener la lista de usuarios actuales");
	}
	
	public void menu_recepcionista() {
		
		System.out.println("Ingrese a partir de las siguientes opciones cual es la que desea realizar:\n");
		System.out.println("(1)- Realizar un check-in");
		System.out.println("(2)- Realizar un check-out");
		System.out.println("(3)- Modificar una reserva");
		System.out.println("(4)- Mostrar lista de reservas activas");
	}
	
	public void menu_recepcionista_habitaciones() {
		
		System.out.println("(1)- Si desea escoger una habitacion de tipo sencilla");
		System.out.println("(2)- Si desea escoger una habitacion de tipo doble");
		System.out.println("(3)- Si desea escoger una habitacion de tipo triple");
		System.out.println("(4)- Si desea escoger una habitacion de tipo suite");
		
	}
	
	
}
