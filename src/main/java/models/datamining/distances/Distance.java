package models.datamining.distances;

import java.util.Map;

public interface Distance {
    double calculate(Map<String, Double> map1, Map<String, Double> map2);
}
