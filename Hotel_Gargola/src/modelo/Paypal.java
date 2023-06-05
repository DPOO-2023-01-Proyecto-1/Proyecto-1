package modelo;

public class Paypal implements IMetodoDePago {
    private String correoElectronico;
    private String contraseña;
    private double montoTotalCuenta;

    public Paypal(String correoElectronico, String contraseña, double montoTotalCuenta) {
        this.correoElectronico = correoElectronico;
        this.contraseña = contraseña;
        this.montoTotalCuenta =  montoTotalCuenta;
        
        
    }
    
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public String getContraseña() {
        return contraseña;
    }
        
    public double getMontoTotalCuenta() {
	    return montoTotalCuenta;
	}
    
}