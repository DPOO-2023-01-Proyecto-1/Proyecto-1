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
	public Boolean occupancyStatus; 
	private ArrayList<String> availableServices;
	public ArrayList<String> consumptionRecord;
	private ArrayList<String> guestList;
	private Integer valueByNight;
	private Integer guestCapacity;
	private String roomType;
	private Integer totalValue;
	//-----------ACTUALIZACION PROYECTO 3-----------//
	private Integer roomSize; //ahí se guarda el tamaño de la habitación en metros cuadrados
	private Boolean tieneAireAcondicionado;
	private Boolean tieneCalefaccion;
	private String  bedSize; //dice si la cama es grande, pequeña o mediana
	private Boolean tieneTv;
	private Boolean tieneCafetera;
	private Boolean tieneRopaCama;
	private Boolean tienePlancha;
	private Boolean tieneSecador;
	private Boolean tieneVoltaje;
	private Boolean tieneTipoA;
	private Boolean tieneTipoC;
	private Boolean tieneDesayuno;
	
	

	public Habitacion(Integer roomId, Boolean occupancyStatus, ArrayList<String> availableServices, ArrayList<String> consumptionRecord, ArrayList<String> guestList, Integer valueByNight, Integer guestCapacity, String roomType, Integer totalValue,
			Integer roomSize, Boolean aireAcondicionado, Boolean calefaccion, String bedSize, Boolean tieneTv, Boolean tieneCafetera, Boolean tieneRopaCama, Boolean tienePlancha,
			Boolean tieneSecador, Boolean tieneVoltaje, Boolean tieneTipoA, Boolean tieneTipoC, Boolean tieneDesayuno) 
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
		//-----------ACTUALIZACION PROYECTO 3-----------//
		this.roomSize = roomSize;
		this.tieneAireAcondicionado = aireAcondicionado;
		this.tieneCalefaccion = calefaccion;
		this.bedSize = bedSize;
		this.tieneTv = tieneTv;
		this.tieneCafetera = tieneCafetera;
		this.tieneRopaCama = tieneRopaCama;
		this.tienePlancha = tienePlancha;
		this.tieneSecador = tieneSecador;
		this.tieneVoltaje = tieneVoltaje;
		this.tieneTipoA = tieneTipoA;
		this.tieneTipoC = tieneTipoC;
		this.tieneDesayuno = tieneDesayuno;
		
		
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
	
	//Método auxiliar para verificar si un producto está en el registro de consumo de una habitación
	
}