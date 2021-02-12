package eg.gov.iti.jets.data.dao;

import domains.Contact;

import java.util.List;

public interface ContactDao {
    int insertContact(Contact contact);
    Contact getContact(String userPhone,String contactPhone);
    //function to return all user contacts
    List<Contact> getContacts(String userPhone);
}
