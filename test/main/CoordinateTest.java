package main;

import static org.junit.Assert.*;

import org.junit.Test;

public class CoordinateTest {

	@Test
	public void testGetDistanceInKM() {
		
		// FAR DISTANCE 1
		
		Coordinate c1 = new Coordinate(-25.0, 160.0);
		Coordinate c2 = new Coordinate(25.0, -160.0);

		// Distance according to
		// https://gps-coordinates.org/distance-between-coordinates.php
		// = 7029.37 KM
		
		double dist = 7029.37;
		//System.out.println(c1.getDistanceInKM(c2));
		
		double calculated = c1.getDistanceInKM(c2);
		// Weniger als 1 % Abweichung
		assertTrue(calculated > (dist * 0.99) && calculated < (dist * 1.01));
		
		// FAR DISTANCE 2
		
		c1 = new Coordinate(-90.0, 0.0);
		c2 = new Coordinate(90.0, 0.0);
		calculated = c1.getDistanceInKM(c2);
		dist = 20015.09;
		// Weniger als 1 % Abweichung
		assertTrue(calculated > (dist * 0.99) && calculated < (dist * 1.01));
		
		// Close Distance 1
		c1 = new Coordinate(33.01, 120.0);
		c2 = new Coordinate(33.02, 120.0);
		calculated = c1.getDistanceInKM(c2);
		dist = 1.11;
		// Weniger als 1 % Abweichung
		assertTrue(calculated > (dist * 0.99) && calculated < (dist * 1.01));

	}

}
