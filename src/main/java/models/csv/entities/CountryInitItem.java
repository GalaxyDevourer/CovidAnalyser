package models.csv.entities;

import com.opencsv.bean.CsvBindByName;

public class CountryInitItem {
    @CsvBindByName private String FIPS;
    @CsvBindByName private String Admin2;
    @CsvBindByName private String Province_State;
    @CsvBindByName private String Country_Region;
    @CsvBindByName private String Last_Update;
    @CsvBindByName private String Lat;
    @CsvBindByName private String Long_;
    @CsvBindByName private String Confirmed;
    @CsvBindByName private String Deaths;
    @CsvBindByName private String Recovered;
    @CsvBindByName private String Active;
    @CsvBindByName private String Combined_Key;
    @CsvBindByName private String Incident_Rate;
    @CsvBindByName private String Case_Fatality_Ratio;

    public String getFIPS() {
        return FIPS;
    }

    public void setFIPS(String FIPS) {
        this.FIPS = FIPS;
    }

    public String getAdmin2() {
        return Admin2;
    }

    public void setAdmin2(String admin2) {
        Admin2 = admin2;
    }

    public String getProvince_State() {
        return Province_State;
    }

    public void setProvince_State(String province_State) {
        Province_State = province_State;
    }

    public String getCountry_Region() {
        return Country_Region;
    }

    public void setCountry_Region(String country_Region) {
        Country_Region = country_Region;
    }

    public String getLast_Update() {
        return Last_Update;
    }

    public void setLast_Update(String last_Update) {
        Last_Update = last_Update;
    }

    public String getLat() {
        return Lat;
    }

    public void setLat(String lat) {
        Lat = lat;
    }

    public String getLong_() {
        return Long_;
    }

    public void setLong_(String long_) {
        Long_ = long_;
    }

    public String getConfirmed() {
        return Confirmed;
    }

    public void setConfirmed(String confirmed) {
        Confirmed = confirmed;
    }

    public String getDeaths() {
        return Deaths;
    }

    public void setDeaths(String deaths) {
        Deaths = deaths;
    }

    public String getRecovered() {
        return Recovered;
    }

    public void setRecovered(String recovered) {
        Recovered = recovered;
    }

    public String getActive() {
        return Active;
    }

    public void setActive(String active) {
        Active = active;
    }

    public String getCombined_Key() {
        return Combined_Key;
    }

    public void setCombined_Key(String combined_Key) {
        Combined_Key = combined_Key;
    }

    public String getIncident_Rate() {
        return Incident_Rate;
    }

    public void setIncident_Rate(String incident_Rate) {
        Incident_Rate = incident_Rate;
    }

    public String getCase_Fatality_Ratio() {
        return Case_Fatality_Ratio;
    }

    public void setCase_Fatality_Ratio(String case_Fatality_Ratio) {
        Case_Fatality_Ratio = case_Fatality_Ratio;
    }
}
