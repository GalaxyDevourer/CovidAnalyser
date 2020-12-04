package models.csv.entities;

import com.opencsv.bean.CsvBindByName;

public class CountryItem {
    @CsvBindByName(column = "Country_Region") private String countryRegion;
    @CsvBindByName(column = "Confirmed") private Double confirmed;
    @CsvBindByName(column = "Deaths") private Double deaths;
    @CsvBindByName(column = "Recovered") private Double recovered;
    @CsvBindByName(column = "Active") private Double active;
    @CsvBindByName(column = "Incident_Rate") private Double incidentRate;
    @CsvBindByName(column = "Case_Fatality_Ratio") private Double caseFatalityRatio;

    public CountryItem () {}

    public CountryItem(String countryRegion, Double confirmed, Double deaths, Double recovered,
                       Double active, Double incidentRate, Double caseFatalityRatio) {
        this.countryRegion = countryRegion;
        this.confirmed = (confirmed == null) ? 0.0 : confirmed;
        this.deaths = (deaths == null) ? 0.0 : deaths;
        this.recovered = (recovered == null) ? 0.0 : recovered;
        this.active = (active == null) ? 0.0 : active;
        this.incidentRate = (incidentRate == null) ? 0.0 : incidentRate;
        this.caseFatalityRatio = (caseFatalityRatio == null) ? 0.0 : caseFatalityRatio;
    }

    public String getCountryRegion() {
        return countryRegion;
    }

    public void setCountryRegion(String countryRegion) {
        this.countryRegion = countryRegion;
    }

    public Double getConfirmed() {
        return (confirmed == null) ? 0.0 : confirmed;
    }

    public void setConfirmed(Double confirmed) {
        this.confirmed = (confirmed == null) ? 0.0 : confirmed;
    }

    public Double getDeaths() {
        return (deaths == null) ? 0.0 : deaths;
    }

    public void setDeaths(Double deaths) {
        this.deaths = (deaths == null) ? 0.0 : deaths;
    }

    public Double getRecovered() {
        return (recovered == null) ? 0.0 : recovered;
    }

    public void setRecovered(Double recovered) {
        this.recovered = (recovered == null) ? 0.0 : recovered;
    }

    public Double getActive() {
        return (active == null) ? 0.0 : active;
    }

    public void setActive(Double active) {
        this.active = (active == null) ? 0.0 : active;
    }

    public Double getIncidentRate() {
        return (incidentRate == null) ? 0.0 : incidentRate;
    }

    public void setIncidentRate(Double incidentRate) {
        this.incidentRate = (incidentRate == null) ? 0.0 : incidentRate;
    }

    public Double getCaseFatalityRatio() {
        return (caseFatalityRatio == null) ? 0.0 : caseFatalityRatio;
    }

    public void setCaseFatalityRatio(Double caseFatalityRatio) {
        this.caseFatalityRatio = (caseFatalityRatio == null) ? 0.0 : caseFatalityRatio;
    }

    @Override
    public String toString() {
        return "CountryItem{" +
                "countryRegion='" + countryRegion + '\'' +
                ", confirmed='" + confirmed + '\'' +
                ", deaths='" + deaths + '\'' +
                ", recovered='" + recovered + '\'' +
                ", active='" + active + '\'' +
                ", incidentRate='" + incidentRate + '\'' +
                ", caseFatalityRatio='" + caseFatalityRatio + '\'' +
                '}';
    }
}
