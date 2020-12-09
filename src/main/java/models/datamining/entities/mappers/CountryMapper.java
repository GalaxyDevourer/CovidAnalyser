package models.datamining.entities.mappers;

import models.csv.entities.CountryItem;
import models.datamining.entities.Country;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountryMapper {
    public List<Country> buildFinalCountryList (Map<String, CountryItem> countryItemMap) {
        List<Country> countryList = new ArrayList<>();

        countryItemMap.forEach( (x,y) -> {
            countryList.add(mapItemToCountry(y));
        });

        return countryList;
    }

    private Country mapItemToCountry (CountryItem item) {
        Map<String, Double> features = new HashMap<>();
        features.put("Confirmed", item.getConfirmed());
        features.put("Deaths", item.getDeaths());
        features.put("Recovered", item.getRecovered());
        features.put("Active", item.getActive());
        features.put("Incident_Rate", item.getIncidentRate());
        features.put("Case_Fatality_Ratio", item.getCaseFatalityRatio());

        return new Country(item.getCountryRegion(), features);
    }

    public List<CountryItem> buildFinalCountryItemList (List<Country> countryList) {
        List<CountryItem> countryItemList = new ArrayList<>();

        countryList.forEach( (x) -> {
            countryItemList.add(mapCountryToItem(x));
        });

        return countryItemList;
    }

    private CountryItem mapCountryToItem (Country country) {
        Map<String, Double> features = country.getFeatures();
        String country_region = country.getCountry();
        Double confirmed = features.get("Confirmed");
        Double deaths = features.get("Deaths");
        Double recovered = features.get("Recovered");
        Double active = features.get("Active");
        Double incident_Rate = features.get("Incident_Rate");
        Double case_Fatality_Ratio = features.get("Case_Fatality_Ratio");

        return new CountryItem(country_region, confirmed, deaths, recovered, active, incident_Rate, case_Fatality_Ratio);
    }
}
