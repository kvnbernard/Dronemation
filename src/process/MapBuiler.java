package process;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

import config.GuiData;
import config.SimulationParameters;
import data.Drone;
import data.Element;
import data.Fire;
import data.House;
import data.Map;
import data.Normal;
import data.Position;
import data.Tree;

public class MapBuiler {
	private Element[][] elements = new Element[SimulationParameters.MAP_HEIGHT][SimulationParameters.MAP_WIDTH];

	private int[][] freeSquares;
	private int[][] SquaresWhithCode;
	private ArrayList<Tree> treesList;
	private ArrayList<Fire> firesList;
	private ArrayList<House> housesList;
	private ArrayList<Normal> normalList;
	private Map resultMap;
	private Drone drone;

	/**
	 * Code for Squares :
	 * 
	 * 5 : Tree 2 : Fire 3 : House
	 * 
	 */

	public MapBuiler() {
		this.freeSquares = new int[SimulationParameters.NUMBER_OF_HEIGHT_SQUARES][SimulationParameters.NUMBER_OF_WIDTH_SQUARES];
		this.SquaresWhithCode = new int[SimulationParameters.NUMBER_OF_HEIGHT_SQUARES][SimulationParameters.NUMBER_OF_WIDTH_SQUARES];
		this.firesList = new ArrayList<Fire>();
		this.housesList = new ArrayList<House>();
		this.treesList = new ArrayList<Tree>();
		this.normalList = new ArrayList<Normal>();

		this.drone = new Drone(new Position(0, 0));
		this.resultMap = new Map(SquaresWhithCode, "Carte_Proto", drone);
	}

	public Map getMap() {
		newCreation();
		//createElementInMap();
		//mapSmoothing();
		resultMap.setHouses(housesList);
		resultMap.setTrees(treesList);
		resultMap.setFires(firesList);
		resultMap.setCases(SquaresWhithCode);
		resultMap.setElementsInMap(elements);
		return resultMap;
	}

	/**
	 * Ajoute les forets sur la carte
	 * 
	 * @param line
	 * @param column
	 */
	public void addForest(int line, int column) {
		Position position = new Position(line, column);
		Tree tree = new Tree(position);
		treesList.add(tree);
		elements[line][column] = tree;

	}
public void addNormal(int line, int column) {
	Position position = new Position(line, column);
	Normal normal = new Normal(position);
	normalList.add(normal);
	elements[line][column] = normal;
}
	/**
	 * Ajoute les feux sur la carte
	 * 
	 * @param line
	 * @param column
	 */
	public void addFire(int line, int column) {
		Position position = new Position(line, column);
		Tree tree = new Tree(position);
		treesList.remove(tree);
		tree.setIsfire(true);
		treesList.add(tree);
		elements[line][column] = tree;
		}


	/**
	 * Ajoute les Maisons sur la carte
	 * 
	 * @param line
	 * @param column
	 */
	public void addHouses(int line, int column) {

		Position position = new Position(line, column);
		House house = new House(position);
		housesList.add(house);
		elements[line][column] = house;
	}

	/**
	 * Creer la tableau contenant tout les elements de la carte
	 */
	public void createElementInMap() {
		int elementCode = 0;
		for (int indexLine = 0; indexLine < SimulationParameters.MAP_HEIGHT; indexLine++) {
			for (int indexColumn = 0; indexColumn < SimulationParameters.MAP_WIDTH; indexColumn++) {
				elementCode = getRandom(0, 10);
				// 4 null + 3 for�t + 2 maison +1 feu
				switch (elementCode) {
				case 0: {
					for (int indexForest = 0; indexForest < 3; indexForest++) {
						int newLine = indexLine + indexForest;
						int newColumn = indexColumn + indexForest;
						if (newLine < SimulationParameters.MAP_HEIGHT && newColumn < SimulationParameters.MAP_WIDTH) {
							addForest(indexLine + indexForest, indexColumn);
							addForest(indexLine + indexForest, indexColumn);
						}
					}
					indexColumn += 10;
					break;
				}

				case 1: {
					addHouses(indexLine, indexColumn);
					break;
				}
				case 2: {
					addHouses(indexLine, indexColumn);
					break;
				}
				case 3: {
					addFire(indexLine, indexColumn);
					break;
				}
				default:
					elements[indexLine][indexColumn] = null;
				}

			}
		}
	}

