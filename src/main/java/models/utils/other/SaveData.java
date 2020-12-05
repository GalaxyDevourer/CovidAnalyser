package models.utils.other;

import models.datamining.entities.Centroid;
import models.datamining.entities.Country;

import java.util.List;
import java.util.Map;

public class SaveData {
    private Map<String, String> otherData;
    private Map<Centroid, List<Country>> resultData;

    public SaveData () {};

    public Map<String, String> getOtherData() {
        return otherData;
    }

    public void setOtherData(Map<String, String> otherData) {
        this.otherData = otherData;
    }

    public Map<Centroid, List<Country>> getResultData() {
        return resultData;
    }

    public void setResultData(Map<Centroid, List<Country>> resultData) {
        this.resultData = resultData;
    }
}
