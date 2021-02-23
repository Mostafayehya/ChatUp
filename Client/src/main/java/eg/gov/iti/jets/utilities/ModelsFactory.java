package eg.gov.iti.jets.utilities;

import domains.*;
import eg.gov.iti.jets.io.RMIManager;
import eg.gov.iti.jets.ui.models.ContactModel;
import eg.gov.iti.jets.ui.models.UserModel;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static eg.gov.iti.jets.utilities.DomainModelConverter.contactListToContactModelList;
import static eg.gov.iti.jets.utilities.DomainModelConverter.getContactModel;

public class ModelsFactory {
    private static ModelsFactory modelsFactory;
    //has reference from all models
    // todo) refactor this to have a public method to get it as the MVC demo
    UserModel currentUser;

    ObservableList<Message> messagesObservableList;
    ContactModel selectedOnlineContactModel;



    // todo) create map of obervableChatLists to have the chat's data with different contacts, changes when clicking a contact

    public synchronized static ModelsFactory getInstance() {
        if (modelsFactory == null)
            modelsFactory = new ModelsFactory();
        return modelsFactory;
    }


    public void setCurrentUser(User user) {
        if (currentUser != null) {
            throw new RuntimeException("current user already set");
        }
        if (user.getUserPhoto() != null) {
            InputStream inputStream = new ByteArrayInputStream(user.getUserPhoto().getFileBytes());
            currentUser = new UserModel(user.getPhoneNumber(), user.getName(), user.getEmail(), user.getPassword(),
                    user.getGender(), user.getCountry(), user.getDateOfBirth(), user.getBio(), user.getStatus()
                    , user.getMode(), new Image(inputStream));

        }
        retrieveContacts();
    }

    public void retrieveContacts() {
        List<Contact> contacts = null;
        try { // Todo) move the if inside the try, no point having it out side
            contacts = RMIManager.getHandleContactsService().getUserContacts(currentUser.getPhoneNumber());
            System.out.println("Current user's all contacts loaded successfully with size = " + contacts.size());

        } catch (RemoteException e) {
            e.printStackTrace();
        }
        if (currentUser.getContacts() == null || contacts.size() == 0) {
            currentUser.setContacts(FXCollections.observableList(contactListToContactModelList(contacts)));
            currentUser.setMessagesList(contactListToContactModelList(contacts));
        } else { // todo This looks really ugly, use temp references to simplify this
            currentUser.getContacts().add(contactListToContactModelList(contacts).get(contacts.size() - 1));
            // Todo this is a result of the horrible handling of adding Contacts, refactor to push based mechanism instead of pulling.
            currentUser.addNewChatList(contactListToContactModelList(contacts).get(contacts.size() - 1));
        }

        getCurrentSelectedOnlineContact().setContactModel(getContactModel(contacts.get(0)));
    }

    public UserModel getCurrentUser() {

        if (currentUser == null) {
            currentUser = new UserModel();
            return currentUser;
        } else {
            return currentUser;
        }
    }

    public ObservableList<Message> updateMessagesObservableList(String contactPhoneNumber) {
        if (messagesObservableList == null) {
            messagesObservableList = FXCollections.observableArrayList();
            messagesObservableList.setAll(currentUser.getObservableMessageListForContact(contactPhoneNumber));
            return messagesObservableList;
        }

        messagesObservableList.setAll(currentUser.getObservableMessageListForContact(contactPhoneNumber));

        return messagesObservableList;
    }

    public void receiveMessage(Message message) {

        if (message != null) {

            currentUser.receiveMessage(message.getSenderPhoneNumber(), message);

            if (message.getSenderPhoneNumber().equals(selectedOnlineContactModel.getContactPhoneNumber()))
                messagesObservableList.add(message);

        }
    }



    /*/////////////////////////////////////
     *   Contacts inside chat Page Handling
     * *///////////////////////////////////
    public void setSelectedOnlineContactModel(ContactModel contactModel) {

        if (selectedOnlineContactModel == null) {
            selectedOnlineContactModel = contactModel;
        }
        selectedOnlineContactModel.setContactModel(contactModel);

        System.out.println(contactModel.nameProperty().get() + " was set inside models factory");
    }


    public ContactModel getCurrentSelectedOnlineContact() {

        if (selectedOnlineContactModel == null) {
            selectedOnlineContactModel = new ContactModel();
            return selectedOnlineContactModel;
        } else {
            return selectedOnlineContactModel;

        }

    }
}