	public void mapSmoothing() {
		int indexLine;
		int indexColumn;
		Color elementMap;
		int y;
		int x;
		// maison
		for (indexLine = 0; indexLine < SimulationParameters.MAP_HEIGHT; indexLine++) {
			for (indexColumn = 0; indexColumn < SimulationParameters.MAP_WIDTH; indexColumn++) {
				if (elements[indexLine][indexColumn] != null) {
					elementMap = elements[indexLine][indexColumn].getColor();
					y = elements[indexLine][indexColumn].getPosition().getColumn();
					x = elements[indexLine][indexColumn].getPosition().getLine();
					if (elementMap.equals(GuiData.HOUSE_COLOR)) {
						// if the house is on edge of the map we delete it
						if (x == SimulationParameters.MAP_HEIGHT || y == SimulationParameters.MAP_WIDTH || x == 0
								|| y == 0) {
							housesList.remove(elements[indexLine][indexColumn]);
							elements[indexLine][indexColumn] = null;
						} else {
							// if the house is in middle of tree he became a tree
							int tree = 0;
							if (inRange(indexLine) && inRange(indexColumn)
									&& elements[indexLine + 1][indexColumn].getColor().equals(GuiData.TREE_COLOR)) {
								tree++;
							}
							if (inRange(indexLine) && inRange(indexColumn)
									&& elements[indexLine + 1][indexColumn + 1].getColor().equals(GuiData.TREE_COLOR)) {
								tree++;
							}
							if (inRange(indexLine) && inRange(indexColumn)
									&& elements[indexLine][indexColumn + 1].getColor().equals(GuiData.TREE_COLOR)) {
								tree++;
							}
							if (inRange(indexLine) && inRange(indexColumn)
									&& elements[indexLine + 1][indexColumn - 1].getColor().equals(GuiData.TREE_COLOR)) {
								tree++;
							}
							if (inRange(indexLine) && inRange(indexColumn)
									&& elements[indexLine - 1][indexColumn + 1].getColor().equals(GuiData.TREE_COLOR)) {
								tree++;
							}
							if (inRange(indexLine) && inRange(indexColumn)
									&& elements[indexLine][indexColumn - 1].getColor().equals(GuiData.TREE_COLOR)) {
								tree++;
							}
							if (inRange(indexLine) && inRange(indexColumn)
									&& elements[indexLine - 1][indexColumn].getColor().equals(GuiData.TREE_COLOR)) {
								tree++;
							}
							if (inRange(indexLine) && inRange(indexColumn)
									&& elements[indexLine - 1][indexColumn - 1].getColor().equals(GuiData.TREE_COLOR)) {
								tree++;
							}
							if (tree >= 2) {
								housesList.remove(elements[indexLine][indexColumn]);
								elements[indexLine][indexColumn] = new Tree(new Position(x, y));
								treesList.add(new Tree(new Position(x, y)));
							}
						}
					}
				}
			}
		}
		// case null
		for (indexLine = 0; indexLine < SimulationParameters.MAP_HEIGHT; indexLine++) {
			for (indexColumn = 0; indexColumn < SimulationParameters.MAP_WIDTH; indexColumn++) {
				if (elements[indexLine][indexColumn] == null) {
					// we verify if a cell is null
					int tree = 0;
					if (inRange(indexLine) && inRange(indexColumn)
							&& isNotNull(elements[indexLine - 1][indexColumn + 1])) {
						if (elements[indexLine - 1][indexColumn + 1].getColor().equals(GuiData.TREE_COLOR)) {
							tree++;
						}
					}
					if (inRange(indexLine) && inRange(indexColumn) && isNotNull(elements[indexLine - 1][indexColumn])) {
						if (elements[indexLine - 1][indexColumn].getColor().equals(GuiData.TREE_COLOR)) {
							tree++;
						}
					}
					if (inRange(indexLine) && inRange(indexColumn)
							&& isNotNull(elements[indexLine - 1][indexColumn - 1])) {
						if (elements[indexLine - 1][indexColumn - 1].getColor().equals(GuiData.TREE_COLOR)) {
							tree++;
						}
					}
					if (inRange(indexLine) && inRange(indexColumn) && isNotNull(elements[indexLine][indexColumn + 1])) {
						if (elements[indexLine][indexColumn + 1].getColor().equals(GuiData.TREE_COLOR)) {
							tree++;
						}
					}
					if (inRange(indexLine) && inRange(indexColumn) && isNotNull(elements[indexLine][indexColumn - 1])) {
						if (elements[indexLine][indexColumn - 1].getColor().equals(GuiData.TREE_COLOR)) {
							tree++;
						}
					}
					if (inRange(indexLine) && inRange(indexColumn)
							&& isNotNull(elements[indexLine + 1][indexColumn + 1])) {
						if (elements[indexLine + 1][indexColumn + 1].equals(GuiData.TREE_COLOR)) {
							tree++;
						}
					}
					if (inRange(indexLine) && inRange(indexColumn) && isNotNull(elements[indexLine][indexColumn + 1])) {
						if (elements[indexLine][indexColumn + 1].equals(GuiData.TREE_COLOR)) {
							tree++;
						}
					}
					if (inRange(indexLine) && inRange(indexColumn)
							&& isNotNull(elements[indexLine - 1][indexColumn + 1])) {
						if (elements[indexLine - 1][indexColumn + 1].getColor().equals(GuiData.TREE_COLOR)) {
							tree++;
						}
					}
					if (tree >= 3) {

						elements[indexLine][indexColumn] = new Tree(new Position(indexLine, indexColumn));
						treesList.add(new Tree(new Position(indexLine, indexColumn)));
					}
				}
			}
		}

		// foret
		for (indexLine = 0; indexLine < SimulationParameters.MAP_HEIGHT; indexLine++) {
			for (indexColumn = 0; indexColumn < SimulationParameters.MAP_WIDTH; indexColumn++) {
				if (elements[indexLine][indexColumn] != null) {
					elementMap = elements[indexLine][indexColumn].getColor();
					y = elements[indexLine][indexColumn].getPosition().getColumn();
					x = elements[indexLine][indexColumn].getPosition().getLine();

					if (elementMap.equals(GuiData.TREE_COLOR)) {
						int tree = 0;
						// we verify if tree is not alone we need 3 more tree around
						if (inRange(indexLine) && inRange(indexColumn)
								&& isNotNull(elements[indexLine - 1][indexColumn + 1])) {
							if (elements[indexLine - 1][indexColumn + 1].getColor().equals(GuiData.TREE_COLOR)) {
								tree++;
							}
						}
						if (inRange(indexLine) && inRange(indexColumn)
								&& isNotNull(elements[indexLine - 1][indexColumn])) {
							if (elements[indexLine - 1][indexColumn].getColor().equals(GuiData.TREE_COLOR)) {
								tree++;
							}
						}
						if (inRange(indexLine) && inRange(indexColumn)
								&& isNotNull(elements[indexLine - 1][indexColumn - 1])) {
							if (elements[indexLine - 1][indexColumn - 1].getColor().equals(GuiData.TREE_COLOR)) {
								tree++;
							}
						}
						if (inRange(indexLine) && inRange(indexColumn)
								&& isNotNull(elements[indexLine][indexColumn + 1])) {
							if (elements[indexLine][indexColumn + 1].getColor().equals(GuiData.TREE_COLOR)) {
								tree++;
							}
						}
						if (inRange(indexLine) && inRange(indexColumn)
								&& isNotNull(elements[indexLine][indexColumn - 1])) {
							if (elements[indexLine][indexColumn - 1].getColor().equals(GuiData.TREE_COLOR)) {
								tree++;
							}
						}
						if (inRange(indexLine) && inRange(indexColumn)
								&& isNotNull(elements[indexLine + 1][indexColumn + 1])) {
							if (elements[indexLine + 1][indexColumn + 1].equals(GuiData.TREE_COLOR)) {
								tree++;
							}
						}
						if (inRange(indexLine) && inRange(indexColumn)
								&& isNotNull(elements[indexLine][indexColumn + 1])) {
							if (elements[indexLine][indexColumn + 1].equals(GuiData.TREE_COLOR)) {
								tree++;
							}
						}
						if (inRange(indexLine) && inRange(indexColumn)
								&& isNotNull(elements[indexLine - 1][indexColumn + 1])) {
							if (elements[indexLine - 1][indexColumn + 1].getColor().equals(GuiData.TREE_COLOR)) {
								tree++;
							}
						}
						if (tree < 1) {
							treesList.remove(elements[indexLine][indexColumn]);
							elements[indexLine][indexColumn] = null;
						}
					}

				}
			}
		}

		// feu
		for (indexLine = 0; indexLine < SimulationParameters.MAP_HEIGHT; indexLine++) {
			for (indexColumn = 0; indexColumn < SimulationParameters.MAP_WIDTH; indexColumn++) {
				if (elements[indexLine][indexColumn] != null) {
					elementMap = elements[indexLine][indexColumn].getColor();
					y = elements[indexLine][indexColumn].getPosition().getColumn();
					x = elements[indexLine][indexColumn].getPosition().getLine();
					if (elements[indexLine][indexColumn] != null) {
						if (elementMap.equals(GuiData.FIRE_COLOR)) {
							// we verify if a forest around the fire
							int tree = 0;

							if (inRange(indexLine) && inRange(indexColumn)
									&& isNotNull(elements[indexLine - 1][indexColumn])) {
								if (elements[indexLine - 1][indexColumn].getColor().equals(GuiData.TREE_COLOR)) {
									tree++;
								}
							}

							if (inRange(indexLine) && inRange(indexColumn)
									&& isNotNull(elements[indexLine][indexColumn + 1])) {
								if (elements[indexLine][indexColumn + 1].getColor().equals(GuiData.TREE_COLOR)) {
									tree++;
								}
							}
							if (inRange(indexLine) && inRange(indexColumn)
									&& isNotNull(elements[indexLine][indexColumn - 1])) {
								if (elements[indexLine][indexColumn - 1].getColor().equals(GuiData.TREE_COLOR)) {
									tree++;
								}
							}

							if (inRange(indexLine) && inRange(indexColumn)
									&& isNotNull(elements[indexLine + 1][indexColumn])) {
								if (elements[indexLine + 1][indexColumn].equals(GuiData.TREE_COLOR)) {
									tree++;
								}
							}
							if (tree < 1) {
								firesList.remove(elements[indexLine][indexColumn]);
								elements[indexLine][indexColumn] = null;
							}
						}
					}
				}
			}
		}
		// maison
		for (indexLine = 0; indexLine < SimulationParameters.MAP_HEIGHT; indexLine++) {
			for (indexColumn = 0; indexColumn < SimulationParameters.MAP_WIDTH; indexColumn++) {
				if (elements[indexLine][indexColumn] != null) {
					elementMap = elements[indexLine][indexColumn].getColor();
					y = elements[indexLine][indexColumn].getPosition().getColumn();
					x = elements[indexLine][indexColumn].getPosition().getLine();
					if (elementMap.equals(GuiData.HOUSE_COLOR)) {
						// if the house is on edge of the map we delete it
						if (x == SimulationParameters.MAP_HEIGHT || y == SimulationParameters.MAP_WIDTH || x == 0
								|| y == 0) {
							housesList.remove(elements[indexLine][indexColumn]);
							elements[indexLine][indexColumn] = null;
						} else {
							// if the house is in middle of tree he became a tree
							int tree = 0;
							if (inRange(indexLine) && inRange(indexColumn)
									&& elements[indexLine + 1][indexColumn].getColor().equals(GuiData.TREE_COLOR)) {
								tree++;
							}
							if (inRange(indexLine) && inRange(indexColumn)
									&& elements[indexLine + 1][indexColumn + 1].getColor().equals(GuiData.TREE_COLOR)) {
								tree++;
							}
							if (inRange(indexLine) && inRange(indexColumn)
									&& elements[indexLine][indexColumn + 1].getColor().equals(GuiData.TREE_COLOR)) {
								tree++;
							}
							if (inRange(indexLine) && inRange(indexColumn)
									&& elements[indexLine + 1][indexColumn - 1].getColor().equals(GuiData.TREE_COLOR)) {
								tree++;
							}
							if (inRange(indexLine) && inRange(indexColumn)
									&& elements[indexLine - 1][indexColumn + 1].getColor().equals(GuiData.TREE_COLOR)) {
								tree++;
							}
							if (inRange(indexLine) && inRange(indexColumn)
									&& elements[indexLine][indexColumn - 1].getColor().equals(GuiData.TREE_COLOR)) {
								tree++;
							}
							if (inRange(indexLine) && inRange(indexColumn)
									&& elements[indexLine - 1][indexColumn].getColor().equals(GuiData.TREE_COLOR)) {
								tree++;
							}
							if (inRange(indexLine) && inRange(indexColumn)
									&& elements[indexLine - 1][indexColumn - 1].getColor().equals(GuiData.TREE_COLOR)) {
								tree++;
							}
							if (tree >= 2) {
								housesList.remove(elements[indexLine][indexColumn]);
								elements[indexLine][indexColumn] = null;
							}
						}
					}
				}
			}
		}
	}

