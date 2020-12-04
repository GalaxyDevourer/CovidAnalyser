package models.datamining.entities;

import models.csv.entities.CountryItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountryProcessor {

    private List<CountryItem> countries;
    private Map<String, CountryItem> processedCountriesItems = new HashMap<>();
    private List<Country> finalCountries = new ArrayList<>();

    public CountryProcessor(List<CountryItem> countries) {
        this.countries = countries;
    }

    public void start () {
        processedCountriesItems = buildProcessedMap();
        //System.out.println("Countries: " + processedCountriesItems.size());
        //processedCountriesItems.forEach((x,y) -> System.out.println(y));
        finalCountries = buildFinalList();
        finalCountries.forEach(System.out::println);
    }

    private Map<String, CountryItem> buildProcessedMap () {
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

    private List<Country> buildFinalList () {
        List<Country> countryList = new ArrayList<>();

        processedCountriesItems.forEach( (x,y) -> {
            countryList.add(itemMapping(y));
        });

        return countryList;
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

    private Country itemMapping (CountryItem item) {
        Map<String, Double> features = new HashMap<>();
        features.put("Confirmed", item.getConfirmed());
        features.put("Deaths", item.getDeaths());
        features.put("Recovered", item.getRecovered());
        features.put("Active", item.getActive());
        features.put("Incident_Rate", item.getIncidentRate());
        features.put("Case_Fatality_Ratio", item.getCaseFatalityRatio());

        return new Country(item.getCountryRegion(), features);
    }

    public List<CountryItem> getCountries() {
        return countries;
    }

    public void setCountries(List<CountryItem> countries) {
        this.countries = countries;
    }

    public Map<String, CountryItem> getProcessedCountriesItems() {
        return processedCountriesItems;
    }

    public void setProcessedCountriesItems(Map<String, CountryItem> processedCountriesItems) {
        this.processedCountriesItems = processedCountriesItems;
    }

    public List<Country> getFinalCountries() {
        return finalCountries;
    }

    public void setFinalCountries(List<Country> finalCountries) {
        this.finalCountries = finalCountries;
    }
}
