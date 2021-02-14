package eg.gov.iti.jets.utilities;

import domains.*;
import eg.gov.iti.jets.io.RMIManager;
import eg.gov.iti.jets.ui.models.ContactModel;
import eg.gov.iti.jets.ui.models.UserModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class ModelsFactory {
    private static ModelsFactory modelsFactory;
    //has reference from all models
    // todo) refactor this to have a public method to get it as the MVC demo
    UserModel currentUser = null;

    ContactModel selectedContact = new ContactModel("01068867847",
            "Stranger","src/main/resources/photos/user.jpg");
    ObservableList<Message> messagesObservableList = FXCollections.observableArrayList(new Message());


    // todo) create map of obervableChatLists to have the chat's data with different contacts, changes when clicking a contact
    private ModelsFactory() {

    }

    public synchronized static ModelsFactory getInstance() {
        if (modelsFactory == null)
            modelsFactory = new ModelsFactory();
        return modelsFactory;
    }


    public void setCurrentUser(User user) {
        if (currentUser != null) {
            throw new RuntimeException("current user already set");
        }
        currentUser = new UserModel("01068867848","MostafaYehya",
                "src/main/resources/images/img.png");/* new UserModel(user.getPhoneNumber(), user.getName(), user.getEmail(), user.getPassword(), user.getPicture()
                , user.getGender(), user.getCountry(), user.getDateOfBirth(), user.getBio(), user.getStatus(), user.getMode());*/
        try {
            List<Contact> contacts = RMIManager.getHandleContactsService().getUserContacts(user.getPhoneNumber());
            System.out.println(contacts.size());
            currentUser.setContacts(FXCollections.observableList(getContactModelsList(contacts)));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
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
        ContactModel contactModel = new ContactModel(contact.getContactPhoneNumber(), contact.getName(), contact.getBio(),
                contact.getEmail(), contact.getImage(), contact.getStatus(), contact.getMode());
        return contactModel;
    }

    public UserModel getCurrentUser() {
        return currentUser;
    }

    public ObservableList<Message> getMessagesObservableList() {
        if (messagesObservableList == null) {
            return FXCollections.observableArrayList(new Message());
        }
        return messagesObservableList;
    }

    public ContactModel getSelectedContact() {

        if (selectedContact == null) {
            selectedContact = new ContactModel();

        }
        // This should be replaced by updating the selected contact
        selectedContact.setContactPhoneNumber("01068867847");
        selectedContact.setName("the Contact");
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

        messagesObservableList.add(message);
    }
}
