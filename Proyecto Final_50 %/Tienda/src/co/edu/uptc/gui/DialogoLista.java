package co.edu.uptc.gui;

import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JTextArea;

public class DialogoLista extends JDialog {

	private JTextArea txaLista;
	public DialogoLista() {
		// TODO Auto-generated constructor stub
		txaLista= new JTextArea();
		setSize(600, 300);
		add(txaLista);
	}
	
	public void agregrarTexto(String texto) {		
		txaLista.setFont(new Font ("monospaced", Font.PLAIN, 12));
			txaLista.append(texto);
			txaLista.append("\n");	
			
		
	}
}
