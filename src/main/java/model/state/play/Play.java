package model.state.play;

import controller.MainPlayController;
import model.state.Phase;

import java.io.Serializable;

/**
 * ConcreteState of the State pattern. In this example, defines behavior
 * for commands that are valid in this state, and for the others signifies
 * that the command is invalid.
 * <p>
 * This state represents a group of states, and defines the behavior
 * that is common to all the states in its group. All the states in its
 * group need to extend this class.
 */
public abstract class Play extends Phase implements Serializable {

    /**
     * Instantiates a new Play.
     *
     * @param p_Ml the p ml
     */
    Play(MainPlayController p_Ml) {
        super(p_Ml);
    }

    /**
     * Shows information of the map.
     */
    @Override
    public void showMap() {
        d_Ml.showMap();
    }

}
