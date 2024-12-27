package co.edu.uptc.modelo;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import co.edu.uptc.dto.ReporteImpuestoDTO;
import co.edu.uptc.dto.ReporteInventarioDTO;
import co.edu.uptc.dto.ReporteMasVendido;
import co.edu.uptc.dto.ReporteVentasVendedorDto;

public class Tienda {

	private Map<String, Vendedor> listaVendedores;

	private ArrayList<Inventario> listaInvetario;

	private ArrayList<Venta> listaVentas;

	public Map<String, Vendedor> getListaVendedores() {
		return listaVendedores;
	}

	public void setListaVendedores(Map<String, Vendedor> listaVendedores) {
		this.listaVendedores = listaVendedores;
	}

	public List<Inventario> getListaInvetario() {
		return listaInvetario;
	}

	public void setListaInvetario(ArrayList<Inventario> listaInvetario) {
		this.listaInvetario = listaInvetario;
	}

	public List<Venta> getListaVentas() {
		return listaVentas;
	}

	public void setListaVentas(ArrayList<Venta> listaVentas) {
		this.listaVentas = listaVentas;
	}

	public Tienda() {
		super();
		// TODO Auto-generated constructor stub
		listaInvetario = new ArrayList<Inventario>();
		listaVendedores = new TreeMap<String, Vendedor>();
		listaVentas = new ArrayList<Venta>();

	}

	public void cargarInventario(String infoInventario) {
		// TODO desarrollar metodo que cargue el invetario
		// lista de invetarios listaInvetario
		String[] arregloLineas = separarLineasVenta(infoInventario);
		for (String datosInv : arregloLineas) {
			Inventario nuevo = crearLineaInventario(datosInv);
			listaInvetario.add(nuevo);

		}
	}

	public String[] separarLineas(String cadenaInventarios) {
		String[] arreglo = cadenaInventarios.split("\n");
		return arreglo;
	}

	public Inventario crearLineaInventario(String linea) {
		String[] arreglo = linea.split("\\;");
		Inventario nuevo = new Inventario();
		// TODO crear logica que separe la linea en los diferentes
		// atributos de inventario y asignarselos al objeto

		nuevo.setMarca(arreglo[0]);
		nuevo.setLinea(arreglo[1]);
		nuevo.setCodigo((arreglo[2]));
		nuevo.setprecioBase(Double.parseDouble(arreglo[3]));
		nuevo.setCantDisponible(Integer.parseInt(arreglo[4]));
		return nuevo;
	}

	public ReporteInventarioDTO obtenerReporteInventario() {
		ReporteInventarioDTO reporte = new ReporteInventarioDTO();

		reporte.calcularTotalInventario(listaInvetario);
		reporte.setTotalPrecioVenta(reporte.calcularPrecioVenta(listaInvetario));

		return reporte;

	}

	///////////// CARGAR INFORMACIÓN DEL VENDEDOR//////////////////

	public void cargarPersonaVendedor(String cargarInfoPersonaVendedor) {
		// TODO desarrollar metodo que cargue el invetario
		// lista de vendedores listaVendedores
		String[] arregloLineasPersona = separarLineasPersona(cargarInfoPersonaVendedor);
		for (String datosInv : arregloLineasPersona) {
			Vendedor nuevo = crearLineaVendedor(datosInv);

			listaVendedores.put(nuevo.getCodigoUnicoVenta(), nuevo);
		}
	}

	public String[] separarLineasPersona(String cadenaVendedor) {
		String[] arreglo = cadenaVendedor.split("\n");
		return arreglo;
	}

	public Vendedor crearLineaVendedor(String linea) {
		String[] arreglo = linea.split("\\;");
		Vendedor nuevo = new Vendedor();
		// TODO crear logica que separe la linea en los diferentes
		// atributos de inventario y asignarselos al objeto

		nuevo.setTipoDocumento(arreglo[0]);
		nuevo.setNumeroDocumento(Long.parseLong(arreglo[1]));
		nuevo.setNombres(arreglo[2]);
		nuevo.setApellidos(arreglo[3]);
		nuevo.setNumeroCuenta(Long.parseLong(arreglo[4]));
		nuevo.setTipoCuentaBancaria(arreglo[5]);

		nuevo.setCodigoUnicoVenta(nuevo.generarCodigoUnico());
		return nuevo;

	}

