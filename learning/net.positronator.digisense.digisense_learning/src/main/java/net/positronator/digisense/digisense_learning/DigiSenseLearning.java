package net.positronator.digisense.digisense_learning;

import java.util.Random;
import java.util.Scanner;

public class DigiSenseLearning {

	
	public static void main(String[] args) throws InterruptedException {

		new ConfigWindow();
		

		if(!SerialCommunication.initialize("COM6", 115200)) {
			System.out.println("Failed to open Serial Communication");
			return;
//			System.exit(-1);
		}
		

		Random r = new Random();
		Scanner s = new Scanner(System.in);
		
		while(true) {
			int next = r.nextInt(10);
			SerialCommunication.sendByte(next);
			Thread.sleep(1000);
			System.out.println("Which number was that?");
			while(!s.hasNextLine());
			String guess = s.nextLine();
			while(guess.equals("")) {
				SerialCommunication.sendByte(next);
				Thread.sleep(1000);
				while(!s.hasNextLine());
				guess = s.nextLine();
			}
			int guessNumber = Integer.parseInt(guess);
			if(guessNumber == next) {
				System.out.println("Correct!");
			} else if(guessNumber == -1) {
				break;
			} else {
				System.out.println("Wrong - " + next);
				SerialCommunication.sendByte(next);
			}
			Thread.sleep(2000);
		}
		s.close();
		SerialCommunication.close();
		
	}

	
	
}
