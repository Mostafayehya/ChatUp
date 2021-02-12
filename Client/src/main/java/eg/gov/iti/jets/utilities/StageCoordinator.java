package eg.gov.iti.jets.utilities;

import javafx.animation.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class StageCoordinator {
    Stage stage;
    StackPane parentContainer;
    BorderPane visibleRoot;
    Popup addNewContactPopup;
    private static StageCoordinator stageCoordinator;
    private final Map<String, SceneData> scenes = new HashMap<>();

    private StageCoordinator(){
        stage=null;
    }

    public void setStage(Stage stage){
        if(this.stage!=null){
            throw new IllegalStateException("Stage is already assigned");
        }
        else{
            this.stage=stage;
        }
    }

    public static synchronized StageCoordinator getInstance(){
        if(stageCoordinator==null){
            stageCoordinator=new StageCoordinator();
        }
        return stageCoordinator;
    }

    public void goToLoginPage(){
        try {
            Parent root=FXMLLoader.load(getClass().getResource("/views/LoginPage.fxml"));
            parentContainer = new StackPane(root);
            stage.setScene(new Scene(parentContainer,759.0,626.0));
            visibleRoot=(BorderPane)root;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goToSignupPage(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/views/SignUpPage.fxml"));
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public void gotoContactsListPage(){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/views/ContactsListPage.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(new Scene(root));

    }
    public void getAddNewContactPopUp(){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/views/AddNewContactPopup.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        addNewContactPopup = new Popup();
        root.setStyle("-fx-background-color: white");
        addNewContactPopup.getContent().add(root);
        addNewContactPopup.show(stage);
    }

    public void hideNewContactPopup(){
        if(addNewContactPopup!=null){
            addNewContactPopup.hide();
        }
    }
}
