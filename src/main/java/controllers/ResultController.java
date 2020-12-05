package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import models.utils.other.SaveData;
import models.utils.other.WindowsUtils;

public class ResultController implements WindowsUtils, Controller {
    @FXML Label distanceLabel;
    @FXML Label objectsNumberLabel;
    @FXML Label iterationsLabel;
    @FXML Label centroidTypeLabel;
    @FXML Label clustersNumberLabel;

    @Override
    public void loadData(SaveData data) {

    }
}
