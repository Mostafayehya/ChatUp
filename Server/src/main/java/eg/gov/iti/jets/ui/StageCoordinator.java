package eg.gov.iti.jets.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class StageCoordinator {
    Stage stage;
    StackPane parentContainer;
    BorderPane visibleRoot;
    private static StageCoordinator stageCoordinator;
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
    //add public function to load each page like public void goToLoginPage();
    public void goToDashBoard(){
        try {
            Parent root= FXMLLoader.load(getClass().getResource("/views/serverDashBoard.fxml"));
            parentContainer = new StackPane(root);
            stage.setScene(new Scene(parentContainer,759.0,626.0));
            visibleRoot=(BorderPane)root;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
