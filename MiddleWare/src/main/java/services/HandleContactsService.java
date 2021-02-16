package services;

import domains.Contact;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface HandleContactsService extends Remote {
    int addNewContact(Contact contact) throws RemoteException;
    List<Contact> getUserContacts(String userPhone) throws RemoteException;
}
