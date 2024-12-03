package data;

import java.util.ArrayList;

import config.GuiData;
import config.SimulationParameters;

public class Map {

	private int[][] cases;
	private ArrayList<Tree> trees;
	private ArrayList<Fire> fires;
	private ArrayList<House> houses;
	private String name;
	private Drone drone;
	private Element[][] elementsInMap = new Element[SimulationParameters.MAP_HEIGHT][SimulationParameters.MAP_WIDTH];
	private Element[][] droneVision = new Element[GuiData.WINDOW_HEIGHT][GuiData.WINDOW_WIDTH];
	
	public Map(int[][] cases, String name, Drone drone) {
		this.cases = cases;
		this.name = "MAP";
		this.drone = drone;
		this.trees = new ArrayList<Tree>();
		this.fires = new ArrayList<Fire>();
		this.houses = new ArrayList<House>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Drone getDrone() {
		return drone;
	}

	public void setDrone(Drone drone) {
		this.drone = drone;
	}

	public int[][] getCases() {
		return cases;
	}

	public void setCases(int[][] cases) {
		this.cases = cases;
	}

	public ArrayList<Tree> getTrees() {
		return trees;
	}

	public void setTrees(ArrayList<Tree> trees) {
		this.trees = trees;
	}

	public ArrayList<Fire> getFires() {
		return fires;
	}

	public void setFires(ArrayList<Fire> fires) {
		this.fires = fires;
	}

	public ArrayList<House> getHouses() {
		return houses;
	}

	public void setHouses(ArrayList<House> houses) {
		this.houses = houses;
	}

	public Element[][] getVisionDrone() {
		return droneVision;
	}

	public void setVisionDrone(Element[][] visionDrone) {
		this.droneVision = visionDrone;
	}

	public Element[][] getElementsInMap() {
		return elementsInMap;
	}

	public void setElementsInMap(Element[][] elementsInMap) {
		this.elementsInMap = elementsInMap;
	}

}
