package AdventureModel.Outcomes;

import java.lang.Double;

import AdventureModel.Endings.Ending;
import AdventureModel.Endings.MediocreEnding;
import AdventureModel.Endings.PerformanceEnding;
import AdventureModel.Endings.SatisfactoryEnding;
import AdventureModel.jtuples.*;

import java.util.*;

public class Progression {
    /**
     * This list consists of all possible AdventureModel.Endings in the game.
     */
    private List<Ending> endings = new ArrayList<Ending>(Arrays.asList(new MediocreEnding(), new SatisfactoryEnding(), new PerformanceEnding()));
    /**
     * This Hashmap maps Ending IDs to the Player's progress along each Ending
     * (measured in Double), and will keep record of the Player's progress
     * throughout the game.
     */
    private HashMap<String, Double> development = new HashMap<>();
    {
        for (Ending ending : endings) {
            development.put(ending.getID(), 0D);
        }
    }

    /**
     * Given a standardized data format (as outputted by Interpretation.interpret() object),
     * this method updates the development attribute by adding together the impact values.
     * @param data is a standard data format, a tuple of a String and a Double. The
     *             String represents the Ending ID and the Double represents the
     *             impact.
     */
    public void update(Pair<String, Double> data) {
        for (Map.Entry<String, Double> entry : this.development.entrySet()) {
            // If the two EndingIDs are the same
            if (data.getValue0().equals(entry.getKey())) {
                Double currentImpact = entry.getValue();
                currentImpact += data.getValue1();
                this.development.put(entry.getKey(), currentImpact);
                break;
            }
        }
    }

    /**
     * This method returns the ending which the Player has progressed the furthest
     * along, as measured by the impact (Double) value.
     * @return Ending object
     */
    public Ending getEnding() {
        String endingID = "";
        Double maxImpact = 0D;
        for (Map.Entry<String, Double> entry : this.development.entrySet()) {
            if (entry.getValue() > maxImpact) {
                maxImpact = entry.getValue();
                endingID = entry.getKey();
            }
        }
        for (Ending ending : this.endings) {
            if (endingID.equals(ending.getID())) {
                return ending;
            }
        }
        return null;
    }
}
