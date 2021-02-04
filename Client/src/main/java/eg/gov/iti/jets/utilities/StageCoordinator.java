package eg.gov.iti.jets.utilities;

import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class StageCoordinator {
    Stage stage;
    private static StageCoordinator stageCoordinator;
    private final Map<String, SceneData> scenes = new HashMap<>();

    private StageCoordinator() {
        stage = null;
    }

    public void setStage(Stage stage) {
        if (this.stage != null) {
            throw new IllegalStateException("Stage is already assigned");
        } else {
            this.stage = stage;
        }
    }

    public static synchronized StageCoordinator getInstance() {
        if (stageCoordinator == null) {
            stageCoordinator = new StageCoordinator();
        }
        return stageCoordinator;
    }

    public void switchToChatScreen() {
    }
    //add public function to load each age like public void goToLoginPage();
}
