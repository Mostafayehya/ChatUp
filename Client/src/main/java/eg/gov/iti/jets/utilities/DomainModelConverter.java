package eg.gov.iti.jets.utilities;

import domains.Contact;
import eg.gov.iti.jets.ui.models.ContactModel;

import java.util.ArrayList;
import java.util.List;

public class DomainModelConverter {

    public static  ContactModel getContactModel(Contact contact) {
        ContactModel contactModel = new ContactModel(contact.getContactPhoneNumber(), contact.getName(), contact.getBio(),
                contact.getEmail(), contact.getImage(), contact.getStatus(), contact.getMode());
        return contactModel;
    }


    public static List<ContactModel> getContactModelsList(List<Contact> contacts) {
        List<ContactModel> contactModels = new ArrayList<>();
        for (int i = 0; i < contacts.size(); i++) {
            Contact contact = contacts.get(i);
            contactModels.add(getContactModel(contact));
        }
        System.out.println(contactModels.size());
        return contactModels;
    }
}
