package test.unit;

import static org.junit.Assert.*;

import org.junit.Test;

import data.Map;
import data.Position;
import process.ElementManager;
import process.MapBuiler;

public class TestElementMAnager {
	
	private ElementManager elemntManager =new ElementManager();

	@Test
	public void testDeplacementGouche() {
		MapBuiler mapbuilder=new MapBuiler();
		Map map= mapbuilder.getMap();
		Position position=new Position(0,0);
		map.getDrone().setPosition(position);
		elemntManager.moveRightDrone(map);
		//assertEquals(map.getDrone().getPosition().getColumn(), 21);
	}
	public void testDeplacementDroit() {
		MapBuiler mapbuilder=new MapBuiler();
		Map map= mapbuilder.getMap();
		Position position=new Position(0,0);
		map.getDrone().setPosition(position);
		elemntManager.moveLeftDrone(map);
		assertEquals(map.getDrone().getPosition().getLine(), 21);
	}
	
	

}
