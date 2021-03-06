package mapTest;

import model.map.MapListing;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

/**
 * This class is for testing MapListing
 *
 * @author Zitao Wang
 * @version 1.0.0
 */

public class MapListingTest {

    /**
     * This method is test case1 for the country connection in the model.map file.
     */
    @Test
    public void testGetCountryList() {

        File l_file = new File("domination//test_02.map");
        MapListing l_case = new MapListing();
        ArrayList<String> l_List;
        l_List = l_case.getCountryList(l_file);
        int l_A = l_List.size();
        assertEquals(4, l_A);
    }

    /**
     * This method is test case1 for the continent connection in the model.map file.
     */
    @Test
    public void testGetContinentList() {
        File l_file = new File("domination//test_02.map");
        MapListing l_case = new MapListing();
        ArrayList<String> l_List;
        l_List = l_case.getContinentList(l_file);
        int l_A = l_List.size();
        assertEquals(2, l_A);
    }

    /**
     * This method is test case1 for the correspondence between countries and continents in the model.map file.
     */
    @Test
    public void testGetCountryContinent() {
        File l_file = new File("domination//test_02.map");
        MapListing l_case = new MapListing();
        HashMap<String, String> l_CountryContinent;
        l_CountryContinent = l_case.getCountryContinent(l_file);
        int l_A = l_CountryContinent.size();
        assertEquals(4, l_A);
    }

    /**
     * This method is test case1 for the country's neighbour list in the model.map file.
     */
    @Test
    public void testGetNeighbour() {
        File l_file = new File("domination//test_02.map");
        MapListing l_case = new MapListing();
        ArrayList<String> l_List;
        l_List = l_case.getNeighbour(l_file, "China");
        int l_A = l_List.size();
        assertEquals(4, l_A);
    }
}
