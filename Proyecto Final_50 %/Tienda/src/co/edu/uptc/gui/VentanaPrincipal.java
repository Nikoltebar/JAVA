package co.edu.uptc.gui;

import java.awt.BorderLayout;
import java.text.NumberFormat;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import co.edu.uptc.dto.ReporteInventarioDTO;
import co.edu.uptc.modelo.Inventario;
import co.edu.uptc.modelo.Venta;
import co.edu.uptc.modelo.Tienda;
import co.edu.uptc.modelo.Vendedor;

public class VentanaPrincipal extends JFrame {

	private PanelInventario info;
	private PanelVentas infoVentas;
	private PanelBotones botones;
	private PanelPersona persona;

	private Tienda nuevaTienda;

	public VentanaPrincipal() {
		setTitle("Mi Tienda");
		setSize(1100, 600);

		Evento evento = new Evento(this);
		info = new PanelInventario(evento);
		infoVentas = new PanelVentas(evento);
		botones = new PanelBotones(evento);
		persona = new PanelPersona(evento);

		setLayout(new BorderLayout());
		add(info, BorderLayout.WEST);
		add(persona, BorderLayout.CENTER);
		add(infoVentas, BorderLayout.EAST);
		add(botones, BorderLayout.SOUTH);

		nuevaTienda = new Tienda();
	}

	public static void main(String[] args) {
		VentanaPrincipal nueva = new VentanaPrincipal();
		nueva.setVisible(true);
	}

	public void cargarInfoInventario() {
		JOptionPane.showMessageDialog(this, "Cargar contenido de inventario");
		// TODO implementar logica para separa información
		// trae lo que hay en textArea en String
		String variable = info.obtenerDatos();
		nuevaTienda.cargarInventario(variable);

		mostrarConsola();
	}

	private void mostrarConsola() {
		List<Inventario> actualizada = nuevaTienda.getListaInvetario();
		for (Inventario inventario : actualizada) {
			System.out.println(inventario.getMarca());
			System.out.println(inventario.getLinea());
			System.out.println(inventario.getCodigo());
			System.out.println(inventario.getprecioBase());
			System.out.println(inventario.getCantDisponible());

		}
	}

	public void generarInformeInventario() {
		JOptionPane.showMessageDialog(this, "Crear infome de inventario");
		NumberFormat formato = NumberFormat.getCurrencyInstance();
		DialogoLista nuevo = new DialogoLista();
		String titulo = String.format("%-20s | %-20s | %-20s | %-20s | %-20s | %-20s",
				"# celulares", "Total precio base ", "Total Precio de venta", "Total Impuestos ", "Total Comisiones ",
				"Ganancias");
		nuevo.agregrarTexto(titulo);
		ReporteInventarioDTO reporte = nuevaTienda.obtenerReporteInventario();
		String reporteDTO = String.format("%-20d | %-20s | %-20s | %-20s | %-20s | %-20s",
				reporte.getTotalProductos(), formato.format(reporte.getTotalPrecioBase()),
				formato.format(reporte.getTotalPrecioVenta()),
				formato.format(reporte.getTotalImpuestos()), formato.format(reporte.getTotalComisionVenta()),
				formato.format(reporte.getTotalGanancias()));
		nuevo.agregrarTexto(reporteDTO);
		nuevo.setVisible(true);
	}

	public void cargarInfoPersonaVendedor() {
		JOptionPane.showMessageDialog(this, "Cargar contenido Persona");
		// TODO implementar logica para separa información
		// trae lo que hay en textArea en String
		String variable = persona.obtenerDatos();
		nuevaTienda.cargarPersonaVendedor(variable);
		mostrarConsolaPersonaVendedor();
	}

	private void mostrarConsolaPersonaVendedor() {
		Map<String, Vendedor> actualizada = nuevaTienda.getListaVendedores();
		for (Vendedor vendedor : actualizada.values()) {
			System.out.println(vendedor.generarCodigoUnico());
			System.out.println(vendedor.getTipoDocumento());
			System.out.println(vendedor.getNumeroDocumento());
			System.out.println(vendedor.getNombres());
			System.out.println(vendedor.getApellidos());
			System.out.println(vendedor.getNumeroCuenta());
		}
	}

	public void generarInformePersonaVentas() {
		JOptionPane.showMessageDialog(this, "Crear infome de ventas");
		DialogoLista nuevo = new DialogoLista();
		String titulo = String.format("%-18s | %-18s | %-18s | %-18s | %-18s | %-18s| %-18s| %-18s| %-18s",
				"Codigo unico de venta", "Tipo de documento ", "Numero de identificacion", "Nombres  ", "Apellidos ",
				"Numero de cuenta", "Tipo de cuenta", "Total comisiones", "Total celulares");
		nuevo.agregrarTexto(titulo);
		String reporte = nuevaTienda.obtenerReporteVentasVendedor();
		nuevo.agregrarTexto(reporte);
		nuevo.setVisible(true);
	}

	public void cargarInfoVentas() {
		// TODO implementar logica para separa información
		// trae lo que hay en textArea en String
		JOptionPane.showMessageDialog(this, "Cargar contenido de ventas");
		infoVentas.obtenerDatos();
		nuevaTienda.cargarVentas(infoVentas.obtenerDatos());

		mostrarConsolaVentas();
	}

	private void mostrarConsolaVentas() {
		List<Venta> actualizada = nuevaTienda.getListaVentas();// Inicializar correctamente la lista de ventas
		for (Venta venta : actualizada) {
			System.out.println(venta.getCodigoUnicoVenta());
			System.out.println(venta.getCodigoTipoCelular());
			System.out.println(venta.getCantidadVentas());

		}
	}

	public void generarInformeImpuestos() {
		JOptionPane.showMessageDialog(this, "Crear infome de Impuestos");
		DialogoLista nuevo = new DialogoLista();
		String encabezado = "Impuesto% | "
				+ "total bases grabables | "
				+ "Total impuestos | ";
		nuevo.agregrarTexto(encabezado);
		String reporte = nuevaTienda.obtenerReporteImpuestos();
		nuevo.agregrarTexto(reporte);
		nuevo.setVisible(true);
	}

	public void generarReporteMasVendidos() {
		JOptionPane.showMessageDialog(this, "Crear infome de mas vendidos");
		DialogoLista nuevo = new DialogoLista();
		String reporte = nuevaTienda.obtenerReporteMasVendidos();
		nuevo.agregrarTexto(reporte);
		nuevo.setVisible(true);
	}

	public void salir() {
		// TODO investigar como cerrar un JFRAME
		System.exit(0);
	}
}