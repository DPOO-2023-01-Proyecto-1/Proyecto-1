package modelo;

public class Tarjeta implements IMetodoDePago {
	
    private String numeroTarjeta;
    private String fechaVencimiento;
    private String nombreTarjeta;
    private int codigoSeguridad;
    private String nombrePropietario;
    private double montoTotalCuenta;

    public Tarjeta(String numeroTarjeta, String fechaVencimiento, String nombreTarjeta, int codigoSeguridad, String nombrePropietario, double montoTotalCuenta) {
        this.numeroTarjeta = numeroTarjeta;
        this.fechaVencimiento = fechaVencimiento;
        this.nombreTarjeta = nombreTarjeta;
        this.codigoSeguridad = codigoSeguridad;
        this.nombrePropietario = nombrePropietario;
        this.montoTotalCuenta = montoTotalCuenta;
    }
    
    
    
 // Constructor y otros métodos

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public String getNombreTarjeta() {
        return nombreTarjeta;
    }

    public int getCodigoSeguridad() {
        return codigoSeguridad;
    }

    public String getNombrePropietario() {
        return nombrePropietario;
    }
    

    public double getMontoTotalCuenta() {
        return montoTotalCuenta;
    }
    
    
}