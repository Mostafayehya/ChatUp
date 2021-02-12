package eg.gov.iti.jets.data.dao;

import domains.Contact;

public interface ContactDao {
    int insertContact(Contact contact);
    Contact getContact(String userPhone,String contactPhone);
}