	/////////// CARGAR VENTAS/////////

	public void cargarVentas(String infoVentas) {
		// TODO desarrollar metodo que cargue el invetario
		// lista de invetarios listaInvetario
		String[] arregloLineas = separarLineasVenta(infoVentas);
		for (String datosInv : arregloLineas) {
			Venta nuevo = crearLineaVenta(datosInv);
			listaVentas.add(nuevo);
		}
	}

	public String[] separarLineasVenta(String cadenaVentas) {
		String[] arreglo = cadenaVentas.split("\n");
		return arreglo;
	}

	public Venta crearLineaVenta(String linea) {
		String[] arreglo = linea.split("\\;");
		Venta nuevo = new Venta();
		// TODO crear logica que separe la linea en los diferentes
		// atributos de inventario y asignarselos al objeto
		nuevo.setCodigoUnicoVenta(arreglo[0]);
		nuevo.setCodigoTipoCelular(arreglo[1]);
		nuevo.setCantidadVentas(Integer.parseInt(arreglo[2]));
		return nuevo;
	}

	public String obtenerReporteVentasVendedor() {
		ReporteVentasVendedorDto reporte = new ReporteVentasVendedorDto();
		NumberFormat formato = NumberFormat.getCurrencyInstance();
		StringBuilder sb = new StringBuilder();
		for (Vendedor vendedor : listaVendedores.values()) {
			sb.append(String.format("%-21s | %-21s | %-21s | %-18s | %-18s | %-18s| %-18s| %-18s| %-21s\n",
					vendedor.getCodigoUnicoVenta(), vendedor.getTipoDocumento(), vendedor.getNumeroDocumento(),
					vendedor.getNombres(), vendedor.getApellidos(),
					vendedor.getNumeroCuenta(), vendedor.getTipoCuentaBancaria(),
					formato.format(
							reporte.calcularComision(listaVentas, vendedor.getCodigoUnicoVenta(), listaInvetario)),
					reporte.calcularVentas(listaVentas, vendedor.getCodigoUnicoVenta())));

		}
		return sb.toString();

	}

	public String obtenerReporteImpuestos() {

		ReporteImpuestoDTO reporteImpuestos = new ReporteImpuestoDTO();
		reporteImpuestos.calcularPagoImpuestos(listaInvetario, listaVentas);

		StringBuilder sb = new StringBuilder();

		sb.append("-----------------------------------------------------------\n");
		sb.append(String.format("5%%       | $%,.2f         | $%,.2f             \n",
				reporteImpuestos.getBasesGrabables5Porciento(), reporteImpuestos.getImpuesto5Porciento()));
		sb.append(String.format("19%%      | $%,.2f        | $%,.2f             \n",
				reporteImpuestos.getBasesGrabables19Porciento(), reporteImpuestos.getImpuesto19Porciento()));
		sb.append("---------------------------------------------------\n");
		sb.append(String.format("Total    | $%,.2f         | $%,.2f              \n",
				reporteImpuestos.getTotalBasesGrabables5() + reporteImpuestos.getTotalBasesGrabables19(),
				reporteImpuestos.getTotalImpuestos()));

		return sb.toString();
	}

	public String obtenerReporteMasVendidos() {
		// TODO implementar logica para separa información
		// trae lo que hay en textArea en String
		ReporteMasVendido reporte = new ReporteMasVendido();
		reporte = reporte.calcularMarcaMasVendida(listaVentas, listaInvetario);
		ReporteMasVendido reporteLinea = new ReporteMasVendido();
		reporteLinea = reporteLinea.calcularLineaMasVendida(listaVentas, listaInvetario);

		StringBuilder sb = new StringBuilder();
		sb.append(String.format("%-21s | %-21s | %-21s | %-21s \n",
				"Marca más vendida", "Linea más vendida", "Total ventas marca más vendida",
				"Total ventas linea más vendida"));
		sb.append(String.format("%-21s | %-21s | %-30s | %-21s \n",
				reporte.getMarcaMasVendida(), reporteLinea.getLineaMasVedida(),
				String.format("$%,.2f", reporte.getTotalVentasMaca()),
				String.format("$%,.2f", reporteLinea.getTotalVentasLinea())));

		return sb.toString();
	}
	
}