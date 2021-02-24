package eg.gov.iti.jets.utilities;

import javafx.event.ActionEvent;
import eg.gov.iti.jets.ui.models.ContactModel;
import eg.gov.iti.jets.io.UserProperties;
import javafx.animation.*;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;


import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class StageCoordinator {
    Stage stage;
    StackPane parentContainer;
    BorderPane visibleRoot;
    Popup addNewContactPopup;
    Popup changUserPassPopup;
    private static StageCoordinator stageCoordinator;
    ModelsFactory modelsFactory;
    UserProperties userProperties;

    private final Map<String, SceneData> scenes = new HashMap<>();

    private StageCoordinator() {
        stage = null;
        modelsFactory = ModelsFactory.getInstance();
        userProperties = new UserProperties();
       // modelsFactory = ModelsFactory.getInstance();
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

    public void goToLoginPage() {
        if (stage == null) {
            throw new RuntimeException("Stage Coordinator must be assigned a stage before being able to use it");
        }

        if (!scenes.containsKey("login")) {
            try {
                System.out.println("Created New Scene");
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/LoginPage.fxml"));
                Parent loginView = fxmlLoader.load();
                parentContainer = new StackPane(loginView);
                Scene loginScene = new Scene(parentContainer, 759.0, 626.0);
                SceneData loginSceneData = new SceneData(fxmlLoader, parentContainer, loginScene);
                scenes.put("login", loginSceneData);
                stage.setScene(loginScene);
            } catch (IOException e) {
                System.out.println("IO Exception: Couldn't load 'login Page' FXML file");
                e.printStackTrace();
            }
        } else {
            System.out.println("Loaded Existing Scene");
            SceneData loginSceneData = scenes.get("login");
            Scene loginScene = loginSceneData.getScene();
            stage.setScene(loginScene);
        }
    }

    public void navigateToGroupChatListPage() {
        if (stage == null) {
            throw new RuntimeException("Stage Coordinator must be assigned a stage before being able to use it");
        }

        if (!scenes.containsKey("groupChatListPage")) {
            try {
                System.out.println("Created New Scene");
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/GroupchatListPage.fxml"));
                Parent groupChatPageView = fxmlLoader.load();
                parentContainer = new StackPane(groupChatPageView);
                Scene groupChatListScene = new Scene(parentContainer, 759.0, 626.0);
                SceneData groupChatListSceneData = new SceneData(fxmlLoader, parentContainer, groupChatListScene);
                scenes.put("groupChatListPage", groupChatListSceneData);
                stage.setScene(groupChatListScene);
            } catch (IOException e) {
                System.out.println("IO Exception: Couldn't load 'GroupchatPage' FXML file");
                e.printStackTrace();
            }
        } else {
            System.out.println("Loaded Existing Scene");
            SceneData groupChatListSceneData = scenes.get("groupChatListPage");
            Scene groupChatListScene = groupChatListSceneData.getScene();
            stage.setScene(groupChatListScene);
        }
    }

    public void navigateToGroupChatCreatePage() {
        if (stage == null) {
            throw new RuntimeException("Stage Coordinator must be assigned a stage before being able to use it");
        }

        if (!scenes.containsKey("CreateGroupChatPage")) {
            try {
                System.out.println("Created New Scene");
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/CreateChatGroupPage.fxml"));
                Parent createGroupChatPageView = fxmlLoader.load();
                parentContainer = new StackPane(createGroupChatPageView);
                Scene createGroupChatScene = new Scene(parentContainer, 759.0, 626.0);
                SceneData createGroupChatSceneData = new SceneData(fxmlLoader, parentContainer, createGroupChatScene);
                scenes.put("groupChatListPage", createGroupChatSceneData);
                stage.setScene(createGroupChatScene);
            } catch (IOException e) {
                System.out.println("IO Exception: Couldn't load 'CreateChatGroup' FXML file");
                e.printStackTrace();
            }
        } else {
            System.out.println("Loaded Existing Scene");
            SceneData createGroupChatSceneData = scenes.get("groupChatListPage");
            Scene createGroupChatScene = createGroupChatSceneData.getScene();
            stage.setScene(createGroupChatScene);
        }
    }

    public void goToSignupPage() {

        if (stage == null) {
            throw new RuntimeException("Stage Coordinator must be assigned a stage before being able to use it");
        }

        if (!scenes.containsKey("signup")) {
            try {
                System.out.println("Created New Scene");
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/SignUpPage.fxml"));
                Parent signupView = fxmlLoader.load();
                Scene chatScene = new Scene(signupView);
                SceneData signupSceneData = new SceneData(fxmlLoader, signupView, chatScene);
                scenes.put("signup", signupSceneData);
                stage.setScene(chatScene);
            } catch (IOException e) {
                System.out.println("IO Exception: Couldn't load 'signup Page' FXML file");
                e.printStackTrace();
            }
        } else {
            System.out.println("Loaded Existing Scene");
            SceneData loginSceneData = scenes.get("signup");
            Scene signUpScene = loginSceneData.getScene();
            stage.setScene(signUpScene);
        }
    }

    public void switchToChatScreen() {

        if (stage ==null){
            throw new RuntimeException("Stage Coordinator must be assigned a stage before being able to use it");
        }

        if (!modelsFactory.getCurrentUser().getContacts().isEmpty()) {
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
        } else { // user has no online contacts yet
            Notifications.create()
                    .title("Chats")
                    .text("Please add contact to have access to chat service")
                    .darkStyle()
                    .position(Pos.CENTER)
                    .hideAfter(Duration.seconds(1))
                    .showWarning();
            gotoContactsListPage();

        }
    }

    public void gotoContactsListPage() {

        if (stage == null) {
            throw new RuntimeException("Stage Coordinator must be assigned a stage before being able to use it");
        }

        if (!scenes.containsKey("contacts")) {
            try {
                System.out.println("Created New Scene");
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/ContactsListPage.fxml"));
                Parent contactsView = fxmlLoader.load();
                Scene contactsScene = new Scene(contactsView);
                SceneData loginSceneData = new SceneData(fxmlLoader, contactsView, contactsScene);
                scenes.put("contacts", loginSceneData);
                stage.setScene(contactsScene);
            } catch (IOException e) {
                System.out.println("IO Exception: Couldn't load 'contacts Page' FXML file");
                e.printStackTrace();
            }
        } else {
            System.out.println("Loaded Existing Scene");
            SceneData contactsSceneData = scenes.get("contacts");
            Scene contactsScene = contactsSceneData.getScene();
            stage.setScene(contactsScene);
        }
    }

    public void getAddNewContactPopUp() {
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

    public void ChangeUserPassword() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/views/passwordDialog.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        changUserPassPopup = new Popup();
        root.setStyle("-fx-background-color: white");
        changUserPassPopup.getContent().add(root);
        changUserPassPopup.show(stage);
    }

    public void goToUserProfilePage() {

        if (stage == null) {
            throw new RuntimeException("Stage Coordinator must be assigned a stage before being able to use it");
        }

        if (!scenes.containsKey("UProfile")) {
            try {
                System.out.println("Created New Scene");
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/UProfile.fxml"));
                Parent contactsView = fxmlLoader.load();
                Scene contactsScene = new Scene(contactsView,759.0, 626.0);
                SceneData loginSceneData = new SceneData(fxmlLoader, contactsView, contactsScene);
                scenes.put("UProfile", loginSceneData);
                stage.setScene(contactsScene);
            } catch (IOException e) {
                System.out.println("IO Exception: Couldn't load 'contacts Page' FXML file");
                e.printStackTrace();
            }
        } else {
            System.out.println("Loaded Existing Scene");
            SceneData contactsSceneData = scenes.get("UProfile");
            Scene contactsScene = contactsSceneData.getScene();
            stage.setScene(contactsScene);
        }

       /* try {
            Parent root = FXMLLoader.load(getClass().getResource("/views/UProfile.fxml"));
            parentContainer = new StackPane(root);
            stage.setScene(new Scene(parentContainer, 759.0, 626.0));
            visibleRoot = (BorderPane) root;
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    public void hideNewContactPopup() {
        if (addNewContactPopup != null) {
            addNewContactPopup.hide();
        }
    }

    public void hidePasswordPopup() {
        if (changUserPassPopup != null) {
            changUserPassPopup.hide();
        }
    }

    public void goToContactProfilePage() {

        if (stage == null) {
            throw new RuntimeException("Stage Coordinator must be assigned a stage before being able to use it");
        }

        if (!scenes.containsKey("contactProfile")) {
            try {
                System.out.println("Created New Scene");
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/contactProfile.fxml"));
                Parent contactsView = fxmlLoader.load();
                Scene contactsScene = new Scene(contactsView);
                SceneData loginSceneData = new SceneData(fxmlLoader, contactsView, contactsScene);
                scenes.put("contactProfile", loginSceneData);
                stage.setScene(contactsScene);
            } catch (IOException e) {
                System.out.println("IO Exception: Couldn't load 'contactProfile Page' FXML file");
                e.printStackTrace();
            }
        } else {
            System.out.println("Loaded Existing Scene");
            SceneData contactsSceneData = scenes.get("contactProfile");
            Scene contactsScene = contactsSceneData.getScene();
            stage.setScene(contactsScene);
        }


    }

    public Stage getStage() {

        return stage;
    }

    public void closeApp() {
        if (stage == null) {
            throw new RuntimeException("Stage must be initialized before trying to close");
        }

        stage.close();

    }

    public void gotoInvitationListPage() {
        if (stage == null) {
            throw new RuntimeException("Stage Coordinator must be assigned a stage before being able to use it");
        }
        if (!scenes.containsKey("invitations")) {
            try {
                System.out.println("Created New Scene");
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/InvitationListPage.fxml"));
                Parent invitationView = fxmlLoader.load();
                Scene invitationScene = new Scene(invitationView,759.0, 626.0);
                SceneData loginSceneData = new SceneData(fxmlLoader, invitationView, invitationScene);
                scenes.put("invitations", loginSceneData);
                stage.setScene(invitationScene);
            } catch (IOException e) {
                System.out.println("IO Exception: Couldn't load 'contacts Page' FXML file");
                e.printStackTrace();
            }
        } else {
            System.out.println("Loaded Existing Scene");
            SceneData invitationSceneData = scenes.get("invitations");
            Scene invitationScene = invitationSceneData.getScene();
            stage.setScene(invitationScene);
        }
    }
}
