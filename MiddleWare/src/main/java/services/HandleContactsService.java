package services;

import domains.Contact;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface HandleContactsService extends Remote {
    int addNewContact(Contact contact) throws RemoteException;
}
