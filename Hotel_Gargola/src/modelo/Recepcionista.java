	package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JTextArea;

import java.util.Random;

public class Recepcionista extends Usuario {

	public Recepcionista(String login, String password, String userType) {
		super(login, password, userType);
	
	}
	
	public void modifyBooking(String Archivo, Integer bookingId, Reserva newBooking) {
        ArrayList<Reserva> bookingsList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(Archivo))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] bookingData = line.split(",");
                Integer currentBookingId = Integer.parseInt(bookingData[0]);
                if (currentBookingId.equals(bookingId)) {
                    bookingsList.add(newBooking);
                } else {
                    String entryDate = bookingData[1];
                    String departureDate = bookingData[2];
                    ArrayList<Integer> associatedRooms = new ArrayList<>();
                    for (String roomId : bookingData[3].split("-")) {
                        associatedRooms.add(Integer.parseInt(roomId));
                    }
                    ArrayList<String> guestList = new ArrayList<>();
                    for (String guestName : bookingData[4].split("-")) {
                        guestList.add(guestName);
                    }
                    Integer associatedValue = Integer.parseInt(bookingData[5]);
                    Reserva booking = new Reserva(currentBookingId, entryDate, departureDate, associatedRooms, guestList, associatedValue);
                    bookingsList.add(booking);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo " + Archivo);
            e.printStackTrace();
            return;
        }

        try (PrintWriter pw = new PrintWriter(new FileWriter(Archivo))) {
            for (Reserva booking : bookingsList) {
                pw.println(booking.getBookingId() + ";" + booking.getEntryDate() + ";" + booking.getDepartureDate() + ";" + booking.getRoomsList() + ";" + booking.getGuestList() + ";" + booking.getAssociatedValue());
            pw.close();
            System.out.println("Reserva modificada con éxito en el archivo " + Archivo);
            } } catch (IOException e) {
            System.out.println("Error al escribir en el archivo " + Archivo);
            e.printStackTrace();
        }
    }
	
	public static void checkIn(Integer bookingId, String entryDate, String departureDate, ArrayList<Integer> associatedRooms, ArrayList<String> guestList, Integer associatedValue, String Archivo) {
		// Verificar si las habitaciones ya están reservadas para las fechas de entrada y salida
		ArrayList<Reserva> reservas = getBookingsList(Archivo);
	    for (Reserva r : reservas) {
	        if ((entryDate.equals(r.getEntryDate()) || departureDate.equals(r.getDepartureDate())) || 
	            r.associatedRooms.stream().anyMatch(associatedRooms::contains)) {
	            System.out.println("Las habitaciones ya están reservadas para las fechas de entrada y salida especificadas");
	            return;
	        }
	    }
	    

		
		// Crear una nueva reserva
        Reserva nuevaReserva = new Reserva(bookingId, entryDate, departureDate, associatedRooms, guestList, associatedValue);
        
        // Guardar la reserva en un archivo de texto
        try {
            FileWriter fw = new FileWriter(Archivo, true); // true para que se agregue al final del archivo
            PrintWriter pw = new PrintWriter(fw);
            pw.println(nuevaReserva.getBookingId() + ";" + nuevaReserva.getEntryDate() + ";" + nuevaReserva.getDepartureDate() + ";" + nuevaReserva.getRoomsList() + ";" + nuevaReserva.getGuestList() + ";" + nuevaReserva.getAssociatedValue());
            pw.close();
            System.out.println("Reserva guardada con éxito en el archivo "+ Archivo);
        } catch (IOException e) {
            System.out.println("Error al guardar la reserva en el archivo "+ Archivo);
            e.printStackTrace();
        }
    }
	
	public static void generateBill(String archivoReservas, String bookingId, JTextArea textArea) {
	    try {
	        FileReader frReservas = new FileReader(archivoReservas);
	        BufferedReader brReservas = new BufferedReader(frReservas);
	        String lineaReserva;
	        while ((lineaReserva = brReservas.readLine()) != null) {
	            String[] partesReserva = lineaReserva.split(";");
	            String id = partesReserva[0];
	            if (id.equals(bookingId)) {
	                String[] fechas = partesReserva[1].split(";");
	                String[] fechas2 = partesReserva[2].split(";");
	                ArrayList<Integer> habitaciones = new ArrayList<>();
	                String[] habitacionesArray = partesReserva[3].replace("[", "").replace("]", "").split(",");
	                for (String h : habitacionesArray) {
	                    habitaciones.add(Integer.parseInt(h.trim()));
	                }
	                ArrayList<String> invitados = new ArrayList<>();
	                String[] invitadosArray = partesReserva[4].replace("[", "").replace("]", "").split(",");
	                for (String i : invitadosArray) {
	                    invitados.add(i.trim());
	                }
	                Integer valorAsociado = Integer.parseInt(partesReserva[5]);

	                System.out.println("Información de la reserva:");
	                System.out.println("ID de reserva: " + id);
	                System.out.println("Fechas de entrada: " + fechas[0]);
	                System.out.println("Fechas de salida: " + fechas2[0]);
	                System.out.println("Habitaciones asociadas: " + habitaciones.toString());
	                System.out.println("Huéspedes: " + invitados.toString());
	                System.out.println("Valor asociado: " + valorAsociado);
	         

	                // Calcula y muestra el valor total de la reserva en función de las habitaciones y los servicios adicionales
	                int totalValue = valorAsociado; // Inicialmente se considera solo el valor asociado
	                // Aquí puedes agregar la lógica para calcular el valor total de la reserva
	                // Puedes tener en cuenta los precios de las habitaciones y los servicios adicionales
	                // y sumarlos al valor asociado
	                textArea.append("Información de la reserva:\n");
	                textArea.append("ID de reserva: " + id + "\n");
	                textArea.append("Fechas de entrada: " + fechas[0] + "\n");
	                textArea.append("Fechas de salida: " + fechas2[0] + "\n");
	                textArea.append("Habitaciones asociadas: " + habitaciones.toString() + "\n");
	                textArea.append("Huéspedes: " + invitados.toString() + "\n");
	                textArea.append("Valor asociado: " + valorAsociado + "\n");
	                textArea.append("Valor total: " + totalValue + "\n");

	                System.out.println("Valor total: " + totalValue);
	                break; // Termina el bucle después de encontrar la reserva con el ID correspondiente
	            }
	        }
	        brReservas.close();
	    } catch (FileNotFoundException e) {
	        System.out.println("No se encontró el archivo " + archivoReservas);
	    } catch (IOException e) {
	        System.out.println("Error al leer el archivo " + archivoReservas);
	        e.printStackTrace();
	    }
	}

	
	public static void checkOut(Integer bookingId, String archivoReservas, String huespedesArchivo) {
	    try {
	        // Leer el archivo de reservas y guardar las reservas que no se van a eliminar en una lista temporal
	        ArrayList<Reserva> reservasTemporales = new ArrayList<>();
	        FileReader frReservas = new FileReader(archivoReservas);
	        BufferedReader brReservas = new BufferedReader(frReservas);
	        String lineaReserva;
	        while ((lineaReserva = brReservas.readLine()) != null) {
	            String[] partesReserva = lineaReserva.split(";");
	            Integer id = Integer.parseInt(partesReserva[0]);
	            if (!id.equals(bookingId)) {
	                String[] fechas = partesReserva[1].split(";");
	                String[] fechas2 = partesReserva[2].split(";");
	                ArrayList<Integer> habitaciones = new ArrayList<>();
	                String[] habitacionesArray = partesReserva[3].replace("[", "").replace("]", "").split(",");
	                for (String h : habitacionesArray) {
	                    habitaciones.add(Integer.parseInt(h.trim()));
	                }
	                ArrayList<String> invitados = new ArrayList<>();
	                String[] invitadosArray = partesReserva[4].replace("[", "").replace("]", "").split(",");
	                for (String i : invitadosArray) {
	                    invitados.add(i.trim());
	                }
	                Integer valorAsociado = Integer.parseInt(partesReserva[5]);

	                Reserva reserva = new Reserva(id, fechas[0], fechas2[0], habitaciones, invitados, valorAsociado);

	                reservasTemporales.add(reserva);
	            } else {
	                // Eliminar los huéspedes asociados a la reserva del archivo de huéspedes
	                FileReader frHuespedes = new FileReader(huespedesArchivo);
	                BufferedReader brHuespedes = new BufferedReader(frHuespedes);
	                String lineaHuesped;
	                ArrayList<String> lineasHuespedes = new ArrayList<>();
	                while ((lineaHuesped = brHuespedes.readLine()) != null) {
	                    String[] partesHuesped = lineaHuesped.split(";");
	                    Integer bookingIdHuesped = Integer.parseInt(partesHuesped[0]);
	                    if (!partesReserva[4].equals(bookingIdHuesped)) {
	                        lineasHuespedes.add(lineaHuesped);
	                    } else {
	                    	
	                        // Si el huésped está asociado a la reserva que se va a eliminar, liberar la habitación
	                        
	                    }
	                }
	                brHuespedes.close();

	                // Sobrescribir el archivo de texto con los huéspedes que no se van a eliminar
	                FileWriter fwHuespedes = new FileWriter(huespedesArchivo, false); // false para sobrescribir el archivo
	                PrintWriter pwHuespedes = new PrintWriter(fwHuespedes);
	                for (String linea : lineasHuespedes) {
	                    pwHuespedes.println(linea);
	                }
	                pwHuespedes.close();
	                System.out.println("Huéspedes eliminados con éxito del archivo " + huespedesArchivo);
	            }
	        }
	        brReservas.close();

	     // Sobrescribir el archivo de texto con las reservas que no se van a eliminar
	        FileWriter fwReservas = new FileWriter(archivoReservas, false); // false para sobrescribir el archivo
	        PrintWriter pwReservas = new PrintWriter(fwReservas);
	        for (Reserva reserva : reservasTemporales) {
	            pwReservas.println(reserva.getBookingId()+ ";" +
	                               reserva.getEntryDate()+ ";" +
	                               reserva.getDepartureDate()+ ";" +
	                               reserva.getRoomsList().toString() + ";" +
	                               reserva.getGuestList().toString() + ";" +
	                               reserva.getAssociatedValue());
	        }

	        pwReservas.close();
	        System.out.println("Reservas actualizadas con éxito en el archivo " + archivoReservas);


	    } catch (FileNotFoundException e) {
	        System.out.println("No se encontró el archivo " + e.getMessage());
	    } catch (IOException e) {
	        System.out.println("Error al leer/escribir el archivo: " + e.getMessage());
	    }
	    }

	public static ArrayList<Reserva> getBookingsList(String archivo) {
        ArrayList<Reserva> bookingsList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] bookingData = line.split(";");
                Integer bookingId = Integer.parseInt(bookingData[0]);
                String entryDate = bookingData[1];
                String departureDate = bookingData[2];
                ArrayList<Integer> associatedRooms = new ArrayList<>();
                String idsTemp=bookingData[3];
                idsTemp=idsTemp.replace("[", "");
                idsTemp=idsTemp.replace("]", "");
                idsTemp=idsTemp.replace(" ", "");
                for (String roomId : idsTemp.split(",")) {
                    associatedRooms.add(Integer.parseInt(roomId));
                }
                ArrayList<String> guestList = new ArrayList<>();
                String guesTemp=bookingData[4];
                guesTemp=guesTemp.replace("[", "");
                guesTemp=guesTemp.replace("]", "");
                guesTemp=guesTemp.replace(" ", "");
                for (String guestName : guesTemp.split("-")) {
                    guestList.add(guestName);
                }
                Integer associatedValue = Integer.parseInt(bookingData[5]);
                Reserva booking = new Reserva(bookingId, entryDate, departureDate, associatedRooms, guestList, associatedValue);
                bookingsList.add(booking);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo reservas.txt");
            e.printStackTrace();
        }

        return bookingsList;
    }
	
	public static Huesped crearHuesped(String name, int age, String email, String Archivo, int guestID) {
	    // Crear un nuevo huésped
	    Huesped nuevoHuesped = new Huesped(name, age, email, guestID);
	    
	    try {
	        FileWriter fw = new FileWriter(Archivo, true); // true para que se agregue al final del archivo
	        PrintWriter pw = new PrintWriter(fw);
	        pw.println(nuevoHuesped.getGuestID() + ";" + nuevoHuesped.getName() + ";" + nuevoHuesped.getAge() + ";" + nuevoHuesped.getEmail() + ";" + nuevoHuesped.getRoomID() + ";" + nuevoHuesped.getBookingID());
	        pw.close();
	        System.out.println("Huésped guardado con éxito en el archivo "+ Archivo);
	    } catch (IOException e) {
	        System.out.println("Error al guardar el huésped en el archivo "+ Archivo );
	        e.printStackTrace();
	    }
	    
		return nuevoHuesped;
	  
	}

	
	public static int generateID() {
	    String chars = "0123456789";
	    StringBuilder sb = new StringBuilder();
	    Random random = new Random();
	    for (int i = 0; i < 5; i++) 
	    {
	      sb.append(chars.charAt(random.nextInt(chars.length())));
	    }
	    
	    int retorno = Integer.parseInt(sb.toString());
	    
	    return retorno;
	
	}
	
	
	public void imprimirHabitacionesDisponiblesPorCapacidad(int capacidad, Map<Integer, Habitacion> habitaciones) {
	    for (Map.Entry<Integer, Habitacion> habitacionEntry : habitaciones.entrySet()) {
	        Habitacion habitacion = habitacionEntry.getValue();
	        if (habitacion.getOccupancyStatus() && habitacion.getGuestCapacity() >= capacidad) {
	            System.out.println("Habitación #" + habitacion.getId() + ":");
	            System.out.println("\tEstado de ocupación: Disponible");
	            System.out.println("\tTipo de habitación: " + habitacion.getRoomType());
	            System.out.println("\tCapacidad de huéspedes: " + habitacion.getGuestCapacity());
	            System.out.println("\tServicios disponibles: " + habitacion.getServices());
	            System.out.println("\tRegistro de consumos: " + habitacion.getConsumptionRecord());
	            System.out.println("\tLista de huéspedes: " + habitacion.getGuestList());
	            System.out.println("\tValor por noche: " + habitacion.getValueByNight());
	            System.out.println("\tValor total: " + habitacion.getTotalValue());
	            System.out.println("-------------------------------------------------");
	        }
	    }
	}
	
	public ArrayList<Integer> convertirListaStringAInt(String[] listaString) {
	    ArrayList<Integer> listaInt = new ArrayList<Integer>();
	    for (String str : listaString) {
	        listaInt.add(Integer.parseInt(str));
	    }
	    return listaInt;
	}
	
}
