import javafx.application.Application;
import javafx.stage.Stage;
import models.utils.other.SaveData;
import models.utils.other.WindowsUtils;

public class Main extends Application implements WindowsUtils {

    @Override
    public void start(Stage primaryStage) throws Exception{
        loadWindow("/views/main_gui.fxml","COVID-19 Country Analyser", new SaveData());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
