package model.gameelements.order;

import model.gameelements.Country;
import model.gameelements.Player;

/**
 * The class to represent a Deploy order, which inherits from class of type Order .
 */
public class DeployOrder extends Order {

    /**
     * Instantiates a new object of type DeployOrder.
     */
    public DeployOrder() {
        super();
        setType("Deploy");
    }

    /**
     * Executes an deploy order.
     *
     * @return if the order executes successfully, false otherwise
     */
    @Override
    public boolean execute() {

        if (!valid()) {
            return false;
        }

        Player l_Player = getOrderInfo().getPlayer();
        String l_Destination = getOrderInfo().getDestination();
        int l_ArmiesToDeploy = getOrderInfo().getNumberOfArmy();
        l_ArmiesToDeploy = Math.min(l_ArmiesToDeploy, l_Player.getReinforcementArmies());

        // deploy the armies, if there not enough armies left, deploy all the armies.
        Country l_Country = l_Player.getCountriesInControl().get(l_Destination.toLowerCase());
        l_Country.deployArmies(l_ArmiesToDeploy);
        if (!l_Player.deployReinforcementArmies(getOrderInfo().getNumberOfArmy())) {
            System.out.println("\nWarning: insufficient armies to deploy. Deploy only " + l_ArmiesToDeploy + " armies to Country " + l_Country.getName() + ".");
        } else {
            System.out.println("\n\"DEPLOY\" Execution is completed: deploy " + l_ArmiesToDeploy + " armies to Country " + l_Country.getName() + ".");
        }

        return true;
    }

    @Override
    public boolean valid() {
        if (getOrderInfo().getPlayer() == null || getOrderInfo().getDestination() == null) {
            System.out.println("\nFail to execute \"DEPLOY\" order: Invalid order information.");
            return false;
        }

        Player l_Player = getOrderInfo().getPlayer();
        String l_Destination = getOrderInfo().getDestination();
        int l_ArmiesToDeploy = getOrderInfo().getNumberOfArmy();
        l_ArmiesToDeploy = Math.min(l_ArmiesToDeploy, l_Player.getReinforcementArmies());

        // If the player decides to deploy armies to the country he/she doesn't control, the player will lost the armies.
        if (!l_Player.getCountriesInControl().containsKey(l_Destination.toLowerCase())) {
            l_Player.setReinforcementArmies(l_Player.getReinforcementArmies() - l_ArmiesToDeploy);
            System.out.println("\nFail to execute \"DEPLOY\" order: the country " + l_Destination + " is not in the control of player " + l_Player.getColour() + ".");
            System.out.println("The player " + l_Player.getColour() + " will lose " + l_ArmiesToDeploy + " armies.");
            return false;
        }

        return true;
    }
}
