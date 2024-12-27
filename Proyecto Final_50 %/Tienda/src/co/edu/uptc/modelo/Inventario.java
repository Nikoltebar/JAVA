package co.edu.uptc.modelo;

public class Inventario {

    private String marca;
    private String linea;
    private String codigo;
    private double precioBase;
    private int cantDisponible;

    public Inventario() {
    }

    public Inventario(String marca, String linea, String codigo, double precioBase, int cantDisponible) {
        this.marca = marca;
        this.linea = linea;
        this.codigo = codigo;
        this.precioBase = precioBase;
        this.cantDisponible = cantDisponible;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getLinea() {
        return linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getprecioBase() {
        return precioBase;
    }

    public void setprecioBase(double precioBase) {
        this.precioBase = precioBase;
    }

    public int getCantDisponible() {
        return cantDisponible;
    }

    public void setCantDisponible(int cantDisponible) {
        this.cantDisponible = cantDisponible;

    }

}