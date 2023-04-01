package modelo;
import java.util.ArrayList;

public class Reserva 
{
	private Integer bookingId;
	private String entryDate;
	private String departureDate;
	public ArrayList<Integer> associatedRooms;
	private ArrayList<String> guestList;
	public Integer associatedValue;
	
	
	public  Reserva(Integer bookingId, String entryDate, String departureDate, ArrayList<Integer> associatedRooms, ArrayList<String> guestList, Integer associatedValue)
	{
		this.bookingId = bookingId;
		this.entryDate = entryDate;
		this.departureDate = departureDate;
		this.associatedRooms = associatedRooms;
		this.guestList = guestList;
		this.associatedValue = associatedValue;
	}
	public String getDate()
	{
		String fechas_reservadas = entryDate+"-"+departureDate;
		
		return fechas_reservadas;
	}
	public Integer getAssociatedValue()
	{
		return associatedValue;
	}
	public ArrayList<String> getGuestList()
	{
		return guestList;
	}
	public ArrayList<Integer> getRoomsList()
	{
		return associatedRooms;
	}
	public Integer getBookingId()
	{
		return bookingId;
	}
	
}
