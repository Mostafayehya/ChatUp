package eg.gov.iti.jets.utilities;

import domains.*;
import eg.gov.iti.jets.io.RMIManager;
import eg.gov.iti.jets.ui.models.ContactModel;
import eg.gov.iti.jets.ui.models.UserModel;
import javafx.collections.FXCollections;
import javafx.scene.image.Image;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
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
        if(user.getUserPhoto()!=null){
            InputStream inputStream = new ByteArrayInputStream(user.getUserPhoto().getFileBytes());
            currentUser = new UserModel(user.getPhoneNumber(),user.getName(),user.getEmail(),user.getPassword(),
                    user.getGender(),user.getCountry(),user.getDateOfBirth(),user.getBio(),user.getStatus()
                    ,user.getMode(),new Image(inputStream));

        }
        else {
            currentUser = new UserModel(user.getPhoneNumber(), user.getName(), user.getEmail(), user.getPassword(), user.getUserPhotoPath()
                    , user.getGender(), user.getCountry(), user.getDateOfBirth(), user.getBio(), user.getStatus(), user.getMode());
        }
        try {
            List<Contact> contacts = RMIManager.getHandleContactsService().getUserContacts(user.getPhoneNumber());
            System.out.println(contacts.size());
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
        System.out.println(contactModels.size());
        return contactModels;
    }

    public ContactModel getContactModel(Contact contact){
        ContactModel contactModel;
        if(contact.getContactImage()!=null){
            System.out.println("has image");
            InputStream inputStream = new ByteArrayInputStream(contact.getContactImage());
            contactModel = new ContactModel(contact.getContactPhoneNumber(),contact.getName(),contact.getBio(),
                    contact.getEmail(),contact.getStatus(),contact.getMode(),new Image(inputStream));

        }
        else {
            System.out.println("no have image");
            contactModel = new ContactModel(contact.getContactPhoneNumber(),contact.getName(),contact.getBio(),
                    contact.getEmail(),contact.getImage(),contact.getStatus(),contact.getMode());
        }

        return contactModel;
    }

    public UserModel getCurrentUser(){
        return currentUser;
    }


}
