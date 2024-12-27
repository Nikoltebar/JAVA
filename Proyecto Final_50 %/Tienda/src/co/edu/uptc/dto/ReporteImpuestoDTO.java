package co.edu.uptc.dto;

import java.util.List;
import co.edu.uptc.modelo.Venta;
import co.edu.uptc.modelo.Inventario;

public class ReporteImpuestoDTO {


    private double totalImpuestos;
    private double totalBasesGrabables5;
    private double totalBasesGrabables19;
    private double impuesto5Porciento;
    private double impuesto19Porciento;
    private double basesGrabables5Porciento;
    private double basesGrabables19Porciento;

    public static final double PORCENTAJEGANANCIA = 1.35;
    public static final double IMPUESTO19PORCIENTO= 0.19;
	public static final double IMPUESTO5PORCIENTO = 0.05;
    public ReporteImpuestoDTO() {
        this.totalImpuestos = 0;
        this.totalBasesGrabables5 = 0;
        this.totalBasesGrabables19 = 0;
        this.impuesto5Porciento = 0;
        this.impuesto19Porciento = 0;
        this.basesGrabables5Porciento = 0;
        this.basesGrabables19Porciento = 0;
    
    }

    public double getTotalImpuestos() {
        return totalImpuestos;
    }

    public void setTotalImpuestos(double totalImpuestos) {
        this.totalImpuestos = totalImpuestos;
    }

    public double getTotalBasesGrabables5() {
        return totalBasesGrabables5;
    }

    public void setTotalBasesGrabables5(double totalBasesGrabables5) {
        this.totalBasesGrabables5 = totalBasesGrabables5;
    }

    public double getTotalBasesGrabables19() {
        return totalBasesGrabables19;
    }

    public void setTotalBasesGrabables19(double totalBasesGrabables19) {
        this.totalBasesGrabables19 = totalBasesGrabables19;
    }

    public double getImpuesto5Porciento() {
        return impuesto5Porciento;
    }

    public void setImpuesto5Porciento(double impuesto5Porciento) {
        this.impuesto5Porciento = impuesto5Porciento;
    }

    public double getImpuesto19Porciento() {
        return impuesto19Porciento;
    }

    public void setImpuesto19Porciento(double impuesto19Porciento) {
        this.impuesto19Porciento = impuesto19Porciento;
    }

    public double getBasesGrabables5Porciento() {
        return basesGrabables5Porciento;
    }

    public void setBasesGrabables5Porciento(double basesGrabables5Porciento) {
        this.basesGrabables5Porciento = basesGrabables5Porciento;
    }

    public double getBasesGrabables19Porciento() {
        return basesGrabables19Porciento;
    }

    public void setBasesGrabables19Porciento(double basesGrabables19Porciento) {
        this.basesGrabables19Porciento = basesGrabables19Porciento;
    }

        public void calcularPagoImpuestos(List<Inventario> listaInventario, List<Venta> listaVentas) {
            this.impuesto5Porciento = 0;
            this.impuesto19Porciento = 0;
            this.basesGrabables5Porciento = 0;
            this.basesGrabables19Porciento = 0;

            for (Venta venta : listaVentas) {
                double precioBase = 0;

                for (Inventario inventario : listaInventario) {
                    if (inventario.getCodigo().equals(venta.getCodigoTipoCelular())) {
                        precioBase = inventario.getprecioBase();
                        break;
                    }
                }

                // Ganancia
                double precioConGanancia = precioBase * PORCENTAJEGANANCIA;

                //// Impuestos y bases grabables
                if (precioConGanancia > 600000) {
                    double impuesto = precioConGanancia * IMPUESTO19PORCIENTO;
                    this.impuesto19Porciento += impuesto * venta.getCantidadVentas();
                    this.basesGrabables19Porciento += precioConGanancia * venta.getCantidadVentas();
                } else {
                    double impuesto = precioConGanancia *IMPUESTO5PORCIENTO;
                    this.impuesto5Porciento += impuesto * venta.getCantidadVentas();
                    this.basesGrabables5Porciento += precioConGanancia * venta.getCantidadVentas();
                }
            }

            // Calcular totales
            this.totalImpuestos = this.impuesto5Porciento + this.impuesto19Porciento;
            this.totalBasesGrabables5 = this.basesGrabables5Porciento;
            this.totalBasesGrabables19 = this.basesGrabables19Porciento;
        }
    }