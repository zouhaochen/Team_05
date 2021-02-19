package gameelements;


/**
 * This is a class to represent the concept of an area on a map.
 * This serves as a parent class of Continent and Country.
 */
public class Territory {

	private String d_name;

	/**
	 * Instantiates a new Territory.
	 *
	 * @param p_name the name of the territory
	 */
	public Territory(String p_name) {
		d_name = p_name;
	}

	/**
	 * Gets name.
	 *
	 * @return the name of the territory
	 */
	public String getName() {
		return d_name;
	}

	/**
	 * Sets name.
	 *
	 * @param p_name the name of the territory
	 */
	public void setName(String p_name) {
		d_name = p_name;
	}
}