package model.gameelements.order;

import model.gameelements.Card;
import model.gameelements.Country;
import model.gameelements.Player;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for BlockadeOrder class
 */
public class BlockadeOrderTest {
	/** player */
	Player d_player;
	/** country */
	Country d_country;
	/** blockade order */
	BlockadeOrder d_order;
	
	/**
	 * This method can set up game context before test cases begin.
	 */
	@Before
	public void setup() {
		d_player=new Player("player1");
		d_player.getCards();
		d_country=new Country("country1");
		d_order=new BlockadeOrder(d_player, 0);
	}
	
	/**
	 * This method tests the valid method of BlockadeOrder class
	 */
	@Test
	public void testValid() {
	}
}