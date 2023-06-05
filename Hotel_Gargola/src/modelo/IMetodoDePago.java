package modelo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public interface IMetodoDePago {
	
	
	public static int calculateTotalValue(Integer bookingId, String archivoReservas) {
	    int totalValue = 0;

	    try {
	        FileReader frReservas = new FileReader(archivoReservas);
	        BufferedReader brReservas = new BufferedReader(frReservas);
	        String lineaReserva;
	        while ((lineaReserva = brReservas.readLine()) != null) {
	            String[] partesReserva = lineaReserva.split(";");
	            Integer id = Integer.parseInt(partesReserva[0]);
	            if (id.equals(bookingId)) {
	                Integer valorAsociado = Integer.parseInt(partesReserva[5]);
	                totalValue += valorAsociado;
	                break; // Assuming there is only one reservation with the given bookingId
	            }
	        }
	        brReservas.close();
	    } catch (FileNotFoundException e) {
	        System.out.println("No se encontró el archivo " + e.getMessage());
	    } catch (IOException e) {
	        System.out.println("Error al leer el archivo: " + e.getMessage());
	    }

	    return totalValue;
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
	
}
	