	public void newCreation() {
		double[][] noiseMap1 = new double[SimulationParameters.MAP_HEIGHT + 5][SimulationParameters.MAP_WIDTH + 5];
		double[][] noiseMap2 = new double[SimulationParameters.MAP_HEIGHT + 5][SimulationParameters.MAP_WIDTH + 5];

		int[][] finalNoiseMap = new int[SimulationParameters.MAP_HEIGHT][SimulationParameters.MAP_WIDTH];

		// Filling the first noiseMap
		for (int indexLine = 0; indexLine < (SimulationParameters.MAP_HEIGHT + 4); indexLine++) {
			for (int indexColumn = 0; indexColumn < (SimulationParameters.MAP_WIDTH + 4); indexColumn++) {
				// random choice
				int noiseChoice = getRandom(0, 2);
				// value in [0, 0.7, 1]
				double noiseValue;
				switch (noiseChoice) {
				case 0:
					noiseValue = (double) 0;
					break;
				case 1:
					noiseValue = (double) 0.5;
					break;
				default:
					noiseValue = (double) 1;
				}
				noiseMap1[indexLine][indexColumn] = noiseValue;

			}
		}
		// System.out.println(Arrays.deepToString(noiseMap1));

		double fusedValue;

		// First fusing in the other float[][]
		for (int indexLine = 0; indexLine < (SimulationParameters.MAP_HEIGHT + 4); indexLine++) {
			for (int indexColumn = 0; indexColumn < (SimulationParameters.MAP_WIDTH + 4); indexColumn++) {
				fusedValue = 0.24 * noiseMap1[indexLine][indexColumn] + 0.06 * noiseMap1[indexLine + 1][indexColumn]
						+ 0.56 * noiseMap1[indexLine][indexColumn + 1]
						+ 0.14 * noiseMap1[indexLine + 1][indexColumn + 1];
				noiseMap2[indexLine][indexColumn] = fusedValue;

			}
		}

		// Second fusing in the first float[][]
		for (int indexLine = 0; indexLine < (SimulationParameters.MAP_HEIGHT + 4); indexLine++) {
			for (int indexColumn = 0; indexColumn < (SimulationParameters.MAP_WIDTH + 4); indexColumn++) {
				fusedValue = 0.24 * noiseMap2[indexLine][indexColumn] + 0.06 * noiseMap2[indexLine + 1][indexColumn]
						+ 0.56 * noiseMap2[indexLine][indexColumn + 1]
						+ 0.14 * noiseMap2[indexLine + 1][indexColumn + 1];
				noiseMap1[indexLine][indexColumn] = fusedValue;
			}
		}

		// 3rd one, same as first
		for (int indexLine = 0; indexLine < (SimulationParameters.MAP_HEIGHT + 4); indexLine++) {
			for (int indexColumn = 0; indexColumn < (SimulationParameters.MAP_WIDTH + 4); indexColumn++) {
				fusedValue = 0.24 * noiseMap1[indexLine][indexColumn] + 0.06 * noiseMap1[indexLine + 1][indexColumn]
						+ 0.56 * noiseMap1[indexLine][indexColumn + 1]
						+ 0.14 * noiseMap1[indexLine + 1][indexColumn + 1];
				noiseMap2[indexLine][indexColumn] = fusedValue;

			}
		}

		// last one, same as second
		for (int indexLine = 0; indexLine < (SimulationParameters.MAP_HEIGHT + 4); indexLine++) {
			for (int indexColumn = 0; indexColumn < (SimulationParameters.MAP_WIDTH + 4); indexColumn++) {
				fusedValue = 0.24 * noiseMap2[indexLine][indexColumn] + 0.06 * noiseMap2[indexLine + 1][indexColumn]
						+ 0.56 * noiseMap2[indexLine][indexColumn + 1]
						+ 0.14 * noiseMap2[indexLine + 1][indexColumn + 1];
				noiseMap1[indexLine][indexColumn] = fusedValue;
			}
		}

		// We now declare our threshold for the quantification
		double threshold = 0.5;
		int integerValue;

		for (int indexLine = 0; indexLine < SimulationParameters.MAP_HEIGHT; indexLine++) {
			for (int indexColumn = 0; indexColumn < SimulationParameters.MAP_WIDTH; indexColumn++) {
				if (noiseMap1[indexLine][indexColumn] > threshold) {
					integerValue = 1;
				} else {
					integerValue = 0;
				}

				if (integerValue == 0) {
					if ((getRandom(0, 4) == 0)) {
						integerValue = 2;
					}
				} else {
					if (integerValue == 1) {
						if (getRandom(0, 19) == 0) {
							integerValue = 3;
						}
					}
				}

				finalNoiseMap[indexLine][indexColumn] = integerValue;

				// System.out.println(Arrays.deepToString(finalNoiseMap));
			}
		}

		for (int indexLine = 0; indexLine < SimulationParameters.MAP_HEIGHT; indexLine++) {
			for (int indexColumn = 0; indexColumn < SimulationParameters.MAP_WIDTH; indexColumn++) {

						if(finalNoiseMap[indexLine][indexColumn] == 0) {
							addNormal(indexLine, indexColumn);
							
						} 
						else if(finalNoiseMap[indexLine][indexColumn] == 1) {
							addForest(indexLine, indexColumn);
						}
						else if (finalNoiseMap[indexLine][indexColumn]== 2) {
							addHouses(indexLine, indexColumn);
						}
						else if(finalNoiseMap[indexLine][indexColumn]==3) {
							addFire(indexLine, indexColumn);
						}
						
			}
		}
		// System.out.println(Arrays.deepToString(finalNoiseMap));
	}

