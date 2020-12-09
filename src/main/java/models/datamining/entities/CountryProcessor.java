package models.datamining.entities;

import models.csv.entities.CountryItem;
import models.datamining.entities.mappers.CountryMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountryProcessor {
    public CountryProcessor () {}

    public List<Country> getProcessedCountries(List<CountryItem> countries) {
        Map<String, CountryItem> processedCountriesItems = buildProcessedMap(countries);
        List<Country> finalCountries = new CountryMapper().buildFinalCountryList(processedCountriesItems);
        finalCountries.forEach(System.out::println);

        return finalCountries;
    }

    private Map<String, CountryItem> buildProcessedMap (List<CountryItem> countries) {
        Map<String, CountryItem> countriesList = new HashMap<>();

        countries.forEach( x -> {
            String thisName = x.getCountryRegion();
            if (!countriesList.containsKey(thisName)) {
                countriesList.put(thisName, x);
            }
            else {
                countriesList.replace(thisName, calculate(x, countriesList.get(thisName)));
            }
        });

        return countriesList;
    }

    private CountryItem calculate (CountryItem first, CountryItem second) {
        CountryItem country = new CountryItem();

        country.setCountryRegion(first.getCountryRegion());
        country.setActive(first.getActive() + second.getActive());
        country.setConfirmed(first.getConfirmed() + second.getConfirmed());
        country.setDeaths(first.getDeaths() + second.getDeaths());
        country.setRecovered(first.getRecovered() + second.getRecovered());
        country.setIncidentRate((first.getIncidentRate() + second.getIncidentRate()) / 2);
        country.setCaseFatalityRatio((first.getCaseFatalityRatio() + second.getCaseFatalityRatio()) /2 );

        return country;
    }
}
