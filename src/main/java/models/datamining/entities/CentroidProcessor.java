package models.datamining.entities;

import models.csv.entities.CentroidItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CentroidProcessor {

    public List<Centroid> buildFinalList (List<CentroidItem> centroidItemList) {
        List<Centroid> centroidList = new ArrayList<>();

        centroidItemList.forEach( x -> {
            centroidList.add(itemMapping(x));
        });

        return centroidList;
    }

    private Centroid itemMapping (CentroidItem item) {
        Map<String, Double> features = new HashMap<>();
        features.put("Confirmed", item.getConfirmed());
        features.put("Deaths", item.getDeaths());
        features.put("Recovered", item.getRecovered());
        features.put("Active", item.getActive());
        features.put("Incident_Rate", item.getIncidentRate());
        features.put("Case_Fatality_Ratio", item.getCaseFatalityRatio());

        return new Centroid(features);
    }
}
