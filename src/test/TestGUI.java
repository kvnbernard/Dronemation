package test;

import config.SimulationParameters;
import data.Map;
import gui.MainPanel;
import process.MapBuiler;

public class TestGUI {

	public static void main(String[] args) {
		
		/**
		 * Test de creation d'une Map 
		*/
		MainPanel test = new MainPanel("MAP");
		Thread guiTread = new Thread(test);
		guiTread.start();
	}
	

	
}
