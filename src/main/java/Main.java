import javafx.application.Application;
import javafx.stage.Stage;
import models.utils.windows.windowsUtils;

public class Main extends Application implements windowsUtils {

    @Override
    public void start(Stage primaryStage) throws Exception{
        loadWindow("/views/main_gui.fxml","COVID-19 Country Analyser");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
