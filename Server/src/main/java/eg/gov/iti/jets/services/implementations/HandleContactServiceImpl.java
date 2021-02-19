package eg.gov.iti.jets.services.implementations;

import domains.Contact;
import eg.gov.iti.jets.data.dao.ContactDao;
import eg.gov.iti.jets.data.dao.ContactDaoImpl;
import eg.gov.iti.jets.data.dao.UserDao;
import eg.gov.iti.jets.data.dao.UserDaoImpl;
import services.HandleContactsService;
import utilities.FilesUtilities;

import java.io.File;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class HandleContactServiceImpl extends UnicastRemoteObject implements HandleContactsService {
    ContactDao contactDao;
    UserDao userDao;
    public HandleContactServiceImpl() throws RemoteException {
        contactDao = new ContactDaoImpl();
        userDao = new UserDaoImpl();
    }

    @Override
    public int addNewContact(Contact contact) {
        if(userDao.getUserByPhone(contact.getContactPhoneNumber())==null){
            return -2;
        }
        if(contactDao.getContact(contact.getUserPhoneNumber(),contact.getContactPhoneNumber())!=null){
            return -3;
        }
        return contactDao.insertContact(contact);
    }

    @Override
    public List<Contact> getUserContacts(String userPhone)
    {
        List<Contact>contacts =  contactDao.getContacts(userPhone);
        for(int i=0;i<contacts.size();i++){
            if(!contacts.get(i).getImage().equals("")){
                System.out.println("Contact has image");
                System.out.println(contacts.get(i).getImage());
                System.out.println(getClass().getResource(contacts.get(i).getImage()));
                File file = new File(contacts.get(i).getImage());
                byte[] contactImageBytes = FilesUtilities.convertFileToByteArray(file,FilesUtilities.getFileExtension(file));
                contacts.get(i).setContactImage(contactImageBytes);
            }
        }
        return contacts;
    }
}
