package org.technikradio.cui;

import java.awt.LayoutManager;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ConsolePanel extends JPanel implements ConsoleListener{

	private static final long serialVersionUID = 7912605838877188364L;
	private JTextField input;
	private JTextArea output;
	
	private void setupGUI(){
		
	}

	public ConsolePanel() {
		super();
		setupGUI();
	}

	public ConsolePanel(LayoutManager layout) {
		super(layout);
		setupGUI();
	}

	public ConsolePanel(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		setupGUI();
	}

	public ConsolePanel(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		setupGUI();
	}

	@Override
	public void listen() {
	}

	/**
	 * @return the input
	 */
	public JTextField getInput() {
		return input;
	}

	/**
	 * @param input the input to set
	 */
	public void setInput(JTextField input) {
		this.input = input;
	}

	/**
	 * @return the output
	 */
	public JTextArea getOutput() {
		return output;
	}

	/**
	 * @param output the output to set
	 */
	public void setOutput(JTextArea output) {
		this.output = output;
	}

}
