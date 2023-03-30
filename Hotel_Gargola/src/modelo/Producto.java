package modelo;

public class Producto implements Item
{
	private Integer productId;
	private Integer value;
	private String name;
	private String locationRestrictions;
	
	public Producto(Integer productId,  Integer value, String name, String locationRestrictions)
	{
		this.productId = productId;
		this.value = value;
		this.name = name;
		this.locationRestrictions = locationRestrictions; 
	}
	
	
	public String getRestrictions() {
		return locationRestrictions;
	}


	public Integer getId() {
		// TODO Auto-generated method stub
		return productId;
	}


	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}



	public Integer getValue() {
		// TODO Auto-generated method stub
		return value;
	}

}
