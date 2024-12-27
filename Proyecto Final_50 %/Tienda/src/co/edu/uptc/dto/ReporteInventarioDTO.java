package co.edu.uptc.dto;

import java.util.ArrayList;

import co.edu.uptc.modelo.Inventario;

public class ReporteInventarioDTO {

	private int totalProductos;
	private double totalPrecioBase;
	private double totalPrecioVenta;
	private double totalImpuestos;
	private double totalComisionVenta;
	private double totalGanancias;

	public static final double PORCENTAJEGANANCIA = 1.35;
	public static final double IVA19PORCIENTO= 0.19;
	public static final double IVA5PORCIENTO = 0.05;
	public static final double COMISION5PORCIENTO =0.05;


	public int getTotalProductos() {
		return totalProductos;
	}

	public void setTotalProductos(int totalProductos) {
		this.totalProductos = totalProductos;
	}

	public double getTotalPrecioBase() {
		return totalPrecioBase;
	}

	public void setTotalPrecioBase(double totalPrecioBase) {
		this.totalPrecioBase = totalPrecioBase;
	}

	public double getTotalPrecioVenta() {
		return totalPrecioVenta;
	}

	public void setTotalPrecioVenta(double totalPrecioVenta) {
		this.totalPrecioVenta = totalPrecioVenta;
	}

	public double getTotalImpuestos() {
		return totalImpuestos;
	}

	public void setTotalImpuestos(double totalImpuestos) {
		this.totalImpuestos = totalImpuestos;
	}

	public double getTotalComisionVenta() {
		return totalComisionVenta;
	}

	public void setTotalComisionVenta(double totalComisionVenta) {
		this.totalComisionVenta = totalComisionVenta;
	}

	public double getTotalGanancias() {
		return totalGanancias;
	}

	public void setTotalGanancias(double totalGanancias) {
		this.totalGanancias = totalGanancias;
	}

	public void calcularTotalInventario(ArrayList<Inventario> listaInvetario) {
		totalProductos = 0;
		totalPrecioBase = 0;
		totalPrecioVenta = 0;
		totalImpuestos = 0;
		totalComisionVenta = 0;
		totalGanancias = 0;

		for (Inventario item : listaInvetario) {
			int cantDisponible = item.getCantDisponible();
			double precioBase = item.getprecioBase() * cantDisponible;
			double precio = item.getprecioBase() * PORCENTAJEGANANCIA;
			double ganancia = calcularGanancia(precioBase);

			double ivaUnitario = calcularIVA(precio);
			double impuesto = ivaUnitario * cantDisponible - cantDisponible * precio;
			double comisionesPorProducto = precioBase * COMISION5PORCIENTO;
			double gananciaTotal = ganancia - precioBase - comisionesPorProducto;

			totalProductos += cantDisponible;
			totalPrecioBase += precioBase;
			totalImpuestos += impuesto;
			totalComisionVenta += comisionesPorProducto;
			totalGanancias += gananciaTotal;
		}
	}

	public double calcularIVA(double precioBase) {
		if (precioBase > 600000) {
			return precioBase * (1+(IVA19PORCIENTO));
		} else {
			return precioBase * (1+(IVA5PORCIENTO));
		}
	}

	public double calcularGanancia(double precioBase) {
		return precioBase * PORCENTAJEGANANCIA;
	}

	public double calcularPrecioVenta(ArrayList<Inventario> listaInventario) {
		double precioVenta = 0;
		for (Inventario inventario : listaInventario) {
			double precio = calcularGanancia(inventario.getprecioBase());
			if (precio > 600000) {
				precioVenta += inventario.getCantDisponible() * precio
						+ (1+(inventario.getCantDisponible() * precio )* IVA19PORCIENTO);
			} else {
				precioVenta += inventario.getCantDisponible() * precio
						+ (1+(inventario.getCantDisponible() * precio )* IVA5PORCIENTO);
			}

		}
		return precioVenta;
	}

}