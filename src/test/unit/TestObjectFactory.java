package test.unit;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.Test;

import data.Drone;
import data.Fire;
import data.House;
import data.Position;
import data.Tree;
import process.ObjectFactory;


/**
 * This class allows the test of the class ObjectFactory
 * 
 * @author Edson De Carvalho
 *
 */
public class TestObjectFactory {
	private ObjectFactory objectFactory=new ObjectFactory();
	@Test
	public void testCreatDrone()  {
		Position position=new Position(1,1);
		Tree tree=new Tree(position);
		assertTrue(tree instanceof Tree);
	}
	@Test
	public void testCreateFire() {
		Position position=new Position(1,1);
		Fire fire=new Fire(position);
		assertTrue(fire instanceof Fire);
	}

	@Test
	public void testCreateHouse()  {
		Position position=new Position(1,1);
		House house=new House(position);	
		assertTrue(house instanceof House);
	}
	
	public void testCreateTree() {
		Position position=new Position(1,1);
		Tree tree=new Tree(position);
		assertTrue(tree instanceof Tree);
	}
}
