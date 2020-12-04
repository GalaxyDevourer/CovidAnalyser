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
import models.datamining.distances.EuclideanDistance;
import models.datamining.entities.Centroid;
import models.datamining.entities.Country;
import models.datamining.entities.CountryProcessor;
import models.utils.downloader.FileDownloader;
import models.utils.webparser.PageParser;
import models.utils.windows.WindowsUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class MainController implements WindowsUtils {
    @FXML Button folderDownloadChooserButton;
    @FXML TextField folderDownloadPathText;
    @FXML RadioButton gitRadioButton;
    @FXML RadioButton folderRadioButton;
    @FXML TextField fileFolderPathText;
    @FXML Button fileFolderChooser;
    @FXML TextField gitSourceText;
    @FXML ComboBox<String> gitFileChooserList;
    @FXML Button startButton;

    @FXML
    public void initialize () throws IOException {
        gitRadioButton.setSelected(true);

        ToggleGroup radioGroup = new ToggleGroup();
        gitRadioButton.setToggleGroup(radioGroup);
        folderRadioButton.setToggleGroup(radioGroup);

        List<String> files = new PageParser().getListFileNames();
        ObservableList<String> file_names = FXCollections.observableArrayList(files);
        gitFileChooserList.setItems(file_names);
        gitFileChooserList.setValue(files.get(0));

        gitSourceText.setText("https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_daily_reports");
    }

    @FXML
    public void startDownloading () throws IOException {

        if (gitRadioButton.isSelected()) {
            if (!folderDownloadPathText.getText().equals("")) {
                FileDownloader downloader = new FileDownloader(gitSourceText.getText(), folderDownloadPathText.getText());
                downloader.downloadFile(gitFileChooserList.getValue());
            }
            else dialogWarningMessage("Warning!","Download path must not be empty!",
                    "Please, select some folder for download file from github!");
        }
        else {
            if (!fileFolderPathText.getText().equals("")) {
                List<CountryItem> countries = new CsvToBeanBuilder<CountryItem>(new FileReader(fileFolderPathText.getText()))
                        .withType(CountryItem.class).build().parse();

                CountryProcessor processor = new CountryProcessor(countries);
                processor.start();

                List<Country> countryList = processor.getFinalCountries();
                KMeans kMeans = new KMeans();
                Map<Centroid, List<Country>> data = kMeans.startAnalysis(countryList, 7, new EuclideanDistance(), 2500, false);
                data.forEach( (x,y) -> {
                    System.out.println("Centroid: " + x);
                    y.forEach(System.out::println);
                    System.out.println("----------------------------------------------");
                });
            }
            else dialogWarningMessage("Warning!","File path must not be empty!",
                    "Please, select some file for next actions!");
        }

        //loadWindow("/views/setting_gui.fxml","Analysis`s Settings");
    }

    @FXML
    public void chooseFile () {
        File file = new FileChooser().showOpenDialog(fileFolderChooser.getScene().getWindow());
        fileFolderPathText.setText(file.getAbsolutePath());

    }

    @FXML
    public void chooseFolder () {
        File file = new DirectoryChooser().showDialog(fileFolderChooser.getScene().getWindow());
        folderDownloadPathText.setText(file.getAbsolutePath());
    }

}