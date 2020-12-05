package controllers;

import com.opencsv.bean.CsvToBeanBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import models.csv.entities.CountryItem;
import models.datamining.algorithms.KMeans;
import models.datamining.distances.ChebyshevDistance;
import models.datamining.distances.Distance;
import models.datamining.distances.EuclideanDistance;
import models.datamining.distances.ManhattanDistance;
import models.datamining.entities.Centroid;
import models.datamining.entities.Country;
import models.datamining.entities.CountryProcessor;
import models.utils.other.SaveData;
import models.utils.other.WindowsUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SettingsController implements WindowsUtils, Controller {

    @FXML Spinner<Integer> iterationChooserSpinner;
    @FXML Spinner<Integer> clustersChooserSpinner;
    @FXML ComboBox<String> algoChooserList;
    @FXML RadioButton randomModeRadio;
    @FXML RadioButton customModeRadio;
    @FXML TextField centroidsFilePathText;
    @FXML TextField folderPathText;
    @FXML Button fileChooserButton;
    @FXML Button folderChooserButton;
    @FXML Button startClusteringButton;

    private String filePath = "";
    private Map<String, Distance> algorithms = new HashMap<>();

    @FXML
    public void initialize () {
        algorithms.put("Euclidean", new EuclideanDistance());
        algorithms.put("Chebyshev", new ChebyshevDistance());
        algorithms.put("Manhattan", new ManhattanDistance());

        ObservableList<String> algo_names = FXCollections.observableList(Arrays.asList("Euclidean", "Chebyshev","Manhattan"));
        algoChooserList.setItems(algo_names);
        algoChooserList.setValue(algo_names.get(0));

        randomModeRadio.setSelected(true);

        ToggleGroup radioGroup = new ToggleGroup();
        randomModeRadio.setToggleGroup(radioGroup);
        customModeRadio.setToggleGroup(radioGroup);

        clustersChooserSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(2, 100, 5));
        iterationChooserSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(10, 10000, 100));
    }

    @FXML
    public void startClustering () throws IOException {
        String centroidsFilePath = centroidsFilePathText.getText();
        Integer iterations = iterationChooserSpinner.getValue();
        Integer clusters = clustersChooserSpinner.getValue();
        Boolean isRandom = randomModeRadio.isSelected();
        Distance distance = getDistance();

        SaveData saveData = new SaveData();

        List<CountryItem> countries = new CsvToBeanBuilder<CountryItem>(new FileReader(filePath))
                .withType(CountryItem.class).build().parse();

        CountryProcessor processor = new CountryProcessor(countries);
        processor.start();

        List<Country> countryList = processor.getFinalCountries();

        KMeans kMeans = new KMeans();
        Map<Centroid, List<Country>> data = new HashMap<>();

        if (!folderPathText.getText().equals("")) {
            if (isRandom) {
                data = kMeans.startAnalysis(countryList, clusters, distance, iterations, isRandom);
            }
            else {
                if (!centroidsFilePath.equals("")) {
                    kMeans.setFilePath(centroidsFilePath);
                    data = kMeans.startAnalysis(countryList, clusters, distance, iterations, isRandom);
                }
                else dialogWarningMessage("Warning!","No centroids file selected!",
                        "Please, select some file for next actions!");
            }

            //loadWindow("/views/result_gui.fxml","Analysis`s Results", saveData);
        }
        else dialogWarningMessage("Warning!","No folder to save results file selected!",
                "Please, select some folder for next actions!");

    }

    @Override
    public void loadData(SaveData data) {
        this.filePath = data.getOtherData().get("filePath");
    }

    @FXML
    public void chooseFile () {
        File file = new FileChooser().showOpenDialog(centroidsFilePathText.getScene().getWindow());
        centroidsFilePathText.setText(file.getAbsolutePath());

    }

    @FXML
    public void chooseFolder () {
        File file = new DirectoryChooser().showDialog(folderPathText.getScene().getWindow());
        folderPathText.setText(file.getAbsolutePath());

    }

    private Distance getDistance () {
        String name = algoChooserList.getSelectionModel().getSelectedItem();
        return algorithms.get(name);
    }
}
