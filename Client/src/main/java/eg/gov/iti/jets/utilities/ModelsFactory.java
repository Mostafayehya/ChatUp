package eg.gov.iti.jets.utilities;

import domains.*;
import eg.gov.iti.jets.io.RMIManager;
import eg.gov.iti.jets.ui.models.ContactModel;
import eg.gov.iti.jets.ui.models.UserModel;
import javafx.collections.FXCollections;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class ModelsFactory {
    private static ModelsFactory modelsFactory;
    //has reference from all models
    // todo) refactor this to have a public method to get it as the MVC demo
    UserModel currentUser;

    ContactModel selectedContact;
    ObservableList<Message> messagesObservableList;
    ContactModel selectedOnlineContactModel = new ContactModel();

    List<ContactModel> contactModelList;

    // todo) create map of obervableChatLists to have the chat's data with different contacts, changes when clicking a contact
    private ModelsFactory() {
    UserModel currentUser = null;

    private ModelsFactory() {

    }

    public synchronized static ModelsFactory getInstance() {
        if (modelsFactory == null)
            modelsFactory = new ModelsFactory();
        return modelsFactory;
    }


    public void setUpUserInfoForFirstTime(User user) {
        if (currentUser != null) {
    public void setCurrentUser(User user) {
        if (currentUser != null) {
            throw new RuntimeException("current user already set");
        }
        if (user.getUserPhoto() != null) {
            InputStream inputStream = new ByteArrayInputStream(user.getUserPhoto().getFileBytes());
            currentUser = new UserModel(user.getPhoneNumber(), user.getName(), user.getEmail(), user.getPassword(),
                    user.getGender(), user.getCountry(), user.getDateOfBirth(), user.getBio(), user.getStatus()
                    , user.getMode(), new Image(inputStream));


        // We need to save current user's data in DB or external file .
        currentUser = new UserModel(user.getPhoneNumber(), user.getName(), user.getEmail(), user.getPassword(),
                "src/main/resources/images/img.png"
                , user.getGender(), user.getCountry(), user.getDateOfBirth(), user.getBio(), user.getStatus(), user.getMode());
        try {
            List<Contact> contacts = RMIManager.getHandleContactsService().getUserContacts(user.getPhoneNumber());
            System.out.println("Current user's all contacts loaded successfully with size = " + contacts.size());
            contactModelList = contactListToContactModelList(contacts);
            if (!contactModelList.isEmpty())
                setSelectedOnlineContactModel(contactModelList.get(0));
            currentUser.setContacts(FXCollections.observableList(contactModelList));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        retrieveContacts();
    }

    public List<ContactModel> getContactModelsList(List<Contact> contacts) {
        List<ContactModel> contactModels = new ArrayList<>();
        for (int i = 0; i < contacts.size(); i++) {
            Contact contact = contacts.get(i);
            contactModels.add(getContactModel(contact));
        }
        System.out.println(contactModels.size());
        return contactModels;
    }

    public ContactModel getContactModel(Contact contact) {
        ContactModel contactModel = null;
        if (contact.getContactImage() != null) {
            System.out.println("has image");
            InputStream inputStream = new ByteArrayInputStream(contact.getContactImage());
            contactModel = new ContactModel(contact.getContactPhoneNumber(), contact.getName(), contact.getBio(),
                    contact.getEmail(), contact.getStatus(), contact.getMode(), new Image(inputStream));

        }
        return contactModel;
    }

    public UserModel getCurrentUser() {
        return currentUser;
    public UserModel getCurrentUser() {

        if (currentUser == null) {
            currentUser = new UserModel();
            return currentUser;
        } else {
            return currentUser;
        }
    }

    public ObservableList<Message> getMessagesObservableList() {
        if (messagesObservableList == null) {
            messagesObservableList = FXCollections.observableArrayList();
            return messagesObservableList;
        }
        return messagesObservableList;
    }

    // This will be used only in contact -> contact profile flow
    public ContactModel getSelectedContact() {

        if (selectedContact == null) {
            selectedContact = new ContactModel();
            return selectedContact;
        }

        return selectedContact;
    }

    /*
         After receiving the message I need to update the ObserverList of messages and I can do this by different ways
            1) have the observer list here and then add the message to it and since observed the chatpage controller
               would be notified
            2) have a method called receiveMEssage() inside the controller and call it from here ( bad no use of properties)
            3) Make oberverlist public and setting it here from the modelsFactory ( terrible )

    */
    public void receiveMessage(Message message) {

        if (message != null) {
            System.out.println("Message received :" + message.getContent());
            messagesObservableList.add(message);

        }
    }

    public void retrieveContacts() {
        List<Contact> contacts = null;
        try {
            contacts = RMIManager.getHandleContactsService().getUserContacts(currentUser.getPhoneNumber());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        if (currentUser.getContacts() == null) {
            currentUser.setContacts(FXCollections.observableList(getContactModelsList(contacts)));
        } else {
            currentUser.getContacts().add(getContactModelsList(contacts).get(contacts.size()-1));


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
