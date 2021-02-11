package eg.gov.iti.jets.services.implementations;

import domains.Contact;
import eg.gov.iti.jets.data.dao.ContactDao;
import eg.gov.iti.jets.data.dao.ContactDaoImpl;
import services.HandleContactsService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class HandleContactServiceImpl extends UnicastRemoteObject implements HandleContactsService {
    ContactDao contactDao;
    public HandleContactServiceImpl() throws RemoteException {
        contactDao = new ContactDaoImpl();
    }

    @Override
    public int addNewContact(Contact contact) throws RemoteException {
        return contactDao.insertContact(contact);
    }
}
