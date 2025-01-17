package models.datamining.distances;

import java.util.Map;

public class ManhattanDistance implements Distance {

    @Override
    public double calculate(Map<String, Double> map1, Map<String, Double> map2) {
        double sum = 0;
        for (String key : map1.keySet()) {

            Double x1 = map1.get(key);
            Double x2 = map2.get(key);

            sum += Math.abs(x1-x2);
        }

        return sum;
    }

}
