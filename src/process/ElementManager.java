package process;

import config.GuiData;
import config.SimulationParameters;
import data.*;

public class ElementManager {

	public static void moveDownDrone(Map map) {
		int droneColumn = map.getDrone().getPosition().getColumn();
		if (droneColumn < GuiData.WINDOW_WIDTH - 1) {
			droneColumn += SimulationParameters.NUMBER_OF_WIDTH_SQUARES;
			map.getDrone().getPosition().setColumn((droneColumn));
		}
	}

	public static void moveUPDrone(Map map) {
		int droneColumn = map.getDrone().getPosition().getColumn();
		if (map.getDrone().getPosition().getColumn() > 0) {
			droneColumn -= SimulationParameters.NUMBER_OF_WIDTH_SQUARES;
			if (droneColumn < 0) {
				droneColumn += SimulationParameters.NUMBER_OF_WIDTH_SQUARES;
			}
			map.getDrone().getPosition().setColumn(droneColumn);
		}
	}

	public static void moveLeftDrone(Map map) {
		int droneLine = map.getDrone().getPosition().getLine();
		if (map.getDrone().getPosition().getLine() > 0) {
			droneLine -= SimulationParameters.NUMBER_OF_HEIGHT_SQUARES;
			if (droneLine < 0) {
				droneLine += SimulationParameters.NUMBER_OF_HEIGHT_SQUARES;
			}
			map.getDrone().getPosition().setLine(droneLine);
		}
	}

	public static void moveRightDrone(Map map) {
		int droneLine = map.getDrone().getPosition().getLine();
		if (map.getDrone().getPosition().getLine() < GuiData.WINDOW_HEIGHT - 1) {
			droneLine += SimulationParameters.NUMBER_OF_HEIGHT_SQUARES;
			map.getDrone().getPosition().setLine(droneLine);
		}
	}

}
