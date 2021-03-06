package model.gameelements.strategy;

import model.GameData;
import model.gameelements.Card;
import model.gameelements.CardOrderCreator;
import model.gameelements.Country;
import model.gameelements.Player;
import model.gameelements.order.Order;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * The type Player strategy.
 */
public abstract class PlayerStrategy implements Serializable {

    /**
     * The game data.
     */
    private GameData d_GameData;
    /**
     * The player.
     */
    private Player d_Player;
    /**
     * The random.
     */
    private Random d_Random;

    /**
     * Instantiates a new Player strategy.
     *
     * @param p_Player   the player
     * @param p_GameData the game data
     */
    public PlayerStrategy(Player p_Player, GameData p_GameData) {
        d_Player = p_Player;
        d_GameData = p_GameData;
        d_Random = new Random();
    }

    /**
     * Gets game data.
     *
     * @return the game data
     */
    public GameData getGameData() {
        return d_GameData;
    }

    /**
     * Sets game data.
     *
     * @param p_GameData the game data
     */
    public void setGameData(GameData p_GameData) {
        this.d_GameData = p_GameData;
    }

    /**
     * Gets player.
     *
     * @return the player
     */
    public Player getPlayer() {
        return d_Player;
    }

    /**
     * Sets player.
     *
     * @param p_Player the player
     */
    public void setPlayer(Player p_Player) {
        this.d_Player = p_Player;
    }

    /**
     * Gets random.
     *
     * @return the random
     */
    public Random getRandom() {
        return d_Random;
    }

    /**
     * Create order order.
     *
     * @return the order
     */
    public abstract Order createOrder();

    /**
     * Reset strategy states.
     */
    public abstract void reset();

    /**
     * To attack an random country.
     *
     * @return the country
     */
    protected Country toAttack() {
        List<Country> l_Countries = getGameData().getCountryList();
        Country l_CountryToAttack;
        do {
            l_CountryToAttack = l_Countries.get(d_Random.nextInt(l_Countries.size()));
        } while (l_CountryToAttack.getOwner().getColour().equalsIgnoreCase(getPlayer().getColour()));

        return l_CountryToAttack;
    }

    /**
     * Attack from country.
     *
     * @return the country
     */
    protected Country attackFrom() {
        return null;
    }

    /**
     * Move from country.
     *
     * @return the country
     */
    protected Country moveFrom() {
        if (getPlayer().getCountriesInControl().size() == 0) {
            return null;
        }
        Random l_Rand = new Random();
        List<Country> l_Countries = new ArrayList<>(getPlayer().getCountriesInControl().values());

        return l_Countries.get(l_Rand.nextInt(l_Countries.size()));
    }

    /**
     * To defend country.
     *
     * @return the country
     */
    protected Country toDefend() {
        return null;
    }

    /**
     * Gets random neighboring opponent of country.
     *
     * @param p_Departure the p departure
     * @return the random neighboring opponent of country
     */
    protected Country getRandomNeighborOpponentOfCountry(Country p_Departure) {
        List<Country> l_Neighbors = new ArrayList<>(p_Departure.getBorderCountries().values());
        if (l_Neighbors.isEmpty()) {
            return null;
        }

        Set<String> l_CountriesInControl = getPlayer().getCountriesInControl().keySet();
        List<Country> l_Options = new ArrayList<>();
        for (Country l_Neighbor :
                l_Neighbors) {
            if (!l_CountriesInControl.contains(l_Neighbor.getName())) {
                l_Options.add(l_Neighbor);
            }
        }

        return l_Options.size() > 0 ? l_Options.get(d_Random.nextInt(l_Options.size())) : null;
    }

    /**
     * Gets random neighbor of country.
     *
     * @param p_Departure the p departure
     * @return the random neighbor of country
     */
    protected Country getRandomNeighborOfCountry(Country p_Departure) {
        List<Country> l_Neighbors = new ArrayList<>(p_Departure.getBorderCountries().values());
        if (l_Neighbors.isEmpty()) {
            return null;
        }

        Set<String> l_CountriesInControl = getPlayer().getCountriesInControl().keySet();
        List<Country> l_Options = new ArrayList<>();
        for (Country l_Neighbor :
                l_Neighbors) {
            if (l_CountriesInControl.contains(l_Neighbor.getName())) {
                l_Options.add(l_Neighbor);
            }
        }

        return l_Options.size() > 0 ? l_Options.get(d_Random.nextInt(l_Options.size())) : null;
    }

    /**
     * Gets random neighbor.
     *
     * @return the random neighbor
     */
    protected Country getRandomNeighbor() {
        List<Country> l_CountriesInControl = new ArrayList<>(d_Player.getCountriesInControl().values());
        List<Country> l_RandomNeighbors = new ArrayList<>(l_CountriesInControl.get(d_Random.nextInt(l_CountriesInControl.size())).getBorderCountries().values());
        l_RandomNeighbors.removeAll(l_CountriesInControl);
        if (l_RandomNeighbors.size() == 0) {
            return null;
        }
        return l_RandomNeighbors.get(d_Random.nextInt(l_RandomNeighbors.size()));
    }

    /**
     * Gets a random opponent player.
     *
     * @return the opponent player
     */
    protected Player getRandomOpponentPlayer() {
        List<Player> l_Players = d_GameData.getPlayerList();
        Player l_Target;

        do {
            l_Target = l_Players.get(d_Random.nextInt(l_Players.size()));
        } while (l_Target.getId() == d_Player.getId());

        return l_Target;
    }

    /**
     * Create random Card order.
     *
     * @param l_MoveFrom the l move from
     * @param p_MoveTo   the p move to
     * @return the Card order
     */
    protected Order createRandomCardOrder(Country l_MoveFrom, Country p_MoveTo) {
        Card l_Card = getPlayer().getCards().remove(0);
        if (l_MoveFrom == null) {
            return null;
        }

        int l_ArmiesToMove = l_MoveFrom.getArmies() - l_MoveFrom.getCommittedArmies();
        return CardOrderCreator.createCardOrder(l_Card, getPlayer(), getRandomOpponentPlayer(), l_MoveFrom, p_MoveTo, getRandomNeighbor(), l_ArmiesToMove);
    }

}
