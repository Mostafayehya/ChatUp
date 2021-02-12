package eg.gov.iti.jets.utilities;

import domains.*;
import eg.gov.iti.jets.io.RMIManager;
import eg.gov.iti.jets.ui.models.ContactModel;
import eg.gov.iti.jets.ui.models.UserModel;
import javafx.collections.FXCollections;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class ModelsFactory {
    private static ModelsFactory modelsFactory;
    //has reference from all models
    UserModel currentUser  = null;
    private ModelsFactory(){

    }
    public synchronized static ModelsFactory getInstance(){
        if(modelsFactory==null)
            modelsFactory=new ModelsFactory();
        return modelsFactory;
    }

    public void setCurrentUser(User user){
        if (currentUser != null){
            throw new RuntimeException("current user already set");
        }
        currentUser = new UserModel(user.getPhoneNumber(),user.getName(),user.getEmail(),user.getPassword(),user.getPicture()
         ,user.getGender(),user.getCountry(),user.getDateOfBirth(),user.getBio(),user.getStatus(),user.getMode());
        try {
            List<Contact> contacts = RMIManager.getHandleContactsService().getUserContacts(user.getPhoneNumber());
            currentUser.setContacts(FXCollections.observableList(getContactModelsList(contacts)));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public List<ContactModel> getContactModelsList(List<Contact> contacts){
        List<ContactModel> contactModels = new ArrayList<>();
        for(int i=0;i<contacts.size();i++){
            Contact contact = contacts.get(i);
            contactModels.add(getContactModel(contact));
        }
        return contactModels;
    }

    public ContactModel getContactModel(Contact contact){
        ContactModel contactModel = new ContactModel(contact.getContactPhoneNumber(),contact.getName(),contact.getBio(),
                contact.getEmail(),contact.getImage(),contact.getStatus(),contact.getMode());
        return contactModel;
    }

    public UserModel getCurrentUser(){
        return currentUser;
    }


}
