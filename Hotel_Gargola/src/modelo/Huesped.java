package modelo;

import java.util.Random;

public class Huesped

	{
    private int guestID;
    private String name;
    private int age;
    private String email;
    private int roomID;
    private int bookingID;
    
    public Huesped (String name, int age, String email, int guestID) {
        this.guestID = generateID();
        this.name = name;
        this.age = age;
        this.email = email;
      
    }
    
    public int getAge() {
        return this.age;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public int getGuestID() {
        return this.guestID;
    }
    
    public int getRoomID() {
        return this.roomID;
    }
    
    public int getBookingID() {
        return this.bookingID;
    }
    
    public int generateID() {
    	
	    String chars = "0123456789";
	    StringBuilder sb = new StringBuilder();
	    Random random = new Random();
	    
	    for (int i = 0; i < 3; i++)
	    {
	      sb.append(chars.charAt(random.nextInt(chars.length())));
	    }
	    
	    int retorno = Integer.parseInt(sb.toString());
	   
	    return retorno;
   
    	}
}