package models.csv.entities;

import com.opencsv.bean.CsvBindByName;

public class CountryItem {
    @CsvBindByName(column = "Country_Region") private String countryRegion;
    @CsvBindByName(column = "Confirmed") private String confirmed;
    @CsvBindByName(column = "Deaths") private String deaths;
    @CsvBindByName(column = "Recovered") private String recovered;
    @CsvBindByName(column = "Active") private String active;
    @CsvBindByName(column = "Incident_Rate") private String incidentRate;
    @CsvBindByName(column = "Case_Fatality_Ratio") private String caseFatalityRatio;

    public CountryItem () {};

    public CountryItem(String country_Region, String confirmed, String deaths, String recovered,
                       String active, String incident_Rate, String case_Fatality_Ratio) {
        this.countryRegion = country_Region;
        this.confirmed = confirmed;
        this.deaths = deaths;
        this.recovered = recovered;
        this.active = active;
        this.incidentRate = incident_Rate;
        this.caseFatalityRatio = case_Fatality_Ratio;
    }

    public String getCountryRegion() {
        return countryRegion;
    }

    public void setCountryRegion(String countryRegion) {
        this.countryRegion = countryRegion;
    }

    public String getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(String confirmed) {
        this.confirmed = confirmed;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getIncidentRate() {
        return incidentRate;
    }

    public void setIncidentRate(String incidentRate) {
        this.incidentRate = incidentRate;
    }

    public String getCaseFatalityRatio() {
        return caseFatalityRatio;
    }

    public void setCaseFatalityRatio(String caseFatalityRatio) {
        this.caseFatalityRatio = caseFatalityRatio;
    }
}
