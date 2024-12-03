package process;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import config.SimulationParameters;
import data.Drone;
import data.Element;
import data.Map;
import data.Position;
import data.Tree;

public class VisionManager {
	private Map map;
	private Drone drone;
	private Position firstPositionV;
	private Position lastPositionV;
	private Timer timer = new Timer(2000, new TimerListener());

	public VisionManager(Map map, Drone drone) {
		this.map = map;
		this.drone = drone;
		firstPositionV = new Position(drone.getPosition().getColumn() - SimulationParameters.NUMBER_OF_WIDTH_SQUARES,
				drone.getPosition().getColumn() - SimulationParameters.NUMBER_OF_WIDTH_SQUARES);
		lastPositionV = new Position(drone.getPosition().getColumn() + SimulationParameters.NUMBER_OF_WIDTH_SQUARES,
				drone.getPosition().getColumn() + SimulationParameters.NUMBER_OF_WIDTH_SQUARES);
	}

	public String detectionTree(Element element) {
		String isTree="C'est pas un arbre";
		if (element.getColor().equals(Color.GREEN)) {
			isTree="C'est un arbre";
		}
		return isTree;
	}

	public void droneVision(Map map) {
		int droneColumn = drone.getPosition().getColumn();
		int droneLine = drone.getPosition().getLine();
		Element[][] visionElements = new Element[SimulationParameters.NUMBER_OF_HEIGHT_SQUARES][SimulationParameters.NUMBER_OF_WIDTH_SQUARES];
		for (int indexLine = 0; indexLine < SimulationParameters.NUMBER_OF_HEIGHT_SQUARES; indexLine++) {
			for (int indexColumn = 0; indexColumn < SimulationParameters.NUMBER_OF_WIDTH_SQUARES; indexColumn++) {
				visionElements[indexLine][indexColumn]=map.getElementsInMap()[droneLine+indexLine][droneColumn+indexColumn];
			}
		}
		map.setVisionDrone(visionElements);		
	}

	/**
	 * Cette Methode indique si le drone a fait une detection de la partie affich�
	 * Methode appel� dans PaintStategy
	 * @param map
	 * NAME CHANGED! CALLS "boolean isDetected" BEFORE!
	 */
	public void checkIfDetected(Map map) {
		droneVision(map);
		Detection detection = new Detection(map);
		detection.detecter();
		detection.detectedFire();
		timer.start();
	}
	
	/**
	 * Cette methode affiche la carte du drone avec les elements (Arbre) precedement d�tect� d'une couleurs differente.
	 * Methode appel� dans isDetected()
	 * @param map
	 */
	public void detectedDroneVision(Map map) {
        int droneColumn = drone.getPosition().getColumn();
        int droneLine = drone.getPosition().getLine();
        int numberFireDetect = 0;

        for (int indexLine = 0; indexLine < SimulationParameters.NUMBER_OF_HEIGHT_SQUARES; indexLine++) {
            for (int indexColumn = 0; indexColumn < SimulationParameters.NUMBER_OF_WIDTH_SQUARES; indexColumn++) {

                if (map.getElementsInMap()[droneLine+indexLine][droneColumn+indexColumn]!=null){
                    if (map.getElementsInMap()[droneLine+indexLine][droneColumn+indexColumn].getColor().equals(Color.green)) {
                        Tree tree = (Tree) map.getElementsInMap()[droneLine+indexLine][droneColumn+indexColumn];
                        if(tree.isContour()) {
                            map.getElementsInMap()[droneLine+indexLine][droneColumn+indexColumn].setColor(new Color(74, 100, 50));
                        }
                    }
                    else if(map.getElementsInMap()[droneLine+indexLine][droneColumn+indexColumn].getColor().equals(Color.RED)) {
                    		numberFireDetect++;
                    }
                }
            }
        }
        drone.setNumberFireDetect(numberFireDetect);
    }
	
	private class TimerListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			detectedDroneVision(map);
		}
		
	}
}
