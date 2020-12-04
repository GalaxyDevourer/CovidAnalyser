package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import models.utils.windows.WindowsUtils;

import java.io.IOException;

public class SettingsController implements WindowsUtils {

    @FXML Button folderChooserButton;
    @FXML TextField folderPathText;
    @FXML ComboBox algoChooserList;
    @FXML Spinner clustersChooserSpinner;
    @FXML Button startClusteringButton;

    @FXML
    public void startClustering () throws IOException {
        loadWindow("/views/result_gui.fxml","Analysis`s Results");
    }

}
