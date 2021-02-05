package eg.gov.iti.jets.utilities;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
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

        if (stage ==null){
            throw new RuntimeException("Stage Coordinator must be assigned a stage before being able to use it");
        }

        if (!scenes.containsKey("chat")) {
            try {
                System.out.println("Created New Scene");
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/ChatPage.fxml"));
                Parent chatView = fxmlLoader.load();
                Scene chatScene = new Scene(chatView);
                SceneData loginSceneData = new SceneData(fxmlLoader, chatView, chatScene);
                scenes.put("chat", loginSceneData);
                stage.setScene(chatScene);
            } catch (IOException e) {
                System.out.println("IO Exception: Couldn't load 'Chat Page' FXML file");
                e.printStackTrace();
            }
        } else {
            System.out.println("Loaded Existing Scene");
            SceneData loginSceneData = scenes.get("chat");
            Scene loginScene = loginSceneData.getScene();
            stage.setScene(loginScene);
        }

    }
    //add public function to load each age like public void goToLoginPage();
}
