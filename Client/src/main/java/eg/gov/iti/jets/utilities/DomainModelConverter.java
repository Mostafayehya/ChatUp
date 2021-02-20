package eg.gov.iti.jets.utilities;

import domains.Contact;
import eg.gov.iti.jets.ui.models.ContactModel;
import javafx.scene.image.Image;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DomainModelConverter {

    public static  ContactModel getContactModel(Contact contact) {
        ContactModel contactModel = null;
        if (contact.getContactImage() != null) {
            System.out.println("has image");
            InputStream inputStream = new ByteArrayInputStream(contact.getContactImage());
            contactModel = new ContactModel(contact.getContactPhoneNumber(), contact.getName(), contact.getBio(),
                    contact.getEmail(), contact.getStatus(), contact.getMode(), new Image(inputStream));
        }
        return contactModel;
    }


    public static List<ContactModel> contactListToContactModelList(List<Contact> contacts) {
        List<ContactModel> contactModels = new ArrayList<>();
        for (int i = 0; i < contacts.size(); i++) {
            Contact contact = contacts.get(i);
            contactModels.add(getContactModel(contact));
        }
        System.out.println(contactModels.size());
        return contactModels;
    }
}
