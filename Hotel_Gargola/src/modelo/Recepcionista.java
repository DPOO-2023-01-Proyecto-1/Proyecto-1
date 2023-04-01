package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

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
                pw.println(booking.getBookingId() + "," + booking.getDate() + "," + String.join("-", booking.getRoomsList().toString().replace("[", "").replace("]", "").split(", ")) + "," + String.join("-", booking.getGuestList().toString().replace("[", "").replace("]", "").split(", ")) + "," + booking.getAssociatedValue());
            }
            pw.close();
            System.out.println("Reserva modificada con éxito en el archivo " + Archivo);
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo " + Archivo);
            e.printStackTrace();
        }
    }
	
	public void checkIn(Integer bookingId, String entryDate, String departureDate, ArrayList<Integer> associatedRooms, ArrayList<String> guestList, Integer associatedValue, String Archivo) {
        // Crear una nueva reserva
        Reserva nuevaReserva = new Reserva(bookingId, entryDate, departureDate, associatedRooms, guestList, associatedValue);
        
        // Guardar la reserva en un archivo de texto
        try {
            FileWriter fw = new FileWriter(Archivo, true); // true para que se agregue al final del archivo
            PrintWriter pw = new PrintWriter(fw);
            pw.println(nuevaReserva.getBookingId() + "," + nuevaReserva.getDate() + "," + nuevaReserva.getRoomsList() + "," + nuevaReserva.getGuestList() + "," + nuevaReserva.getAssociatedValue());
            pw.close();
            System.out.println("Reserva guardada con éxito en el archivo "+ Archivo);
        } catch (IOException e) {
            System.out.println("Error al guardar la reserva en el archivo "+ Archivo);
            e.printStackTrace();
        }
    }
	
	public void generateBill() {
		
	}
	
	public void checkOut(Integer bookingId, String Archivo) {
	        // Leer el archivo de texto y guardar las reservas que no se van a eliminar en una lista temporal
	        ArrayList<Reserva> reservasTemporales = new ArrayList<>();
	        try {
	            File archivoReservas = new File(Archivo);
	            Scanner scanner = new Scanner(archivoReservas);
	            while (scanner.hasNextLine()) {
	                String linea = scanner.nextLine();
	                String[] partes = linea.split(",");
	                Integer id = Integer.parseInt(partes[0]);
	                if (!id.equals(bookingId)) {
	                    String[] fechas = partes[1].split("-");
	                    ArrayList<Integer> habitaciones = new ArrayList<>();
	                    String[] habitacionesArray = partes[2].replace("[", "").replace("]", "").split(",");
	                    for (String h : habitacionesArray) {
	                        habitaciones.add(Integer.parseInt(h.trim()));
	                    }
	                    ArrayList<String> invitados = new ArrayList<>();
	                    String[] invitadosArray = partes[3].replace("[", "").replace("]", "").split(",");
	                    for (String i : invitadosArray) {
	                        invitados.add(i.trim());
	                    }
	                    Integer valorAsociado = Integer.parseInt(partes[4]);
	                    Reserva reserva = new Reserva(id, fechas[0], fechas[1], habitaciones, invitados, valorAsociado);
	                    reservasTemporales.add(reserva);
	                }
	            }
	            scanner.close();
	        } catch (IOException e) {
	            System.out.println("Error al leer el archivo de reservas");
	            e.printStackTrace();
	            return;
	        }
	        
	        // Sobrescribir el archivo de texto con las reservas que quedaron en la lista temporal
	        try {
	            FileWriter fw = new FileWriter(Archivo, false); // false para sobrescribir el archivo
	            PrintWriter pw = new PrintWriter(fw);
	            for (Reserva reserva : reservasTemporales) {
	                pw.println(reserva.getBookingId() + "," + reserva.getDate() + "," + reserva.getRoomsList() + "," + reserva.getGuestList() + "," + reserva.getAssociatedValue());
	            }
	            pw.close();
	            System.out.println("Reserva eliminada con éxito del archivo reservas.txt");
	        } 
	        catch (IOException e) {
	            System.out.println("Error al eliminar la reserva del archivo reservas.txt");
	            e.printStackTrace();
	        }
	    }
	public ArrayList<Reserva> getBookingsList() {
        ArrayList<Reserva> bookingsList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("reservas.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] bookingData = line.split(",");
                Integer bookingId = Integer.parseInt(bookingData[0]);
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
                Reserva booking = new Reserva(bookingId, entryDate, departureDate, associatedRooms, guestList, associatedValue);
                bookingsList.add(booking);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo reservas.txt");
            e.printStackTrace();
        }

        return bookingsList;
    }

}
	