	public int[][] getFreeSquares() {
		return freeSquares;
	}

	public void setFreeSquares(int[][] freeSquares) {
		this.freeSquares = freeSquares;
	}

	public int[][] getSquaresWhithCode() {
		return SquaresWhithCode;
	}

	public void setSquaresWhithCode(int[][] squaresWhithCode) {
		SquaresWhithCode = squaresWhithCode;
	}

	public ArrayList<Tree> getTrees() {
		return treesList;
	}

	public void setTrees(ArrayList<Tree> trees) {
		this.treesList = trees;
	}

	public ArrayList<Fire> getFire() {
		return firesList;
	}

	public void setFire(ArrayList<Fire> fire) {
		this.firesList = fire;
	}

	public ArrayList<House> getHouse() {
		return housesList;
	}

	public void setHouse(ArrayList<House> house) {
		this.housesList = house;
	}

	public Map getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map resultMap) {
		this.resultMap = resultMap;
	}

	private int getRandom(int min, int max) {
		return (int) (Math.random() * (max + 1 - min)) + min;
	}

	private boolean inRange(int position) {
		if (position + 1 < SimulationParameters.MAP_WIDTH && position - 1 > 0) {
			return true;
		}
		return false;
	}

	private boolean isNotNull(Element elt) {
		if (elt != null) {
			return true;
		}
		return false;
	}
}
