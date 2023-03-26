package modelo;

public class Servicio implements Item
{
	private Integer serviceId;
	private Integer value;
	private String name;
	private String description;
	
	public Servicio(Integer serviceId, Integer value, String name, String description)
	{
		this.serviceId = serviceId;
		this.value = value;
		this.name = name;
		this.description = description; 
	}
	public Integer getId() 
	{
		// TODO Auto-generated method stub
		return serviceId;
	}

	public String getName() 
	{
		// TODO Auto-generated method stub
		return name;
	}

	
	public Integer getValue() 
	{
		// TODO Auto-generated method stub
		return value;
	}
	public String getDescription() // cambio
	{
		return description;
	}

}
