package co.edu.uptc.dto;

import java.util.ArrayList;
import java.util.List;

import co.edu.uptc.modelo.Inventario;
import co.edu.uptc.modelo.Venta;

public class ReporteVentasVendedorDto {

    private double totalComision;
    private double totalcelulares;

    public double getTotalComision() {
        return totalComision;
    }

    public void setTotalComision(double totalComision) {
        this.totalComision = totalComision;
    }

    public double getTotalcelulares() {
        return totalcelulares;
    }

    public void setTotalcelulares(double totalcelulares) {
        this.totalcelulares = totalcelulares;
    }

    public int calcularTotalVentas(List<Inventario> listaInventario,
            List<Venta> listaVentas, String codigoUnicoVenta) {

        int totalComision = 0;
        for (Venta venta : listaVentas) {
            if (venta.getCodigoUnicoVenta().equalsIgnoreCase(codigoUnicoVenta)) {
                String codigoTipoCelular = venta.getCodigoTipoCelular();
                int cantidadVendida = venta.getCantidadVentas();
                double precioBase = buscarPrecioBasePorCodigo(codigoTipoCelular, listaInventario);
                double comisionVenta = precioBase * cantidadVendida * 0.05;
                totalComision += comisionVenta;

            }

        }
        return totalComision;

    }

    public double calcularComision(ArrayList<Venta> listaVentas, String codigoUnicoVenta,
            ArrayList<Inventario> listaInventario) {
        double totalComision = 0;
        for (Venta venta : listaVentas) {
            if (venta.getCodigoUnicoVenta().equalsIgnoreCase(codigoUnicoVenta)) {
                for (Inventario inventario : listaInventario) {
                    if (inventario.getCodigo().equals(venta.getCodigoTipoCelular())) {
                        totalComision += venta.getCantidadVentas() * inventario.getprecioBase() * 0.05;
                    }
                }
            }
        }

        return totalComision;
    }

    public int calcularCantidadVendida(ArrayList<Venta> listaVentas) {
        int cantidadVendida = 0;
        for (Venta venta : listaVentas) {
            cantidadVendida += venta.getCantidadVentas();
        }
        return cantidadVendida;
    }

    public double buscarPrecioBasePorCodigo(String codigo, List<Inventario> inventarioList) {
        for (Inventario item : inventarioList) {
            if (item.getCodigo().equals(codigo)) {
                return item.getprecioBase();
            }
        }
        return 0;
    }

    public int calcularVentas(ArrayList<Venta> listaVentas, String codigoUnicoVenta) {
        int cantidadVentas = 0;
        for (Venta venta : listaVentas) {
            if (venta.getCodigoUnicoVenta().equalsIgnoreCase(codigoUnicoVenta)) {
                cantidadVentas += venta.getCantidadVentas();

            }
        }
        return cantidadVentas;
    }
}