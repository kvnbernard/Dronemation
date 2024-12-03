package process;

import config.SimulationParameters;
import data.Element;
import data.Map;
import data.Position;
import data.Tree;

import java.awt.*;

public class Detection {
	private Map map;
	private Element[][] area;

	public Detection(Map map) {
		this.map = map;
		area = map.getVisionDrone();
	}

	public void detecter() {

		for (int indexLine = 0; indexLine < SimulationParameters.NUMBER_OF_HEIGHT_SQUARES; indexLine++) {
			for (int indexColumn = 0; indexColumn < SimulationParameters.NUMBER_OF_WIDTH_SQUARES; indexColumn++) {
				if (area[indexLine][indexColumn] != null) {
					if (!area[indexLine][indexColumn].isVisit()) {
						if (area[indexLine][indexColumn].getColor().equals(Color.GREEN)) {
							if (((Tree) area[indexLine][indexColumn]).isIsfire()) {
								area[indexLine][indexColumn].setColor(Color.red);
							}
							dealTree((Tree) area[indexLine][indexColumn], indexLine, indexColumn);
						} else {
							area[indexLine][indexColumn].setVisit();
						}
					}
				}
			}
		}
	}

	private void openFire(Tree tree) {
		int random = getRandom(0, 3);
		int line = tree.getPosition().getLine();
		int column = tree.getPosition().getColumn();
		Element[][] elementsInMap = map.getElementsInMap();

		if (random == 0) {
				if (elementsInMap[line+1][column]!= null) {
					if (elementsInMap[line + 1][column].getColor().equals(Color.GREEN) || elementsInMap[line + 1][column].getColor().equals(new Color(74, 100, 50))) {
						elementsInMap[line + 1][column].setColor(Color.RED);
					}
				}
			}
		if (random == 1) {
				if (elementsInMap[line - 1][column] != null) {
					if (elementsInMap[line - 1][column].getColor().equals(Color.GREEN) || elementsInMap[line - 1][column].getColor().equals(new Color(74, 100, 50))) {
						elementsInMap[line - 1][column].setColor(Color.RED);
					}
				}
			}
		if (random == 2) {
				if (elementsInMap[line][column + 1] != null) {
					if (elementsInMap[line][column + 1].getColor().equals(Color.GREEN) || elementsInMap[line][column + 1].getColor().equals(new Color(74, 100, 50))) {
						elementsInMap[line][column + 1].setColor(Color.RED);
					}
				}
			}
		if (random == 3) {
				if (elementsInMap[line][column - 1] != null) {
					if (elementsInMap[line][column - 1].getColor().equals(Color.GREEN) || elementsInMap[line][column - 1].getColor().equals(new Color(74, 100, 50))) {
						elementsInMap[line][column - 1].setColor(Color.RED);
					}
				}
			}
	}

	private void dealTree(Tree tree, int localLine, int localColumn) {
		tree.setVisit();
		// if (localColumn==0)

		if (localLine < SimulationParameters.NUMBER_OF_HEIGHT_SQUARES - 1) {
			if (area[localLine + 1][localColumn] == null) {
				tree.setUpTree(false);
			} else if (!area[localLine + 1][localColumn].isVisit()) {
				if (area[localLine + 1][localColumn].getColor().equals(Color.GREEN)) {

					dealTree((Tree) area[localLine + 1][localColumn], localLine + 1, localColumn);
				} else {
					tree.setUpTree(false);
//					area[localLine + 1][localColumn].setVisit();
				}
			}
		}
		if (localLine >= 1) {
			if (area[localLine - 1][localColumn] == null) {
				tree.setDownTree(false);
			} else if (!area[localLine - 1][localColumn].isVisit()) {
				if (area[localLine - 1][localColumn].getColor().equals(Color.GREEN)) {

					dealTree((Tree) area[localLine - 1][localColumn], localLine - 1, localColumn);
				} else {
					tree.setDownTree(false);
//					area[localLine - 1][localColumn].setVisit();
				}
			}
		}
		if (localColumn < SimulationParameters.NUMBER_OF_WIDTH_SQUARES - 1) {
			if (area[localLine][localColumn + 1] == null) {
				tree.setRightTree(false);
			} else if (!area[localLine][localColumn + 1].isVisit()) {
				if (area[localLine][localColumn + 1].getColor().equals(Color.GREEN)) {

					dealTree((Tree) area[localLine][localColumn + 1], localLine, localColumn + 1);
				} else {
					tree.setRightTree(false);
//					area[localLine][localColumn + 1].setVisit();
				}
			}
		}
		if (localColumn >= 1) {
			if (area[localLine][localColumn - 1] == null) {
				tree.setLeftTree(false);
			} else if (!area[localLine][localColumn - 1].isVisit()) {
				if (area[localLine][localColumn - 1].getColor().equals(Color.GREEN)) {

					dealTree((Tree) area[localLine][localColumn - 1], localLine, localColumn - 1);
				} else {
					tree.setLeftTree(false);
//					area[localLine][localColumn - 1].setVisit();
				}
			}
		}
//        System.out.println(tree.toString()+tree.isLeftTree()+tree.isUpTree()+tree.isRightTree()+tree.isDownTree());
		tree.setContour(!(tree.isDownTree() && tree.isRightTree() && tree.isUpTree() && tree.isLeftTree()));
	}

	public void detectedFire() {
		Element[][] elementsInMap = map.getElementsInMap();
		for (Element[] elements : elementsInMap) {
			if (elements != null) {
				for (Element elements2 : elements) {
					if (elements2 != null) {
						if (elements2.getColor().equals(Color.RED)) {
							int random = getRandom(0, 30);
							if (random < 3) {
								openFire((Tree) elements2);
							} else if (random == 10) {
								elements2.setColor(Color.white);
							}
						}
					}
				}
			}
		}
	}

	private int getRandom(int min, int max) {
		return (int) (Math.random() * (max + 1 - min)) + min;
	}

}
