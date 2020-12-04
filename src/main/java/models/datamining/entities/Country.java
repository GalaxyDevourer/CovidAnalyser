package models.datamining.entities;

import java.util.Map;
import java.util.Objects;

public class Country {
    private String country;
    private Map<String, Double> features;

    public Country () {};

    public Country(String country, Map<String, Double> features) {
        this.country = country;
        this.features = features;
    }

    public String getCountry() {
        return country;
    }

    public Map<String, Double> getFeatures() {
        return features;
    }

    @Override
    public String toString() {
        return "Country{" +
                "country='" + country + '\'' +
                ", features=" + features +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Country)) return false;
        Country country1 = (Country) o;
        return getCountry().equals(country1.getCountry()) &&
                getFeatures().equals(country1.getFeatures());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCountry(), getFeatures());
    }
}
