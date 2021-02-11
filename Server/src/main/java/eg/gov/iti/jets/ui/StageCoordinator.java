package eg.gov.iti.jets.ui;

import javafx.stage.Stage;

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
}
