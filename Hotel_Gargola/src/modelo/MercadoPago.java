package modelo;

public class MercadoPago implements IMetodoDePago {
    private String numeroTelefono;
    private String correoAsociado;
    private String contraseña;
    private double montoTotalCuenta;

    public MercadoPago(String numeroTelefono, String correoAsociado, String contraseña, double montoTotalCuenta) {
        this.numeroTelefono = numeroTelefono;
        this.correoAsociado = correoAsociado;
        this.contraseña = contraseña;
        this.montoTotalCuenta = montoTotalCuenta;
    }
    
    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public String getCorreoAsociado() {
        return correoAsociado;
    }

    public String getContraseña() {
        return contraseña;
    }

    public double getMontoTotalCuenta() {
        return montoTotalCuenta;
    }
    
}