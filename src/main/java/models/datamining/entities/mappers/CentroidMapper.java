package models.datamining.entities.mappers;

import models.csv.entities.CentroidItem;
import models.datamining.entities.Centroid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CentroidMapper {
    public List<Centroid> buildFinalCentroidList(List<CentroidItem> centroidItemList) {
        List<Centroid> centroidList = new ArrayList<>();

        centroidItemList.forEach( x -> {
            centroidList.add(mapItemToCentroid(x));
        });

        return centroidList;
    }

    private Centroid mapItemToCentroid(CentroidItem item) {
        Map<String, Double> features = new HashMap<>();
        features.put("Confirmed", item.getConfirmed());
        features.put("Deaths", item.getDeaths());
        features.put("Recovered", item.getRecovered());
        features.put("Active", item.getActive());
        features.put("Incident_Rate", item.getIncidentRate());
        features.put("Case_Fatality_Ratio", item.getCaseFatalityRatio());

        return new Centroid(features);
    }

    public List<CentroidItem> buildFinalCentroidItemList(List<Centroid> centroidList) {
        List<CentroidItem> centroidItemList = new ArrayList<>();

        centroidList.forEach( x -> {
            centroidItemList.add(mapCentroidToItem(x));
        });

        return centroidItemList;
    }

    public CentroidItem mapCentroidToItem (Centroid centroid) {
        Map<String, Double> features = centroid.getCoordinates();
        Double confirmed = features.get("Confirmed");
        Double deaths = features.get("Deaths");
        Double recovered = features.get("Recovered");
        Double active = features.get("Active");
        Double incident_Rate = features.get("Incident_Rate");
        Double case_Fatality_Ratio = features.get("Case_Fatality_Ratio");

        return new CentroidItem(confirmed, deaths, recovered, active, incident_Rate, case_Fatality_Ratio);
    }
}
