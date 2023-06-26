package net.positronator.digisense.digisense_learning;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ConfigWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -716782866572157726L;

	private JComboBox<String> portSelector;
	private JTextField baudRateSelector;
	private JTextField levelsPerMotorSelector;
	private JTextField timePerByteSelector;
	private JFileChooser idTableSelector;
	
	
	public ConfigWindow() {
		super("DigiSense-Learner Configuration");
		
		JPanel p = new JPanel();
		p.setLayout(null);
		p.setSize(400, 400);
		
		initPortSelector(p);
		initBaudRateSelector(p);
		
		initLevelsPerMotorSelector(p);
		initTimePerByteSelector(p);
		
		initIdTabelSelector(p);
		
		initConfirmButton(p);
		
		add(p);
		setSize(400, 400);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}



	private void initConfirmButton(JPanel p) {

		JButton confirmButton = new JButton("Confirm");
		confirmButton.setSize(100, 20);
		confirmButton.setLocation(250, 320);
		confirmButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				int selectedPortId = portSelector.getSelectedIndex();
				if(selectedPortId == -1) {
					throw new RuntimeException("No Port was selected!");
				} else if(!SerialCommunication.getAvailableSerialPorts()[selectedPortId].equals(portSelector.getSelectedItem())) {
					throw new RuntimeException("Serial Ports have been modified!");
				}
				
			}
			
		});
		confirmButton.setVisible(true);
		p.add(confirmButton);
		
		
	}



	private void initIdTabelSelector(JPanel p) {
		
		JLabel idTableLabel = new JLabel("Character Decoder Table:");
		idTableLabel.setSize(150, 20);
		idTableLabel.setLocation(20, 120);
		idTableLabel.setVisible(true);
		p.add(idTableLabel);
		

		final JButton idTableSelectorButton = new JButton("Select File");
		idTableSelectorButton.setSize(200, 20);
		idTableSelectorButton.setLocation(20, 140);
		idTableSelectorButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				idTableSelector = new JFileChooser();
				idTableSelector.removeChoosableFileFilter(idTableSelector.getChoosableFileFilters()[0]);
				idTableSelector.addChoosableFileFilter(new FileNameExtensionFilter("CSV", "csv"));
				int response = idTableSelector.showOpenDialog(null);
				if(response == JFileChooser.APPROVE_OPTION) {
					String fileName = idTableSelector.getSelectedFile().getName();
					idTableSelectorButton.setText(fileName);
				}
			}
		});
		idTableSelectorButton.setVisible(true);
		p.add(idTableSelectorButton);
		
	}



	private void initTimePerByteSelector(JPanel p) {
		
		JLabel timePerByteLabel = new JLabel("Time Per Byte (ms):");
		timePerByteLabel.setSize(120, 20);
		timePerByteLabel.setLocation(260, 60);
		timePerByteLabel.setVisible(true);
		p.add(timePerByteLabel);
		
		timePerByteSelector = new JTextField("1000");
		timePerByteSelector.setSize(100, 20);
		timePerByteSelector.setLocation(260, 80);
		timePerByteSelector.setVisible(true);
		p.add(timePerByteSelector);
		
	}



	private void initLevelsPerMotorSelector(JPanel p) {

		JLabel levelsPerMotorLabel = new JLabel("Levels Per Motor:");
		levelsPerMotorLabel.setSize(100, 20);
		levelsPerMotorLabel.setLocation(20, 60);
		levelsPerMotorLabel.setVisible(true);
		p.add(levelsPerMotorLabel);
		
		levelsPerMotorSelector = new JTextField("4");
		levelsPerMotorSelector.setSize(100, 20);
		levelsPerMotorSelector.setLocation(20, 80);
		levelsPerMotorSelector.setVisible(true);
		p.add(levelsPerMotorSelector);
	}



	private void initBaudRateSelector(JPanel p) {

		baudRateSelector = new JTextField("115200");
		baudRateSelector.setSize(100, 20);
		baudRateSelector.setLocation(260, 20);
		baudRateSelector.setVisible(true);
		p.add(baudRateSelector);
		
	}



	private void initPortSelector(JPanel p) {

		String[] ports = SerialCommunication.getAvailableSerialPorts();
		
		portSelector = new JComboBox<String>(ports);
		portSelector.setSize(200, 20);
		portSelector.setLocation(20, 20);
		portSelector.setVisible(true);
		
		p.add(portSelector);
	}
	

}
