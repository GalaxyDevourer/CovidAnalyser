package models.csv.entities;

import com.opencsv.bean.CsvBindByName;

public class CentroidItem {
    @CsvBindByName(column = "Confirmed") private Double confirmed;
    @CsvBindByName(column = "Deaths") private Double deaths;
    @CsvBindByName(column = "Recovered") private Double recovered;
    @CsvBindByName(column = "Active") private Double active;
    @CsvBindByName(column = "Incident_Rate") private Double incidentRate;
    @CsvBindByName(column = "Case_Fatality_Ratio") private Double caseFatalityRatio;

    public CentroidItem () {}

    public CentroidItem(Double confirmed, Double deaths, Double recovered, Double active, Double incidentRate, Double caseFatalityRatio) {
        this.confirmed = confirmed;
        this.deaths = deaths;
        this.recovered = recovered;
        this.active = active;
        this.incidentRate = incidentRate;
        this.caseFatalityRatio = caseFatalityRatio;
    }

    public Double getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Double confirmed) {
        this.confirmed = confirmed;
    }

    public Double getDeaths() {
        return deaths;
    }

    public void setDeaths(Double deaths) {
        this.deaths = deaths;
    }

    public Double getRecovered() {
        return recovered;
    }

    public void setRecovered(Double recovered) {
        this.recovered = recovered;
    }

    public Double getActive() {
        return active;
    }

    public void setActive(Double active) {
        this.active = active;
    }

    public Double getIncidentRate() {
        return incidentRate;
    }

    public void setIncidentRate(Double incidentRate) {
        this.incidentRate = incidentRate;
    }

    public Double getCaseFatalityRatio() {
        return caseFatalityRatio;
    }

    public void setCaseFatalityRatio(Double caseFatalityRatio) {
        this.caseFatalityRatio = caseFatalityRatio;
    }
}
