package process;

import data.Drone;
import data.Fire;
import data.House;
import data.Position;
import data.Tree;

public class ObjectFactory {

	public ObjectFactory(){
		
	}
	
	public Drone createDrone(int line, int column) {
		Position position=new Position(line,column);
		return new Drone(position); 
	}
	
	public Fire createFire(int line,int column) {
		Position position=new Position(line,column);
		return new Fire(position);
	}
	
	public Tree createForest(int line,int column) {
		Position position=new Position(line,column);
		return new Tree(position);
	}
	
	public House createHouse(int line,int column) {
		Position position=new Position(line,column);
		return new House(position);
	}
}
