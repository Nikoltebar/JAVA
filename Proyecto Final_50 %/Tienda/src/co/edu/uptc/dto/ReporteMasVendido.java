package co.edu.uptc.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import co.edu.uptc.modelo.Venta;
import co.edu.uptc.modelo.Inventario;

public class ReporteMasVendido {

    private String marcaMasVendida;
    private String lineaMasVedida;
    private double totalVentasMaca;
    private double totalVentasLinea;

    public static final double PORCENTAJEGANANCIA = 1.35;
    public static final double IMPUESTO19PORCIENTO= 0.19;
	public static final double IMPUESTO5PORCIENTO = 0.05;
    
    public String getMarcaMasVendida() {
        return marcaMasVendida;
    }

    public void setMarcaMasVendida(String marcaMasVendida) {
        this.marcaMasVendida = marcaMasVendida;
    }

    public String getLineaMasVedida() {
        return lineaMasVedida;
    }

    public void setLineaMasVedida(String lineaMasVedida) {
        this.lineaMasVedida = lineaMasVedida;
    }

    public double getTotalVentasMaca() {
        return totalVentasMaca;
    }

    public void setTotalVentasMaca(double totalVentasMaca) {
        this.totalVentasMaca += totalVentasMaca;
    }

    public double getTotalVentasLinea() {
        return totalVentasLinea;
    }

    public void setTotalVentasLinea(double totalVentasLinea) {
        this.totalVentasLinea += totalVentasLinea;
    }

    public ReporteMasVendido calcularMarcaMasVendida(ArrayList<Venta> listaVentas, ArrayList<Inventario> inventarios) {
        HashMap<String, ReporteMasVendido> ventasPorMarca = new HashMap<>();

        for (Venta venta : listaVentas) {
            String marca = buscarMarca(venta.getCodigoTipoCelular(), inventarios);
            int cantidadVendida = venta.getCantidadVentas();

            if (ventasPorMarca.containsKey(marca)) {
                double totalVenta = calclularVenta(venta.getCodigoTipoCelular(), cantidadVendida, inventarios);
                ventasPorMarca.get(marca).setTotalVentasMaca(totalVenta);
            } else {
                double totalVenta = calclularVenta(venta.getCodigoTipoCelular(), cantidadVendida, inventarios);
                ReporteMasVendido reporte = new ReporteMasVendido();
                reporte.setMarcaMasVendida(marca);
                reporte.setTotalVentasMaca(totalVenta);
                ventasPorMarca.put(marca, reporte);
            }

        }
        return encontrarMasVendidoMarca(ventasPorMarca);
    }

    public ReporteMasVendido calcularLineaMasVendida(ArrayList<Venta> listaVentas, ArrayList<Inventario> inventarios) {
        HashMap<String, ReporteMasVendido> ventasPorLinea = new HashMap<>();

        for (Venta venta : listaVentas) {
            String linea = venta.getCodigoTipoCelular();
            int cantidadVendida = venta.getCantidadVentas();

            if (ventasPorLinea.containsKey(linea)) {
                double totalVenta = calclularVenta(venta.getCodigoTipoCelular(), cantidadVendida, inventarios);
                ventasPorLinea.get(linea).setTotalVentasLinea(totalVenta);
            } else {
                double totalVenta = calclularVenta(venta.getCodigoTipoCelular(), cantidadVendida, inventarios);
                ReporteMasVendido reporte = new ReporteMasVendido();
                reporte.setLineaMasVedida(linea);
                reporte.setTotalVentasLinea(totalVenta);
                ventasPorLinea.put(linea, reporte);
            }

        }
        return encontrarMasVendidoLinea(ventasPorLinea);
    }

    public double calclularVenta(String codigo, double cantidad, ArrayList<Inventario> inventarios) {
        double precio = buscarPrecio(codigo, inventarios);
        double ganancia = precio * PORCENTAJEGANANCIA;
        if (ganancia > 600000) {
            return cantidad * ganancia * (1+(IMPUESTO19PORCIENTO));
        } else {
            return cantidad * ganancia * (1+(IMPUESTO5PORCIENTO));

        }

    }

    public double buscarPrecio(String codigoProducto, ArrayList<Inventario> inventarios) {
        for (Inventario inventario : inventarios) {
            if (inventario.getCodigo().equals(codigoProducto)) {
                return inventario.getprecioBase();
            }
        }
        return 0;
    }

    public ReporteMasVendido encontrarMasVendidoMarca(Map<String, ReporteMasVendido> ventasPorMarca) {
        ReporteMasVendido reporte = new ReporteMasVendido();
        double masVendido = 0;
        for (ReporteMasVendido venta : ventasPorMarca.values()) {
            if (venta.getTotalVentasMaca() > masVendido) {
                masVendido = venta.getTotalVentasMaca();
                reporte = venta;
            }
        }
        return reporte;
    }

    public ReporteMasVendido encontrarMasVendidoLinea(Map<String, ReporteMasVendido> ventasPorLinea) {
        ReporteMasVendido reporte = new ReporteMasVendido();
        double masVendido = 0;
        for (ReporteMasVendido venta : ventasPorLinea.values()) {
            if (venta.getTotalVentasLinea() > masVendido) {
                masVendido = venta.getTotalVentasLinea();
                reporte = venta;
            }
        }
        return reporte;
    }

    public String buscarMarca(String codigoProducto, ArrayList<Inventario> inventarios) {
        for (Inventario inventario : inventarios) {
            if (inventario.getCodigo().equals(codigoProducto)) {
                return inventario.getMarca();
            }
        }
        return null;
    }

}