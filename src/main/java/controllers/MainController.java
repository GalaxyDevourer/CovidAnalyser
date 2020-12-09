package controllers;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import models.utils.downloader.FileDownloader;
import models.utils.other.SaveData;
import models.utils.webparser.PageParser;
import models.utils.other.WindowsUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainController implements WindowsUtils, Controller {
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
    public void startDownloading () throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        SaveData saveData = new SaveData();
        String filePath;
        Map<String, String> dataMap = new HashMap<>();

        if (gitRadioButton.isSelected()) {
            if (!folderDownloadPathText.getText().equals("")) {
                FileDownloader downloader = new FileDownloader(gitSourceText.getText(), folderDownloadPathText.getText());
                downloader.downloadFile(gitFileChooserList.getValue());

                filePath = folderDownloadPathText.getText() + "/" + gitSourceText.getText();
                dataMap.put("filePath", filePath);
                saveData.setOtherData(dataMap);

                loadWindow("/views/setting_gui.fxml","Analysis`s Settings", saveData);
            }
            else dialogWarningMessage("Warning!","Download path must not be empty!",
                    "Please, select some folder for download file from github!");
        }
        else {
            if (!fileFolderPathText.getText().equals("")) {
                filePath = fileFolderPathText.getText();
                dataMap.put("filePath", filePath);
                saveData.setOtherData(dataMap);

                loadWindow("/views/setting_gui.fxml","Analysis`s Settings", saveData);
            }
            else dialogWarningMessage("Warning!","File path must not be empty!",
                    "Please, select some file for next actions!");
        }
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

    @Deprecated
    @Override
    public void loadData(SaveData data) {
    }
}