package controllers;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.csv.entities.CentroidItem;
import models.csv.entities.CountryItem;
import models.utils.other.SaveData;
import models.utils.other.WindowsUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class ResultController implements WindowsUtils, Controller {
    @FXML TableView<CentroidItem> centroidsTable;
    @FXML TableColumn<CentroidItem, Double> centroidConfirmed;
    @FXML TableColumn<CentroidItem, Double> centroidDeath;
    @FXML TableColumn<CentroidItem, Double> centroidRecovered;
    @FXML TableColumn<CentroidItem, Double> centroidActive;
    @FXML TableColumn<CentroidItem, Double> centroidIncident;
    @FXML TableColumn<CentroidItem, Double> centroidFatality;

    @FXML TableView<CountryItem> countriesTable;
    @FXML TableColumn<CountryItem, String> countryName;
    @FXML TableColumn<CountryItem, Double> countryConfirmed;
    @FXML TableColumn<CountryItem, Double> countryDeath;
    @FXML TableColumn<CountryItem, Double> countryRecovered;
    @FXML TableColumn<CountryItem, Double> countryActive;
    @FXML TableColumn<CountryItem, Double> countryIncident;
    @FXML TableColumn<CountryItem, Double> countryFatality;

    @FXML Label distanceLabel;
    @FXML Label objectsNumberLabel;
    @FXML Label iterationsLabel;
    @FXML Label centroidModeLabel;
    @FXML Label clustersNumberLabel;

    private SaveData saveData;

    @FXML
    public void initialize () {
        centroidConfirmed.setCellValueFactory(new PropertyValueFactory<>("confirmed"));
        centroidDeath.setCellValueFactory(new PropertyValueFactory<>("deaths"));
        centroidRecovered.setCellValueFactory(new PropertyValueFactory<>("recovered"));
        centroidActive.setCellValueFactory(new PropertyValueFactory<>("active"));
        centroidIncident.setCellValueFactory(new PropertyValueFactory<>("incidentRate"));
        centroidFatality.setCellValueFactory(new PropertyValueFactory<>("caseFatalityRatio"));

        countryName.setCellValueFactory(new PropertyValueFactory<>("countryRegion"));
        countryConfirmed.setCellValueFactory(new PropertyValueFactory<>("confirmed"));
        countryDeath.setCellValueFactory(new PropertyValueFactory<>("deaths"));
        countryRecovered.setCellValueFactory(new PropertyValueFactory<>("recovered"));
        countryActive.setCellValueFactory(new PropertyValueFactory<>("active"));
        countryIncident.setCellValueFactory(new PropertyValueFactory<>("incidentRate"));
        countryFatality.setCellValueFactory(new PropertyValueFactory<>("caseFatalityRatio"));
    }

    @Override
    public void loadData(SaveData data) throws CsvRequiredFieldEmptyException, IOException, CsvDataTypeMismatchException {
        this.saveData = data;

        distanceLabel.setText(saveData.getOtherData().get("Distance"));
        objectsNumberLabel.setText(saveData.getOtherData().get("ObjectNumber"));
        iterationsLabel.setText(saveData.getOtherData().get("Iterations"));
        centroidModeLabel.setText(saveData.getOtherData().get("CentroidMode"));
        clustersNumberLabel.setText(saveData.getOtherData().get("ClustersNumber"));

        ObservableList<CentroidItem> countryItemObservableList = FXCollections.observableArrayList(saveData.getCentroidsList());
        centroidsTable.setItems(countryItemObservableList);

        saveResultInFiles();
    }

    @FXML
    private void chooseCentroidsCountry () {
        CentroidItem centroidItem = centroidsTable.getSelectionModel().getSelectedItem();

        if (centroidItem != null) {
            List<CountryItem> countryItemList = saveData.getCountriesListByCentroid(centroidItem);
            ObservableList<CountryItem> obs_course_list = FXCollections.observableArrayList(countryItemList);

            countriesTable.setItems(obs_course_list);
        }
    }

    private void saveResultInFiles () throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String folderPath = saveData.getOtherData().get("SaveFolderPath");
        List<CentroidItem> centroidsItemList = saveData.getCentroidsList();
        saveCentroidFile(folderPath, "ResultedCentroids.csv", centroidsItemList);

        AtomicReference<Integer> fileNumber = new AtomicReference<>(1);
        centroidsItemList.forEach( x -> {
            List<CountryItem> countryItemList = saveData.getCountriesListByCentroid(x);
            try {
                saveCountriesFile(folderPath, "CountrySet_" + fileNumber + ".csv", countryItemList);
                fileNumber.getAndSet(fileNumber.get() + 1);
            } catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
                dialogWarningMessage("Error!","Something went wrong with save files :(",
                        "Please, check the folder path and try again!");
                e.printStackTrace();
            }
        });

    }

    private void saveCentroidFile (String folder, String filename, List<CentroidItem> entities) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        String fullPath = folder + "/" + filename;

        Writer writer = new FileWriter(fullPath);
        StatefulBeanToCsv<CentroidItem> beanToCsv = new StatefulBeanToCsvBuilder<CentroidItem>(writer).build();
        beanToCsv.write(entities);
        writer.close();
    }

    private void saveCountriesFile (String folder, String filename, List<CountryItem> entities) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        String fullPath = folder + "/" + filename;

        Writer writer = new FileWriter(fullPath);
        StatefulBeanToCsv<CountryItem> beanToCsv = new StatefulBeanToCsvBuilder<CountryItem>(writer).build();
        beanToCsv.write(entities);
        writer.close();
    }
}
