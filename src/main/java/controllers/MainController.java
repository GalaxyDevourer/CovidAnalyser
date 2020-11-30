package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.utils.windows.windowsUtils;

import java.io.IOException;

public class MainController implements windowsUtils {
    @FXML RadioButton gitRadioButton;
    @FXML RadioButton folderRadioButton;
    @FXML TextField fileFolderPathText;
    @FXML Button fileFolderChooser;
    @FXML TextField gitSourceText;
    @FXML ComboBox gitFileChooserList;
    @FXML Button startButton;

    @FXML
    public void initialize () {
        gitRadioButton.setSelected(true);

        ToggleGroup radioGroup = new ToggleGroup();
        gitRadioButton.setToggleGroup(radioGroup);
        folderRadioButton.setToggleGroup(radioGroup);
    }

    @FXML
    public void startDownloading () throws IOException {
        loadWindow("/views/setting_gui.fxml","Analysis`s Settings");
    }

    @FXML
    public void chooseFile () {
        //File file = new DirectoryChooser().showDialog(fileFolderChooser.getScene().getWindow());
        //System.out.println("File name: " + file.getName() + " Path: " + file.getAbsolutePath());

        //File file = new FileChooser().showOpenDialog(fileFolderChooser.getScene().getWindow());
        //System.out.println("File name: " + file.getName());
    }

}