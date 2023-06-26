package net.positronator.digisense.digisense_learning;

import java.io.IOException;

import com.fazecast.jSerialComm.SerialPort;

public class SerialCommunication {
	
	static boolean initialized = false;
	
	static SerialPort sp; 
	
	public static boolean initialize(SerialPort p, int baudRate) {
		sp = p;
		sp.setComPortParameters(baudRate, 8, 1, 0);
		sp.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0);		
		
		initialized = sp.openPort();
		return initialized;
	}
	
	public static boolean initialize(String port, int baudRate) {
		return initialize(SerialPort.getCommPort(port), baudRate);
	}
	
	
	public static String[] getAvailableSerialPorts() {
		SerialPort[] ports = SerialPort.getCommPorts();
		String[] portNames = new String[ports.length];
		for(int i = 0; i < ports.length; i++)
			portNames[i] = ports[i].getSystemPortName() + " - " + ports[i].getPortDescription();
		return portNames;
	}
	
	public static boolean initialize(int port, int baudRate) {
		return initialize(SerialPort.getCommPorts()[port], baudRate);
	}
	
	
	public static void sendByte(int b) {
		try {
			sp.getOutputStream().write(b);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void close() {
		if(initialized) {
			sp.closePort();
		}
	}
}
