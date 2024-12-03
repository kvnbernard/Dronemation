package data;

import java.awt.*;
import java.util.ArrayList;

import static config.SimulationParameters.NUMBER_OF_HEIGHT_SQUARES;
import static config.SimulationParameters.NUMBER_OF_WIDTH_SQUARES;

public class Drone extends Element {
	private int locationX;
	private int locationY;
	private Forest[][] detectionForest;
	private int numberFireDetect=0;

	public Drone(Position position) {
		super(position, Color.CYAN);
		locationX=0;
		locationY=0;
		detectionForest = new Forest[NUMBER_OF_WIDTH_SQUARES][NUMBER_OF_HEIGHT_SQUARES];
	}

	public Forest getDetectionForest(int x, int y) {
		return detectionForest[x][y];
	}
	public Forest getDetectionForest(){
		return detectionForest[locationX][locationY];
	}
	public void addForest (Tree head){
		detectionForest[locationX][locationX].addForest(head);
	}

	public int getNumberFireDetect() {
		return numberFireDetect;
	}

	public void setNumberFireDetect(int numberFireDetect) {
		this.numberFireDetect = numberFireDetect;
	}


}