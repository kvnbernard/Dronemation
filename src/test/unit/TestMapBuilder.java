package test.unit;



import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import data.Fire;
import data.Map;
import process.MapBuiler;



/**
 * This class allows the test of the class MapBuilder
 * 
 * @author Edson De Carvalho
 *
 */
public class TestMapBuilder {

	@Test
	public void creationMapBuilder(){
		MapBuiler mapBuilder=new MapBuiler();
		assertTrue(mapBuilder instanceof MapBuiler);
	}
	
	public void creationOfMap() {
		MapBuiler mapBuilder=new MapBuiler();
		assertTrue(mapBuilder.getMap() instanceof Map);
	}
	
	public void elemtecreation() {
		MapBuiler mapBuilder=new MapBuiler();
		mapBuilder.getMap();
		assertTrue(mapBuilder.getFire().get(1) instanceof Fire);
	}
	
}
