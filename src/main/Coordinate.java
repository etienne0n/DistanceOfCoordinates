package main;

/**
 * 
 * PRECONDITION:<br><br>
 * First Value is latitude:<br> 
 * must be >= -90 and <= 90 <br><br>
 * Second Value is longnitude:<br>
 * must be >= -180 and <= 180 <br><br>
 * 
 * Every entry out of this bounds will be normalized:<br><br>
 * For example the pair of values (91, 181) will be normalized to (90,180)<br>
 * the pair of values (91, -181) will be normalized to (90, -180)
 * 
 * 
 * 
 * @author Etienne Onasch
 * 
 * 
 */
public class Coordinate {
	
	// CONSTANTS
	
	private static final double RADIUS_EARTH_IN_KM = 6371.0;
	//private static final double KM_PER_NM = 1.852;
	
	private static final double MIN_LAT = -90.0;
	private static final double MAX_LAT = 90.0;
	
	private static final double MIN_LON = -180.0;
	private static final double MAX_LON = 180.0;
	

	/**
	 * Latitude measures how far north or south of the equator a place is located.
	 * The equator is situated at 0°, the North Pole at 90° north (or 90°, because a
	 * positive latitude implies north), and the South Pole at 90° south (or –90°).
	 * Latitude measurements range from 0° to (+/–)90°.
	 * 
	 * 
	 */
	private final double latitude;
	
	/**
	 * Longitude measures how far east or west of the prime meridian a place is
	 * located. The prime meridian runs through Greenwich, England. Longitude
	 * measurements range from 0° to (+/–)180°.
	 */
	private final double longnitude;

	public Coordinate(double latitude, double longnitude) {
		this.latitude = latitude < MIN_LAT ? MIN_LAT : latitude > MAX_LAT ? MAX_LAT : latitude;
		this.longnitude = longnitude < MIN_LON ? MIN_LON : longnitude > MAX_LON ? MAX_LON : longnitude;
	}

	/**
	 * Entfernung (in km) zwischen zwei Punkten auf der Erdoberfläche:<br><br>
	 * 
	 * Entf_km = 6371 * acos(sin(lat1) * sin(lat2) + cos(lat1) * cos(lat2) * cos(lon2 - lon1))
	 * 
	 * Die Gleichung liefert die Länge des Großkeisbogens zwischen zwei Punkten
	 * (lat1, lon1) und (lat2, lon2) auf einer Kugel mit einem Radius von 6371
	 * Kilometern. Da die Erde keine perfekte Kugel ist (6371 km ist der mittlere
	 * Radius), stellt die Berechnung eine Näherung dar, die vor allem für größere
	 * Distanzen geeignet ist. Möchte man die Seemeile (= 1,852 km) als
	 * fundamentales Abstandsmaß für das Geosystem zugrundelegen (der geliefert Wert
	 * ist nach wie vor km, aber der Erdumfang wird als das 60*360-fache einer
	 * Seemeile definiert), ersetzt man 6371 durch
	 * 
	 * 1.852 * 60 * 180/PI
	 * 
	 * Die Formel geht davon aus, dass die trigonometrischen Funktionen acos(),
	 * sin(), cos() im Bogenmaß (rad) rechnen, was für Programmiersprachen
	 * typischerweise der Fall ist. Liegen lat1, lon1, lat2, lon2 in Grad vor, was
	 * bei Geopositionen üblich ist, müssen diese vor der Einsetzung ins Bogenmaß
	 * umgerechnet, also mit Pi/180 multipliziert werden.
	 * 
	 * rad = degree * PI / 180
	 * 
	 * 
	 * @param other die Andere Koordinate
	 * @return die Entfernung zwischen beiden Koordinaten in KM
	 */
	public double getDistanceInKM(Coordinate other) {
		
		double lon1 = degreeToRad(longnitude);
		double lat1 = degreeToRad(latitude);
		
		double lon2 = degreeToRad(other.getLongnitude());
		double lat2 = degreeToRad(other.getLatitude());

		return RADIUS_EARTH_IN_KM * Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon2 - lon1));
	}
	/**
	 * Rechnet Grad in rad um.
	 * @param degree die Gradzahl
	 * @return die Gradzahl umgerechnet in rad
	 */
	private double degreeToRad(double degree) {
		return degree * Math.PI / 180.0;
	}
	/**
	 * Gibt den Laengengrade dieser Koordinate zurueck
	 * @return longnitude bzw. Laengengrad
	 */
	public double getLongnitude() {
		return longnitude;
	}
	/**
	 * Gibt den Breitengrad dieser Koordinate zurueck
	 * @return latitude bzw. Breitengrad
	 */
	public double getLatitude() {
		return latitude;
	}

}
