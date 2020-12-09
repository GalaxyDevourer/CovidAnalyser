package models.utils.other;

import models.csv.entities.CentroidItem;
import models.csv.entities.CountryItem;
import models.datamining.entities.Centroid;
import models.datamining.entities.Country;
import models.datamining.entities.mappers.CentroidMapper;
import models.datamining.entities.mappers.CountryMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SaveData {
    private Map<String, String> otherData;
    private Map<Centroid, List<Country>> resultData;
    private Map<CentroidItem, List<CountryItem>> resultItemData;

    public SaveData () {}

    public void createResultItemData() {
        CountryMapper countryMapper = new CountryMapper();
        CentroidMapper centroidMapper = new CentroidMapper();
        Map<CentroidItem, List<CountryItem>> resultItems = new HashMap<>();

        resultData.forEach( (x,y) -> {
            List<CountryItem> countriesItems = countryMapper.buildFinalCountryItemList(y);
            resultItems.put(centroidMapper.mapCentroidToItem(x), countriesItems);
        });

        this.resultItemData = resultItems;
    }

    public List<CentroidItem> getCentroidsList () {
        List<CentroidItem> centroidItemList = new ArrayList<>();

        resultItemData.forEach( (x,y) -> centroidItemList.add(x));

        return centroidItemList;
    }

    public List<CountryItem> getCountriesListByCentroid (CentroidItem centroidItem) {
        return resultItemData.get(centroidItem);
    }

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
