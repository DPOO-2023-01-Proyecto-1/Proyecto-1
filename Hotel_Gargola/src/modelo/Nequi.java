package modelo;

public class Nequi implements IMetodoDePago{
	
	
	    private String numeroTelefono;
	    private double montoTotalCuenta;

	    public Nequi(String numeroTelefono, double montoTotalCuenta) {
	        this.numeroTelefono = numeroTelefono;
	        this.montoTotalCuenta = montoTotalCuenta;
	    }
	    
	    
	    public String getNumeroTelefono() {
	        return numeroTelefono;
	    }

	    public double getMontoTotalCuenta() {
	        return montoTotalCuenta;
	    }
	    
	    

	    
	    
	    


	

}
