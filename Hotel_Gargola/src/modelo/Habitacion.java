package modelo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Habitacion 
{
	private Integer roomId;
	public Boolean occupancyStatus; //es public porque el admin puede cambiar eso añañay
	private ArrayList<String> availableServices;
	private ArrayList<String> consumptionRecord;
	private ArrayList<String> guestList;
	private Integer valueByNight;
	private Integer guestCapacity;
	private String roomType;
	private Integer totalValue;
	
	

	public Habitacion(Integer roomId, Boolean occupancyStatus, ArrayList<String> availableServices, ArrayList<String> consumptionRecord, ArrayList<String> guestList, Integer valueByNight, Integer guestCapacity, String roomType, Integer totalValue) 
	{
		this.roomId = roomId;
		this.occupancyStatus = occupancyStatus;
		this.availableServices = availableServices;
		this.consumptionRecord=consumptionRecord;
		this.guestList = guestList;
		this.valueByNight = valueByNight;
		this.guestCapacity = guestCapacity;
		this.roomType = roomType;
		this.totalValue = totalValue;
	}
	public int getId()
	{
		return roomId;
	}
	public Boolean getOccupancyStatus()
	{
		return occupancyStatus;
	}
	public ArrayList<String> getServices()
	{
		return availableServices;
	}
	public ArrayList<String> getConsumptionRecord()
	{
		return consumptionRecord;
	}
	public ArrayList<String> getGuestList()
	{
		return guestList;
	}
	public Integer getValueByNight()
	{
		return valueByNight;
	}
	public Integer getGuestCapacity()
	{
		return guestCapacity;
	}
	public String getRoomType()
	{
		return roomType;
	}
	public Integer getTotalValue()
	{
		return totalValue;
	}
}
