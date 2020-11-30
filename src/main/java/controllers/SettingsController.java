package controllers;

import models.utils.windows.windowsUtils;

import java.io.IOException;

public class SettingsController implements windowsUtils {

    public void startDownloading () throws IOException {
        loadWindow("/views/setting_gui.fxml","Analysis`s settings");
    }

}
