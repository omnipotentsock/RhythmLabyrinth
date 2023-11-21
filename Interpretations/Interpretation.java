package Interpretations;

import jtuples.Pair;

public interface Interpretation {
    final String performanceEnding = "PERFORMANCE_ENDING";
    final String satisfactoryEnding = "SATISFACTORY_ENDING";
    final String mediocreEnding = "MEDIOCRE_ENDING";
    public Pair<String, Double> interpret();
}
