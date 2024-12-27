package co.edu.uptc.modelo;

public class Vendedor {

    private String codigoUnicoVenta;
    private String tipoDocumento;
    private long numeroDocumento;
    private String nombres;
    private String apellidos;
    private long numeroCuenta;
    private String tipoCuentaBancaria;
    private double comision;

    private static int contadorVendedores = 1;

    public Vendedor() {
    }

    public Vendedor(String codigoUnicoVenta, String tipoDocumento, long numeroDocumento, String nombres,
            String apellidos, long numeroCuenta, String tipoCuentaBancaria, double comision) {
        this.codigoUnicoVenta = generarCodigoUnico();
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.numeroCuenta = numeroCuenta;
        this.tipoCuentaBancaria = tipoCuentaBancaria;
        this.comision = comision;
    }

    public String generarCodigoUnico() {

        return "VEN" + String.format("%03d", contadorVendedores++);
    }

    public String getCodigoUnicoVenta() {
        return codigoUnicoVenta;
    }

    public void setCodigoUnicoVenta(String codigoUnicoVenta) {
        this.codigoUnicoVenta = codigoUnicoVenta;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public long getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(long numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public long getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(long numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getTipoCuentaBancaria() {
        return tipoCuentaBancaria;
    }

    public void setTipoCuentaBancaria(String tipoCuentaBancaria) {
        this.tipoCuentaBancaria = tipoCuentaBancaria;
    }

    public double getComision() {
        return comision;
    }

    public void setComision(double comision) {
        this.comision = comision;
    }

    @Override
    public String toString() {
        return "Vendedor [codigoUnicoVenta=" + codigoUnicoVenta + ", tipoDocumento=" + tipoDocumento
                + ", numeroDocumento=" + numeroDocumento + ", nombres=" + nombres + ", apellidos=" + apellidos
                + ", numeroCuenta=" + numeroCuenta + ", tipoCuentaBancaria=" + tipoCuentaBancaria + ", comision="
                + comision + "]";
    }

}