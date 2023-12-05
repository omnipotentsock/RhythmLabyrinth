package AdventureModel.Interpretations;

import jtuples.Pair;

public interface Interpretation {
    final String performanceEnding = "PERFORMANCE_ENDING";
    final String satisfactoryEnding = "SATISFACTORY_ENDING";
    final String mediocreEnding = "MEDIOCRE_ENDING";

    /**
     * This method, in a Mini-game context, interprets the result of its execute method
     * and interprets this into a standardized data format that the Progression class can
     * update itself with.
     * @return a standard data format, a tuple of a String and a Double. The
     *         String represents the Ending ID and the Double represents the
     *         impact.
     */
    public Pair<String, Double> interpret();
}
