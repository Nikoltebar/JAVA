package co.edu.uptc.modelo;

public class Venta {

    private String codigoUnicoVenta;
    private String codigoTipoCelular;
    private int cantidadVentas;

    public Venta() {
    }

    public Venta(String codigoUnicoVenta, String codigoTipoCelular, int cantidadVentas) {
        this.codigoUnicoVenta = codigoUnicoVenta;
        this.codigoTipoCelular = codigoTipoCelular;
        this.cantidadVentas = cantidadVentas;
    }

    public String getCodigoUnicoVenta() {
        return codigoUnicoVenta;
    }

    public void setCodigoUnicoVenta(String codigoUnicoVenta) {
        this.codigoUnicoVenta = codigoUnicoVenta;
    }

    public String getCodigoTipoCelular() {
        return codigoTipoCelular;
    }

    public void setCodigoTipoCelular(String codigoTipoCelular) {
        this.codigoTipoCelular = codigoTipoCelular;
    }

    public int getCantidadVentas() {
        return cantidadVentas;
    }

    public void setCantidadVentas(int cantidadVentas) {
        this.cantidadVentas = cantidadVentas;
    }

    @Override
    public String toString() {
        return "Venta [codigoUnicoVenta=" + codigoUnicoVenta + ", codigoTipoCelular=" + codigoTipoCelular
                + ", cantidadVentas=" + cantidadVentas + "]";
    }

}