package eg.gov.iti.jets.utilities;


import domains.Invitation;
import eg.gov.iti.jets.ui.controllers.ContactItemController;
import eg.gov.iti.jets.ui.controllers.InvitationItemController;
import eg.gov.iti.jets.ui.models.ContactModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class InvitationListCell  extends ListCell<Invitation> {
    FXMLLoader loader;
    @Override
    protected void updateItem(Invitation invitationModel, boolean empty) {
        super.updateItem(invitationModel, empty);
        if(empty || invitationModel == null){
            setText(null);
            setGraphic(null);
        }
        else{
            if(loader == null){
                loader = new FXMLLoader(getClass().getResource("/views/InvitationItem.fxml"));
                InvitationItemController invitationItemController = new InvitationItemController(invitationModel);
                loader.setController(invitationItemController);
                try {
                    setText(null);
                    setGraphic(loader.load());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }

    }


}
