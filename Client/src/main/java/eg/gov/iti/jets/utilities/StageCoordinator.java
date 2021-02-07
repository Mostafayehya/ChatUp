package eg.gov.iti.jets.utilities;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StageCoordinator {
    Stage stage;
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
    public void gotoContactsListPage(){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/views/ContactsListPage.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(new Scene(root));

    }
}
