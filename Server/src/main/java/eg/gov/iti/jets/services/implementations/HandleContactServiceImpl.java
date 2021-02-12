package eg.gov.iti.jets.services.implementations;

import domains.Contact;
import eg.gov.iti.jets.data.dao.ContactDao;
import eg.gov.iti.jets.data.dao.ContactDaoImpl;
import eg.gov.iti.jets.data.dao.UserDao;
import eg.gov.iti.jets.data.dao.UserDaoImpl;
import services.HandleContactsService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class HandleContactServiceImpl extends UnicastRemoteObject implements HandleContactsService {
    ContactDao contactDao;
    UserDao userDao;
    public HandleContactServiceImpl() throws RemoteException {
        contactDao = new ContactDaoImpl();
        userDao = new UserDaoImpl();
    }

    @Override
    public int addNewContact(Contact contact) throws RemoteException {
//        if(userDao.getUserByPhone(user.getPhoneNumber())==null){
//            return -2;
//        }
        if(contactDao.getContact(contact.getUserPhoneNumber(),contact.getContactPhoneNumber())==null){
            return -3;
        }
        return contactDao.insertContact(contact);
    }
}
